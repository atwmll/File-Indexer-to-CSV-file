package me.atwmll.indexer.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties class that is used to access values from properties file within
 * and outside of the program/jar.
 *
 * @author tux
 */
public class IndexerProperties {

    private static final String propsPath = "./indexer.properties";

    public static String getOutputCsv() throws IOException {
        var props = new Properties();
        try (var file = new FileInputStream(propsPath)) {
            props.load(file);
            return props.getProperty("indexer.output-csv");
        }
    }

    public static String getIndexDir() throws IOException {
        var props = new Properties();
        try (var file = new FileInputStream(propsPath)) {
            props.load(file);
            return props.getProperty("indexer.dir");
        }
    }

    public static String getMapping() throws IOException {
        var props = new Properties();
        try (var file = new FileInputStream(propsPath)) {
            props.load(file);
            return props.getProperty("indexer.mapping");
        }
    }

    public static String getCsvHeader() throws IOException {
        var props = new Properties();
        try (var file = new FileInputStream(propsPath)) {
            props.load(file);
            return props.getProperty("indexer.csv-header");
        }
    }

    public static String getDateFormat() throws IOException {
        var props = new Properties();
        try (var file = new FileInputStream(propsPath)) {
            props.load(file);
            return props.getProperty("indexer.date-format");
        }
    }
}
