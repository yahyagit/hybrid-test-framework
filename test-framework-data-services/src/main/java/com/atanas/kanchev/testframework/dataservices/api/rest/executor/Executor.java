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

package com.atanas.kanchev.testframework.dataservices.api.rest.executor;

import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * <p>Executor class.</p>
 *
 * @author Atanas Kanchev
 */
public class Executor {

    private static final Logger logger = LoggerFactory.getLogger(Executor.class);
    static HttpClientBuilder httpClientBuilder;

    /**
     * <p>Configure custom cClient.</p>
     *
     * @return a {@link org.apache.http.impl.client.HttpClientBuilder} object.
     */
    public static HttpClientBuilder confClient() {

        return httpClientBuilder = HttpClients.custom();
    }

    /**
     * <p>GET.</p>
     *
     * @param endpoint a {@link java.lang.String} object.
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     */
    public HttpResponse<String> GET(String endpoint) {

        return GET(endpoint, null);
    }

    /**
     * <p>GET.</p>
     *
     * @param endpoint a {@link java.lang.String} object.
     * @param headers  a {@link java.util.HashMap} object.
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     */
    public HttpResponse<String> GET(String endpoint, HashMap<String, String> headers) {

        return exec(HttpMethod.GET, endpoint, headers, null);
    }

    /**
     * <p>POST.</p>
     *
     * @param endpoint a {@link java.lang.String} object.
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     */
    public HttpResponse<String> POST(String endpoint) {

        return POST(endpoint, null, null);
    }

    /**
     * <p>POST.</p>
     *
     * @param endpoint a {@link java.lang.String} object.
     * @param payload  a {@link java.lang.String} object.
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     */
    public HttpResponse<String> POST(String endpoint, String payload) {

        return POST(endpoint, null, payload);
    }

    /**
     * <p>POST.</p>
     *
     * @param endpoint a {@link java.lang.String} object.
     * @param headers  a {@link java.util.HashMap} object.
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     */
    public HttpResponse<String> POST(String endpoint, HashMap<String, String> headers) {

        return POST(endpoint, headers, null);
    }

    /**
     * <p>POST.</p>
     *
     * @param endpoint a {@link java.lang.String} object.
     * @param headers  a {@link java.util.HashMap} object.
     * @param payload  a {@link java.lang.String} object.
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     */
    public HttpResponse<String> POST(String endpoint, HashMap<String, String> headers,
        String payload) {

        return exec(HttpMethod.POST, endpoint, headers, payload);
    }

    /**
     * <p>PUT.</p>
     *
     * @param endpoint a {@link java.lang.String} object.
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     */
    public HttpResponse<String> PUT(String endpoint) {

        return PUT(endpoint, null, null);
    }

    /**
     * <p>PUT.</p>
     *
     * @param endpoint a {@link java.lang.String} object.
     * @param payload  a {@link java.lang.String} object.
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     */
    public HttpResponse<String> PUT(String endpoint, String payload) {

        return PUT(endpoint, null, payload);
    }

    /**
     * <p>PUT.</p>
     *
     * @param endpoint a {@link java.lang.String} object.
     * @param headers  a {@link java.util.HashMap} object.
     * @param payload  a {@link java.lang.String} object.
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     */
    public HttpResponse<String> PUT(String endpoint, HashMap<String, String> headers,
        String payload) {

        return exec(HttpMethod.PUT, endpoint, headers, payload);
    }

    /**
     * <p>DELETE.</p>
     *
     * @param endpoint a {@link java.lang.String} object.
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     */
    public HttpResponse<String> DELETE(String endpoint) {

        return DELETE(endpoint, null, null);
    }

    /**
     * <p>DELETE.</p>
     *
     * @param endpoint a {@link java.lang.String} object.
     * @param payload  a {@link java.lang.String} object.
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     */
    public HttpResponse<String> DELETE(String endpoint, String payload) {

        return DELETE(endpoint, null, payload);
    }

    /**
     * <p>DELETE.</p>
     *
     * @param endpoint a {@link java.lang.String} object.
     * @param headers  a {@link java.util.HashMap} object.
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     */
    public HttpResponse<String> DELETE(String endpoint, HashMap<String, String> headers) {

        return DELETE(endpoint, headers, null);
    }

    /**
     * <p>DELETE.</p>
     *
     * @param endpoint a {@link java.lang.String} object.
     * @param headers  a {@link java.util.HashMap} object.
     * @param payload  a {@link java.lang.String} object.
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     */
    public HttpResponse<String> DELETE(String endpoint, HashMap<String, String> headers,
        String payload) {

        return exec(HttpMethod.DELETE, endpoint, headers, payload);
    }

    private static HttpResponse<String> exec(HttpMethod httpMethod, String endpoint,
        HashMap<String, String> headers, String payload) {

        HttpRequest req;

        if (httpMethod == HttpMethod.GET)
            req = new GetRequest(httpMethod, endpoint);
        else
            req = new HttpRequestWithBody(httpMethod, endpoint);
        if (headers != null)
            req.headers(headers);
        if (payload != null)
            ((HttpRequestWithBody) req).body(payload);


        if (httpClientBuilder == null)
            httpClientBuilder = HttpClients.custom().setConnectionTimeToLive(30, TimeUnit.SECONDS);

        CloseableHttpClient httpClient = httpClientBuilder.build();
        Unirest.setHttpClient(httpClient);

        try {
            return req.asString();
        } catch (UnirestException e) {
            logger.error("Unirest exception ", e);
        }

        return null;
    }

    /**
     * <p>shutdown.</p>
     */
    public static void shutdown() {
        try {
            Unirest.shutdown();
        } catch (IOException e) {
            logger.error("IO exception ", e);
        }
    }
}
