package org.chtijbug.drools.console.service.model.kie;

public class KieContainerInfo {

    private String containerId;
    private String artifactId;
    private String groupId;
    private String version;
    private String containerAlias;

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContainerAlias() {
        return containerAlias;
    }

    public void setContainerAlias(String containerAlias) {
        this.containerAlias = containerAlias;
    }
}
