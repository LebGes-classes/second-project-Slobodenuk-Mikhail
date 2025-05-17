import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class WorkWithFile {
    static Map<String, List<Map<String, String>>> workbook;
    static BaseOfID baseOfID;

    static String inputExcelPath = "C:\\Users\\slobo\\IdeaProjects\\Market\\src\\main\\resources\\Market.xlsx";
    static String tempJsonPath = "C:\\Users\\slobo\\IdeaProjects\\Market\\temp.json";
    static String outputExcelPath = "C:\\Users\\slobo\\IdeaProjects\\Market\\output.xlsx";


    public WorkWithFile(BaseOfID baseOfID, Map<String, List<Map<String, String>>> workbook) {
        this.baseOfID = baseOfID;
        this.workbook = workbook;
    }

    public static Map<String, List<Map<String, String>>> convertExceltoJson() {
        Map<String, List<Map<String, String>>> result = new LinkedHashMap<>();
        try (FileInputStream fis = new FileInputStream(inputExcelPath); Workbook workbook = new XSSFWorkbook(fis)) {

            // Обрабатываем все листы
            for (Sheet sheet : workbook) {
                List<Map<String, String>> sheetData = new ArrayList<>();
                Row headerRow = sheet.getRow(0);
                List<String> headers = new ArrayList<>();

                //
                for (Cell cell : headerRow) {
                    headers.add(cell.getStringCellValue());
                }

                // Парсинг данных листа
                for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                    boolean flag = true;
                    Row row = sheet.getRow(j);
                    if (row == null) {continue;}

                    Map<String, String> rowData = new LinkedHashMap<>();
                    for (int k = 0; k < headers.size(); k++) {
                        Cell cell = row.getCell(k, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        if (!Objects.equals(String.valueOf(getCellValueAsString(cell)), "")) {
                            rowData.put(headers.get(k), String.valueOf(getCellValueAsString(cell)));
                        } else{
                            flag = false;
                        }
                    }
                    if (flag) {
                        sheetData.add(rowData);
                    }

                }
                result.put(sheet.getSheetName(), sheetData);
            }

        } catch (IOException io) {
            System.out.println("Error: " + io.getMessage());
        }
        return result;
    }

//    private static String getCellValueAsString(Cell cell) {
//        if (cell == null) {
//            return "";
//        }
//        switch (cell.getCellType()) {
//            case STRING:
//                return cell.getStringCellValue();
//            case NUMERIC:
//                if (DateUtil.isCellDateFormatted(cell)) {
//                    return cell.getDateCellValue().toString();
//                } else {
//                    return String.valueOf(cell.getNumericCellValue());
//                }
//            case BOOLEAN:
//                return String.valueOf(cell.getBooleanCellValue());
//            case FORMULA:
//                return cell.getCellFormula();
//            default:
//                return "";
//        }
//    }

    private static Object getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    double numericValue = cell.getNumericCellValue();
                    // Проверяем, является ли число целым
                    if (numericValue == (int) numericValue) {
                        return (int) numericValue;
                    } else {
                        return numericValue;
                    }
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    // Сохранение JSON (все листы)
    public static void saveJsonToFile(Map<String, List<Map<String, String>>> data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(tempJsonPath), data);
    }


    public static void convertJsonToExcel(Map<String, List<Map<String, String>>> allData) {
        try (Workbook workbook = new XSSFWorkbook()) {
            for (Map.Entry<String, List<Map<String, String>>> entry : allData.entrySet()) {
                String sheetName = entry.getKey();
                List<Map<String, String>> sheetData = entry.getValue();
                Sheet sheet = workbook.createSheet(sheetName);

                if (!sheetData.isEmpty()) {
                    // создание заголовков
                    Row headerRow = sheet.createRow(0);
                    int colNum = 0;
                    for (String header : sheetData.get(0).keySet()) {
                        headerRow.createCell(colNum++).setCellValue(header);
                    }

                    // Заполнение ячеек
                    int rowNum = 1;
                    for (Map<String, String> row : sheetData) {
                        Row currentRow = sheet.createRow(rowNum++);
                        colNum = 0;
                        for (String value : row.values()) {
                            currentRow.createCell(colNum++).setCellValue(value);
                        }
                    }
                }
            }



            try (FileOutputStream fos = new FileOutputStream(outputExcelPath)) {
                workbook.write(fos);
            }

        } catch (IOException io) {
            System.out.println("Error: " + io.getMessage());
        }


    }

    public static Map<String, List<Map<String, String>>> convertJsonToMap() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
            new File(tempJsonPath),
            new TypeReference<Map<String, List<Map<String, String>>>>() {}
        );
    }

    public static void saveJSON(Map<String, List<Map<String, String>>> workBook) throws Exception {
        WorkWithFile.saveJsonToFile(workBook);
    }

    public static void saveExcel() {
        WorkWithFile.convertJsonToExcel(workbook);
    }


}
