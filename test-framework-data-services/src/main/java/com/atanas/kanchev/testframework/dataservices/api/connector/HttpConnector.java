package com.atanas.kanchev.testframework.dataservices.api.connector;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Http Connector boilerplate
 * @author Atanas Ksnchev
 */
public class HttpConnector {

    private String endpoint;
    private String username;
    private String passowrd;

    public HttpConnector(String endpoint, String username, String passowrd) {
        this.endpoint = endpoint;
        this.username = username;
        this.passowrd = passowrd;
    }

    public HttpResponse<JsonNode> get() throws UnirestException {
        return Unirest.get(endpoint).asJson();
    }

    public HttpResponse<JsonNode> post() throws UnirestException {
        return Unirest.post(endpoint).asJson();
    }
}
