package org.chtijbug.drools.console.service;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import org.apache.tomcat.util.threads.TaskThread;
import org.chtijbug.drools.console.AddLog;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.JobStatus;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.model.kie.KieContainerInfo;
import org.chtijbug.drools.console.service.model.kie.KieServerJobStatus;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.proxy.persistence.model.ProjectPersist;
import org.chtijbug.drools.proxy.persistence.model.RuntimePersist;
import org.chtijbug.drools.proxy.persistence.repository.ProjectRepository;
import org.chtijbug.guvnor.server.jaxrs.model.PlatformProjectResponse;
import org.guvnor.rest.client.ProjectResponse;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.ReleaseId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private KieServerRepositoryService kieServerRepositoryService;

    private String isJobDone = "NO";


    public ProjectPersistService(){
        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);

    }


    public void saveIfnotExist(List<PlatformProjectResponse> platformProjectResponses){

        for(PlatformProjectResponse platformProjectResponse:platformProjectResponses) {

            ProjectPersist projectPersist = projectRepository.findByProjectName(platformProjectResponse.getSpaceName() + "-" + platformProjectResponse.getName());

            if (projectPersist == null) {
                projectPersist = platformProjectResponseToProjectPersist(platformProjectResponse);

                projectRepository.save(projectPersist);
                addProjectToSession(projectPersist);

            }else {
                addProjectToSession(projectPersist);
            }
        }
    }

    public Set<ProjectPersist> getProjectsSession() {
        return (Set<ProjectPersist>) VaadinSession.getCurrent().getAttribute(PROJECT);
    }

    public void addProjectToSession(ProjectPersist projectPersist) {

        Set<ProjectPersist> projectPersists=getProjectsSession();

        if(projectPersists==null){
            projectPersists=new HashSet<>();
        }
        projectPersists.add(projectPersist);

        VaadinSession.getCurrent().setAttribute(PROJECT, projectPersists);
    }
    public boolean associate(ProjectPersist projectPersist, RuntimePersist runtimePersist){

        List<ProjectPersist> projectPersists=projectRepository.findByServerNameAndDeploymentName(runtimePersist.getServerName(),projectPersist.getDeploymentName());

        if(projectPersists.size()!=0){
            return false;
        }else {

            projectPersist.setServerName(runtimePersist.getServerName());
            projectPersist.setStatus(ProjectPersist.Deployable);
            projectPersist.setContainerID(projectPersist.getDeploymentName()+"-"+projectPersist.getProjectName());
            projectRepository.save(projectPersist);
            addProjectToSession(projectPersist);

            return true;
        }
    }

    public boolean deployer(ProjectPersist projectPersist, AddLog addLog, UI ui){


        waitForJobToBeEnded(config.getKiewbUrl(), userConnectedService.getUserConnected().getUserName(),
                userConnectedService.getUserConnected().getUserPassword(), projectPersist, addLog,ui);

        return false;
    }


    public ProjectPersist platformProjectResponseToProjectPersist(PlatformProjectResponse platformProjectResponse){
        ProjectPersist projectPersist=new ProjectPersist();
        projectPersist.setArtifactID(platformProjectResponse.getArtifactId());
        projectPersist.setGroupID(platformProjectResponse.getGroupId());
        projectPersist.setProjectName(platformProjectResponse.getSpaceName()+"-"+platformProjectResponse.getName());
        projectPersist.setProjectVersion(platformProjectResponse.getVersion());
        projectPersist.setStatus(ProjectPersist.ADEFINIR);
        projectPersist.setClassNameList(platformProjectResponse.getJavaClasses());
        projectPersist.setSpaceName(platformProjectResponse.getSpaceName());
        projectPersist.setOldName(platformProjectResponse.getName());
        return projectPersist;
    }

    public String waitForJobToBeEnded(String url, String username, String password,ProjectPersist projectPersist, AddLog workOnGoingView, UI ui) {

        UserConnected userConnected=userConnectedService.getUserConnected();

        Thread thread=new Thread(){
            public void run() {

                JobStatus result = kieRepositoryService.buildProject(config.getKiewbUrl(), userConnected.getUserName(),
                        userConnected.getUserPassword(), projectPersist.getSpaceName(), projectPersist.getOldName(), "compile", workOnGoingView,ui);

                executeWrite(url,username,password,workOnGoingView,result.getJobId(),ui);

                result = kieRepositoryService.buildProject(config.getKiewbUrl(), userConnected.getUserName(),
                        userConnected.getUserPassword(), projectPersist.getSpaceName(), projectPersist.getOldName(), "install", workOnGoingView,ui);

                executeWrite(url,username,password,workOnGoingView,result.getJobId(),ui);

                if (projectPersist.getContainerID() != null) {
                    KieServerJobStatus jobresult = kieServerRepositoryService.stopContainer(config.getKieserverUrl(), config.getKieserverUserName(), config.getKieserverPassword(), projectPersist.getContainerID(), workOnGoingView,ui);
                    if (jobresult != null
                            && "SUCCESS".equals(jobresult.getType())) {
                    }
                }
                KieContainerResource newContainer = new KieContainerResource();
                newContainer.setContainerId(projectPersist.getContainerID());
                newContainer.setReleaseId(new ReleaseId());
                newContainer.getReleaseId().setArtifactId(projectPersist.getArtifactID());
                newContainer.getReleaseId().setGroupId(projectPersist.getGroupID());
                newContainer.getReleaseId().setVersion(projectPersist.getProjectVersion());
                KieContainerInfo createdContainer = kieServerRepositoryService.createContainer(config.getKieserverUrl(), config.getKieserverUserName(), config.getKieserverPassword(), projectPersist.getArtifactID(), newContainer, workOnGoingView,ui);


            }
        };
        thread.start();


        return isJobDone;
    }
    private void executeWrite(String url, String username, String password,AddLog workOnGoingView,String jobID,UI ui){
        while ("NO".equals(isJobDone)) {
            JobStatus jobStatus = kieRepositoryService.getStatusJobID(url,
                    username,
                    password, jobID);
            if ("DUPLICATE_RESOURCE".equals(jobStatus.getStatus())
                    || "SUCCESS".equals(jobStatus.getStatus())) {
                isJobDone = "YES";
                workOnGoingView.addRow("JobID=" + jobID + " finished",ui);
            } else if ("ACCEPTED".equals(jobStatus.getStatus())
                    || ("APPROVED".equals(jobStatus.getStatus()))) {
                try {
                    workOnGoingView.addRow("JobID=" + jobID + " not yet finished",ui);
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

    public static String getPROJECT() {
        return PROJECT;
    }

    public static void setPROJECT(String PROJECT) {
        ProjectPersistService.PROJECT = PROJECT;
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
}
