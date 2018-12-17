package org.chtijbug.drools.console;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.UserConnectedService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("")
public class WelcomeView extends DroolsAdminConsoleMainView {

    private UserConnected userConnected;
    private TextField userNameTextField;
    private PasswordField userpasswdTextField;
    private Button loginButton;
    private Button logoutButton;

    @Autowired
    private KieRepositoryService kieRepositoryService;
    @Autowired
    private KieConfigurationData configKie;

    @Autowired
    private UserConnectedService userConnectedService;

    public WelcomeView() {
        super();
    }

    @PostConstruct
    public void buildUI() {

        userConnected = new UserConnected();
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout userHorizontal = new HorizontalLayout();
        verticalLayout.add(userHorizontal);
        TextField urlTextField = new TextField("Kie-Wb url");
        urlTextField.setValue(configKie.getKiewbUrl());
        urlTextField.setMaxLength(200);
        userHorizontal.add(urlTextField);
        userNameTextField = new TextField("User name");
        userNameTextField.setValue(configKie.getUserName());
        userConnected.setUserName(configKie.getUserName());
        userNameTextField.addValueChangeListener(valueChangeEvent -> userConnected.setUserName(valueChangeEvent.getValue()));

        userHorizontal.add(userNameTextField);
        userpasswdTextField = new PasswordField("Password");
        userpasswdTextField.setValue(configKie.getPassword());
        userConnected.setUserPassword(configKie.getPassword());
        userpasswdTextField.addValueChangeListener(valueChangeEvent -> userConnected.setUserPassword(valueChangeEvent.getValue()));
        userHorizontal.add(userpasswdTextField);
        loginButton = new Button("login");
        userHorizontal.add(loginButton);
        logoutButton = new Button("logout");
        logoutButton.setEnabled(false);
        userHorizontal.add(logoutButton);

        loginButton.addClickListener(event -> {
            UserConnected connected = kieRepositoryService.login(this.configKie.getKiewbUrl(), userConnected.getUserName(), userConnected.getUserPassword());
            userConnected.getProjectResponses().clear();
            userConnected.getProjectResponses().addAll(connected.getProjectResponses());
            userConnected.getRoles().clear();
            userConnected.getRoles().addAll(connected.getRoles());
            loginButton.setEnabled(false);
            logoutButton.setEnabled(true);
            userConnected.setConnected(true);
            userConnectedService.addToSession(userConnected);
        });
        logoutButton.addClickListener(event -> {
            userConnected.getRoles().clear();
            userConnected.getProjectResponses().clear();
            userConnected.setConnected(false);
            loginButton.setEnabled(true);
            logoutButton.setEnabled(false);
            userConnectedService.addToSession(null);
        });
        setActionView(verticalLayout);

    }



}
