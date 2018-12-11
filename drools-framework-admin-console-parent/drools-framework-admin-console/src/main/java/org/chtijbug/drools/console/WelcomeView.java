package org.chtijbug.drools.console;

import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;

public class WelcomeView extends VerticalLayout implements View {

    private UserConnected userConnected;
    private TextField userNameTextField;
    private PasswordField userpasswdTextField;

    public WelcomeView(UserConnected userConnected, MenuBar menuBar) {
        this.userConnected = userConnected;
        final VerticalLayout layout = new VerticalLayout();
        layout.addComponent(menuBar);
        KieConfigurationData configKie = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
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
        userNameTextField.addTextChangeListener((FieldEvents.TextChangeListener) textChangeEvent -> userConnected.setUserName(textChangeEvent.getText()));

        userHorizontal.addComponent(userNameTextField);
        userpasswdTextField = new PasswordField("Password");
        userpasswdTextField.setValue(configKie.getPassword());
        userConnected.setUserPassword(configKie.getPassword());
        userpasswdTextField.addTextChangeListener((FieldEvents.TextChangeListener) textChangeEvent -> userConnected.setUserPassword(textChangeEvent.getText()));
        userHorizontal.addComponent(userpasswdTextField);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

}
