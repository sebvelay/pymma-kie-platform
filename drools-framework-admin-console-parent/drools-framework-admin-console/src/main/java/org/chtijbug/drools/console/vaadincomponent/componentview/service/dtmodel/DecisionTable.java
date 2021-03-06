/*
 * Copyright 2014 Pymma Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chtijbug.drools.console.vaadincomponent.componentview.service.dtmodel;


import org.drools.workbench.models.guided.dtable.shared.model.*;

import java.util.ArrayList;
import java.util.List;

public class DecisionTable {
    private String name;
    private GuidedDecisionTable52 guidedDecisionTable52;
    private List<ColumnDefinition> columnDefinitionList = new ArrayList<>();
    private List<Row> rows = new ArrayList<>();

    public DecisionTable(GuidedDecisionTable52 guidedDecisionTable52) throws GuidedException {
        this.guidedDecisionTable52 = guidedDecisionTable52;
        this.name = this.guidedDecisionTable52.getTableName();
        ColumnDefinition rowNumberColumn = new ColumnDefinition(0, guidedDecisionTable52.getRowNumberCol());
        rowNumberColumn.setHeader("ID");
        columnDefinitionList.add(rowNumberColumn);
        ColumnDefinition descriptionColumn = new ColumnDefinition(1, guidedDecisionTable52.getDescriptionCol());
        descriptionColumn.setHeader("Description");
        columnDefinitionList.add(descriptionColumn);
        int columnNumber = 2;
        for (AttributeCol52 attributeCol52 : this.guidedDecisionTable52.getAttributeCols()) {
            ColumnDefinition columnDefinition = new ColumnDefinition(columnNumber, attributeCol52);
            columnDefinition.setHeader(attributeCol52.getAttribute());
            columnDefinitionList.add(columnDefinition);
            columnNumber++;

        }
        for (Pattern52 pattern52 : this.guidedDecisionTable52.getPatterns()) {
            for (ConditionCol52 conditionCol52 : pattern52.getChildColumns()) {
                ColumnDefinition columnDefinition = new ColumnDefinition(columnNumber, conditionCol52);
                columnDefinitionList.add(columnDefinition);
                columnNumber++;
            }
        }
        for (ActionCol52 actionCol52 : this.guidedDecisionTable52.getActionCols()) {
            if (actionCol52 instanceof ActionInsertFactCol52) {
                ActionInsertFactCol52 actionInsertFactCol52 = (ActionInsertFactCol52) actionCol52;
                ColumnDefinition columnDefinition = new ColumnDefinition(columnNumber, actionInsertFactCol52);
                columnDefinitionList.add(columnDefinition);
                columnNumber++;
            }else if (actionCol52 instanceof ActionSetFieldCol52){
                ActionSetFieldCol52 actionSetFieldCol52 = (ActionSetFieldCol52) actionCol52;
                ColumnDefinition columnDefinition = new ColumnDefinition(columnNumber, actionSetFieldCol52);
                columnDefinitionList.add(columnDefinition);
                columnNumber++;
            }
        }
        for (List<DTCellValue52> line : this.guidedDecisionTable52.getData()) {
            try {
                Row newRow = new Row(line, this);
                rows.add(newRow);
            } catch (GuidedException e) {
                GuidedException chtijbugDroolsRestException = new GuidedException(e);
                e.setClassName("DecisionTable.Constructor");
                e.setAttribute("Data");
                e.setValue(line.toString());
                throw chtijbugDroolsRestException;

            }

        }
    }

    public Row createEmptyRow(int rowNumber) throws GuidedException {
        for (int j = rowNumber; j < this.rows.size(); j++) {
            this.rows.get(j).updateRowNumber(j + 1);
        }
        Row newRow = new Row(this, rowNumber);
        rows.add(newRow);
        this.guidedDecisionTable52.getData().add(newRow.getCellValue52List());
        return newRow;
    }

    private void removeRow(int rowNumber) throws GuidedException {
        rows.remove(rowNumber);
        this.guidedDecisionTable52.getData().remove(rowNumber);
        for (int j = rowNumber; j < this.rows.size(); j++) {
            this.rows.get(j).updateRowNumber(j);
        }
    }

    public void clearAllData() {
        this.guidedDecisionTable52.getData().clear();
        this.getRows().clear();
    }

    public String getName() {
        return name;
    }

    public GuidedDecisionTable52 getGuidedDecisionTable52() {
        return guidedDecisionTable52;
    }

    public List<ColumnDefinition> getColumnDefinitionList() {
        return columnDefinitionList;
    }

    public List<Row> getRows() {
        return rows;
    }
}
