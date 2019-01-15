package org.chtijbug.drools.console.service.model.kie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="release-id")
@XStreamAlias( "release-id" )
@JsonIgnoreProperties({"snapshot"})
public class ReleaseDefinition {

    @JsonProperty("artifact-id")
    @XStreamAlias( "artifact-id" )
    private String artifactId;
    @JsonProperty("group-id")
    @XStreamAlias( "group-id" )
    private String groupId;
    @XStreamAlias( "version" )
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
