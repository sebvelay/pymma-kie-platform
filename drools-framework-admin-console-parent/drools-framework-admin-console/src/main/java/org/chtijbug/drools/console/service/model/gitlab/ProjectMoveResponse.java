package org.chtijbug.drools.console.service.model.gitlab;

import java.util.List;

public class ProjectMoveResponse {
    private String id;
    private String name;
    private String path;
    private String description;
    private String visibility_level;
    private String lfs_enabled;
    private String avatar_url;
    private String web_url;

    private String request_access_enabled;

    private String full_name;

    private String full_path;
    private List<GroupElementResponse> projects;
    private List<GroupElementResponse> shared_projects;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVisibility_level() {
        return visibility_level;
    }

    public void setVisibility_level(String visibility_level) {
        this.visibility_level = visibility_level;
    }

    public String getLfs_enabled() {
        return lfs_enabled;
    }

    public void setLfs_enabled(String lfs_enabled) {
        this.lfs_enabled = lfs_enabled;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getRequest_access_enabled() {
        return request_access_enabled;
    }

    public void setRequest_access_enabled(String request_access_enabled) {
        this.request_access_enabled = request_access_enabled;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getFull_path() {
        return full_path;
    }

    public void setFull_path(String full_path) {
        this.full_path = full_path;
    }

    public List<GroupElementResponse> getProjects() {
        return projects;
    }

    public void setProjects(List<GroupElementResponse> projects) {
        this.projects = projects;
    }

    public List<GroupElementResponse> getShared_projects() {
        return shared_projects;
    }

    public void setShared_projects(List<GroupElementResponse> shared_projects) {
        this.shared_projects = shared_projects;
    }
}
