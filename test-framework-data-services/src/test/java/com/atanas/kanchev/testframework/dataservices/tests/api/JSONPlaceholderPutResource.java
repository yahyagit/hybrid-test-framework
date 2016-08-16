package com.atanas.kanchev.testframework.dataservices.tests.api;

import com.atanas.kanchev.testframework.commons.properties.PropertyReader;
import com.atanas.kanchev.testframework.dataservices.api.factory.Resource;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by atanas on 15/08/16.
 */
public class JSONPlaceholderPutResource extends Resource {

    public JSONPlaceholderPutResource() throws MalformedURLException {
        this(new URL(PropertyReader.getProp("test.env.properties", "root")), PropertyReader.getProp("endpoints.properties", "put"));
    }

    public JSONPlaceholderPutResource(URL url) {
        this(url, PropertyReader.getProp("endpoints.properties", "put"));
    }

    public JSONPlaceholderPutResource(String endpoint) throws MalformedURLException {
        this(new URL(PropertyReader.getProp("test.env.properties", "root")), endpoint);
    }

    public JSONPlaceholderPutResource(URL url, String endpoint) {
        super(HttpMethodsEnum.PUT);
        super.url.append(url);
        super.endpoint.append(endpoint);
    }

}
