package org.chtijbug.drools.proxy.persistence.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class User {

    @Id
    @Indexed
    private String ID;

    @Indexed(unique = true)
    private String login;

    private String wbName;

    private String password;



    public User() {
    }

    public User(String ID, String login, String password) {
        this.ID = ID;
        this.login = login;
        this.password = password;
    }

    @DBRef
    private List<UserRoles> userRoles = new ArrayList<>();

    @DBRef
    private List<UserGroups> userGroups = new ArrayList<>();

    @DBRef
    private Customer customer;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    public List<UserGroups> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroups> userGroups) {
        this.userGroups = userGroups;
    }

    public String getWbName() {
        return wbName;
    }

    public void setWbName(String wbName) {
        this.wbName = wbName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
