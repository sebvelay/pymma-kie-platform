package org.chtijbug.drools.console.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.KieServerRepositoryService;
import org.chtijbug.drools.console.service.UserConnectedService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.console.vaadinComponent.Squelette.SqueletteComposant;
import org.chtijbug.guvnor.server.jaxrs.jaxb.Asset;
import org.chtijbug.guvnor.server.jaxrs.model.PlatformProjectResponse;

import javax.annotation.PostConstruct;
import java.util.*;

@Route("AssetUpdate")
@StyleSheet("css/accueil.css")
public class TableLikeArtefactView extends SqueletteComposant {

    private KieConfigurationData config;


    private KieRepositoryService kieRepositoryService;

    private KieServerRepositoryService kieServerRepositoryService;

    private UserConnected userConnected;


    private Grid<Map<String, String>> assetListGrid;

    private ComboBox<PlatformProjectResponse> spaceSelection;
    private Button deleteRow;

    private Button editRow;

    private Button duplicateRow;
    private UserConnectedService userConnectedService;

    public TableLikeArtefactView() {

        this.kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);
        this.userConnectedService = AppContext.getApplicationContext().getBean(UserConnectedService.class);
        this.kieServerRepositoryService = AppContext.getApplicationContext().getBean(KieServerRepositoryService.class);

        this.userConnected = userConnectedService.getUserConnected();

        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        VerticalLayout verticalLayout = new VerticalLayout();
        Button button = new Button("Refresh");
        button.addClickListener(event -> {
            this.refreshList();
        });
        verticalLayout.add(button);
        spaceSelection = new ComboBox("Project", userConnected.getProjectResponses());
        spaceSelection.setItemLabelGenerator(PlatformProjectResponse::getName);
        spaceSelection.addValueChangeListener(valueChangeEvent -> {
            PlatformProjectResponse response = (PlatformProjectResponse) spaceSelection.getValue();
            //spaceSelection.setSelectedItem(response);
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
        verticalLayout.add(spaceSelection);
        HorizontalLayout actionButtons = new HorizontalLayout();
        verticalLayout.add(actionButtons);
        duplicateRow = new Button("Duplicate");
        actionButtons.add(duplicateRow);
        editRow = new Button("Edit");
        actionButtons.add(editRow);
        deleteRow = new Button("Delete");


        assetListGrid = new Grid();
        assetListGrid.setClassName("grid-perso");
        assetListGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        assetListGrid.setSizeFull();

        verticalLayout.add(assetListGrid);
        editRow.addClickListener(clickEvent -> {
            Set<Map<String, String>> selectedElements = assetListGrid.getSelectedItems();
            if (selectedElements.toArray().length > 0) {
                String assetName = ((Map<String, String>) selectedElements.toArray()[0]).get("title");
                if (assetName != null) {
                    PlatformProjectResponse response =  spaceSelection.getValue();
                    userConnectedService.addAssetToSession(assetName);
                    userConnectedService.addProjectToSession(response.getName());
                    userConnectedService.addSpaceToSession(response.getSpaceName());
                    getUI().get().navigate("AssetDetail");
                    // AssetEditView assetEditView = new AssetEditView(userConnected, response.getSpaceName(), response.getName(), assetName);

                }
            }
        });
        getInfoPage().add(verticalLayout);
    }


    public void refreshList() {
        spaceSelection.setItems(userConnected.getProjectResponses());

    }


}
