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

package com.atanas.kanchev.testframework.dataservices.tests.api.rest.requestfactory;

import com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Request;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link Request}
 */
public class RequestTest {

    @Test
    public void setContentTypeJSONTEst() throws Exception {
        Request request = new Request();
        request.setContentType(Request.ApplicationType.JSON);
        Assert.assertEquals(Request.ApplicationType.JSON.toString(), request.getHeaders().get("Content-Type"));

    }

    @Test
    public void setContentTypeXMLTEst() throws Exception {
        Request request = new Request();
        request.setContentType(Request.ApplicationType.XML);
        Assert.assertEquals(Request.ApplicationType.XML.toString(), request.getHeaders().get("Content-Type"));

    }

    @Test
    public void addQueryParameter() throws Exception {
        Request request = new Request();
        request.addQueryParameter("name", "value");
        Assert.assertEquals("value", request.getQueryParameters().get("name"));
        Assert.assertEquals(1, request.getQueryParameters().size());
    }

    @Test
    public void getQueryParameters() throws Exception {
        Request request = new Request();
        request.addQueryParameter("name", "value");
        request.addQueryParameter("anotherName", "anotherValue");
        Assert.assertEquals("value", request.getQueryParameters().get("name"));
        Assert.assertEquals("anotherValue", request.getQueryParameters().get("anotherName"));
        Assert.assertEquals(2, request.getQueryParameters().size());

        System.out.println(request.toString());
    }

}