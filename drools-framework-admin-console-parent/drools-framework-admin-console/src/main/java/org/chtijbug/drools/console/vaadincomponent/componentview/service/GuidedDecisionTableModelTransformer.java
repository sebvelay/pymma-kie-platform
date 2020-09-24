package org.chtijbug.drools.console.vaadincomponent.componentview.service;

import com.vaadin.flow.data.binder.Binder;
import org.chtijbug.drools.console.vaadincomponent.componentview.AssetEdit;
import org.chtijbug.drools.console.vaadincomponent.componentview.service.dtmodel.ColumnDefinition;
import org.chtijbug.drools.console.vaadincomponent.componentview.service.dtmodel.DecisionTable;
import org.chtijbug.drools.console.vaadincomponent.componentview.service.dtmodel.GuidedException;
import org.chtijbug.drools.console.vaadincomponent.componentview.service.dtmodel.Row;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuidedDecisionTableModelTransformer {

    private GuidedDecisionTable52 model;

    private Binder binder;

    private AssetEdit assetEdit;

    public GuidedDecisionTableModelTransformer(GuidedDecisionTable52 model, Binder binder, AssetEdit assetEdit) {
        this.model = model;
        this.binder = binder;
        this.assetEdit = assetEdit;
    }

    public void run() {
        try {
            DecisionTable decisionTable = new DecisionTable(model);
            for (ColumnDefinition columnDefinition : decisionTable.getColumnDefinitionList()) {
                if (!columnDefinition.isHideColumn()) {
                    assetEdit.addColumn(hashmap -> hashmap.get(columnDefinition.getHeader())).setHeader(columnDefinition.getHeader());
                }
            }
            fillTable(decisionTable);
        } catch (GuidedException e) {
            e.printStackTrace();
        }

    }

    private void fillTable(DecisionTable decisionTable) {
        List<HashMap<String, Object>> rows = new ArrayList<>();
        for (int i = 0; i < decisionTable.getRows().size(); i++) {
            Row row = decisionTable.getRows().get(i);
            HashMap<String, Object> newRow = new HashMap<>();
            rows.add(newRow);
            int k = 0;
            for (ColumnDefinition columnDefinition : decisionTable.getColumnDefinitionList()) {
                newRow.put(columnDefinition.getHeader(), row.getRowElements().get(k).getValue());
                k++;
            }
        }
        assetEdit.setItems(rows);

    }
}
