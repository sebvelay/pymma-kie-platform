package org.chtijbug.drools.console.vaadincomponent.componentview.service;

import com.vaadin.flow.data.binder.Binder;
import org.chtijbug.drools.console.vaadincomponent.componentview.AssetEdit;
import org.drools.workbench.models.datamodel.rule.InterpolationVariable;
import org.drools.workbench.models.guided.template.shared.TemplateModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
