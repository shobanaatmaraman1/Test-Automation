package main.java.com.xadcentral.regression;

/**
 * Created by xAD-inc on 2/17/2015.
 */
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xad on 10/10/14.
 */
public class ExcelOps {

    private static String priority;
    private static String filePath;
    static ReadConf conf;
    static HSSFSheet sheet;
    FileInputStream file;
    HSSFWorkbook workbook;
    static ExcelOps excelOps;
    Cell t;
    Cell p;


    public ExcelOps() throws Exception {

        conf = new ReadConf();
        conf.readJson();
        priority = conf.getPriority();
        filePath=conf.getExcelFilePath();

        file=new FileInputStream(new File(filePath));
        workbook=new HSSFWorkbook(file);

        sheet = workbook.getSheetAt(0);
    }


    public void readAndWriteToSheet(String testName, String testResult) throws Exception {
        int rowCount=56;
        for(int i=2;i<rowCount;i++ ) {
            t=sheet.getRow(i).getCell(3);
            if(t.getStringCellValue().equals(testName))  {
                Cell cell;
                cell=sheet.getRow(i).getCell(5);
                cell.setCellValue(testResult);
                if(testResult=="Pass") {
                    //cell.setCellStyle();
                }
            }
        }

    }

    public boolean readPriority(String testName) throws Exception{
        for(int i=2;i<56;i++) {
            t=sheet.getRow(i).getCell(3);
            if(t.getStringCellValue().equals(testName)) {
                p=sheet.getRow(i).getCell(2);
                if(p.getStringCellValue().equals(priority)) {
                    return true;
                }
                else {
                    readAndWriteToSheet(testName,"N/A");
                }
            }
        }
        return false;
    }


    // Write to file, save changes and close the file
    public void saveChanges() throws IOException {
        file.close();
        FileOutputStream outputFile=new FileOutputStream(new File(filePath));
        workbook.write(outputFile);
        outputFile.close();


    }



}
