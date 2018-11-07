package org.chtijbug.drools.console.service.model.kie;

import java.util.ArrayList;
import java.util.List;

public class Space {
    private String name;
    private String description;
    private List<SpaceProject> projects = new ArrayList<>();
    private String owner;
    private String defaultGroupId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SpaceProject> getProjects() {
        return projects;
    }

    public void setProjects(List<SpaceProject> projects) {
        this.projects = projects;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDefaultGroupId() {
        return defaultGroupId;
    }

    public void setDefaultGroupId(String defaultGroupId) {
        this.defaultGroupId = defaultGroupId;
    }
}
