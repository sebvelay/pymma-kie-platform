package org.chtijbug.drools.proxy.persistence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RuntimePersist {
    public enum STATUS {
        UP,
        DOWN
    }
    @Id
    private String id=new ObjectId().toString();

    @Indexed
    private String serverName;

    private String version;

    private String hostname;

    private String serverPort;

    private String sftpHost;

    private String sftpPort;

    private String status;

    public RuntimePersist(String serverName, String version, String hostname,String serverPort,String sftpPort,String sftpHost,String status) {
        this.serverName = serverName;
        this.version = version;
        this.hostname = hostname;
        this.serverPort = serverPort;
        this.sftpPort = sftpPort;
        this.sftpHost = sftpHost;
        this.status=status;
    }

    public RuntimePersist duplicate(){
        RuntimePersist duplicate = new RuntimePersist(serverName,version,hostname,serverPort,sftpPort,sftpHost,status);
        return duplicate;
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

    public String getSftpHost() {
        return sftpHost;
    }

    public void setSftpHost(String sftpHost) {
        this.sftpHost = sftpHost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}