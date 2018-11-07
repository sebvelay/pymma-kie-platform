package org.chtijbug.drools.console.service.model.kie;

public class KieConfigurationData {

    private String kiewbUrl;

    private String userName;

    private String password;

    private String kieserverUrl;

    private String kieserverUserName;

    private String kieserverPassword;

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

    public String getKieserverUrl() {
        return kieserverUrl;
    }

    public void setKieserverUrl(String kieserverUrl) {
        this.kieserverUrl = kieserverUrl;
    }

    public String getKieserverUserName() {
        return kieserverUserName;
    }

    public void setKieserverUserName(String kieserverUserName) {
        this.kieserverUserName = kieserverUserName;
    }

    public String getKieserverPassword() {
        return kieserverPassword;
    }

    public void setKieserverPassword(String kieserverPassword) {
        this.kieserverPassword = kieserverPassword;
    }
}
