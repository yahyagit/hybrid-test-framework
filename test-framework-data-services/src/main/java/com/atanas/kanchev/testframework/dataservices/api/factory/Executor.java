package com.atanas.kanchev.testframework.dataservices.api.factory;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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
    private static HttpClientBuilder httpClientBuilder;

    /**
     * Instantiates a new executor.
     */
    public Executor() {

        if (httpClientBuilder == null)
            httpClientBuilder = HttpClients.custom()
                    .setConnectionTimeToLive(30, TimeUnit.SECONDS);

        CloseableHttpClient httpClient = httpClientBuilder.build();
        Unirest.setHttpClient(httpClient);
    }

    /**
     * Configure http client.
     *
     * @return the http client builder
     */
    public static HttpClientBuilder confClient() {
        return httpClientBuilder = HttpClients.custom();
    }

    /**
     * GET http rest request.
     *
     * @param endpoint the endpoint
     * @return the http response
     */
    public static HttpResponse<String> GET(String endpoint) {
        try {
            return Unirest.get(endpoint).asString();
        } catch (UnirestException e) {
            logger.error("Unirest exception ", e);
        }
        shutdown();
        return null;
    }

    /**
     * GET http rest request.
     *
     * @param endpoint the endpoint
     * @param headers  the headers
     * @return the http response
     */
    public static HttpResponse<String> GET(String endpoint, HashMap<String, String> headers) {
        try {
            return Unirest.get(endpoint)
                    .headers(headers)
                    .asString();
        } catch (UnirestException e) {
            logger.error("Unirest exception ", e);
        }
        shutdown();
        return null;
    }

    /**
     * POST http rest request.
     *
     * @param endpoint the endpoint
     * @param payload  the payload
     * @return the http response
     */
    public static HttpResponse<String> POST(String endpoint, String payload) {
        try {
            return Unirest.post(endpoint)
                    .body(payload)
                    .asString();
        } catch (UnirestException e) {
            logger.error("Unirest exception ", e);
        }
        shutdown();
        return null;
    }

    /**
     * POST http rest request.
     *
     * @param endpoint the endpoint
     * @param headers  the headers
     * @return the http response
     */
    public static HttpResponse<String> POST(String endpoint, HashMap<String, String> headers) {
        try {
            return Unirest.post(endpoint)
                    .headers(headers)
                    .asString();
        } catch (UnirestException e) {
            logger.error("Unirest exception ", e);
        }
        shutdown();
        return null;
    }

    /**
     * POST http rest request.
     *
     * @param endpoint the endpoint
     * @param headers  the headers
     * @param payload  the payload
     * @return the http response
     */
    public static HttpResponse<String> POST(String endpoint, HashMap<String, String> headers, String payload) {
        try {
            return Unirest.post(endpoint).headers(headers)
                    .body(payload)
                    .asString();
        } catch (UnirestException e) {
            logger.error("Unirest exception ", e);
        }
        shutdown();
        return null;
    }

    /**
     * POST http rest request.
     *
     * @param endpoint the endpoint
     * @return the http response
     */
    public static HttpResponse<String> POST(String endpoint) {
        try {
            return Unirest.post(endpoint)
                    .asString();

        } catch (Exception e) {
            logger.error("Unirest exception ", e);
        }
        shutdown();
        return null;
    }

    /**
     * PUT http rest request.
     *
     * @param endpoint the endpoint
     * @param payload  the payload
     * @return the http response
     */
    public static HttpResponse<String> PUT(String endpoint, String payload) {
        try {
            return Unirest.put(endpoint)
                    .body(payload)
                    .asString();
        } catch (UnirestException e) {
            logger.error("Unirest exception ", e);
        }
        shutdown();
        return null;
    }

    /**
     * PUT http rest request.
     *
     * @param endpoint the endpoint
     * @param headers  the headers
     * @param payload  the payload
     * @return the http response
     */
    public static HttpResponse<String> PUT(String endpoint, HashMap<String, String> headers, String payload) {
        try {
            return Unirest.put(endpoint)
                    .headers(headers)
                    .body(payload)
                    .asString();
        } catch (UnirestException e) {
            logger.error("Unirest exception ", e);
        }
        shutdown();
        return null;
    }

    /**
     * PUT http rest request.
     *
     * @param endpoint the endpoint
     * @return the http response
     */
    public static HttpResponse<String> PUT(String endpoint) {
        try {
            return Unirest.put(endpoint)
                    .asString();
        } catch (UnirestException e) {
            logger.error("Unirest exception ", e);
        }
        shutdown();
        return null;
    }

    /**
     * DELETE http rest request.
     *
     * @param endpoint the endpoint
     * @param headers  the headers
     * @param payload  the payload
     * @return the http response
     */
    public static HttpResponse<String> DELETE(String endpoint, HashMap<String, String> headers, String payload) {
        try {
            return Unirest.delete(endpoint)
                    .headers(headers)
                    .body(payload)
                    .asString();
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
    private static void shutdown() {
        try {
            Unirest.shutdown();
        } catch (IOException e) {
            logger.error("IO exception ", e);
        }
    }
}