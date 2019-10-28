package me.atwmll.indexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    final static Logger LOG = Logger.getLogger(Indexer.class.getName());

    public static void main(String[] args) throws IOException {

        LOG.info("Indexer called from main");
        Indexer.indexer();
    }

    public static String getProps(String propKey) {
        var props = new Properties();
        // If running from IDE, use "src/main/resources/indexer.properties"
        // For running uberjar/fatjar, use ./indexer.properties
        try (var propsFile = new FileInputStream("./indexer.properties")) {

            props.load(propsFile);

        } catch (IOException iox) {
            // Log me
        }
        return props.getProperty(propKey);
    }

    public static String indexer() throws IOException {

        // Give directory location and filename with extension:
        try (var writer = new PrintWriter(new File(getProps("outputCsv")))) {
            // Give directory of files to index:
            var folder = new File(getProps("dir"));
            // List all files in directory (Array):
            var files = folder.listFiles();
            // Builds string:
            var sb = new StringBuilder();
            // Adds description of cell:
            sb.append(getProps("csvHeader"));
            // Iterate through the directory:
            for (var file : files) {
                var fName = file.getName().substring(file.getName().indexOf("-"), file.getName().indexOf(".")).replace("- ", "").replace(",", "");
                // Date of program execution:
                sb.append(date()).append(',');
                // First account nums:
                sb.append(file.getName().substring(0, file.getName().indexOf(" -"))).append(',');
                // Empty cell:
                sb.append(',');
                // Map file description to ID:
                sb.append(getMapData(fName)).append(',');
                // Empty cell for example:
                sb.append(',');
                // Random text (github username):
                sb.append("atwmll,");
                // More random text for example:
                sb.append("Just For Fun,");
                // Just gets name of file between number and extension:
                sb.append(fName).append(',');
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

            return "Complete!";
        }
    }

    public static Map<String, Map<String, String>> setMapData() {

        try {
            var mappingFile = new File(getProps("mapping"));

            var workbook = new XSSFWorkbook(mappingFile.toString());

            var sheet = workbook.getSheetAt(0);

            var lastRow = sheet.getLastRowNum();

            Map<String, Map<String, String>> excelFileMap = new HashMap<>();

            Map<String, String> dataMap = new HashMap<>();

            //Looping over entire row
            for (int i = 0; i <= lastRow; i++) {

                var row = sheet.getRow(i);

                //1st Cell as Value
                var valueCell = row.getCell(1);

                //0th Cell as Key
                var keyCell = row.getCell(0);

                var value = valueCell.getStringCellValue().trim();
                var key = keyCell.getStringCellValue().trim();

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
        var value = m.get(key);

        return value;
    }

    /**
     * Generates the date.
     *
     * @return formatted date.
     */
    public static String date() throws IOException {
        // Initialize Date utility:
        var date = new Date();
        // Initialize date-formatter and format date:
        var formatter = new SimpleDateFormat(getProps("dateFormat"));

        // Return formatted date:
        return formatter.format(date);
    }
}
