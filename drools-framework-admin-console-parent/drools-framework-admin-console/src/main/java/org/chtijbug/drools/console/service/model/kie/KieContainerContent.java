package org.chtijbug.drools.console.service.model.kie;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KieContainerContent {
    @JsonProperty("container-id")
    private String containerId;

    private String status;

    @JsonProperty("release-id")
    private ReleaseDefinition releaseId;

    @JsonProperty("resolved-release-id")
    private ReleaseDefinition resolvedReleaseId;

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ReleaseDefinition getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(ReleaseDefinition releaseId) {
        this.releaseId = releaseId;
    }

    public ReleaseDefinition getResolvedReleaseId() {
        return resolvedReleaseId;
    }

    public void setResolvedReleaseId(ReleaseDefinition resolvedReleaseId) {
        this.resolvedReleaseId = resolvedReleaseId;
    }
}
