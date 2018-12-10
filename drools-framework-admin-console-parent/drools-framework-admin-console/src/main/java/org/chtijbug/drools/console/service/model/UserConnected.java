package org.chtijbug.drools.console.service.model;

public class UserConnected {
    private String userName;

    private String userPassword;

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
}
