package org.chtijbug.drools.console.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.drools.workbench.models.datamodel.rule.InterpolationVariable;
import org.drools.workbench.models.guided.template.backend.RuleTemplateModelXMLPersistenceImpl;
import org.drools.workbench.models.guided.template.shared.TemplateModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@DependsOn("applicationContext")
public class GuidedRuleTemplateExcelService {

    @Value("${adminConsole.tmpdir}")
    public String tmpDir;

    private KieRepositoryService kieRepositoryService;
    private KieConfigurationData config;

    private UserConnectedService userConnectedService;

    private String assetContent;

    public GuidedRuleTemplateExcelService(){
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

    public List<HashMap<String,Object>> importExcel(InputStream inputStream) throws IOException {

        Workbook workbook= WorkbookFactory.create(inputStream);

        DataFormatter dataFormatter = new DataFormatter();

        List<HashMap<String,Object>> result=new ArrayList<>();

        for(Sheet sheet: workbook) {
            for (Row row: sheet) {

                if(row.getRowNum()!=0) {

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

        return result;
    }
    public ByteArrayInputStream exportExcel(String nameTemplate){

        //Récupération des objets JAVA

        String assetContent = kieRepositoryService.getAssetSource(config.getKiewbUrl(),
                userConnectedService.getUserConnected().getUserName(),
                userConnectedService.getUserConnected().getUserPassword(),
                userConnectedService.getSpace(),
                userConnectedService.getProject(),
                userConnectedService.getAsset());

        TemplateModel model = RuleTemplateModelXMLPersistenceImpl.getInstance().unmarshal(assetContent);
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

        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet(nameTemplate);

        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow=sheet.createRow(0);

        if(rows!=null&&rows.size()!=0){
            int columnIndex=0;
            for(Map.Entry<String,Object> t:rows.get(0).entrySet()){
                Cell cell=headerRow.createCell(columnIndex);
                cell.setCellValue(t.getKey());
                cell.setCellStyle(headerCellStyle);
                columnIndex++;
            }
            int rowIndex=1;
            for(HashMap<String,Object> t:rows) {

                int columnIndexTmp=0;
                Row r=sheet.createRow(rowIndex);

                for (Map.Entry<String, Object> tmp : t.entrySet()) {
                    Cell cell=r.createCell(columnIndexTmp);
                    cell.setCellValue(String.valueOf(tmp.getValue()));
                    cell.setCellStyle(headerCellStyle);
                    columnIndexTmp++;
                }
                rowIndex++;
            }
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(tmpDir+"/"+nameTemplate+".xlsx");
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
    }

}
