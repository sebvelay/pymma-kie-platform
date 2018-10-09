package org.chtijbug.drools.console.service.model.kie;

import java.util.ArrayList;
import java.util.List;

public class SpaceProject {
    private String name;
    private String spaceName;
    private String groupId;
    private String version;
    private String description;
    private List<SpaceProjectURI> publicURIs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SpaceProjectURI> getPublicURIs() {
        return publicURIs;
    }

    public void setPublicURIs(List<SpaceProjectURI> publicURIs) {
        this.publicURIs = publicURIs;
    }
}
