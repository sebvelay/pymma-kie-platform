package org.chtijbug.drools.console.service.model;

import org.chtijbug.guvnor.server.jaxrs.model.PlatformProjectResponse;

import java.util.ArrayList;
import java.util.List;

public class UserConnected {
    private String userName;

    private String userPassword;

    private List<PlatformProjectResponse> projectResponses = new ArrayList<>();

    private List<String> roles = new ArrayList<>();

    private boolean connected;

    public UserConnected() {
    }

    public UserConnected(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public List<PlatformProjectResponse> getProjectResponses() {
        return projectResponses;
    }

    public void setProjectResponses(List<PlatformProjectResponse> projectResponses) {
        this.projectResponses = projectResponses;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean getConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

}
