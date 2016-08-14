package com.atanas.kanchev.testframework.dataservices.csv;

import au.com.bytecode.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * CSV File Parser
 *
 * @author Atanas Kanchev
 * @version 1.0
 */
public final class CSVParser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private CSVReader reader;

    /**
     * <p>Constructor for CSVParser.</p>
     *
     * @param csvFilePath a {@link java.lang.String} object.
     */
    public CSVParser(String csvFilePath) {
        try {
            this.reader = new CSVReader(new FileReader(csvFilePath), ',', '"', 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get All Rows
     *
     * @return List<String[]> all rows
     */
    public List<String[]> getAllRows() {

        try {
            return reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }
}



