/*
 * Copyright 2016 Atanas Stoychev Kanchev
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.atanas.kanchev.testframework.dataservices.dataprovider.csv;

import au.com.bytecode.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * <p>CSVParser class.</p>
 *
 * @author Atanas Kanchev
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
            logger.error("Unable to find file in path " + csvFilePath);
        }
    }

    /**
     * <p>getAllRows.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<String[]> getAllRows() {

        try {
            return reader.readAll();
        } catch (IOException e) {
            logger.error("Unable to read file");
            return null;
        }
    }
}



