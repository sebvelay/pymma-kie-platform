package org.chtijbug.drools.console.view;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;
import org.chtijbug.drools.console.AddLog;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.KieServerRepositoryService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.guvnor.server.jaxrs.jaxb.Asset;
import org.guvnor.rest.client.ProjectResponse;

import java.util.List;

public class TableLikeArtefactView extends VerticalLayout implements AddLog, View {

    final private KieConfigurationData config;
    final private BeanItemContainer<ProjectResponse> spaceContainer =
            new BeanItemContainer<ProjectResponse>(ProjectResponse.class);
    ;


    final private KieRepositoryService kieRepositoryService;

    final private KieServerRepositoryService kieServerRepositoryService;

    final private UserConnected userConnected;

    final private BeanItemContainer<Asset> assetBeanItemContainer = new BeanItemContainer<>(Asset.class);

    private Grid assetListGrid;


    private Button deleteRow;

    private Button editRow;

    private Button duplicateRow;

    public TableLikeArtefactView(UserConnected userConnected) {

        this.kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);

        this.kieServerRepositoryService = AppContext.getApplicationContext().getBean(KieServerRepositoryService.class);

        this.userConnected = userConnected;

        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        Button button = new Button("Refresh");
        button.addStyleName(Runo.BUTTON_SMALL);

        button.addClickListener((Button.ClickListener) event -> {
            this.refreshList();
        });

        this.addComponent(button);


        ComboBox spaceSelection = new ComboBox("Project", spaceContainer);

        spaceSelection.setNullSelectionAllowed(false);

        spaceSelection.setItemCaptionPropertyId("name");

        spaceSelection.setNewItemsAllowed(false);
        spaceSelection.setImmediate(true);
        spaceSelection.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                ProjectResponse response = (ProjectResponse) spaceSelection.getValue();
                assetBeanItemContainer.removeAllItems();
                List<Asset> assets = kieRepositoryService.getListAssets(config.getKiewbUrl(), userConnected.getUserName(), userConnected.getUserPassword(), response.getSpaceName(), response.getName());
                for (Asset asset : assets) {
                    if (asset.getTitle().endsWith(".template")
                            || asset.getTitle().endsWith(".gdst")) {
                        assetBeanItemContainer.addBean(asset);
                    }
                }

            }
        });
        this.addComponent(spaceSelection);
        HorizontalLayout actionButtons = new HorizontalLayout();
        this.addComponent(actionButtons);
        duplicateRow = new Button("Duplicate");
        actionButtons.addComponent(duplicateRow);
        editRow = new Button("Edit");
        actionButtons.addComponent(editRow);
        deleteRow = new Button("Delete");
        actionButtons.addComponent(deleteRow);
        assetBeanItemContainer.removeContainerProperty("author");
        assetBeanItemContainer.removeContainerProperty("binaryLink");
        assetBeanItemContainer.removeContainerProperty("sourceLink");
        assetBeanItemContainer.removeContainerProperty("refLink");
        //assetBeanItemContainer.removeContainerProperty("directory");
        assetBeanItemContainer.removeContainerProperty("comment");
        assetBeanItemContainer.removeContainerProperty("content");
        assetBeanItemContainer.removeContainerProperty("description");
        assetBeanItemContainer.removeContainerProperty("binaryContentAttachmentFileName");
        assetBeanItemContainer.removeContainerProperty("published");
        assetBeanItemContainer.removeContainerProperty("metadata");


        assetListGrid = new Grid("List of assets", assetBeanItemContainer);
        assetListGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        assetListGrid.setSizeFull();

        this.addComponent(assetListGrid);
        editRow.addClickListener(clickEvent -> {
            Asset selected = (Asset) assetListGrid.getSelectedRow();
            if (selected != null) {
                ProjectResponse response = (ProjectResponse) spaceSelection.getValue();
                AssetEditView assetEditView = new AssetEditView(userConnected, response.getSpaceName(), response.getName(), selected);
                UI.getCurrent().getNavigator().addView("Asset-" + selected.getTitle(), assetEditView);
                UI.getCurrent().getNavigator().navigateTo("Asset-" + selected.getTitle());
            }
        });

    }


    public void refreshList() {
        spaceContainer.removeAllItems();
        spaceContainer.addAll(userConnected.getProjectResponses());
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        spaceContainer.removeAllItems();
        spaceContainer.addAll(userConnected.getProjectResponses());
    }

    @Override
    public void addRow(String textToAdd) {

    }
}
