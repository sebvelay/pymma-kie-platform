package org.chtijbug.drools;

import java.util.ArrayList;
import java.util.List;

public class ReverseProxyUpdate {
    private String path;

    List<String> serverNames = new ArrayList<>();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getServerNames() {
        return serverNames;
    }

    public void setServerNames(List<String> serverNames) {
        this.serverNames = serverNames;
    }
}
