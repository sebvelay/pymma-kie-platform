package org.chtijbug.drools.console;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.chtijbug.drools.console.service.JenkinsService;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.KieServerRepositoryService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.console.view.DeploymentView;
import org.springframework.beans.factory.annotation.Autowired;

@Push
@Theme("runo")
@SpringUI
@PreserveOnRefresh
@SuppressWarnings("serial")
public class DroolsAdminConsoleMainView extends UI {


    //  @Autowired
    //  private GitLabRepositoryService gitLabRepositoryService;

    final UserConnected userConnected = new UserConnected();
    //  @Autowired
    //  private GitRepositoryService gitRepositoryService;
    @Autowired
    private KieRepositoryService kieRepositoryService;
    @Autowired
    private KieServerRepositoryService kieServerRepositoryService;
    @Autowired
    private JenkinsService jenkinsService;
    private TextField userNameTextField;
    private PasswordField userpasswdTextField;

    public DroolsAdminConsoleMainView() {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        KieConfigurationData configKie = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        //  GitLabConfigurationData configGitLab = AppContext.getApplicationContext().getBean(GitLabConfigurationData.class);
        //  JenkinsConfigurationData jenkinsConfigurationData = AppContext.getApplicationContext().getBean(JenkinsConfigurationData.class);

        // gitLabRepositoryService = AppContext.getApplicationContext().getBean(GitLabRepositoryService.class);

        //jenkinsService = AppContext.getApplicationContext().getBean(JenkinsService.class);

        layout.setMargin(true);
        setContent(layout);
        HorizontalLayout userHorizontal = new HorizontalLayout();
        TextField urlTextField = new TextField("Kie-Wb url");
        urlTextField.setValue(configKie.getKiewbUrl());
        urlTextField.setMaxLength(200);
        userHorizontal.addComponent(urlTextField);
        layout.addComponent(userHorizontal);
        userNameTextField = new TextField("User name");
        userNameTextField.setValue(configKie.getUserName());
        userConnected.setUserName(configKie.getUserName());
        userNameTextField.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent textChangeEvent) {
                userConnected.setUserName(textChangeEvent.getText());
            }
        });

        userHorizontal.addComponent(userNameTextField);
        userpasswdTextField = new PasswordField("Password");
        userpasswdTextField.setValue(configKie.getPassword());
        userConnected.setUserPassword(configKie.getPassword());
        userpasswdTextField.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent textChangeEvent) {
                userConnected.setUserPassword(textChangeEvent.getText());
            }
        });
        userHorizontal.addComponent(userpasswdTextField);
        TabSheet tabsheetRepo = new TabSheet();
        layout.addComponent(tabsheetRepo);

        DeploymentView deploymentView = new DeploymentView(userConnected);
        tabsheetRepo.addTab(deploymentView, "Deploy");


    }


}