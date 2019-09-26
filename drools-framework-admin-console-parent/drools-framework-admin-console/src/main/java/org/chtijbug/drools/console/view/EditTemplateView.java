package org.chtijbug.drools.console.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.server.StreamResource;
import org.chtijbug.drools.console.service.ExcelServiceGuidedRule;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.UserConnectedService;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.console.vaadinComponent.ComponentPerso.DialogPerso;
import com.vaadin.flow.component.html.Label;
import org.chtijbug.drools.console.vaadinComponent.componentView.AssetEdit;
import org.vaadin.olli.FileDownloadWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class EditTemplateView extends VerticalLayout {

    private Button exportExcel;

    private Upload importExcel;

    private Label title;

    private AssetEdit assetEdit;

    private ExcelServiceGuidedRule excelService;

    private KieRepositoryService kieRepositoryService;

    private KieConfigurationData config;

    private UserConnectedService userConnectedService;

    public EditTemplateView(DialogPerso dialogPerso,String nameTemplate){

        excelService= AppContext.getApplicationContext().getBean(ExcelServiceGuidedRule.class);
        kieRepositoryService=AppContext.getApplicationContext().getBean(KieRepositoryService.class);
        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        this.userConnectedService = AppContext.getApplicationContext().getBean(UserConnectedService.class);

        dialogPerso.getClose().setVisible(false);


        MemoryBuffer fileBuffer = new MemoryBuffer();


        importExcel=new Upload(fileBuffer);
        importExcel.setDropLabel(new Span("drag and Drop Excel file here"));
        importExcel.setClassName("menu-upload");
        importExcel.setId("exampleupload");

        dialogPerso.getBar().add(importExcel);

        importExcel.addSucceededListener(succeededEvent -> {

            if(!succeededEvent.getFileName().contains("xlsx")){

                Notification.show("The file is incompatible, it must be in xlsx format");

            }else {

                try {
                    List<HashMap<String, Object>> objects= excelService.importExcel(fileBuffer.getInputStream());
                    if(objects!=null&&objects.size()>0){
                        if(objects.get(0).values().size()!=assetEdit.getColumns().size()){
                            Notification.show("Unable to add columns with the excel import for the moment");
                        }else {
                            remove(assetEdit);
                            assetEdit = new AssetEdit(objects);
                            add(assetEdit);

                            kieRepositoryService.updateAssetSource(config.getKiewbUrl(),
                                    userConnectedService.getUserConnected().getUserName(),
                                    userConnectedService.getUserConnected().getUserPassword(),
                                    userConnectedService.getSpace(),
                                    userConnectedService.getProject(),
                                    userConnectedService.getAsset(),objects);
                        }
                    }else {
                        Notification.show("illegible or empty document");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Notification.show("The file is incompatible, it must be in xlsx format");
                }
            }
        });
        importExcel.addFailedListener(failedEvent -> {
            Notification.show("Error in the upload, please start again with another file");
        });

        FileDownloadWrapper fileDownloadWrapper=new FileDownloadWrapper(
                new StreamResource("fsdfs.xlsx",
                        ()->excelService.exportExcel(nameTemplate)));

        exportExcel=new Button("Export excel");
        fileDownloadWrapper.wrapComponent(exportExcel);
        exportExcel.setClassName("menu-button-asset-edit");



        dialogPerso.getBar().add(fileDownloadWrapper);


        title=new Label(nameTemplate);
        title.setClassName("creation-runtime-title");

        add(title);

        assetEdit=new AssetEdit();
        add(assetEdit);

    }
}
