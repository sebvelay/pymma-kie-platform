package org.chtijbug.drools.console.vaadinComponent.componentView;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.UserConnectedService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.drools.workbench.models.datamodel.rule.InterpolationVariable;
import org.drools.workbench.models.guided.template.backend.RuleTemplateModelXMLPersistenceImpl;
import org.drools.workbench.models.guided.template.shared.TemplateModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@StyleSheet("css/accueil.css")
public class AssetEdit extends Grid<HashMap<String, Object>> {

    private KieRepositoryService kieRepositoryService;
    private KieConfigurationData config;

    private UserConnectedService userConnectedService;

    public AssetEdit() {

        setClassName("assetEdit-content");

        this.kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);
        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        this.userConnectedService = AppContext.getApplicationContext().getBean(UserConnectedService.class);

        String assetContent = kieRepositoryService.getAssetSource(config.getKiewbUrl(),
                userConnectedService.getUserConnected().getUserName(),
                userConnectedService.getUserConnected().getUserPassword(),
                userConnectedService.getSpace(),
                userConnectedService.getProject(),
                userConnectedService.getAsset());

        TemplateModel model = RuleTemplateModelXMLPersistenceImpl.getInstance().unmarshal(assetContent);
        InterpolationVariable[] variablesList = model.getInterpolationVariablesList();

        setClassName("grid-perso");

        Binder<HashMap<String, Object>> binder = new Binder<>();
        getEditor().setBinder(binder);


        for (InterpolationVariable i : variablesList) {
            addColumn(hashmap -> hashmap.get(i.getVarName())).setHeader(i.getVarName());
        }
        fillTable(model);

    }

    public AssetEdit(List<HashMap<String, Object>> objects) {

        setClassName("assetEdit-content");

        this.kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);
        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        this.userConnectedService = AppContext.getApplicationContext().getBean(UserConnectedService.class);

        setClassName("grid-perso");

        Binder<HashMap<String, Object>> binder = new Binder<>();
        getEditor().setBinder(binder);

        if(objects.size()>0){
            for(Map.Entry<String,Object> t:objects.get(0).entrySet()){
                addColumn(hashmap -> hashmap.get(t.getKey())).setHeader(t.getKey());

            }
        }

        setItems(objects);
    }

    public void maj(List<HashMap<String, Object>> objects){

    }


    private void fillTable(TemplateModel model) {
        InterpolationVariable[] variablesList = model.getInterpolationVariablesList();
        DateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.FRANCE);
        String[][] contenuTable = model.getTableAsArray();
        List<HashMap<String, Object>> rows = new ArrayList<>();
        for (int i = 0; i < model.getRowsCount(); i++) {
            HashMap<String, Object> newRow = new HashMap<>();
            rows.add(newRow);
            String[] ligne = contenuTable[i];
            int k = 0;
            for (InterpolationVariable j : variablesList) {
                newRow.put(j.getVarName(), ligne[k]);
                k++;
            }
        }
        setItems(rows);

    }
}
