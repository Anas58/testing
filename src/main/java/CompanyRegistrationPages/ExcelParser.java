package CompanyRegistrationPages;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * A class to read / write Excel files
 */
public class ExcelParser {

    /**
     * Read an Excel file into a list of maps, first row must contain column names
     * @param excelFile full path of the Excel file
     * @param sheetName required sheet name
     * @return Excel data as a list of maps, each row maps its values to the corresponding column names
     */
    public static List<Map<String, String>> readFile(String excelFile, String sheetName, String language, String caseType) {
        try {
            List<Map<String, String>> excelData = new ArrayList<>();

            // open excel file
            FileInputStream fIS = new FileInputStream(excelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(fIS);
            XSSFSheet sheet = workbook.getSheet(sheetName + "_" + language);

            // get rows iterator
            Iterator<Row> rows = sheet.iterator();
            DataFormatter dataFormatter = new DataFormatter();

            // map column name to column index
            Map<Integer, String> columns = new HashMap<>();
            // first row holds column names
            Row header = rows.next();
            Iterator<Cell> columnNames = header.cellIterator();
            int index = 0;
            while (columnNames.hasNext()) {
                Cell cell = columnNames.next();
                columns.put(index++, cell.getStringCellValue());
            }

            // number of columns
//            int numberOfColumns = columns.keySet().size();

            // fill excelData list
            while (rows.hasNext()) {
                // fill each row into a rowData map
                Map<String, String> rowData = new HashMap<>();
                Row row = rows.next();
                //Iterator<Cell> cells = row.cellIterator();
                int numberOfCells = row.getLastCellNum();
                // double format
                DecimalFormat format = new DecimalFormat("#.####");
                for(index = 0; index < numberOfCells; index++) {
                    Cell cell = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    //Cell cell = row.getCell(index);
                    String value = null;
                    if(cell != null) value = dataFormatter.formatCellValue(cell);
                    rowData.put(columns.get(index), value);
//                    if(cell != null){
//                        rowData.put(columns.get(index), null);
//                        continue;
//                    }
//                    switch (cell.getCellType()) {
//                        case NUMERIC -> rowData.put(columns.get(index), format.format(cell.getNumericCellValue()));
//                        case STRING -> rowData.put(columns.get(index), cell.getStringCellValue());
//                        default -> rowData.put(columns.get(index), null);
//                    }
                }

                // add row to excelData list
               if(caseType != null){
                    if(rowData.get("test_case_type").equals(caseType)) {
                        excelData.add(rowData);
                    }
                }else{
                    excelData.add(rowData);
                }
            }
            // close excel file
            workbook.close();

            return excelData;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public static Object[][] passToDataProvider(String excelFile, String sheetName, String language, String caseType){
        List<Map<String, String>> excelData = readFile(excelFile, sheetName, language, caseType);
        Object[][] testData = new Object[excelData.size()][1];
        for(int index = 0; index < excelData.size(); index++){
            testData[index][0] = excelData.get(index);
        }
        return testData;
    }

    /**
     * Write a list of lists to a new Excel file
     * @param excelFile full path of the Excel file
     * @param excelData list of lists of string values, it's good to have the first list as the column names
     * @param sheetName name of the sheet to be created and filled with data
     * @return true if the files was created successfully, false otherwise
     */
    public static boolean writeFile(String excelFile, List<List<String>> excelData, String sheetName) {
        try {
            // create excel file
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet(sheetName);

            // fill each list item in a new row
            for(List<String> item : excelData) {
                // create row
                Row row = sheet.createRow(excelData.indexOf(item));
                // fill row
                for (String value: item) {
                    // create cell
                    Cell cell = row.createCell(item.indexOf(value));
                    // fill cell
                    cell.setCellValue(value);
                }
            }

            // save excel file
            FileOutputStream fOS = new FileOutputStream(excelFile,false);
            workbook.write(fOS);
            workbook.close();

            return true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}