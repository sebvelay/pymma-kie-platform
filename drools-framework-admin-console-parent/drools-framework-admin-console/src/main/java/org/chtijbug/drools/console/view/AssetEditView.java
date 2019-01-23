package org.chtijbug.drools.console.view;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.UserConnectedService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.console.vaadinComponent.Squelette.SqueletteComposant;
import org.drools.workbench.models.datamodel.rule.InterpolationVariable;
import org.drools.workbench.models.guided.template.backend.RuleTemplateModelXMLPersistenceImpl;
import org.drools.workbench.models.guided.template.shared.TemplateModel;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Route("AssetDetail")
@StyleSheet("css/accueil.css")
public class AssetEditView extends SqueletteComposant {

    private KieRepositoryService kieRepositoryService;
    private KieConfigurationData config;
    private UserConnected userConnected;
    private String assetToUpdate;
    private XmlMapper mapper = new XmlMapper();

    private Grid<Map<String, Object>> gridAssetTable;
    private Button startUpdate;
    private Button commitUpdate;
    private Button undoUpdate;
    private UserConnectedService userConnectedService;
    private String spaceName;
    private String projectName;

    public AssetEditView() {

        this.kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);
        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        this.userConnectedService = AppContext.getApplicationContext().getBean(UserConnectedService.class);
        this.userConnected = userConnectedService.getUserConnected();
        this.assetToUpdate = userConnectedService.getAsset();

        this.spaceName = userConnectedService.getSpace();
        this.projectName = userConnectedService.getProject();
        String assetContent = kieRepositoryService.getAssetSource(config.getKiewbUrl(), userConnected.getUserName(), userConnected.getUserPassword(), spaceName, projectName, assetToUpdate);
        TemplateModel model = RuleTemplateModelXMLPersistenceImpl.getInstance().unmarshal(assetContent);


        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout actionButtons = new HorizontalLayout();
        verticalLayout.add(actionButtons);
        startUpdate = new Button("Update");
        actionButtons.add(startUpdate);

        startUpdate.addClickListener(event -> {
            startUpdate.setEnabled(false);
            commitUpdate.setEnabled(true);
            undoUpdate.setEnabled(true);
            gridAssetTable.setEnabled(true);
            //gridAssetTable.getEditor().setEnabled(true);
        });
        commitUpdate = new Button("Commit");
        commitUpdate.setEnabled(false);
        commitUpdate.addClickListener(event -> {
            startUpdate.setEnabled(true);
            commitUpdate.setEnabled(false);
            undoUpdate.setEnabled(false);
            // gridAssetTable.getEditor().setEnabled(false);

        });
        actionButtons.add(commitUpdate);
        undoUpdate = new Button("undo");
        undoUpdate.setEnabled(false);
        undoUpdate.addClickListener(event -> {
            startUpdate.setEnabled(true);
            commitUpdate.setEnabled(false);
            undoUpdate.setEnabled(false);
            //  gridAssetTable.getEditor().setEnabled(false);
            fillTable(model);

        });
        actionButtons.add(undoUpdate);
        InterpolationVariable[] variablesList = model.getInterpolationVariablesList();
        gridAssetTable = new Grid<>();
        verticalLayout.add(gridAssetTable);
        gridAssetTable.setClassName("grid-perso");
        gridAssetTable.setSelectionMode(Grid.SelectionMode.SINGLE);
        Binder<Map<String, Object>> binder = new Binder<>();
        gridAssetTable.getEditor().setBinder(binder);


        Map<String, Object> initRow = new HashMap<>();
        for (InterpolationVariable i : variablesList) {
            gridAssetTable.addColumn(hashmap -> hashmap.get(i.getVarName()));
            /**
            if (i.getDataType().equals("String")) {
                Binder.Binding<Map<String, Object>, String> b = createTextField(i.getVarName(), binder);
                initRow.put(i.getVarName(), i.getVarName());
             gridAssetTable.addColumn(hashmap -> hashmap.get(i.getVarName()),  new ComponentRenderer(()).setEditorBinding(b).setCaption(i.getVarName());

            } else if (i.getDataType().equals("Date")) {
                Binder.Binding<Map<String, Object>, LocalDate> b = createDateField(i.getVarName(), binder);
                initRow.put(i.getVarName(), new Date());
                gridAssetTable.addColumn(hashmap -> toDate((String) hashmap.get(i.getVarName())), new DateRenderer()).setId(i.getVarName()).setEditorBinding(b).setCaption(i.getVarName());
            } else {
                Binder.Binding<Map<String, Object>, String> b = createTextField(i.getVarName(), binder);
                initRow.put(i.getVarName(), i.getVarName());
                gridAssetTable.addColumn(hashmap -> hashmap.get(i.getVarName()), new com.vaadin.ui.renderers.TextRenderer()).setId(i.getVarName()).setEditorBinding(b).setCaption(i.getVarName());
            }
             **/
        }
        binder.setBean(giveInitRow(model));
        fillTable(model);
        gridAssetTable.setSizeFull();
        getInfoPage().add(verticalLayout);
/**
 *
 */
    }

    private Date toDate(String dateString) {
        DateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.FRANCE);
        Date result = null;
        try {
            result = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Binder.Binding<Map<String, Object>, String> createTextField(String code, Binder<Map<String, Object>> binder) {
        TextField tf = new TextField();
        //tf.setWidth(250, Plotter.Unit.PIXELS);

        //bind element

        Binder.Binding<Map<String, Object>, String> binding = binder.forField(tf).bind(// getter
                hash -> {
                    return (String) hash.get(code);
                },
                //setter
                (hash, fieldValue) -> {
                    hash.put(code, fieldValue);
                });
        return binding;

    }

    private Binder.Binding<Map<String, Object>, LocalDate> createDateField(String code, Binder<Map<String, Object>> binder) {
        DatePicker tf = new DatePicker();
        //  tf.setWidth(250, Unit.PIXELS);

        //bind element

        Binder.Binding<Map<String, Object>, LocalDate> binding = binder.forField(tf).bind(// getter
                hash -> {
                    return toLocalDate(toDate((String) hash.get(code)));
                },
                //setter
                (hash, fieldValue) -> {
                    hash.put(code, toString(fieldValue));
                    gridAssetTable.getDataProvider().refreshAll();
                });
        return binding;

    }

    private String toString(LocalDate input) {
        String result = null;

        DateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.FRANCE);
        Date theDate = Date.from(input.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
        result = format.format(theDate);
        return result;
    }

    private LocalDate toLocalDate(Date input) {
        LocalDate result = null;
        if (input != null) {
            result = Instant.ofEpochMilli(input.getTime()).atZone(ZoneId.of("Europe/Paris")).toLocalDate();
        }
        return result;
    }

    private Map<String, Object> giveInitRow(TemplateModel model) {
        InterpolationVariable[] variablesList = model.getInterpolationVariablesList();
        Map<String, Object> newRow = new HashMap<>();
        String[][] contenuTable = model.getTableAsArray();
        if (contenuTable.length > 0) {
            String[] ligne = contenuTable[0];
            int k = 0;
            for (InterpolationVariable j : variablesList) {
                newRow.put(j.getVarName(), ligne[k]);
                k++;
            }
        }
        return newRow;
    }

    private void fillTable(TemplateModel model) {
        InterpolationVariable[] variablesList = model.getInterpolationVariablesList();
        DateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.FRANCE);
        String[][] contenuTable = model.getTableAsArray();
        List<Map<String, Object>> rows = new ArrayList<>();
        for (int i = 0; i < model.getRowsCount(); i++) {
            Map<String, Object> newRow = new HashMap<>();
            rows.add(newRow);
            String[] ligne = contenuTable[i];
            int k = 0;
            for (InterpolationVariable j : variablesList) {
                newRow.put(j.getVarName(), ligne[k]);
                k++;
            }
        }
        gridAssetTable.setItems(rows);

    }


}
