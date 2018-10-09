package org.chtijbug.drools.console.service.model.kie;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReleaseDefinition {

    @JsonProperty("artifact-id")
    private String artifactId;
    @JsonProperty("group-id")
    private String groupId;
    private String version;

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
}
