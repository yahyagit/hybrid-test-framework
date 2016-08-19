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
import com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource;
import com.atanas.kanchev.testframework.dataservices.dataprovider.properties.PropertyReader;
import com.mashape.unirest.http.HttpMethod;
import com.sun.org.apache.xerces.internal.util.URI;
import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;

/**
 * Test for {@link Resource}
 */
public class ResourceTest {

    private static final String URL = "https://www.bbc.co.uk";
    private static final String ENDPOINT = "/endpoint";

    @Test
    public void getRequest() throws Exception {

        Request request = new Request();

        Resource resource = new Resource(HttpMethod.GET);
        resource.setRequest(request);
        Assert.assertEquals(request, resource.getRequest());

        Request newRequest = new Request();
        resource.setRequest(newRequest);
        Assert.assertEquals(newRequest, resource.getRequest());
    }

    @Test
    public void setRequest() throws Exception {

        Request request = new Request();
        Resource resource = new Resource(HttpMethod.GET);
        resource.setRequest(request);
        Assert.assertSame(request, resource.getRequest());
    }

    @Test
    public void setURL() throws Exception {

        Resource resource = new Resource(HttpMethod.GET);
        resource.appendToURL(URL);
        Assert.assertEquals(URL, resource.getUrl().toString());
    }

    @Test(expected = RuntimeException.class)
    public void getInvalidUrl() throws Exception {

        Resource resource = new Resource(HttpMethod.GET);
        resource.appendToURL(URL.replace("http", "£"));
        Assert.assertNull(resource.getUrl());
    }

    @Test
    public void getUrl() throws Exception {

        Resource resource = new Resource(HttpMethod.GET);
        resource.appendToURL(URL);
        Assert.assertEquals(URL, resource.getUrl().toString());
        resource.appendToURL("/news");
        Assert.assertEquals(URL.concat("/news"), resource.getUrl().toString());
    }

    @Test
    public void setEndpoint() throws Exception {

        Resource resource = new Resource(HttpMethod.POST);
        resource.appendToEndpoint(ENDPOINT);
        Assert.assertEquals(ENDPOINT, resource.getEndpoint());

    }

    @Test
    public void getEndpoint() throws Exception {

        Resource resource = new Resource(HttpMethod.POST);
        resource.appendToEndpoint(ENDPOINT);
        Assert.assertEquals(ENDPOINT, resource.getEndpoint());
        resource.appendToEndpoint("/news");
        Assert.assertEquals(ENDPOINT.concat("/news"), resource.getEndpoint());
    }

    @Test
    public void getHttpMethod() throws Exception {
        Resource resource = new Resource(HttpMethod.POST);
        Assert.assertEquals(HttpMethod.POST, resource.getHttpMethod());
    }

    @Test
    public void exec() throws Exception {
        Resource resource = new Resource(
                HttpMethod.GET,
                PropertyReader.getProp("test.env.properties", "root"),
                PropertyReader.getProp("endpoints.properties", "get"));
        resource.exec();
        Assert.assertEquals(200, resource.getResponse().getStatusCode());
    }

    @Test
    public void getResponse() throws Exception {
        Resource resource = new Resource(
                HttpMethod.GET,
                PropertyReader.getProp("test.env.properties", "root"),
                PropertyReader.getProp("endpoints.properties", "get"));
        resource.exec();
        Assert.assertEquals(200, resource.getResponse().getStatusCode());
        Assert.assertEquals("OK", resource.getResponse().getReason());
    }

}