package org.chtijbug.drools.console.view;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializablePredicate;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.UserConnectedService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.console.vaadinComponent.ComponentPerso.DialogPerso;
import org.chtijbug.drools.console.vaadinComponent.leftMenu.Action.TemplatesAction;
import org.chtijbug.guvnor.server.jaxrs.jaxb.Asset;
import org.chtijbug.guvnor.server.jaxrs.model.PlatformProjectResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@StyleSheet("css/accueil.css")
public class TemplateView extends VerticalLayout {

    public static final String pageName="Templates";

    private KieConfigurationData config;

    private KieRepositoryService kieRepositoryService;

    private UserConnected userConnected;

    private UserConnectedService userConnectedService;

    private ListDataProvider<Asset> dataProvider;

    private Grid<Asset> assetListGrid;

    private TextField searchTemplate;

    private ConfigurableFilterDataProvider<Asset,Void,SerializablePredicate<Asset>> filterDataProvider;

    private TemplatesAction templatesAction;

    public TemplateView() {

        setClassName("template-content");

        dataProvider=new ListDataProvider<>(new ArrayList<>());
        filterDataProvider = dataProvider.withConfigurableFilter();

        this.kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);
        this.userConnectedService = AppContext.getApplicationContext().getBean(UserConnectedService.class);
        this.userConnected = userConnectedService.getUserConnected();
        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);

        assetListGrid = new Grid();
        assetListGrid.setClassName("templates-grid-perso");
        assetListGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

        Grid.Column<Asset> assetColumn=assetListGrid.addColumn(asset -> asset.getTitle());
        searchTemplate=new TextField("title");
        searchTemplate.setValueChangeMode(ValueChangeMode.EAGER);
        searchTemplate.addValueChangeListener(e -> {
            refreshtGrid(searchTemplate.getValue(), "title");
        });
        assetColumn.setHeader(searchTemplate);
        add(assetListGrid);

        assetListGrid.addSelectionListener(selectionEvent -> {
           if(assetListGrid.getSelectedItems()==null){
               templatesAction.getEdit().setEnabled(false);
           }else {
               templatesAction.getEdit().setEnabled(true);
           }
        });
    }

    public void setDataProvider(ComboBox<PlatformProjectResponse> spaceSelection){
        PlatformProjectResponse response = spaceSelection.getValue();
        List<Asset> tmp = kieRepositoryService.getListAssets(config.getKiewbUrl(), userConnected.getUserName(), userConnected.getUserPassword(), response.getSpaceName(), response.getName());
        List<Asset> result = new ArrayList<>();
        for (Asset asset : tmp) {
            if (asset.getTitle().endsWith(".template")
                    || asset.getTitle().endsWith(".gdst")) {
                result.add(asset);
            }
        }
        dataProvider=new ListDataProvider<>(result);
        filterDataProvider = dataProvider.withConfigurableFilter();
        assetListGrid.setDataProvider(filterDataProvider);
        reinitFilter();
    }

    public void refreshList(ComboBox<PlatformProjectResponse> spaceSelection) {
        spaceSelection.setItems(userConnected.getProjectResponses());
    }
    public void edit(ComboBox<PlatformProjectResponse> spaceSelection){
        Set<Asset> selectedElements = assetListGrid.getSelectedItems();
        if (selectedElements.toArray().length > 0) {
            Optional<Asset> assetOptional = selectedElements.stream().findFirst();
            if (assetOptional.isPresent()) {
                String assetName = assetOptional.get().getTitle();
                if (assetName != null) {
                    PlatformProjectResponse response = spaceSelection.getValue();
                    userConnectedService.addAssetToSession(assetName);
                    userConnectedService.addProjectToSession(response.getName());
                    userConnectedService.addSpaceToSession(response.getSpaceName());
                    DialogPerso dialog = new DialogPerso();

                    dialog.add(new EditTemplateView(dialog, assetName));
                    dialog.open();
                }
            }
        }
    }
    private void refreshtGrid(String value,String type){

        filterDataProvider.setFilter(filterGrid(value.toUpperCase(),type));
        assetListGrid.getDataProvider().refreshAll();
    }
    private SerializablePredicate<Asset> filterGrid(String value, String type){
        SerializablePredicate<Asset> columnPredicate = null;
        if(value.equals(" ")||type.equals(" ")){
            columnPredicate = asset -> (true);
        }else {
            if (type.equals("Asset Title")) {
                columnPredicate = asset -> (asset.getTitle().contains(value));
            }
        }
        return columnPredicate;
    }

    public UserConnectedService getUserConnectedService() {
        return userConnectedService;
    }

    public void setUserConnectedService(UserConnectedService userConnectedService) {
        this.userConnectedService = userConnectedService;
    }

    public void duplicate(){}

    public void reinitFilter(){
        searchTemplate.setValue("");
    }

    public TemplatesAction getTemplatesAction() {
        return templatesAction;
    }

    public void setTemplatesAction(TemplatesAction templatesAction) {
        this.templatesAction = templatesAction;
    }
}
