package org.chtijbug.drools.console.service;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import org.chtijbug.drools.KieContainerUpdate;
import org.chtijbug.drools.ReverseProxyUpdate;
import org.chtijbug.drools.common.KafkaTopicConstants;
import org.chtijbug.drools.console.AddLog;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.JobStatus;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.proxy.persistence.json.KieProject;
import org.chtijbug.drools.proxy.persistence.model.ContainerPojoPersist;
import org.chtijbug.drools.proxy.persistence.model.ContainerRuntimePojoPersist;
import org.chtijbug.drools.proxy.persistence.model.ProjectPersist;
import org.chtijbug.drools.proxy.persistence.model.RuntimePersist;
import org.chtijbug.drools.proxy.persistence.repository.ContainerRepository;
import org.chtijbug.drools.proxy.persistence.repository.ContainerRuntimeRepository;
import org.chtijbug.drools.proxy.persistence.repository.ProjectRepository;
import org.chtijbug.drools.proxy.persistence.repository.RuntimeRepository;
import org.chtijbug.guvnor.server.jaxrs.model.PlatformProjectResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@DependsOn("applicationContext")
public class ProjectPersistService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectPersistService.class);

    private static String projectVariable = "4";

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private KieRepositoryService kieRepositoryService;

    private KieConfigurationData config;

    @Autowired
    private UserConnectedService userConnectedService;


    @Autowired
    private ContainerRepository containerRepository;

    @Autowired
    private ContainerRuntimeRepository containerRuntimeRepository;

    @Autowired
    private RuntimeRepository runtimeRepository;
    @Autowired
    private KafkaTemplate<String, ReverseProxyUpdate> kafkaTemplateProxyUpdate;

    @Autowired
    private KafkaTemplate<String, KieContainerUpdate> kafkaKieContainerUpdateTemplate;

    public ProjectPersistService() {
        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);

    }


    public void saveIfnotExist(List<PlatformProjectResponse> platformProjectResponses) {

        for (PlatformProjectResponse platformProjectResponse : platformProjectResponses) {

            ProjectPersist projectPersist = projectRepository.findByProjectNameAndBranch(new KieProject(platformProjectResponse.getSpaceName(), platformProjectResponse.getName()), platformProjectResponse.getBranch());

            if (projectPersist == null) {
                projectPersist = platformProjectResponseToProjectPersist(platformProjectResponse);

                projectPersist = projectRepository.save(projectPersist);
                addProjectToSession(projectPersist, true);

            } else {
                projectPersist.getClassNameList().clear();
                for (String className : platformProjectResponse.getJavaClasses()) {
                    projectPersist.getClassNameList().add(className);
                    projectRepository.save(projectPersist);
                }
                addProjectToSession(projectPersist, false);
            }
        }
    }

    public Map<String, ProjectPersist> getProjectsSession() {
        return (Map<String, ProjectPersist>) VaadinSession.getCurrent().getAttribute(projectVariable);
    }

    public void addProjectToSession(ProjectPersist projectPersist, boolean isModifiable) {

        Map<String, ProjectPersist> projectPersists = getProjectsSession();

        if (projectPersists == null) {
            projectPersists = new HashMap<>();
        }

        if (isModifiable) {
            projectPersists.put(projectPersist.getProjectName().toString() + "-" + projectPersist.getBranch(), projectPersist);
        } else {

            ProjectPersist tmp = projectPersists.get(projectPersist.getProjectName().toString() + "-" + projectPersist.getBranch());
            if (tmp == null) {
                projectPersists.put(projectPersist.getProjectName().toString() + "-" + projectPersist.getBranch(), projectPersist);
            }
        }

        VaadinSession.getCurrent().setAttribute(projectVariable, projectPersists);
    }

    public boolean associate(ProjectPersist projectPersist, List<RuntimePersist> runtimePersists) {
        projectPersist.setStatus(ProjectPersist.Deployable);
        projectPersist.setContainerID(projectPersist.getDeploymentName() + "-" + projectPersist.getProjectName());
        projectPersist.getServerNames().clear();
        ReverseProxyUpdate reverseProxyUpdate = new ReverseProxyUpdate();
        reverseProxyUpdate.setPath("/" + projectPersist.getContainerID());
        for (RuntimePersist runtimePersist : runtimePersists) {
            List<String> names = new ArrayList<>();
            names.add(runtimePersist.getServerName());
            projectPersist.getServerNames().add(runtimePersist.getServerName());
            ContainerPojoPersist existingContainer = containerRepository.findByServerNameAndContainerId(runtimePersist.getServerName(), projectPersist.getContainerID());
            if (existingContainer == null) {
                ContainerPojoPersist newContainer = new ContainerPojoPersist();
                newContainer.setClassName(projectPersist.getMainClass());
                newContainer.setProcessID(projectPersist.getProcessID());
                newContainer.setContainerId(projectPersist.getContainerID());
                newContainer.setServerName(runtimePersist.getServerName());
                newContainer.setGroupId(projectPersist.getGroupID());
                newContainer.setArtifactId(projectPersist.getArtifactID());
                newContainer.setVersion(projectPersist.getProjectVersion());
                containerRepository.save(newContainer);

            }
            String hostName = runtimePersist.getServerUrl() + "/api/" + projectPersist.getContainerID();
            reverseProxyUpdate.getServerNames().add(hostName);
        }
        projectRepository.save(projectPersist);
        addProjectToSession(projectPersist, true);
        kafkaTemplateProxyUpdate.send(KafkaTopicConstants.REVERSE_PROXY, reverseProxyUpdate);
        return true;
    }

    public boolean deployer(ProjectPersist projectPersist, AddLog addLog, UI ui) {


        waitForJobToBeEnded(config.getKiewbUrl(), userConnectedService.getUserConnected().getUserName(),
                userConnectedService.getUserConnected().getUserPassword(), projectPersist, addLog, ui);

        return false;
    }

    public ProjectPersist platformProjectResponseToProjectPersist(PlatformProjectResponse platformProjectResponse) {
        ProjectPersist projectPersist = new ProjectPersist();
        projectPersist.setArtifactID(platformProjectResponse.getArtifactId());
        projectPersist.setGroupID(platformProjectResponse.getGroupId());
        projectPersist.setProjectName(new KieProject(platformProjectResponse.getSpaceName(), platformProjectResponse.getName()));
        projectPersist.setProjectVersion(platformProjectResponse.getVersion());
        projectPersist.setBranch(platformProjectResponse.getBranch());
        projectPersist.setStatus(ProjectPersist.ADEFINIR);
        projectPersist.setClassNameList(platformProjectResponse.getJavaClasses());
        return projectPersist;
    }



    public void waitForJobToBeEnded(String url, String username, String password, ProjectPersist projectPersist, AddLog workOnGoingView, UI ui) {

        UserConnected userConnected = userConnectedService.getUserConnected();

        Thread thread = new Thread() {
            @Override
            public void run() {

                JobStatus result = kieRepositoryService.buildProject(config.getKiewbUrl(), userConnected.getUserName(),
                        userConnected.getUserPassword(), projectPersist.getProjectName().getSpaceName(), projectPersist.getProjectName().getName(), projectPersist.getBranch(), "compile", workOnGoingView, ui);

                executeWrite(url, username, password, workOnGoingView, result.getJobId(), ui);

                result = kieRepositoryService.buildProject(config.getKiewbUrl(), userConnected.getUserName(),
                        userConnected.getUserPassword(), projectPersist.getProjectName().getSpaceName(), projectPersist.getProjectName().getName(), projectPersist.getBranch(), "install", workOnGoingView, ui);

                executeWrite(url, username, password, workOnGoingView, result.getJobId(), ui);



                for (String serverName : projectPersist.getServerNames()) {


                    List<ContainerRuntimePojoPersist> existingContainers = containerRuntimeRepository.findByServerNameAndContainerId(serverName, projectPersist.getContainerID());
                    if (!existingContainers.isEmpty()) {
                        for (ContainerRuntimePojoPersist containerRuntimePojoPersist : existingContainers) {
                            containerRuntimePojoPersist.setStatus(ContainerRuntimePojoPersist.STATUS.TODEPLOY.name());
                            containerRuntimeRepository.save(containerRuntimePojoPersist);
                        }
                    } else {
                        List<RuntimePersist> servers = runtimeRepository.findByServerName(serverName);
                        for (RuntimePersist server : servers) {
                            ContainerRuntimePojoPersist runtimePojoPersist = new ContainerRuntimePojoPersist();
                            runtimePojoPersist.setServerName(serverName);
                            runtimePojoPersist.setHostname(server.getHostname());
                            runtimePojoPersist.setContainerId(projectPersist.getContainerID());
                            runtimePojoPersist.setStatus(ContainerRuntimePojoPersist.STATUS.TODEPLOY.name());
                            containerRuntimeRepository.save(runtimePojoPersist);
                        }


                    }
                    KieContainerUpdate kieContainerUpdate = new KieContainerUpdate();
                    kieContainerUpdate.setMainClass(projectPersist.getMainClass());
                    kieContainerUpdate.setArtifactID(projectPersist.getArtifactID());
                    kieContainerUpdate.setGroupID(projectPersist.getGroupID());
                    kieContainerUpdate.setProjectVersion(projectPersist.getProjectVersion());
                    kieContainerUpdate.setContainerID(projectPersist.getContainerID());
                    kieContainerUpdate.setAction(KieContainerUpdate.STATUS.TODEPLOY);
                    kafkaKieContainerUpdateTemplate.send(serverName,kieContainerUpdate);
                    workOnGoingView.addRow("Deploy Request="+kieContainerUpdate,ui);
                }
            }
        };
        thread.start();

    }

    private void executeWrite(String url, String username, String password, AddLog workOnGoingView, String jobID, UI ui) {

        String isJobDone = "NO";
        while ("NO".equals(isJobDone)) {
            JobStatus jobStatus = kieRepositoryService.getStatusJobID(url,
                    username,
                    password, jobID);
            if ("DUPLICATE_RESOURCE".equals(jobStatus.getStatus())
                    || "SUCCESS".equals(jobStatus.getStatus())) {
                isJobDone = "YES";
                workOnGoingView.addRow("JobID=" + jobID + " finished", ui);
            } else if ("ACCEPTED".equals(jobStatus.getStatus())
                    || ("APPROVED".equals(jobStatus.getStatus()))) {
                try {
                    synchronized (this) {
                        workOnGoingView.addRow("JobID=" + jobID + " not yet finished", ui);
                        this.wait(1000);
                    }
                } catch (InterruptedException e) {
                    logger.error("executeWrite", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
}
