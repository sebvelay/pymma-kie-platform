package org.chtijbug.guvnor.server.jaxrs.model;

import org.guvnor.rest.client.ProjectResponse;

import java.util.ArrayList;
import java.util.List;

public class PlatformProjectData extends ProjectResponse {

    private String artifactId;

    private String wbName;

    private String branch;

    private List<String> javaClasses = new ArrayList<>();

    private List<DependencyData> dependencies = new ArrayList<>();

    private KModuleData kModule;


    public PlatformProjectData() {
        super();
    }

    public String getWbName() {
        return wbName;
    }

    public void setWbName(String wbName) {
        this.wbName = wbName;
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

    public List<DependencyData> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<DependencyData> dependencies) {
        this.dependencies = dependencies;
    }

    public KModuleData getkModule() {
        return kModule;
    }

    public void setkModule(KModuleData kModule) {
        this.kModule = kModule;
    }
}
