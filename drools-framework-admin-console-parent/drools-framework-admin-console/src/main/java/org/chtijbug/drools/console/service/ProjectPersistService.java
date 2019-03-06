package org.chtijbug.drools.console.service;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import org.chtijbug.drools.console.AddLog;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.JobStatus;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.proxy.persistence.json.KeyProject;
import org.chtijbug.drools.proxy.persistence.model.ContainerPojoPersist;
import org.chtijbug.drools.proxy.persistence.model.ProjectPersist;
import org.chtijbug.drools.proxy.persistence.model.RuntimePersist;
import org.chtijbug.drools.proxy.persistence.repository.ContainerRepository;
import org.chtijbug.drools.proxy.persistence.repository.ProjectRepository;
import org.chtijbug.drools.proxy.persistence.repository.RuntimeRepository;
import org.chtijbug.guvnor.server.jaxrs.model.PlatformProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProjectPersistService {

    public static String PROJECT = "4";

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
    private RuntimeRepository runtimeRepository;

    public ProjectPersistService() {
        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);

    }

    public static String getPROJECT() {
        return PROJECT;
    }

    public static void setPROJECT(String PROJECT) {
        ProjectPersistService.PROJECT = PROJECT;
    }

    public void saveIfnotExist(List<PlatformProjectResponse> platformProjectResponses) {

        for (PlatformProjectResponse platformProjectResponse : platformProjectResponses) {

            ProjectPersist projectPersist = projectRepository.findByProjectName(new KeyProject(platformProjectResponse.getSpaceName(), platformProjectResponse.getName()));

            if (projectPersist == null) {
                projectPersist = platformProjectResponseToProjectPersist(platformProjectResponse);

                projectRepository.save(projectPersist);
                addProjectToSession(projectPersist, true);

            } else {
                addProjectToSession(projectPersist, false);
            }
        }
    }

    public HashMap<String, ProjectPersist> getProjectsSession() {
        return (HashMap<String, ProjectPersist>) VaadinSession.getCurrent().getAttribute(PROJECT);
    }

    public void addProjectToSession(ProjectPersist projectPersist,boolean isModifiable) {

        HashMap<String, ProjectPersist> projectPersists = getProjectsSession();

        if (projectPersists == null) {
            projectPersists = new HashMap<>();
        }

        if (isModifiable) {
            projectPersists.put(projectPersist.getProjectName().toString(), projectPersist);
        } else {

            ProjectPersist tmp = projectPersists.get(projectPersist.getProjectName().toString());
            if (tmp == null) {
                projectPersists.put(projectPersist.getProjectName().toString(), projectPersist);
            }
        }

        VaadinSession.getCurrent().setAttribute(PROJECT, projectPersists);
    }

    public boolean associate(ProjectPersist projectPersist, List<RuntimePersist> runtimePersists) {

        projectPersist.getServerNames().clear();
        for (RuntimePersist runtimePersist : runtimePersists) {
            List<String> names = new ArrayList<String>();
            names.add(runtimePersist.getServerName());
            List<ProjectPersist> projectPersists = projectRepository.findByServerNamesInAndDeploymentName(names, projectPersist.getDeploymentName());
            //if (projectPersists.size() != 0) {
            //    return false;
           // }
            projectPersist.getServerNames().add(runtimePersist.getServerName());
        }

        projectPersist.setStatus(ProjectPersist.Deployable);
        projectPersist.setContainerID(projectPersist.getDeploymentName() + "-" + projectPersist.getProjectName());
        projectRepository.save(projectPersist);
        addProjectToSession(projectPersist, true);

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
        projectPersist.setProjectName(new KeyProject(platformProjectResponse.getSpaceName(), platformProjectResponse.getName()));
        projectPersist.setProjectVersion(platformProjectResponse.getVersion());
        projectPersist.setStatus(ProjectPersist.ADEFINIR);
        projectPersist.setClassNameList(platformProjectResponse.getJavaClasses());
        return projectPersist;
    }

    public void waitForJobToBeEnded(String url, String username, String password, ProjectPersist projectPersist, AddLog workOnGoingView, UI ui) {

        UserConnected userConnected = userConnectedService.getUserConnected();

        Thread thread = new Thread() {
            public void run() {

                JobStatus result = kieRepositoryService.buildProject(config.getKiewbUrl(), userConnected.getUserName(),
                        userConnected.getUserPassword(), projectPersist.getProjectName().getSpaceName(), projectPersist.getProjectName().getName(), "compile", workOnGoingView, ui);

                executeWrite(url, username, password, workOnGoingView, result.getJobId(), ui);

                result = kieRepositoryService.buildProject(config.getKiewbUrl(), userConnected.getUserName(),
                        userConnected.getUserPassword(), projectPersist.getProjectName().getSpaceName(), projectPersist.getProjectName().getName(), "install", workOnGoingView, ui);

                executeWrite(url, username, password, workOnGoingView, result.getJobId(), ui);

                //   ContainerPojoPersist toto = containerRepository.findByServerNameAndContainerId(projectPersist.getContainerID());
                for (String serverName : projectPersist.getServerNames()) {
                    List<RuntimePersist> kieservers = runtimeRepository.findByServerName(serverName);
                    if (kieservers.size() == 1) {
                        ContainerPojoPersist existingContainer = containerRepository.findByServerNameAndContainerId(serverName, projectPersist.getContainerID());
                        if (existingContainer == null) {
                            ContainerPojoPersist newContainer = new ContainerPojoPersist();
                            newContainer.setStatus(ContainerPojoPersist.STATUS.TODEPLOY.toString());
                            newContainer.setClassName(projectPersist.getMainClass());
                            newContainer.setProcessID(projectPersist.getProcessID());
                            newContainer.setContainerId(projectPersist.getContainerID());
                            newContainer.setServerName(serverName);
                            newContainer.setGroupId(projectPersist.getGroupID());
                            newContainer.setArtifactId(projectPersist.getArtifactID());
                            newContainer.setVersion(projectPersist.getProjectVersion());
                            containerRepository.save(newContainer);
                        } else {
                            existingContainer.setStatus(ContainerPojoPersist.STATUS.TODEPLOY.toString());
                            containerRepository.save(existingContainer);
                        }


                    }
                }
                //remove container from other runtime
                List<ContainerPojoPersist> containerPojoPersists = containerRepository.findByContainerId(projectPersist.getContainerID());
                for (ContainerPojoPersist container : containerPojoPersists){
                    if (projectPersist.getServerNames().contains(container.getServerName())==false){
                        container.setStatus(ContainerPojoPersist.STATUS.TODELETE.toString());
                        containerRepository.save(container);
                    }
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
                    workOnGoingView.addRow("JobID=" + jobID + " not yet finished", ui);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } /*else if("RESOURCE_NOT_EXIST".equals(jobStatus.getStatus())||
                            "SERVER_ERROR".equals(jobStatus.getStatus())||
                            "FAIL".equals(jobStatus.getStatus())){
                        isJobDone = "ERROR";
                    }*/
        }
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
}
