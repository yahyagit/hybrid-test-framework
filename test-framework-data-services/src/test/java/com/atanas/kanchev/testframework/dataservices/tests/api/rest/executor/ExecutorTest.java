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
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ExecutorTest extends Executor {

    private static final String ENDPOINT = "http://www.endpoint.com";
    private static final HashMap<String, String> HEADERS = new HashMap<String, String>() {{
        put("key", "value");
    }};
    private static final String PAYLOAD = "payload";

    @Test public void confClientTest() throws Exception {
        confClient().disableCookieManagement().setUserAgent("userAgent");
    }

    @Test public void GETTest() throws Exception {
        assertThat(GET(ENDPOINT), is(notNullValue()));
    }

    @Test public void GET1Test() throws Exception {
        GET(ENDPOINT, HEADERS);
    }

    @Test public void POSTTest() throws Exception {
        POST(ENDPOINT);
    }

    @Test public void POST1Test() throws Exception {
        POST(ENDPOINT, "payload");
    }

    @Test public void POST2Test() throws Exception {
        POST(ENDPOINT, "payload");
    }

    @Test public void POST3Test() throws Exception {
        POST(ENDPOINT, HEADERS);
    }

    @Test public void PUTTest() throws Exception {
        PUT(ENDPOINT);
    }

    @Test public void PUT1Test() throws Exception {
        PUT(ENDPOINT, PAYLOAD);
    }

    @Test public void PUT2Test() throws Exception {
        PUT(ENDPOINT, HEADERS, PAYLOAD);
    }

    @Test public void DELETETest() throws Exception {
        DELETE(ENDPOINT);
    }

    @Test public void DELETE1Test() throws Exception {
        DELETE(ENDPOINT, HEADERS);
    }

    @Test public void DELETE2Test() throws Exception {
        DELETE(ENDPOINT, HEADERS, PAYLOAD);
    }

    @Test public void DELETE3Test() throws Exception {
        DELETE(ENDPOINT, PAYLOAD);
    }

    @Test public void shutdownTest() throws Exception {
        shutdown();
    }

}
