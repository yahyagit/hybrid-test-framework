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

package com.atanas.kanchev.testframework.dataservices.tests.dataprovider;

import com.atanas.kanchev.testframework.dataservices.dataprovider.excel.ExcelParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ExcelParserTest {

    private ExcelParser parser = new ExcelParser("src/test/resources/TestExcelSheet.xlsx");

    @Test
    public void getData() throws Exception {
        Assert.assertNotNull(parser.getData("Sheet1"));

    }

    @Test
    public void getData1() throws Exception {
        Assert.assertNotNull(parser.getData("Sheet1", "Header2"));
    }

    @Test
    public void getTableData() throws Exception {
        Assert.assertNotNull(parser.getTableData("Sheet1"));
    }

    @Test
    public void getTableData1() throws Exception {
        List<Map<String, Object>> result = parser.getTableData("Sheet1", "Header2");
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() == 3);
    }

}