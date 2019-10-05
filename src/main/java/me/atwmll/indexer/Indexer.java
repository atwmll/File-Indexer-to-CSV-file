package me.atwmll.indexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        // Give directory location and filename with extension:
        try (PrintWriter writer = new PrintWriter(new File("/your/path/to/csv/test.csv"))) {
            // Give directory of files to index:
            File folder = new File("/your/path/to/files");
            // List all files in directory:
            File[] files = folder.listFiles();
            // Iterate through those files based on number of files in directory:
            for (int i = 1; i <= files.length; i++) {
                // Iterate through the directory:
                for (File file : files) {
                    // Build string for csv file:
                    StringBuilder sb = new StringBuilder();
                    // Counter:
                    sb.append(i++).append(',');
                    // Date of program execution:
                    sb.append(date()).append(',');
                    // Empty cell for example:
                    sb.append(',');
                    // Random text (github username):
                    sb.append("atwmll,");
                    // More random text for example:
                    sb.append("Just For Fun,");
                    // Empty cell:
                    sb.append(',');
                    // Name of files in directory:
                    sb.append(file.getName()).append(',');
                    // Line-break:
                    sb.append('\n');
                    // Writes generated strings to csv file:
                    writer.write(sb.toString());
                    // Prints out formatted lines for testing:
                    System.out.println(sb.toString());
                }
                // Breaks infinite loop:
                break;
            }
            // Complete message:
            System.out.println("done!");
            // Exception handling:
        } catch (FileNotFoundException e) {
            // Prints exception:
            System.out.println(e.getMessage());
            // Can add logging if desired.
        }
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
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        // Return formatted date:
        return formatter.format(date);
    }
}
