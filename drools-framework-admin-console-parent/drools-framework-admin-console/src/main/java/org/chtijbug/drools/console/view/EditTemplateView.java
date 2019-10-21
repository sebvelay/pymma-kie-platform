package org.chtijbug.drools.console.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.server.StreamResource;
import org.chtijbug.drools.console.service.DecisionTableExcelService;
import org.chtijbug.drools.console.service.GuidedRuleTemplateExcelService;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.UserConnectedService;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.console.vaadinComponent.ComponentPerso.DialogPerso;
import org.chtijbug.drools.console.vaadinComponent.componentView.AssetEdit;
import org.chtijbug.drools.console.vaadinComponent.componentView.service.dtmodel.ColumnDefinition;
import org.chtijbug.drools.console.vaadinComponent.componentView.service.dtmodel.DecisionTable;
import org.chtijbug.drools.console.vaadinComponent.componentView.service.dtmodel.GuidedException;
import org.chtijbug.drools.console.vaadinComponent.componentView.service.dtmodel.Row;
import org.drools.workbench.models.datamodel.rule.InterpolationVariable;
import org.drools.workbench.models.guided.dtable.backend.GuidedDTXMLPersistence;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.drools.workbench.models.guided.template.backend.RuleTemplateModelXMLPersistenceImpl;
import org.drools.workbench.models.guided.template.shared.TemplateModel;
import org.vaadin.olli.FileDownloadWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EditTemplateView extends VerticalLayout {
    private static int GuidedRuleTemplate = 1;
    private static int DecisionTable = 2;

    private int tableType;

    private Button exportExcel;

    private Upload importExcel;

    private Label title;

    private Button saveButton;


    private AssetEdit assetEdit;

    private GuidedRuleTemplateExcelService guidedRuleTemplateExcelService;

    private DecisionTableExcelService decisionTableExcelService;

    private KieRepositoryService kieRepositoryService;

    private KieConfigurationData config;

    private UserConnectedService userConnectedService;

    private String assetName;

    public String getAssetContent(){
        if (tableType==GuidedRuleTemplate){
            return guidedRuleTemplateExcelService.getAssetContent();
        }else{
            return decisionTableExcelService.getAssetContent();
        }
    }

    public EditTemplateView(DialogPerso dialogPerso, String nameTemplate) {
        this.assetName=nameTemplate;
        if (nameTemplate.contains(".gdst") == false) {
            guidedRuleTemplateExcelService = AppContext.getApplicationContext().getBean(GuidedRuleTemplateExcelService.class);
            tableType = GuidedRuleTemplate;
        } else {
            decisionTableExcelService = AppContext.getApplicationContext().getBean(DecisionTableExcelService.class);
            tableType = DecisionTable;
        }
        kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);
        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        this.userConnectedService = AppContext.getApplicationContext().getBean(UserConnectedService.class);

        dialogPerso.getClose().setVisible(false);


        MemoryBuffer fileBuffer = new MemoryBuffer();


        importExcel = new Upload(fileBuffer);
        importExcel.setDropLabel(new Span("drag and Drop Excel file here"));
        importExcel.setClassName("menu-upload");
        importExcel.setId("exampleupload");

        dialogPerso.getBar().add(importExcel);

        importExcel.addSucceededListener(succeededEvent -> {

            if (!succeededEvent.getFileName().contains("xlsx")) {

                Notification.show("The file is incompatible, it must be in xlsx format");

            } else {

                try {
                    if (tableType == GuidedRuleTemplate) {
                        List<HashMap<String, Object>> objects = guidedRuleTemplateExcelService.importExcel(fileBuffer.getInputStream());
                        if (objects != null && objects.size() > 0) {
                            if (objects.get(0).values().size() != assetEdit.getColumns().size()) {
                                Notification.show("Unable to add columns with the excel import for the moment");
                            } else {
                                remove(assetEdit);
                                assetEdit = new AssetEdit(objects);
                                add(assetEdit);
                                String assetSource=this.getAssetContent();
                                TemplateModel model = RuleTemplateModelXMLPersistenceImpl.getInstance().unmarshal(assetSource);
                                int id=model.getColsCount();
                                model.clearRows();
                                for (Map<String,Object> line : objects){
                                    String[] cols =new String[model.getColsCount()];
                                    int k=0;
                                    for (InterpolationVariable interpolationVariable : model.getInterpolationVariablesList()){

                                        Object o = line.get(interpolationVariable.getVarName());
                                        cols[k]=o.toString();
                                        k++;
                                    }
                                    model.addRow(cols);
                                }

                                model.setIdCol(id);
                                assetSource = RuleTemplateModelXMLPersistenceImpl.getInstance().marshal(model);
                                kieRepositoryService.updateAssetSource(config.getKiewbUrl(),
                                        userConnectedService.getUserConnected().getUserName(),
                                        userConnectedService.getUserConnected().getUserPassword(),
                                        userConnectedService.getSpace(),
                                        userConnectedService.getProject(),
                                        userConnectedService.getAsset(), assetSource);
                            }
                        } else {
                            Notification.show("illegible or empty document");
                        }
                    } else {
                        List<HashMap<String, Object>> objects = decisionTableExcelService.importExcel(fileBuffer.getInputStream());
                        if (objects != null && objects.size() > 0) {
                            Notification.show("Unable to add columns with the excel import for the moment");
                        }else{
                            remove(assetEdit);
                            assetEdit = new AssetEdit(objects);
                            add(assetEdit);
                            String assetSource=this.getAssetContent();
                            GuidedDecisionTable52 model = GuidedDTXMLPersistence.getInstance().unmarshal(assetSource);
                            org.chtijbug.drools.console.vaadinComponent.componentView.service.dtmodel.DecisionTable decisionTable = new DecisionTable(model);
                            decisionTable.getRows().clear();
                            List<ColumnDefinition> columnDefinitions = decisionTable.getColumnDefinitionList();
                            int k=0;
                            for (HashMap<String, Object> line : objects){
                                Row row = decisionTable.createEmptyRow(k);
                                for (ColumnDefinition columnDefinition : columnDefinitions){
                                    line.get(columnDefinition.getHeader());

                                    }
                                }
                                k++;
                            }


                            //row.
                        }

                } catch (IOException e) {
                    Notification.show("The file is incompatible, it must be in xlsx format");
                } catch (GuidedException e) {
                    Notification.show("error "+e.getMessage());
                }
            }
        });
        importExcel.addFailedListener(failedEvent -> {
            Notification.show("Error in the upload, please start again with another file");
        });
        FileDownloadWrapper fileDownloadWrapper = null;
        if (tableType == GuidedRuleTemplate) {
            fileDownloadWrapper = new FileDownloadWrapper(
                    new StreamResource(nameTemplate + ".xlsx",
                            () -> guidedRuleTemplateExcelService.exportExcel(nameTemplate)));
        } else {
            fileDownloadWrapper = new FileDownloadWrapper(
                    new StreamResource(nameTemplate + ".xlsx",
                            () -> decisionTableExcelService.exportExcel(nameTemplate)));
        }
        exportExcel = new Button("Export excel");
        fileDownloadWrapper.wrapComponent(exportExcel);
        exportExcel.setClassName("menu-button-asset-edit");


        dialogPerso.getBar().add(fileDownloadWrapper);


        title = new Label(nameTemplate);
        title.setClassName("creation-runtime-title");

        add(title);

        assetEdit = new AssetEdit();

        add(assetEdit);
        saveButton = new Button("Save");
        add(saveButton);
        saveButton.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                System.out.println("coucou");
            }
        });

    }
}
