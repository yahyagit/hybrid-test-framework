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

import com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Message;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Test for {@link Message}
 */
public class MessageTest {

    private static final String PAYLOAD = "{}";
    private static final HashMap<String, String> HEADERS = new HashMap<String, String>() {{
        put("key", "value");
    }};
    private static final HashMap<String, String> COOKIES = new HashMap<String, String>() {{
        put("key", "value");
    }};

    @Test
    public void setPayloadTest() throws Exception {
        Message message = new Message();
        message.setPayload(PAYLOAD);

    }

    @Test
    public void getPayloadTest() throws Exception {
        Message message = new Message();
        message.setPayload(PAYLOAD);
        Assert.assertEquals(PAYLOAD, message.getPayload());
        String newPayload = "{\"k\": \"v\"}";
        message.setPayload(PAYLOAD.replace(PAYLOAD, newPayload));
        Assert.assertEquals(newPayload, message.getPayload());
    }

    @Test
    public void setHeadersTest() throws Exception {
        Message message = new Message();
        HashMap<String, String> newHeaders = new HashMap<>();
        newHeaders.put("k", "v");
        message.setHeaders(HEADERS);
        message.setHeaders(newHeaders);
        Assert.assertEquals(2, message.getHeaders().size());
    }

    @Test
    public void getHeadersTest() throws Exception {
        Assert.assertEquals(1, HEADERS.size());
    }

    @Test
    public void setCookiesTest() throws Exception {
        Message message = new Message();
        HashMap<String, String> newCookies = new HashMap<>();
        newCookies.put("k", "v");
        message.setCookies(COOKIES);
        message.setCookies(newCookies);
        Assert.assertEquals(2, message.getCookies().size());
    }

    @Test
    public void getCookiesTest() throws Exception {
        Message message = new Message();
        Assert.assertEquals(0, message.getCookies().size());
    }

    @Test
    public void setHeaderTest() throws Exception {
        Message message = new Message();
        message.setHeader("k", "v");
        Assert.assertEquals(1, message.getHeaders().size());
    }

    @Test
    public void setCookieTest() throws Exception {
        Message message = new Message();
        message.setCookie("k", "v");
        Assert.assertEquals(1, message.getCookies().size());
    }

    @Test
    public void immutableCookiesMapTest() throws Exception {
        Message message = new Message();
        message.setCookie("k", "v");
        Assert.assertEquals(1, message.getCookies().size());
        message.getCookies().put("newKey", "v");
        Assert.assertEquals(1, message.getCookies().size());
    }

    @Test
    public void immutableHeadersMapTest() throws Exception {
        Message message = new Message();
        message.setHeader("k", "v");
        Assert.assertEquals(1, message.getHeaders().size());
        message.getHeaders().put("newKey", "v");
        Assert.assertEquals(1, message.getHeaders().size());
    }

}