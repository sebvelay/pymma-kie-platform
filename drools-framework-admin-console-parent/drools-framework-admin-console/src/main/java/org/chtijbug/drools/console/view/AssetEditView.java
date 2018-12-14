package org.chtijbug.drools.console.view;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.chtijbug.drools.console.AddLog;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.guvnor.server.jaxrs.jaxb.Asset;
import org.drools.workbench.models.datamodel.rule.InterpolationVariable;
import org.drools.workbench.models.guided.template.backend.RuleTemplateModelXMLPersistenceImpl;
import org.drools.workbench.models.guided.template.shared.TemplateModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AssetEditView extends VerticalLayout implements AddLog, View {

    final private KieRepositoryService kieRepositoryService;
    final private KieConfigurationData config;
    private UserConnected userConnected;
    private Asset assetToUpdate;
    private XmlMapper mapper = new XmlMapper();
    private String spaceName;
    private String projectName;
    private Grid gridAssetTable;
    private Button startUpdate;
    private Button commitUpdate;
    private Button undoUpdate;


    public AssetEditView(UserConnected userConnected, String spaceName, String projectName, Asset assetToUpdate) {
        this.userConnected = userConnected;
        this.assetToUpdate = assetToUpdate;
        this.spaceName = spaceName;
        this.projectName = projectName;
        this.kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);
        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        String assetContent = kieRepositoryService.getAssetSource(config.getKiewbUrl(), userConnected.getUserName(), userConnected.getUserPassword(), spaceName, projectName, assetToUpdate.getTitle());
        TemplateModel model = RuleTemplateModelXMLPersistenceImpl.getInstance().unmarshal(assetContent);


        HorizontalLayout actionButtons = new HorizontalLayout();
        this.addComponent(actionButtons);
        startUpdate = new Button("Update");
        actionButtons.addComponent(startUpdate);

        startUpdate.addClickListener((Button.ClickListener) event -> {
            startUpdate.setEnabled(false);
            commitUpdate.setEnabled(true);
            undoUpdate.setEnabled(true);
            gridAssetTable.setEditorEnabled(true);
            gridAssetTable.setReadOnly(false);

        });
        commitUpdate = new Button("Commit");
        commitUpdate.setEnabled(false);
        commitUpdate.addClickListener((Button.ClickListener) event -> {
            startUpdate.setEnabled(true);
            commitUpdate.setEnabled(false);
            undoUpdate.setEnabled(false);
            gridAssetTable.cancelEditor();
            gridAssetTable.setEditorEnabled(false);
            gridAssetTable.setReadOnly(true);


        });
        actionButtons.addComponent(commitUpdate);
        undoUpdate = new Button("undo");
        undoUpdate.setEnabled(false);
        undoUpdate.addClickListener((Button.ClickListener) event -> {
            startUpdate.setEnabled(true);
            commitUpdate.setEnabled(false);
            undoUpdate.setEnabled(false);
            gridAssetTable.cancelEditor();
            gridAssetTable.setEditorEnabled(false);
            gridAssetTable.cancelEditor();
            gridAssetTable.setReadOnly(true);
            fillTable(model);

        });
        actionButtons.addComponent(undoUpdate);
        InterpolationVariable[] variablesList = model.getInterpolationVariablesList();
        gridAssetTable = new Grid("Data");
        this.addComponent(gridAssetTable);
        gridAssetTable.setEditorEnabled(false);
        gridAssetTable.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridAssetTable.setReadOnly(true);
        for (InterpolationVariable i : variablesList) {
            if (i.getDataType().equals("String")) {
                gridAssetTable.addColumn(i.getVarName(), String.class);
            } else if (i.getDataType().equals("Date")) {
                gridAssetTable.addColumn(i.getVarName(), Date.class);
            } else {
                gridAssetTable.addColumn(i.getVarName(), String.class);
            }

        }

        fillTable(model);

        gridAssetTable.setSizeFull();
/**
        excelExporter = new ExcelExporter();
        excelExporter.setDateFormat("dd-MM-yyyy");
        excelExporter.setLocale(Locale.FRANCE);
 excelExporter.setTableToBeExported(gridAssetTable);
        excelExporter.setCaption("Export to Excel");
        actionButtons.addComponent(excelExporter);
        excelExporter.setEnabled(true);

 excelImportTable = new ExcelImportTable(gridAssetTable);
        excelImportTable.setLocale(Locale.FRANCE);
        actionButtons.addComponent(excelImportTable);
 **/
    }

    private void fillTable(TemplateModel model) {
        InterpolationVariable[] variablesList = model.getInterpolationVariablesList();
        DateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.FRANCE);
        String[][] contenuTable = model.getTableAsArray();
        Container dataSource = gridAssetTable.getContainerDataSource();
        dataSource.removeAllItems();
        for (int i = 0; i < model.getRowsCount(); i++) {
            String[] ligne = contenuTable[i];
            Object item = dataSource.addItem();
            Item row1 = dataSource.getItem(item);
            int k = 0;
            for (InterpolationVariable j : variablesList) {
                if (j.getDataType().equals("String")) {
                    row1.getItemProperty(j.getVarName()).setValue(ligne[k]);
                } else if (j.getDataType().equals("Date")) {
                    try {
                        row1.getItemProperty(j.getVarName()).setValue(format.parse(ligne[k]));
                    } catch (ParseException e) {
                        row1.getItemProperty(j.getVarName()).setValue(new Date());
                    }
                } else {
                    row1.getItemProperty(j.getVarName()).setValue(ligne[k]);
                }

                k++;
            }

        }

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    @Override
    public void addRow(String textToAdd) {

    }
}
