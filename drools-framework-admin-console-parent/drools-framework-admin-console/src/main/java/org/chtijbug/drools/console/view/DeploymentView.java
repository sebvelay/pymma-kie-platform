package org.chtijbug.drools.console.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.chtijbug.drools.console.AddLog;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.KieServerRepositoryService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.*;
import org.chtijbug.drools.console.service.util.AppContext;
import org.guvnor.rest.client.ProjectResponse;

import java.util.ArrayList;
import java.util.List;

public class DeploymentView extends VerticalLayout implements AddLog, View {


    final private Grid<List<String>> gridLogging = new Grid();
    final private Button buttonDeployProject = new Button("Deploy project");
    final private KieConfigurationData config;
    private ComboBox<ProjectResponse> spaceSelection;

    final private TextField projectArtifactIDTextField = new TextField("Project Artifact ID");
    final private TextField projectGroupIDTextField = new TextField("Project Group ID");
    final private TextField projectVersionTextField = new TextField("Project Version");
    final private TextField containerIdTextField = new TextField("Container ID");

    final private KieRepositoryService kieRepositoryService;

    final private KieServerRepositoryService kieServerRepositoryService;

    final private UserConnected userConnected;
    private List<String> logs = new ArrayList<>();

    public DeploymentView(UserConnected userConnected) {

        this.kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);

        this.kieServerRepositoryService = AppContext.getApplicationContext().getBean(KieServerRepositoryService.class);

        this.userConnected = userConnected;
        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        Button button = new Button("Refresh");
        //  button.addStyleName(Runo.BUTTON_SMALL);

        button.addClickListener((Button.ClickListener) event -> {
            this.refreshCombo();
            this.refreshList();
        });

        this.addComponent(button);


        spaceSelection = new ComboBox("Project", userConnected.getProjectResponses());
        spaceSelection.setItemCaptionGenerator(ProjectResponse::getName);
        spaceSelection.addValueChangeListener(valueChangeEvent -> {
            ProjectResponse response = (ProjectResponse) spaceSelection.getValue();
            projectArtifactIDTextField.setValue(response.getName());
            projectGroupIDTextField.setValue(response.getGroupId());
            projectVersionTextField.setValue(response.getVersion());
            refreshList();
        });


        this.addComponent(spaceSelection);

        projectArtifactIDTextField.setEnabled(false);
        projectGroupIDTextField.setEnabled(false);
        projectVersionTextField.setEnabled(false);
        containerIdTextField.setEnabled(false);
        this.addComponent(projectArtifactIDTextField);
        this.addComponent(projectGroupIDTextField);
        this.addComponent(projectVersionTextField);
        this.addComponent(containerIdTextField);

        this.buttonDeployProject.setEnabled(false);
        this.addComponent(buttonDeployProject);
        // buttonDeployProject.addStyleName(Runo.BUTTON_SMALL);


        buttonDeployProject.addClickListener((Button.ClickListener) event -> {
            //  if (containerIdTextField.getValue() != null
            //      && containerIdTextField.getValue().length() > 0) {


            ProjectResponse response = (ProjectResponse) spaceSelection.getValue();

            JobStatus result = kieRepositoryService.buildProject(config.getKiewbUrl(), userConnected.getUserName(),
                    userConnected.getUserPassword(), response.getSpaceName(), response.getName(), "compile", this);
            kieRepositoryService.waitForJobToBeEnded(config.getKiewbUrl(), userConnected.getUserName(),
                    userConnected.getUserPassword(), result.getJobId(), this);

            result = kieRepositoryService.buildProject(config.getKiewbUrl(), userConnected.getUserName(),
                    userConnected.getUserPassword(), response.getSpaceName(), response.getName(), "install", this);

            kieRepositoryService.waitForJobToBeEnded(config.getKiewbUrl(), userConnected.getUserName(),
                    userConnected.getUserPassword(), result.getJobId(), this);
            if (containerIdTextField.getValue() != null && containerIdTextField.getValue().length() > 0) {
                KieServerJobStatus jobresult = kieServerRepositoryService.stopContainer(config.getKieserverUrl(), config.getKieserverUserName(), config.getKieserverPassword(), containerIdTextField.getValue(), this);
                if (jobresult != null
                        && "SUCCESS".equals(jobresult.getType())) {
                }

            }
            KieContainerRequest newContainer = new KieContainerRequest();
            newContainer.setContainerId(containerIdTextField.getValue());
            newContainer.setReleaseId(new ReleaseDefinition());
            newContainer.getReleaseId().setArtifactId(projectArtifactIDTextField.getValue());
            newContainer.getReleaseId().setGroupId(projectGroupIDTextField.getValue());
            newContainer.getReleaseId().setVersion(projectVersionTextField.getValue());
            KieContainerInfo createdContainer = kieServerRepositoryService.createContainer(config.getKieserverUrl(), config.getKieserverUserName(), config.getKieserverPassword(), projectArtifactIDTextField.getValue(), newContainer, this);
            containerIdTextField.setValue(createdContainer.getContainerId());
            // }


        });
        buttonDeployProject.setEnabled(false);
        gridLogging.setCaption("Logging");
        gridLogging.setSizeFull();

        gridLogging.setColumnReorderingAllowed(false);


        // gridLogging.addColumn("Message", new com.vaadin.ui.renderers.TextRenderer()).setCaption("Message");


        this.addComponent(gridLogging);

    }

    public void refreshCombo() {
        spaceSelection.setItems(userConnected.getProjectResponses());

    }

    private void refreshList() {
        buttonDeployProject.setEnabled(true);
        KieConfigurationData config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        List<KieContainerInfo> listcontainers = kieServerRepositoryService.getContainerList(config.getKieserverUrl(), config.getKieserverUserName(), config.getKieserverPassword());
        containerIdTextField.setValue("");
        for (KieContainerInfo kieContainerInfo : listcontainers) {
            if (kieContainerInfo.getArtifactId() != null
                    && kieContainerInfo.getArtifactId().equals(projectArtifactIDTextField.getValue())
                    && kieContainerInfo.getArtifactId().equals(projectArtifactIDTextField.getValue())
                    && kieContainerInfo.getArtifactId().equals(projectArtifactIDTextField.getValue())) {
                containerIdTextField.setValue(kieContainerInfo.getContainerId());
            }

        }
        System.out.println("coucou");
    }

    public void addRow(String textToAdd) {
        logs.add(textToAdd);
        gridLogging.setItems(logs);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        spaceSelection.setItems(userConnected.getProjectResponses());
    }
}
