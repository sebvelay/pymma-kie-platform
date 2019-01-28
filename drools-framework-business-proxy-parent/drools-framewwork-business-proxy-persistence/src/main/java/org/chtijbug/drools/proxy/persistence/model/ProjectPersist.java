package org.chtijbug.drools.proxy.persistence.model;

import org.bson.types.ObjectId;
import org.chtijbug.drools.proxy.persistence.json.KeyProject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class ProjectPersist implements Serializable {

    public static final String ADEFINIR="A définir";

    public static final String DEFINI="Défini";

    public static final String Deployable="Déployable";

    @Indexed
    private String deploymentName;

    @Id
    @Indexed
    private KeyProject projectName;

    private String mainClass;

    private String groupID;

    private String artifactID;

    private String processID;

    private String projectVersion;

    private String containerID;

    private String serverName;

    private String status;

    private List<String> classNameList;

    public ProjectPersist(){}

    public String getDeploymentName() {
        return deploymentName;
    }

    public void setDeploymentName(String deploymentName) {
        this.deploymentName = deploymentName;
    }

    public KeyProject getProjectName() {
        return projectName;
    }

    public void setProjectName(KeyProject projectName) {
        this.projectName = projectName;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getArtifactID() {
        return artifactID;
    }

    public void setArtifactID(String artifactID) {
        this.artifactID = artifactID;
    }

    public String getProcessID() {
        return processID;
    }

    public String getContainerID() {
        return containerID;
    }

    public void setContainerID(String containerID) {
        this.containerID = containerID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }

    public String getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getClassNameList() {
        return classNameList;
    }

    public void setClassNameList(List<String> classNameList) {
        this.classNameList = classNameList;
    }
}
