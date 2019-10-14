package me.atwmll.indexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Iterates through specified directory and adds names of files to .csv file
 * based on the number of files in that directory.
 *
 * Usage(s): Indexing large amounts of files and adding them to an organized
 * Comma Separated Values file.
 *
 * @author Austin Malone - atwmll
 */
public class Indexer {

    public static void main(String[] args) {
        Indexer.indexer();
    }

    public static String indexer() {

        // Give directory location and filename with extension:
        try (PrintWriter writer = new PrintWriter(new File("/your/path/to/output.csv"))) {
            // Give directory of files to index:
            File folder = new File("/your/path/to/docs_to_index");
            // List all files in directory:
            File[] files = folder.listFiles();
            // Builds string:
            StringBuilder sb = new StringBuilder();
            // Adds description of cell:
            sb.append("Date of Index:,Fake Test Acct. Nums.:,Empty Cell:,Mapping ID:,Empty Cell:,Test Text:,Test Text:,File Description (excluding commas if any):,Full Filename (excluding commas if any):,File Extension:\n");
            // Iterate through the directory:
            for (File file : files) {
                // Date of program execution:
                sb.append(date()).append(',');
                // First account nums:
                sb.append(file.getName().substring(0, file.getName().indexOf(" -"))).append(',');
                // Empty cell:
                sb.append(',');
                // Map file description to ID:
                sb.append(getMapData(file.getName().substring(file.getName().indexOf("-"), file.getName().indexOf(".")).replace("- ", "").replace(",", ""))).append(',');
                // Empty cell for example:
                sb.append(',');
                // Random text (github username):
                sb.append("atwmll,");
                // More random text for example:
                sb.append("Just For Fun,");
                // Just gets name of file between number and extension:
                sb.append(file.getName().substring(file.getName().indexOf("-"), file.getName().indexOf(".")).replace("- ", "").replace(",", "")).append(',');
                // Name of files in directory:
                // Replaces a comma in a filename, messing up formatting:
                sb.append(file.getName().replace(",", "")).append(',');
                // Gets file extension:
                sb.append(file.getName().substring(file.getName().indexOf(".")).replace(".", "").toUpperCase());
                // Line-break:
                sb.append('\n');
            }
            // Prints out formatted lines for testing:
            System.out.println(sb.toString());
            // Writes generated strings to csv file:
            writer.write(sb.toString());
            // Complete message:
            System.out.println("done!");
            // Exception handling:
        } catch (FileNotFoundException e) {
            // Prints exception:
            System.out.println(e.getMessage());
            // Can add logging if desired.
        }
        // Return finshed message:
        return "Complete!";
    }

    public static Map<String, Map<String, String>> setMapData() {

        try {
            File mappingFile = new File("/your/path/to/mapping_file.xlsx (or .csv)");

            Workbook workbook = new XSSFWorkbook(mappingFile.toString());

            Sheet sheet = workbook.getSheetAt(0);

            int lastRow = sheet.getLastRowNum();

            Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String, String>>();

            Map<String, String> dataMap = new HashMap<String, String>();

            //Looping over entire row
            for (int i = 0; i <= lastRow; i++) {

                Row row = sheet.getRow(i);

                //1st Cell as Value
                Cell valueCell = row.getCell(1);

                //0th Cell as Key
                Cell keyCell = row.getCell(0);

                String value = valueCell.getStringCellValue().trim();
                String key = keyCell.getStringCellValue().trim();

                //Putting key & value in dataMap
                dataMap.put(key, value);

                //Putting dataMap to excelFileMap
                excelFileMap.put("DataSheet", dataMap);
            }

            //Returning excelFileMap
            return excelFileMap;
        } catch (IOException ex) {
            Logger.getLogger(Indexer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Map.of();
    }

    //Method to retrieve value
    public static String getMapData(String key) {

        Map<String, String> m = setMapData().get("DataSheet");
        String value = m.get(key);

        return value;
    }

    /**
     * Generates the date.
     *
     * @return formatted date.
     */
    public static String date() {
        // Initialize Date utility:
        Date date = new Date();
        // Initialize date-formatter and format date:
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy @ hh:mm.ss a");

        // Return formatted date:
        return formatter.format(date);
    }
}
