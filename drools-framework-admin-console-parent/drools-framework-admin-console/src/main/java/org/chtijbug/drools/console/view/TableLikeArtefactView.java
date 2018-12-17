package org.chtijbug.drools.console.view;

import com.vaadin.data.HasValue;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.chtijbug.drools.console.AddLog;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.KieServerRepositoryService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.guvnor.server.jaxrs.jaxb.Asset;
import org.guvnor.rest.client.ProjectResponse;

import java.util.*;

public class TableLikeArtefactView extends VerticalLayout implements AddLog, View {

    final private KieConfigurationData config;


    final private KieRepositoryService kieRepositoryService;

    final private KieServerRepositoryService kieServerRepositoryService;

    final private UserConnected userConnected;


    private Grid<Map<String, String>> assetListGrid;

    private ComboBox<ProjectResponse> spaceSelection;
    private Button deleteRow;

    private Button editRow;

    private Button duplicateRow;

    public TableLikeArtefactView(UserConnected userConnected) {

        this.kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);

        this.kieServerRepositoryService = AppContext.getApplicationContext().getBean(KieServerRepositoryService.class);

        this.userConnected = userConnected;

        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        Button button = new Button("Refresh");
        button.addClickListener((Button.ClickListener) event -> {
            this.refreshList();
        });
        this.addComponent(button);
        spaceSelection = new ComboBox("Project", userConnected.getProjectResponses());
        spaceSelection.setItemCaptionGenerator(ProjectResponse::getName);
        spaceSelection.addValueChangeListener((HasValue.ValueChangeListener<ProjectResponse>) valueChangeEvent -> {
            ProjectResponse response = (ProjectResponse) spaceSelection.getValue();
            spaceSelection.setSelectedItem(response);
            assetListGrid.addColumn(hashmap -> hashmap.get("title"));
            List<Asset> assets = kieRepositoryService.getListAssets(config.getKiewbUrl(), userConnected.getUserName(), userConnected.getUserPassword(), response.getSpaceName(), response.getName());
            List<Map<String, String>> rows = new ArrayList<>();
            for (Asset asset : assets) {
                if (asset.getTitle().endsWith(".template")
                        || asset.getTitle().endsWith(".gdst")) {
                    Map<String, String> line = new HashMap<>();
                    line.put("title", asset.getTitle());
                    rows.add(line);
                }
            }
            assetListGrid.setItems(rows);


        });
        this.addComponent(spaceSelection);
        HorizontalLayout actionButtons = new HorizontalLayout();
        this.addComponent(actionButtons);
        duplicateRow = new Button("Duplicate");
        actionButtons.addComponent(duplicateRow);
        editRow = new Button("Edit");
        actionButtons.addComponent(editRow);
        deleteRow = new Button("Delete");


        assetListGrid = new Grid("List of assets");
        assetListGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        assetListGrid.setSizeFull();

        this.addComponent(assetListGrid);
        editRow.addClickListener(clickEvent -> {
            Set<Map<String, String>> selectedElements = assetListGrid.getSelectedItems();
            if (selectedElements.toArray().length > 0) {
                String assetName = ((Map<String, String>) selectedElements.toArray()[0]).get("title");
                if (assetName != null) {
                    ProjectResponse response = (ProjectResponse) spaceSelection.getValue();
                    AssetEditView assetEditView = new AssetEditView(userConnected, response.getSpaceName(), response.getName(), assetName);
                    UI.getCurrent().getNavigator().addView("Asset-" + assetName, assetEditView);
                    UI.getCurrent().getNavigator().navigateTo("Asset-" + assetName);
                }
            }
        });

    }


    public void refreshList() {
        spaceSelection.setItems(userConnected.getProjectResponses());

    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        spaceSelection.setItems(userConnected.getProjectResponses());
    }

    @Override
    public void addRow(String textToAdd) {

    }
}
