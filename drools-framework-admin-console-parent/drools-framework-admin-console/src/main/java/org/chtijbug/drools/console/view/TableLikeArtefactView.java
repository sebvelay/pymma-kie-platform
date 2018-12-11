package org.chtijbug.drools.console.view;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;
import org.chtijbug.drools.console.AddLog;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.KieServerRepositoryService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.drools.guvnor.server.jaxrs.jaxb.Asset;
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

    private Table assetTable;

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


        assetTable = new Table("List of assets", assetBeanItemContainer);
        assetTable.setSelectable(true);
        assetTable.setSizeFull();
        assetTable.setMultiSelectMode(MultiSelectMode.SIMPLE);
        assetTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                if (itemClickEvent.isCtrlKey()) {
                    Asset selected = (Asset) itemClickEvent.getItemId();
                    System.out.println(itemClickEvent.getItemId().toString());
                }

            }
        });
        this.addComponent(assetTable);
        final Action actionDuplicate = new Action("Duplicate");
        final Action actionEdit = new Action("Edit");
        final Action actionDelete = new Action("Delete");
        assetTable.addActionHandler(new Action.Handler() {

            public void handleAction(Action action, Object sender,
                                     Object target) {
                if (action.equals(actionDuplicate)) {
                    Notification.show("Duplicate not yet implemented");
                } else if (action.equals(actionEdit)) {
                    Notification.show("Edit/modification not yet implemented");
                    Asset selected = (Asset) target;
                    ProjectResponse response = (ProjectResponse) spaceSelection.getValue();
                    AssetEditView assetEditView = new AssetEditView(userConnected, response.getSpaceName(), response.getName(), selected);
                    UI.getCurrent().getNavigator().addView("Asset-" + selected.getTitle(), assetEditView);
                    UI.getCurrent().getNavigator().navigateTo("Asset-" + selected.getTitle());
                } else if (action.equals(actionDelete)) {
                    Notification.show("Delete not yet implemented");
                }

            }

            public Action[] getActions(Object target, Object sender) {
                return new Action[]{actionDuplicate, actionEdit, actionDelete};
            }
        });

    }


    public void refreshList() {
        List<ProjectResponse> projectResponses = kieRepositoryService.getListSpaces2(config.getKiewbUrl(), userConnected.getUserName(), userConnected.getUserPassword());
        spaceContainer.removeAllItems();
        spaceContainer.addAll(projectResponses);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    @Override
    public void addRow(String textToAdd) {

    }
}
