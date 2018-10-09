package org.chtijbug.drools.console;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;
import org.chtijbug.drools.console.service.JenkinsService;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.KieServerRepositoryService;
import org.chtijbug.drools.console.service.model.DisplayData;
import org.chtijbug.drools.console.service.model.kie.*;
import org.chtijbug.drools.console.service.util.AppContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Theme("runo")
@SpringUI
@PreserveOnRefresh
@SuppressWarnings("serial")
public class DroolsAdminConsole extends UI {

    final private Button buttonDeployProject = new Button("Deploy project");
    final private List<DisplayData> currentSelected = new ArrayList<>();

    //  @Autowired
    //  private GitLabRepositoryService gitLabRepositoryService;

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
    private Table gitReposTable;
    private BeanItemContainer<DisplayData> gitReposListContainer;

    public DroolsAdminConsole() {
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
        TextField urlTextField = new TextField("Kie-Wb url");
        urlTextField.setValue(configKie.getKiewbUrl());
        urlTextField.setMaxLength(200);
        layout.addComponent(urlTextField);
        HorizontalLayout userHorizontal = new HorizontalLayout();
        layout.addComponent(userHorizontal);
        userNameTextField = new TextField("User name");
        userNameTextField.setValue(configKie.getUserName());
        userHorizontal.addComponent(userNameTextField);
        userpasswdTextField = new PasswordField("Password");
        userpasswdTextField.setValue(configKie.getPassword());
        userHorizontal.addComponent(userpasswdTextField);
        Button button = new Button("Repositories");
        button.addStyleName(Runo.BUTTON_SMALL);
        gitReposListContainer = new BeanItemContainer<>(DisplayData.class);

        gitReposTable = new Table("List of repository/project", gitReposListContainer);
        gitReposTable.setSelectable(true);
        gitReposTable.setMultiSelectMode(MultiSelectMode.SIMPLE);
        //gitReposTable.
        button.addClickListener((Button.ClickListener) event -> {
            this.refreshList();
        });
        layout.addComponent(button);
        layout.addComponent(gitReposTable);


        layout.addComponent(buttonDeployProject);
        buttonDeployProject.addStyleName(Runo.BUTTON_SMALL);

        gitReposTable.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                currentSelected.clear();
                if (gitReposTable.getValue() != null) {
                    currentSelected.add((DisplayData) gitReposTable.getValue());
                    buttonDeployProject.setEnabled(true);
                } else {
                    buttonDeployProject.setEnabled(false);
                }

            }
        });
        buttonDeployProject.addClickListener((Button.ClickListener) event -> {
            if (currentSelected.size() == 1) {
                DisplayData displayData = currentSelected.get(0);
                KieConfigurationData config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
                kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);

                JobStatus result = kieRepositoryService.buildProject(config.getKiewbUrl(), userNameTextField.getValue(),
                        userpasswdTextField.getValue(), displayData.getSpaceName(), displayData.getProjectName(), "compile");


                result = kieRepositoryService.buildProject(config.getKiewbUrl(), userNameTextField.getValue(),
                        userpasswdTextField.getValue(), displayData.getSpaceName(), displayData.getProjectName(), "install");

                kieRepositoryService.waitForJobToBeEnded(config.getKiewbUrl(), userNameTextField.getValue(),
                        userpasswdTextField.getValue(), result.getJobId());
                if (displayData.getContainerId() != null && displayData.getContainerId().length() > 0) {
                    KieServerJobStatus jobresult = kieServerRepositoryService.stopContainer(config.getKieserverUrl(), config.getKieserverUserName(), config.getKieserverPassword(), displayData.getContainerId());
                    if (jobresult != null
                            && "SUCCESS".equals(jobresult.getType())) {
                    }

                }
                KieContainerRequest newContainer = new KieContainerRequest();
                newContainer.setContainerId(displayData.getContainerId());
                newContainer.setReleaseId(new ReleaseDefinition());
                newContainer.getReleaseId().setArtifactId(displayData.getProjectName());
                newContainer.getReleaseId().setGroupId(displayData.getProjectGroupID());
                newContainer.getReleaseId().setVersion(displayData.getProjectVersion());
                KieContainerInfo toto = kieServerRepositoryService.createContainer(config.getKieserverUrl(), config.getKieserverUserName(), config.getKieserverPassword(), displayData.getProjectName(), newContainer);
                System.out.println("coucou");
                this.refreshList();
            }


        });
        buttonDeployProject.setEnabled(false);


    }

    private void refreshList() {
        gitReposListContainer.removeAllItems();
        KieConfigurationData config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);
        List<KieContainerInfo> listcontainers = kieServerRepositoryService.getContainerList(config.getKieserverUrl(), config.getKieserverUserName(), config.getKieserverPassword());

        List<Space> listSpaces = kieRepositoryService.getListSpaces(config.getKiewbUrl(), userNameTextField.getValue(),
                userpasswdTextField.getValue());
        for (Space space : listSpaces) {
            for (SpaceProject spaceProject : space.getProjects()) {
                DisplayData displayData = new DisplayData();
                displayData.setSpaceName(space.getName());
                displayData.setSpaceDescription(space.getDescription());
                displayData.setSpaceDefaultGroupID(space.getDefaultGroupId());
                displayData.setSpaceOwner(space.getOwner());
                displayData.setProjectName(spaceProject.getName());
                displayData.setProjectGroupID(spaceProject.getGroupId());
                displayData.setProjectVersion(spaceProject.getVersion());
                displayData.setProjectDescription(spaceProject.getDescription());
                for (SpaceProjectURI uri : spaceProject.getPublicURIs()) {
                    if (uri.getProtocol().equals("git")) {
                        displayData.setProjectGitAddress(uri.getUri());
                    } else {
                        displayData.setProjectSSHAdress(uri.getUri());
                    }
                }
                for (KieContainerInfo kie : listcontainers) {
                    if (kie.getGroupId().equals(displayData.getProjectGroupID())
                            && kie.getArtifactId().equals(displayData.getProjectName())
                            && kie.getVersion().equals(displayData.getProjectVersion())) {
                        displayData.setKieServerArtifactId(kie.getArtifactId());
                        displayData.setKieServerGroupId(kie.getGroupId());
                        displayData.setKieServerVersion(kie.getVersion());
                        displayData.setContainerId(kie.getContainerId());
                        displayData.setContainerAlias(kie.getContainerAlias());
                    }
                }
                gitReposListContainer.addBean(displayData);
            }
        }

        System.out.println("coucou");
    }


}