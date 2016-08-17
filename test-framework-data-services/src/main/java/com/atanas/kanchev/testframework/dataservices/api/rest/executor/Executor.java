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
 * The type Api executor.
 *
 * @author Atanas Kanchev
 */
public class Executor {

    private static final Logger logger = LoggerFactory.getLogger(Executor.class);
    static HttpClientBuilder httpClientBuilder;

    /**
     * Configure http client.
     *
     * @return the http client builder {@link HttpClientBuilder}
     */
    public static HttpClientBuilder confClient() {

        return httpClientBuilder = HttpClients.custom();
    }

    /**
     * GET http rest request.
     *
     * @param endpoint the endpoint {@code java.lang.String}
     * @return the http response {@code com.mashape.unirest.http.HttpResponse}
     */
    public HttpResponse<String> GET(String endpoint) {

        return GET(endpoint, null);
    }

    /**
     * GET http rest request.
     *
     * @param endpoint the endpoint {@code java.lang.String}
     * @param headers  the headers {@code java.util.HashMap}
     * @return the http response {@code com.mashape.unirest.http.HttpResponse}
     */
    public HttpResponse<String> GET(String endpoint, HashMap<String, String> headers) {

        return exec(HttpMethod.GET, endpoint, headers, null);
    }

    /**
     * POST http rest request.
     *
     * @param endpoint the endpoint {@code java.lang.String}
     * @return the http response {@code com.mashape.unirest.http.HttpResponse}
     */
    public HttpResponse<String> POST(String endpoint) {

        return POST(endpoint, null, null);
    }

    /**
     * POST http rest request.
     *
     * @param endpoint the endpoint {@code java.lang.String}
     * @param payload  the payload {@code java.lang.String}
     * @return the http response {@code com.mashape.unirest.http.HttpResponse}
     */
    public HttpResponse<String> POST(String endpoint, String payload) {

        return POST(endpoint, null, payload);
    }

    /**
     * POST http rest request.
     *
     * @param endpoint the endpoint {@code java.lang.String}
     * @param headers  the headers {@code java.util.HashMap}
     * @return the http response {@code com.mashape.unirest.http.HttpResponse}
     */
    public HttpResponse<String> POST(String endpoint, HashMap<String, String> headers) {

        return POST(endpoint, headers, null);
    }

    /**
     * POST http rest request with headers and a payload.
     *
     * @param endpoint the endpoint {@code java.lang.String}
     * @param headers  the headers {@code java.util.HashMap}
     * @param payload  the payload {@code java.lang.String}
     * @return the http response {@code com.mashape.unirest.http.HttpResponse}
     */
    public HttpResponse<String> POST(String endpoint, HashMap<String, String> headers, String payload) {

        return exec(HttpMethod.POST, endpoint, headers, payload);
    }

    /**
     * PUT http rest request.
     *
     * @param endpoint the endpoint {@code java.lang.String}
     * @return the http response {@code com.mashape.unirest.http.HttpResponse}
     */
    public HttpResponse<String> PUT(String endpoint) {

        return PUT(endpoint, null, null);
    }

    /**
     * PUT http rest request.
     *
     * @param endpoint the endpoint {@code java.lang.String}
     * @param payload  the payload {@code java.lang.String}
     * @return the http response {@code com.mashape.unirest.http.HttpResponse}
     */
    public HttpResponse<String> PUT(String endpoint, String payload) {

        return PUT(endpoint, null, payload);
    }

    /**
     * PUT http rest request.
     *
     * @param endpoint the endpoint {@code java.lang.String}
     * @param headers  the headers {@code java.util.HashMap}
     * @param payload  the payload {@code java.lang.String}
     * @return the http response {@code com.mashape.unirest.http.HttpResponse}
     */
    public HttpResponse<String> PUT(String endpoint, HashMap<String, String> headers, String payload) {

        return exec(HttpMethod.PUT, endpoint, headers, payload);
    }

    /**
     * DELETE http rest request.
     *
     * @param endpoint the endpoint {@code java.lang.String}
     * @return the http response {@code com.mashape.unirest.http.HttpResponse}
     */
    public HttpResponse<String> DELETE(String endpoint) {

        return DELETE(endpoint, null, null);
    }

    /**
     * DELETE http rest request.
     *
     * @param endpoint the endpoint {@code java.lang.String}
     * @param payload  the payload {@code java.lang.String}
     * @return the http response {@code com.mashape.unirest.http.HttpResponse}
     */
    public HttpResponse<String> DELETE(String endpoint, String payload) {

        return DELETE(endpoint, null, payload);
    }

    /**
     * DELETE http rest request.
     *
     * @param endpoint the endpoint {@code java.lang.String}
     * @param headers  the headers {@code java.util.HashMap}
     * @return the http response {@code com.mashape.unirest.http.HttpResponse}
     */
    public HttpResponse<String> DELETE(String endpoint, HashMap<String, String> headers) {

        return DELETE(endpoint, headers, null);
    }

    /**
     * DELETE http rest request.
     *
     * @param endpoint the endpoint {@code java.lang.String}
     * @param headers  the headers {@code java.util.HashMap}
     * @param payload  the payload {@code java.lang.String}
     * @return the http response {@code com.mashape.unirest.http.HttpResponse}
     */
    public HttpResponse<String> DELETE(String endpoint, HashMap<String, String> headers, String payload) {

        return exec(HttpMethod.DELETE, endpoint, headers, payload);
    }

    private static HttpResponse<String> exec(HttpMethod httpMethod, String endpoint, HashMap<String, String> headers, String payload) {

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
            httpClientBuilder = HttpClients.custom()
                    .setConnectionTimeToLive(30, TimeUnit.SECONDS);

        CloseableHttpClient httpClient = httpClientBuilder.build();
        Unirest.setHttpClient(httpClient);

        try {
            return req.asString();
        } catch (UnirestException e) {
            logger.error("Unirest exception ", e);
        }
        shutdown();

        return null;
    }

    /**
     * Close the asynchronous client and its event loop.
     * Use this method to close all the threads and allow an application to exit.
     */
    static void shutdown() {
        try {
            Unirest.shutdown();
        } catch (IOException e) {
            logger.error("IO exception ", e);
        }
    }
}