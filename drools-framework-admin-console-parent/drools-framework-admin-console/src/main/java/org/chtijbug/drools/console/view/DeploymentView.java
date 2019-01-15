package org.chtijbug.drools.console.view;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.chtijbug.drools.console.AddLog;
import org.chtijbug.drools.console.DroolsAdminConsoleMainView;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.KieServerRepositoryService;
import org.chtijbug.drools.console.service.UserConnectedService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.*;
import org.chtijbug.drools.console.service.util.AppContext;
import org.guvnor.rest.client.ProjectResponse;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.ReleaseId;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Route("Deployment")
public class DeploymentView extends DroolsAdminConsoleMainView implements AddLog {


    final private Grid<List<String>> gridLogging = new Grid();
    final private Button buttonDeployProject = new Button("Deploy project");
    private KieConfigurationData config;
    private ComboBox<ProjectResponse> spaceSelection;

    final private TextField projectArtifactIDTextField = new TextField("Project Artifact ID");
    final private TextField projectGroupIDTextField = new TextField("Project Group ID");
    final private TextField projectVersionTextField = new TextField("Project Version");
    final private TextField containerIdTextField = new TextField("Container ID");

    private KieRepositoryService kieRepositoryService;

    private KieServerRepositoryService kieServerRepositoryService;
    private UserConnectedService userConnectedService;
    private UserConnected userConnected;
    private List<String> logs = new ArrayList<>();

    public DeploymentView() {
        super();
    }

    @PostConstruct
    public void buildUI() {

        this.kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);
        this.userConnectedService = AppContext.getApplicationContext().getBean(UserConnectedService.class);
        this.kieServerRepositoryService = AppContext.getApplicationContext().getBean(KieServerRepositoryService.class);

        this.userConnected = userConnectedService.getUserConnected();

        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        VerticalLayout verticalLayout = new VerticalLayout();
        Button button = new Button("Refresh");
        //  button.addStyleName(Runo.BUTTON_SMALL);

        button.addClickListener(event -> {
            this.refreshCombo();
            this.refreshList();
        });

        verticalLayout.add(button);


        spaceSelection = new ComboBox("Project", userConnected.getProjectResponses());
        spaceSelection.setItemLabelGenerator(ProjectResponse::getName);
        spaceSelection.addValueChangeListener(valueChangeEvent -> {
            ProjectResponse response = (ProjectResponse) spaceSelection.getValue();
            projectArtifactIDTextField.setValue(response.getName());
            projectGroupIDTextField.setValue(response.getGroupId());
            projectVersionTextField.setValue(response.getVersion());
            refreshList();
        });


        verticalLayout.add(spaceSelection);

        projectArtifactIDTextField.setEnabled(false);
        projectGroupIDTextField.setEnabled(false);
        projectVersionTextField.setEnabled(false);
        containerIdTextField.setEnabled(false);
        verticalLayout.add(projectArtifactIDTextField);
        verticalLayout.add(projectGroupIDTextField);
        verticalLayout.add(projectVersionTextField);
        verticalLayout.add(containerIdTextField);

        buttonDeployProject.setEnabled(false);
        verticalLayout.add(buttonDeployProject);
        // buttonDeployProject.addStyleName(Runo.BUTTON_SMALL);


        buttonDeployProject.addClickListener(event -> {

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
            KieContainerResource newContainer = new KieContainerResource();
            newContainer.setContainerId(containerIdTextField.getValue());
            newContainer.setReleaseId(new ReleaseId());
            newContainer.getReleaseId().setArtifactId(projectArtifactIDTextField.getValue());
            newContainer.getReleaseId().setGroupId(projectGroupIDTextField.getValue());
            newContainer.getReleaseId().setVersion(projectVersionTextField.getValue());
            KieContainerInfo createdContainer = kieServerRepositoryService.createContainer(config.getKieserverUrl(), config.getKieserverUserName(), config.getKieserverPassword(), projectArtifactIDTextField.getValue(), newContainer, this);
            containerIdTextField.setValue(createdContainer.getContainerId());
            refreshList();


        });
        buttonDeployProject.setEnabled(false);
        gridLogging.setSizeFull();

        gridLogging.setColumnReorderingAllowed(false);


        // gridLogging.addColumn("Message", new com.vaadin.ui.renderers.TextRenderer()).setCaption("Message");


        verticalLayout.add(gridLogging);
        setActionView(verticalLayout);

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


}
