package me.atwmll.indexer.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties class that is used to access values from properties file within
 * and outside of the program/jar.
 *
 * @author Austin Malone - atwmll
 */
public class IndexerProperties {

    private static final String propsPath = "./indexer.properties";
    private static final String prefix = "indexer.";
    private static final Properties props = new Properties();

    public static String getOutputCsv() throws IOException {

        try (var file = new FileInputStream(propsPath)) {
            props.load(file);
            return props.getProperty(prefix + "output-csv");
        }
    }

    public static String getIndexDir() throws IOException {
        try (var file = new FileInputStream(propsPath)) {
            props.load(file);
            return props.getProperty(prefix + "dir");
        }
    }

    public static String getMapping() throws IOException {
        try (var file = new FileInputStream(propsPath)) {
            props.load(file);
            return props.getProperty(prefix + "mapping");
        }
    }

    public static String getCsvHeader() throws IOException {
        try (var file = new FileInputStream(propsPath)) {
            props.load(file);
            return props.getProperty(prefix + "csv-header");
        }
    }

    public static String getDateFormat() throws IOException {
        try (var file = new FileInputStream(propsPath)) {
            props.load(file);
            return props.getProperty(prefix + "date-format");
        }
    }
}
