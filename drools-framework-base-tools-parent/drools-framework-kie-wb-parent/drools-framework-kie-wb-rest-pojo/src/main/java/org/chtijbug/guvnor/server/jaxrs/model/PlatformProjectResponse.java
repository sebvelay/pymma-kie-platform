package org.chtijbug.guvnor.server.jaxrs.model;

import org.guvnor.rest.client.ProjectResponse;

import java.util.ArrayList;
import java.util.List;

public class PlatformProjectResponse extends ProjectResponse {

    private String artifactId;

    private String branch;

    private List<String> javaClasses = new ArrayList<>();

    public PlatformProjectResponse() {
        super();
    }

    public List<String> getJavaClasses() {
        return javaClasses;
    }

    public void setJavaClasses(List<String> javaClasses) {
        this.javaClasses = javaClasses;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
