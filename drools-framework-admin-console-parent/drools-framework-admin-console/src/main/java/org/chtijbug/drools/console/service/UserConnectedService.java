package org.chtijbug.drools.console.service;

import com.vaadin.flow.server.VaadinSession;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.springframework.stereotype.Service;

@Service
public class UserConnectedService {

    public static String USER = "1";
    public static String ASSET = "2";
    public static String SPACE = "3";
    public static String PROJECT = "4";

    public UserConnected getUserConnected() {
        return (UserConnected) VaadinSession.getCurrent().getAttribute(USER);
    }

    public void addToSession(UserConnected userConnected) {
        VaadinSession.getCurrent().setAttribute(USER, userConnected);
    }

    public void disconnect(){
        VaadinSession.getCurrent().setAttribute(USER, null);
    }

    public void addAssetToSession(String asset) {
        VaadinSession.getCurrent().setAttribute(ASSET, asset);
    }

    public String getAsset() {
        return (String) VaadinSession.getCurrent().getAttribute(ASSET);
    }

    public void addSpaceToSession(String spaceName) {
        VaadinSession.getCurrent().setAttribute(SPACE, spaceName);
    }

    public String getSpace() {
        return (String) VaadinSession.getCurrent().getAttribute(SPACE);
    }

    public void addProjectToSession(String projectName) {
        VaadinSession.getCurrent().setAttribute(PROJECT, projectName);
    }

    public String getProject() {
        return (String) VaadinSession.getCurrent().getAttribute(PROJECT);
    }
}
