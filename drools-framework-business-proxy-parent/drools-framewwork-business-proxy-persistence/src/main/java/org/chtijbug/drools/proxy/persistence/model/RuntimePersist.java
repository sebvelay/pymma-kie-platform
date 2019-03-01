package org.chtijbug.drools.proxy.persistence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RuntimePersist {

    @Id
    private String id=new ObjectId().toString();

    @Indexed
    private String serverName;

    private String version;

    private String hostname;

    private String serverPort;

    private String sftpPort;

    public RuntimePersist(String serverName, String version, String hostname,String serverPort,String sftpPort) {
        this.serverName = serverName;
        this.version = version;
        this.hostname = hostname;
        this.serverPort = serverPort;
        this.sftpPort = sftpPort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getSftpPort() {
        return sftpPort;
    }

    public void setSftpPort(String sftpPort) {
        this.sftpPort = sftpPort;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }
}
