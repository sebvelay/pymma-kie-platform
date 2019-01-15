package org.chtijbug.drools.console.service.model.kie;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class KieContainerRequest {
    @JsonProperty("container-id")
    @XStreamAlias( "container-id" )
    private String containerId;
    @JsonProperty("release-id")
    @XStreamAlias( "release-id" )
    private ReleaseDefinition releaseId;

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public ReleaseDefinition getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(ReleaseDefinition releaseId) {
        this.releaseId = releaseId;
    }
}
