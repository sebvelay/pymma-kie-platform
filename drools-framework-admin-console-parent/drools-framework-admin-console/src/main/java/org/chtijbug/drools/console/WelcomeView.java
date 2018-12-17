package org.chtijbug.drools.console;

import com.vaadin.data.HasValue;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;

public class WelcomeView extends VerticalLayout implements View {

    private UserConnected userConnected;
    private TextField userNameTextField;
    private PasswordField userpasswdTextField;
    private Button loginButton;
    private Button logoutButton;
    private KieRepositoryService kieRepositoryService;
    private KieConfigurationData configKie;

    public WelcomeView(UserConnected userConnected, MenuBar menuBar) {
        this.kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);

        this.userConnected = userConnected;
        final VerticalLayout layout = new VerticalLayout();
        layout.addComponent(menuBar);
        this.configKie = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        //  GitLabConfigurationData configGitLab = AppContext.getApplicationContext().getBean(GitLabConfigurationData.class);
        //  JenkinsConfigurationData jenkinsConfigurationData = AppContext.getApplicationContext().getBean(JenkinsConfigurationData.class);

        // gitLabRepositoryService = AppContext.getApplicationContext().getBean(GitLabRepositoryService.class);

        //jenkinsService = AppContext.getApplicationContext().getBean(JenkinsService.class);

        layout.setMargin(true);
        this.addComponent(layout);
        HorizontalLayout userHorizontal = new HorizontalLayout();
        TextField urlTextField = new TextField("Kie-Wb url");
        urlTextField.setValue(configKie.getKiewbUrl());
        urlTextField.setMaxLength(200);
        userHorizontal.addComponent(urlTextField);
        layout.addComponent(userHorizontal);
        userNameTextField = new TextField("User name");
        userNameTextField.setValue(configKie.getUserName());
        userConnected.setUserName(configKie.getUserName());
        userNameTextField.addValueChangeListener((HasValue.ValueChangeListener<String>) valueChangeEvent -> userConnected.setUserName(valueChangeEvent.getValue()));

        userHorizontal.addComponent(userNameTextField);
        userpasswdTextField = new PasswordField("Password");
        userpasswdTextField.setValue(configKie.getPassword());
        userConnected.setUserPassword(configKie.getPassword());
        userpasswdTextField.addValueChangeListener((HasValue.ValueChangeListener<String>) valueChangeEvent -> userConnected.setUserPassword(valueChangeEvent.getValue()));
        userHorizontal.addComponent(userpasswdTextField);
        loginButton = new Button("login");
        userHorizontal.addComponent(loginButton);
        logoutButton = new Button("logout");
        logoutButton.setEnabled(false);
        userHorizontal.addComponent(logoutButton);

        loginButton.addClickListener((Button.ClickListener) event -> {
            UserConnected connected = kieRepositoryService.login(this.configKie.getKiewbUrl(), userConnected.getUserName(), userConnected.getUserPassword());
            userConnected.getProjectResponses().clear();
            userConnected.getProjectResponses().addAll(connected.getProjectResponses());
            userConnected.getRoles().clear();
            userConnected.getRoles().addAll(connected.getRoles());
            loginButton.setEnabled(false);
            logoutButton.setEnabled(true);
            userConnected.setConnected(true);
        });
        logoutButton.addClickListener((Button.ClickListener) event -> {
            userConnected.getRoles().clear();
            userConnected.getProjectResponses().clear();
            userConnected.setConnected(false);
            loginButton.setEnabled(true);
            logoutButton.setEnabled(false);
        });


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

}
