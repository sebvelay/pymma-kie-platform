package org.chtijbug.drools.console.service.model.kie;

public class KieConfigurationData {

    private String kiewbUrl;

    private String userName;

    private String password;

    private String name;


    public String getKiewbUrl() {
        return kiewbUrl;
    }

    public void setKiewbUrl(String kiewbUrl) {
        this.kiewbUrl = kiewbUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
