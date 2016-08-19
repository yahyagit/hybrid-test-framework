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

package com.atanas.kanchev.testframework.dataservices.tests.api.rest.executor;

import com.atanas.kanchev.testframework.dataservices.api.rest.executor.Executor;
import com.atanas.kanchev.testframework.dataservices.api.rest.objectmapper.JacksonMapper;
import com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource;
import com.atanas.kanchev.testframework.dataservices.tests.api.rest.objectmapper.Posts;
import com.mashape.unirest.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class ExecutorTest extends Executor {

    private static final Posts POSTS = new Posts() {{
        setId(1);
        setUserId(1);
        setTitle("title");
    }};

    private static final HashMap<String, String> HEADERS = new HashMap<String, String>() {{
        put("key", "value");
    }};
    private static final String PAYLOAD = "{}";

    @Test
    public void confClientTest() throws Exception {
        confClient().disableCookieManagement().setUserAgent("userAgent");
    }

    @Test
    public void GET() throws Exception {
        Resource resource = new JSONPlaceholderResource().new JSONPlaceholderGETResource();
        HttpResponse<String> resp = GET
                (
                        resource.getUrl()
                );
        Assert.assertEquals(200, resp.getStatus());
        Assert.assertEquals("OK", resp.getStatusText());
    }

    @Test
    public void GET1() throws Exception {
        Resource resource = new JSONPlaceholderResource().new JSONPlaceholderGETResource();
        resource.getRequest().setHeaders(HEADERS);
        HttpResponse<String> resp = GET
                (
                        resource.getUrl(),
                        resource.getRequest().getHeaders()
                );
        Assert.assertEquals(200, resp.getStatus());
        Assert.assertEquals("OK", resp.getStatusText());
    }

    @Test
    public void POST() throws Exception {
        Resource resource = new JSONPlaceholderResource().new JSONPlaceholderPOSTResource();
        HttpResponse<String> resp = POST
                (
                        resource.getUrl().concat(resource.getEndpoint())
                );
        Assert.assertEquals(201, resp.getStatus());
        Assert.assertEquals("Created", resp.getStatusText());
    }

    @Test
    public void POST1() throws Exception {
        Resource resource = new JSONPlaceholderResource().new JSONPlaceholderPOSTResource();
        resource.getRequest().setPayload("");
        HttpResponse<String> resp = POST
                (
                        resource.getUrl().concat(resource.getEndpoint()),
                        resource.getRequest().getPayload()
                );
        Assert.assertEquals(201, resp.getStatus());
        Assert.assertEquals("Created", resp.getStatusText());
    }

    @Test
    public void POST2() throws Exception {
        Resource resource = new JSONPlaceholderResource().new JSONPlaceholderPOSTResource();
        resource.getRequest().setHeaders(HEADERS);
        HttpResponse<String> resp = POST
                (
                        resource.getUrl().concat(resource.getEndpoint()),
                        resource.getRequest().getHeaders()
                );
        Assert.assertEquals(201, resp.getStatus());
        Assert.assertEquals("Created", resp.getStatusText());
    }

    @Test
    public void POST3() throws Exception {
        Resource resource = new JSONPlaceholderResource().new JSONPlaceholderPOSTResource();
        resource.getRequest().setHeaders(HEADERS);
        resource.getRequest().setPayload("");
        HttpResponse<String> resp = POST
                (
                        resource.getUrl().concat(resource.getEndpoint()),
                        resource.getRequest().getHeaders(),
                        resource.getRequest().getPayload()
                );
        Assert.assertEquals(201, resp.getStatus());
        Assert.assertEquals("Created", resp.getStatusText());
    }

    @Test
    public void PUT() throws Exception {
        Resource resource = new JSONPlaceholderResource().new JSONPlaceholderPUTResource();
        HttpResponse<String> resp = PUT
                (
                        resource.getUrl().concat(resource.getEndpoint())
                );
        Assert.assertEquals(200, resp.getStatus());
        Assert.assertEquals("OK", resp.getStatusText());
    }

    @Test
    public void PUT1() throws Exception {
        String json = JacksonMapper.objectToJson(POSTS);
        Resource resource = new JSONPlaceholderResource().new JSONPlaceholderPUTResource();
        resource.getRequest().setPayload(json);
        HttpResponse<String> resp = PUT
                (
                        resource.getUrl().concat(resource.getEndpoint()),
                        resource.getRequest().getPayload()
                );
        Assert.assertEquals(200, resp.getStatus());
        Assert.assertEquals("OK", resp.getStatusText());

    }

    @Test
    public void PUT2Test() throws Exception {
        String json = JacksonMapper.objectToJson(POSTS);
        Resource resource = new JSONPlaceholderResource().new JSONPlaceholderPUTResource();
        resource.getRequest().setPayload(json);
        resource.getRequest().setHeaders(HEADERS);
        HttpResponse<String> resp = PUT
                (
                        resource.getUrl().concat(resource.getEndpoint()),
                        resource.getRequest().getHeaders(),
                        resource.getRequest().getPayload()
                );
        Assert.assertEquals(200, resp.getStatus());
        Assert.assertEquals("OK", resp.getStatusText());

    }

    @Test
    public void DELETE() throws Exception {
        Resource resource = new JSONPlaceholderResource().new JSONPlaceholderDELETEResource();
        HttpResponse<String> resp = DELETE
                (
                        resource.getUrl().concat(resource.getEndpoint())
                );
        Assert.assertEquals(200, resp.getStatus());
        Assert.assertEquals("OK", resp.getStatusText());
    }

    @Test
    public void DELETE1() throws Exception {

        Resource resource = new JSONPlaceholderResource().new JSONPlaceholderDELETEResource();
        resource.getRequest().setPayload("{}");
        HttpResponse<String> resp = DELETE
                (
                        resource.getUrl().concat(resource.getEndpoint()),
                        resource.getRequest().getPayload()
                );
        Assert.assertEquals(200, resp.getStatus());
        Assert.assertEquals("OK", resp.getStatusText());
    }

    @Test
    public void DELETE2() throws Exception {
        String json = JacksonMapper.objectToJson(POSTS);
        Resource resource = new JSONPlaceholderResource().new JSONPlaceholderDELETEResource();
        resource.getRequest().setPayload(json);
        resource.getRequest().setHeaders(HEADERS);
        HttpResponse<String> resp = DELETE
                (
                        resource.getUrl().concat(resource.getEndpoint()),
                        resource.getRequest().getHeaders(),
                        resource.getRequest().getPayload()
                );
        Assert.assertEquals(200, resp.getStatus());
        Assert.assertEquals("OK", resp.getStatusText());
    }

    @Test
    public void DELETE3Test() throws Exception {
        Resource resource = new JSONPlaceholderResource().new JSONPlaceholderDELETEResource();
        resource.getRequest().setHeaders(HEADERS);
        HttpResponse<String> resp = DELETE
                (
                        resource.getUrl().concat(resource.getEndpoint()),
                        resource.getRequest().getHeaders()
                );
        Assert.assertEquals(200, resp.getStatus());
        Assert.assertEquals("OK", resp.getStatusText());

    }

    @Test
    public void shutdownTest() throws Exception {
        shutdown();
    }

}
