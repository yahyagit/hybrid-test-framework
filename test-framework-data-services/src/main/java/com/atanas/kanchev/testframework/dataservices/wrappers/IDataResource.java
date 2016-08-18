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

package com.atanas.kanchev.testframework.dataservices.wrappers;

import com.atanas.kanchev.testframework.commons.wrappers.IContext;
import com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource;
import com.atanas.kanchev.testframework.dataservices.context.APIResourceContext;
import com.atanas.kanchev.testframework.dataservices.dataprovider.csv.CSVParser;
import com.atanas.kanchev.testframework.dataservices.dataprovider.excel.ExcelParser;

public interface IDataResource extends IContext {

    default Resource apiResource(Resource resource) {
        return ((APIResourceContext) context().getCurrentContext()).setResource(resource)
                .getResource();
    }

    default Resource apiResource() {
        return ((APIResourceContext) context().getCurrentContext()).getResource();
    }

    default ExcelParser excel(String filePath) {
        return new ExcelParser(filePath);
    }

    default CSVParser csv(String filePath) {
        return new CSVParser(filePath);
    }
}
