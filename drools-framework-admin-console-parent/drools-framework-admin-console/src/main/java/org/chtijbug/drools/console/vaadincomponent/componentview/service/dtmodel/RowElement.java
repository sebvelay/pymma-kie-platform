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


import org.drools.workbench.models.guided.dtable.shared.model.DTCellValue52;
import org.kie.soup.project.datamodel.oracle.DataType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Date: 26/04/12
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
public class RowElement {
    private static String rowEelement ="RowElement";

    private ColumnDefinition columnDefinition;
    private String value = "";
    private DTCellValue52 dtCellValue52;



    public RowElement(ColumnDefinition columnDefinition, DTCellValue52 dtCellValue52) throws GuidedException {
        this.columnDefinition = columnDefinition;
        this.dtCellValue52 = dtCellValue52;
        this.value = ColumnDefinition.getValue(this.dtCellValue52);
    }

    public RowElement(ColumnDefinition columnDefinition) throws GuidedException {
        this.columnDefinition = columnDefinition;
        this.dtCellValue52 = new DTCellValue52();

        if (this.columnDefinition.isHasDefaultValue()) {
            this.value = this.columnDefinition.getDefaultValue();
            if (this.columnDefinition.getColumnDefinition() == ColumnType.ROW_NUMBER) {
                int rowNumber = Integer.parseInt(value);
                this.dtCellValue52.setNumericValue(rowNumber + 1);
                this.value = value;
            } else if (this.columnDefinition.getColumnDefinition() == ColumnType.DESCRIPTION) {
                this.dtCellValue52.setStringValue(value);

            } else if (this.columnDefinition.getColumnDefinition() == ColumnType.ATTRIBUTE) {
                this.dtCellValue52.setStringValue(value);
                try {
                    setValuedtCell(this.value);
                } catch (Exception e) {
                    GuidedException chtijbugDroolsRestException = new GuidedException(e);
                    chtijbugDroolsRestException.setClassName(rowEelement);
                    chtijbugDroolsRestException.setAttribute(this.columnDefinition.toString());
                    chtijbugDroolsRestException.setValue(this.value);
                    throw chtijbugDroolsRestException;
                }
            } else if (this.columnDefinition.getColumnDefinition() == ColumnType.CONDITION) {
                try {
                    setValuedtCell(this.value);
                } catch (Exception e) {
                    GuidedException chtijbugDroolsRestException = new GuidedException(e);
                    chtijbugDroolsRestException.setClassName(rowEelement);
                    chtijbugDroolsRestException.setAttribute(this.columnDefinition.toString());
                    chtijbugDroolsRestException.setValue(this.value);
                    throw chtijbugDroolsRestException;
                }
            } else if (this.columnDefinition.getColumnDefinition() == ColumnType.ACTION) {
                try {
                    setValuedtCell(this.value);
                } catch (Exception e) {
                    GuidedException chtijbugDroolsRestException = new GuidedException(e);
                    chtijbugDroolsRestException.setClassName(rowEelement);
                    chtijbugDroolsRestException.setAttribute(this.columnDefinition.toString());
                    chtijbugDroolsRestException.setValue(this.value);
                    throw chtijbugDroolsRestException;
                }
            }

        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) throws Exception {
        this.value = value;
        setValuedtCell(value);
    }

    public ColumnDefinition getColumnDefinition() {
        return columnDefinition;
    }

    public DTCellValue52 getDtCellValue52() {
        return dtCellValue52;
    }

    private void setValuedtCell(String aValue) throws Exception {
        if (this.columnDefinition.getFieldType().equalsIgnoreCase(DataType.TYPE_STRING)) {
            this.dtCellValue52.setStringValue(aValue);

        } else if (this.columnDefinition.getFieldType().equalsIgnoreCase(DataType.TYPE_NUMERIC_BIGDECIMAL)) {
            this.dtCellValue52.setNumericValue(new BigDecimal(aValue));
        } else if (this.columnDefinition.getFieldType().equalsIgnoreCase(DataType.TYPE_NUMERIC_BIGINTEGER)) {
            this.dtCellValue52.setNumericValue(new BigInteger(aValue));
        } else if (this.columnDefinition.getFieldType().equalsIgnoreCase(DataType.TYPE_NUMERIC_BYTE)) {
            this.dtCellValue52.setNumericValue(Byte.parseByte(aValue));
        } else if (this.columnDefinition.getFieldType().equalsIgnoreCase(DataType.TYPE_NUMERIC_DOUBLE)) {
            this.dtCellValue52.setNumericValue( Double.parseDouble(aValue));
        } else if (this.columnDefinition.getFieldType().equalsIgnoreCase("DOUBLE")) {
            this.dtCellValue52.setNumericValue(Double.parseDouble(aValue));
        } else if (this.columnDefinition.getFieldType().equalsIgnoreCase(DataType.TYPE_NUMERIC_FLOAT)) {
            this.dtCellValue52.setNumericValue(Float.parseFloat(aValue));
        } else if (this.columnDefinition.getFieldType().equalsIgnoreCase(DataType.TYPE_NUMERIC_INTEGER)) {
            this.dtCellValue52.setNumericValue(Integer.parseInt(aValue));
        } else if (this.columnDefinition.getFieldType().equalsIgnoreCase(DataType.TYPE_NUMERIC_LONG)) {
            this.dtCellValue52.setNumericValue( Long.parseLong(aValue));
        } else if (this.columnDefinition.getFieldType().equalsIgnoreCase(DataType.TYPE_NUMERIC_SHORT)) {
            this.dtCellValue52.setNumericValue(Short.parseShort(aValue));
        } else if (this.columnDefinition.getFieldType().equalsIgnoreCase(DataType.TYPE_DATE)) {
            SimpleDateFormat sdf = new SimpleDateFormat();
            Date newDate = sdf.parse(aValue);
            this.dtCellValue52.setDateValue(newDate);
        } else if (this.columnDefinition.getFieldType().equalsIgnoreCase(DataType.TYPE_BOOLEAN)) {
            this.dtCellValue52.setBooleanValue( Boolean.parseBoolean(aValue));
        } else if (this.columnDefinition.getFieldType().equalsIgnoreCase(DataType.TYPE_NUMERIC)) {
            this.dtCellValue52.setNumericValue( Double.parseDouble(aValue));
        }

    }

}
