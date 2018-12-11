package org.chtijbug.drools.console.view;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;
import org.chtijbug.drools.console.AddLog;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.KieServerRepositoryService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.*;
import org.chtijbug.drools.console.service.util.AppContext;
import org.guvnor.rest.client.ProjectResponse;

import java.util.List;

public class DeploymentView extends VerticalLayout implements AddLog, View {


    final private Table table = new Table();
    final private Button buttonDeployProject = new Button("Deploy project");
    final private KieConfigurationData config;
    final private BeanItemContainer<ProjectResponse> spaceContainer;

    final private TextField projectArtifactIDTextField = new TextField("Project Artifact ID");
    final private TextField projectGroupIDTextField = new TextField("Project Group ID");
    final private TextField projectVersionTextField = new TextField("Project Version");
    final private TextField containerIdTextField = new TextField("Container ID");

    final private KieRepositoryService kieRepositoryService;

    final private KieServerRepositoryService kieServerRepositoryService;

    final private UserConnected userConnected;

    public DeploymentView(UserConnected userConnected) {

        this.kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);

        this.kieServerRepositoryService = AppContext.getApplicationContext().getBean(KieServerRepositoryService.class);

        this.userConnected = userConnected;
        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        Button button = new Button("Refresh");
        button.addStyleName(Runo.BUTTON_SMALL);

        button.addClickListener((Button.ClickListener) event -> {
            this.refreshCombo();
            this.refreshList();
        });

        this.addComponent(button);


        spaceContainer =
                new BeanItemContainer<ProjectResponse>(ProjectResponse.class);
        ComboBox spaceSelection = new ComboBox("Project", spaceContainer);

        spaceSelection.setNullSelectionAllowed(false);

        spaceSelection.setItemCaptionPropertyId("name");

        spaceSelection.setNewItemsAllowed(false);
        spaceSelection.setImmediate(true);
        spaceSelection.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                ProjectResponse response = (ProjectResponse) spaceSelection.getValue();
                projectArtifactIDTextField.setValue(response.getName());
                projectGroupIDTextField.setValue(response.getGroupId());
                projectVersionTextField.setValue(response.getVersion());
                refreshList();
            }
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
        buttonDeployProject.addStyleName(Runo.BUTTON_SMALL);


        buttonDeployProject.addClickListener((Button.ClickListener) event -> {
            //  if (containerIdTextField.getValue() != null
            //      && containerIdTextField.getValue().length() > 0) {
            table.removeAllItems();

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
        table.setCaption("Logging");
        table.setSizeFull();
        table.setPageLength(0);
        table.setSelectable(false);
        table.setColumnCollapsingAllowed(false);
        table.setColumnReorderingAllowed(false);
        table.setImmediate(true);
        table.setNullSelectionAllowed(false);
        table.setColumnHeaderMode(Table.ColumnHeaderMode.ID);
        Container container = new IndexedContainer();
        container.addContainerProperty("Message", String.class, "none");
        table.setContainerDataSource(container);
        this.addComponent(table);

    }

    public void refreshCombo() {
        List<ProjectResponse> projectResponses = kieRepositoryService.getListSpaces2(config.getKiewbUrl(), userConnected.getUserName(), userConnected.getUserPassword());
        spaceContainer.removeAllItems();

        spaceContainer.addAll(projectResponses);

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
        int nbRows = table.getContainerDataSource().getItemIds().size() + 1;
        Item item = table.getContainerDataSource().addItem(nbRows);
        if (item != null) {
            Property<String> nameProperty =
                    item.getItemProperty("Message");

            nameProperty.setValue(textToAdd);
            table.setContainerDataSource(table.getContainerDataSource());
        } else {
            System.out.println("null");
        }

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
