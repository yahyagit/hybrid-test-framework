package com.atanas.kanchev.testframework.dataservices.tests.api;

import com.atanas.kanchev.testframework.commons.properties.PropertyReader;
import com.atanas.kanchev.testframework.dataservices.api.factory.Resource;
import com.mashape.unirest.http.HttpMethod;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by atanas on 15/08/16.
 */
public class JSONPlaceholderGetResource extends Resource {

    public JSONPlaceholderGetResource() throws MalformedURLException {
        this(new URL(PropertyReader.getProp("test.env.properties", "root")),
            PropertyReader.getProp("endpoints.properties", "get"));
    }

    public JSONPlaceholderGetResource(URL url) {
        this(url, PropertyReader.getProp("endpoints.properties", "get"));
    }

    public JSONPlaceholderGetResource(String endpoint) throws MalformedURLException {
        this(new URL(PropertyReader.getProp("test.env.properties", "root")), endpoint);
    }

    public JSONPlaceholderGetResource(URL url, String endpoint) {
        super(HttpMethod.GET);
        super.url.append(url);
        super.endpoint.append(endpoint);
    }

}
