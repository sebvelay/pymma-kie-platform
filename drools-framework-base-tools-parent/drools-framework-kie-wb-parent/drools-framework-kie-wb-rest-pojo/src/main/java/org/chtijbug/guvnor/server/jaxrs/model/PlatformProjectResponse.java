package org.chtijbug.guvnor.server.jaxrs.model;

import org.guvnor.rest.client.ProjectResponse;

import java.util.ArrayList;
import java.util.List;

public class PlatformProjectResponse extends ProjectResponse {

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
}
