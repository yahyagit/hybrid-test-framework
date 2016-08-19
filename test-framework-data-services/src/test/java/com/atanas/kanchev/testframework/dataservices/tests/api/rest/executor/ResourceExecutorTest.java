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

package com.atanas.kanchev.testframework.dataservices.tests.api.rest.executor;

import com.atanas.kanchev.testframework.dataservices.api.rest.executor.ResourceExecutor;
import com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource;
import com.atanas.kanchev.testframework.dataservices.dataprovider.properties.PropertyReader;
import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

public class ResourceExecutorTest {


    @Test public void executeResource() throws Exception {

        Resource resource = new Resource(
                HttpMethod.GET,
                PropertyReader.getProp("test.env", "root"),
                PropertyReader.getProp("endpoints", "get"));

        HttpResponse response = new ResourceExecutor(resource).executeResource();
        resource.getResponse().getStatusCode();
        Assert.assertEquals(200, response.getStatus());

    }

}
