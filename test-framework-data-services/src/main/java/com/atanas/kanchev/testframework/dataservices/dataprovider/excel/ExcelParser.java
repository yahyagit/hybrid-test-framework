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

package com.atanas.kanchev.testframework.dataservices.dataprovider.excel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * The type Excel parser.
 */
public class ExcelParser {

    private final Logger logger = LoggerFactory.getLogger(ExcelParser.class);

    private XSSFWorkbook workbook = null;

    /**
     * Instantiates a new Excel parser.
     *
     * @param file the file
     */
    public ExcelParser(String file) {
        this(new File(file));
    }

    /**
     * Instantiates a new Excel parser.
     *
     * @param file the file
     */
    public ExcelParser(File file) {
        try {
            workbook = new XSSFWorkbook(file);
        } catch (InvalidFormatException | IOException e) {
            logger.error("Error opening Excel Sheet : " + file.getAbsolutePath(), e);
        }
    }

    private List<String> getHeaders(String sheetName) {

        logger.debug("Reading headers");

        LinkedList<String> headers = new LinkedList<>();
        XSSFSheet sheet = workbook.getSheet(sheetName);

        for (int i = sheet.getRow(0).getFirstCellNum(); i < sheet.getRow(0).getLastCellNum(); i++) {
            headers.add(sheet.getRow(0).getCell(i).getStringCellValue());
            logger.debug("Header text: " + sheet.getRow(0).getCell(i).getStringCellValue());
        }
        return headers;
    }

    /**
     * Get data object [ ] [ ].
     *
     * @param sheet the sheet
     * @return the object [ ] [ ]
     */
    public synchronized Object[][] getData(String sheet) {

        List<Map<String, Object>> tableData = getTableData(sheet);
        Object[][] data = new Object[tableData.size()][1];

        int i = 0;
        for (Map<String, Object> map : tableData) {
            data[i++][0] = map;
        }

        return data;
    }

    /**
     * Get data object [ ] [ ].
     *
     * @param sheet      the sheet
     * @param columnName the column name
     * @return the object [ ] [ ]
     */
    public synchronized Object[][] getData(String sheet, String... columnName) {

        List<Map<String, Object>> tableData = getTableData(sheet, columnName);

        Object[][] data = new Object[tableData.size()][1];

        int i = 0;
        for (Map<String, Object> map : tableData) {
            data[i++][0] = map;
        }

        return data;
    }

    /**
     * Gets table data.
     *
     * @param sheetName the sheet name
     * @return the table data
     */
    public synchronized List<Map<String, Object>> getTableData(String sheetName) {

        XSSFSheet sheet = workbook.getSheet(sheetName);
        int totalColumns = sheet.getRow(0).getLastCellNum();
        logger.debug("Number of columns: " + totalColumns);
        logger.debug("Number of data rows: ".concat(String.valueOf(sheet.getLastRowNum() - sheet.getFirstRowNum())));
        List<String> header = getHeaders(sheetName);
        List<Map<String, Object>> data = new LinkedList<>();
        LinkedHashMap<String, Object> xlData;

        for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
            xlData = new LinkedHashMap<>();
            XSSFRow dRow = sheet.getRow(i);

            if (null == dRow) {
                logger.warn("Empty row, exiting excel reader");
                break;
            }

            for (int j = dRow.getFirstCellNum(); j < totalColumns; j++) {

                switch (dRow.getCell(j).getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        xlData.put(header.get(j), dRow.getCell(j).getStringCellValue());
                        logger.debug("Data in cell " + dRow.getCell(j).getAddress() + " : " + dRow.getCell(j).getStringCellValue());
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        xlData.put(header.get(j), dRow.getCell(j).getNumericCellValue());
                        logger.debug("Data in cell " + dRow.getCell(j).getAddress() + " : " + dRow.getCell(j).getNumericCellValue());
                        break;
                }
            }
            data.add(xlData);
        }
        return data;
    }

    /**
     * Gets table data.
     *
     * @param sheet      the sheet
     * @param columnName the column name
     * @return the table data
     */
    public synchronized List<Map<String, Object>> getTableData(String sheet, String... columnName) {

        List<Map<String, Object>> tableData = getTableData(sheet);
        List<Map<String, Object>> filteredData = new LinkedList<>();

        for (Map<String, Object> map : tableData) {
            Map<String, Object> filterMap = new LinkedHashMap<>();

            for (String aColumnName : columnName) {
                filterMap.put(aColumnName, map.get(aColumnName));
            }
            filteredData.add(filterMap);
        }

        logger.debug("Filtered data rows " + filteredData.size());

        return filteredData;
    }
}