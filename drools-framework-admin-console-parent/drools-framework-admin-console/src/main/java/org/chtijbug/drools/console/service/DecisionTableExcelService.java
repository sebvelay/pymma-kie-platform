package org.chtijbug.drools.console.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.console.vaadinComponent.componentView.service.dtmodel.ColumnDefinition;
import org.chtijbug.drools.console.vaadinComponent.componentView.service.dtmodel.DecisionTable;
import org.chtijbug.drools.console.vaadinComponent.componentView.service.dtmodel.GuidedException;
import org.drools.workbench.models.guided.dtable.backend.GuidedDTXMLPersistence;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@DependsOn("applicationContext")
public class DecisionTableExcelService {

    @Value("${adminConsole.tmpdir}")
    public String tmpDir;

    private KieRepositoryService kieRepositoryService;
    private KieConfigurationData config;

    private UserConnectedService userConnectedService;

    private String assetContent;

    public DecisionTableExcelService() {
        this.kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);
        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        this.userConnectedService = AppContext.getApplicationContext().getBean(UserConnectedService.class);
    }

    public String getAssetContent() {
        assetContent = kieRepositoryService.getAssetSource(config.getKiewbUrl(),
                userConnectedService.getUserConnected().getUserName(),
                userConnectedService.getUserConnected().getUserPassword(),
                userConnectedService.getSpace(),
                userConnectedService.getProject(),
                userConnectedService.getAsset());
        return assetContent;
    }

    public List<HashMap<String, Object>> importExcel(InputStream inputStream) throws IOException {
        Workbook workbook = null;
        List<HashMap<String, Object>> result = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            result = new ArrayList<>();
            DataFormatter dataFormatter = new DataFormatter();
            for (Sheet sheet : workbook) {
                for (Row row : sheet) {

                    if (row.getRowNum() != 0) {

                        HashMap<String, Object> tmp = new HashMap<>();
                        result.add(tmp);

                        for (Cell cell : row) {
                            String cellValue = dataFormatter.formatCellValue(cell);

                            Integer numColumn = cell.getColumnIndex();
                            Cell title = sheet.getRow(0).getCell(numColumn);
                            tmp.put(dataFormatter.formatCellValue(title), cellValue);

                        }
                    }
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return result;
    }

    public ByteArrayInputStream exportExcel(String nameTemplate)  {

        //Récupération des objets JAVA

        assetContent = kieRepositoryService.getAssetSource(config.getKiewbUrl(),
                userConnectedService.getUserConnected().getUserName(),
                userConnectedService.getUserConnected().getUserPassword(),
                userConnectedService.getSpace(),
                userConnectedService.getProject(),
                userConnectedService.getAsset());
        GuidedDecisionTable52 model = GuidedDTXMLPersistence.getInstance().unmarshal(assetContent);
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook();
            DecisionTable decisionTable = new DecisionTable(model);
            CreationHelper createHelper = workbook.getCreationHelper();
            Sheet sheet = workbook.createSheet(nameTemplate);

            Font headerFont = workbook.createFont();
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setColor(IndexedColors.BLACK.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);

            if (decisionTable.getRows() != null && decisionTable.getRows().size() != 0) {
                int columnIndex = 0;
                int j = 0;
                for (ColumnDefinition columnDefinition : decisionTable.getColumnDefinitionList()) {
                    if (columnDefinition.isHideColumn() == false) {
                        Cell cell = headerRow.createCell(j);
                        cell.setCellValue(columnDefinition.getHeader());
                        cell.setCellStyle(headerCellStyle);
                        j++;
                    }
                    columnIndex++;
                }
                int rowIndex = 1;

                for (int i = 0; i < decisionTable.getRows().size(); i++) {
                    org.chtijbug.drools.console.vaadinComponent.componentView.service.dtmodel.Row row = decisionTable.getRows().get(i);
                    int k = 0;
                    int jj = 0;
                    Row r = sheet.createRow(rowIndex);
                    for (ColumnDefinition columnDefinition : decisionTable.getColumnDefinitionList()) {
                        if (columnDefinition.isHideColumn() == false) {
                            Cell cell = r.createCell(k);
                            cell.setCellValue(row.getRowElements().get(jj).getValue());
                            cell.setCellStyle(headerCellStyle);
                            k++;
                        }
                        jj++;
                    }
                    rowIndex++;
                }

            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(tmpDir + "/" + nameTemplate + ".xlsx");
                workbook.write(fileOutputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                workbook.write(bos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] barray = bos.toByteArray();
            ByteArrayInputStream is = new ByteArrayInputStream(barray);

            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return is;

        } catch (GuidedException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
