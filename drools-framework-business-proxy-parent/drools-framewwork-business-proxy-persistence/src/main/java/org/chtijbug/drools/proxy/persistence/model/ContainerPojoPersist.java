package org.chtijbug.drools.proxy.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ContainerPojoPersist {

    @Id
    private String id;


    @Indexed
    private String className;
    @Indexed
    private String containerId;
    @Indexed
    private String serverName;


    private String projectName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContainerPojoPersist{");
        sb.append("id='").append(id).append('\'');
        sb.append(", className='").append(className).append('\'');
        sb.append(", containerId='").append(containerId).append('\'');
        sb.append(", serverName='").append(serverName).append('\'');
        sb.append(", projectName='").append(projectName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
