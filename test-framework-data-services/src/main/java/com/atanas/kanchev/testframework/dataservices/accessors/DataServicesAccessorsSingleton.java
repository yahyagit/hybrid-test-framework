/*
 * Copyright 2016 Atanas Stoychev Kanchev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atanas.kanchev.testframework.dataservices.accessors;

import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource;
import com.atanas.kanchev.testframework.dataservices.context.APIResourceContext;
import com.atanas.kanchev.testframework.dataservices.dataprovider.csv.CSVParser;
import com.atanas.kanchev.testframework.dataservices.dataprovider.excel.ExcelParser;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.$context;

/**
 * @author Atanas Kanchev
 */
public class DataServicesAccessorsSingleton {

    private static DataServicesAccessorsSingleton instance = null;
    ContextKey<APIResourceContext> key;

    private DataServicesAccessorsSingleton() {
    }

    static DataServicesAccessorsSingleton getInstance() {
        if (instance == null) {
            instance = new DataServicesAccessorsSingleton();
        }
        return instance;
    }

    public Resource apiResource(Resource resource) {
        return $context().getContext(key).setResource(resource).getResource();
    }

    public Resource apiResource() {
        return $context().getContext(key).getResource();
    }

    public ExcelParser excel(String filePath) {
        return new ExcelParser(filePath);
    }

    public CSVParser csv(String filePath) {
        return new CSVParser(filePath);
    }

}
