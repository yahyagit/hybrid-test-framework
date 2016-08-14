package com.atanas.kanchev.testframework.dataservices.api.connector;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Http Connector boilerplate
 *
 * @author Atanas Ksnchev
 * @version $Id: $Id
 */
public class HttpConnector {

    private String endpoint;
    private String username;
    private String passowrd;

    /**
     * <p>Constructor for HttpConnector.</p>
     *
     * @param endpoint a {@link java.lang.String} object.
     * @param username a {@link java.lang.String} object.
     * @param passowrd a {@link java.lang.String} object.
     */
    public HttpConnector(String endpoint, String username, String passowrd) {
        this.endpoint = endpoint;
        this.username = username;
        this.passowrd = passowrd;
    }

    /**
     * <p>get.</p>
     *
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     * @throws com.mashape.unirest.http.exceptions.UnirestException if any.
     */
    public HttpResponse<JsonNode> get() throws UnirestException {
        return Unirest.get(endpoint).asJson();
    }

    /**
     * <p>post.</p>
     *
     * @return a {@link com.mashape.unirest.http.HttpResponse} object.
     * @throws com.mashape.unirest.http.exceptions.UnirestException if any.
     */
    public HttpResponse<JsonNode> post() throws UnirestException {
        return Unirest.post(endpoint).asJson();
    }
}
