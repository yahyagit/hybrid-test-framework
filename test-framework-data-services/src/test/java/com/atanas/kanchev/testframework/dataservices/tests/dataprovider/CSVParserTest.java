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

import com.atanas.kanchev.testframework.dataservices.dataprovider.csv.CSVParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CSVParserTest {

    @Test
    public void getAllRows() throws Exception {
        CSVParser csvParser = new CSVParser("src/test/resources/TestCSV.csv");
        List<String[]> result = csvParser.getAllRows();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() == 3);
        Assert.assertTrue(result.get(0).length == 10);
        Assert.assertTrue(result.get(2).length == 6);

    }
}