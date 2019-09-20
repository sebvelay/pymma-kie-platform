package org.chtijbug.drools.console.vaadinComponent.componentView.service;

import com.vaadin.flow.data.binder.Binder;
import org.chtijbug.drools.console.vaadinComponent.componentView.AssetEdit;
import org.drools.workbench.models.datamodel.rule.InterpolationVariable;
import org.drools.workbench.models.guided.template.shared.TemplateModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class GuidedRuleTemplateModelTransformer {

    private TemplateModel model;

    private Binder binder;

    private AssetEdit assetEdit;

    public GuidedRuleTemplateModelTransformer(TemplateModel model, Binder binder,AssetEdit assetEdit) {
        this.model = model;
        this.binder = binder;
        this.assetEdit = assetEdit;
    }
    public void run(){
        InterpolationVariable[] variablesList = model.getInterpolationVariablesList();
        for (InterpolationVariable i : variablesList) {
            assetEdit.addColumn(hashmap -> hashmap.get(i.getVarName())).setHeader(i.getVarName());
        }
        fillTable(model);
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
        assetEdit.setItems(rows);

    }
}
