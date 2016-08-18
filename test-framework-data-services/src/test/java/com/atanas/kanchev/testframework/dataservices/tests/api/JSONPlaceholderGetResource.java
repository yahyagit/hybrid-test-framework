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

package com.atanas.kanchev.testframework.dataservices.tests.api;

import com.atanas.kanchev.testframework.dataservices.dataprovider.properties.PropertyReader;
import com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource;
import com.mashape.unirest.http.HttpMethod;

import java.net.MalformedURLException;
import java.net.URL;

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
        super.setURL(url.toString());
        super.setEndpoint(endpoint);

    }

}
