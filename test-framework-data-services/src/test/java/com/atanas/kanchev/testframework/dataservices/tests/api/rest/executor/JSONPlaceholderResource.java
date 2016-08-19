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

package com.atanas.kanchev.testframework.dataservices.tests.api.rest.executor;

import com.atanas.kanchev.testframework.dataservices.dataprovider.properties.PropertyReader;
import com.atanas.kanchev.testframework.dataservices.api.rest.requetsfactory.Resource;
import com.mashape.unirest.http.HttpMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class JSONPlaceholderResource {

    public class JSONPlaceholderGETResource extends Resource {

        public JSONPlaceholderGETResource() throws MalformedURLException {
            this(new URL(PropertyReader.getProp("test.env", "root")),
                    PropertyReader.getProp("endpoints", "get"));
        }

        public JSONPlaceholderGETResource(URL url, String endpoint) {
            super(HttpMethod.GET);
            super.appendToURL(url.toString());
            super.appendToEndpoint(endpoint);

        }
    }

    public class JSONPlaceholderPOSTResource extends Resource {

        public JSONPlaceholderPOSTResource() throws MalformedURLException {
            this(new URL(PropertyReader.getProp("test.env", "root")), PropertyReader.getProp("endpoints", "post"));
        }

        public JSONPlaceholderPOSTResource(URL url, String endpoint) {
            super(HttpMethod.POST);
            super.appendToURL(url.toString());
            super.appendToEndpoint(endpoint);
        }
    }

    public class JSONPlaceholderPUTResource extends Resource {

        public JSONPlaceholderPUTResource() throws MalformedURLException {
            this(new URL(PropertyReader.getProp("test.env", "root")), PropertyReader.getProp("endpoints", "put"));
        }

        public JSONPlaceholderPUTResource(String endpoint) throws MalformedURLException {
            this(new URL(PropertyReader.getProp("test.env", "root")), endpoint);
        }

        public JSONPlaceholderPUTResource(URL url, String endpoint) {
            super(HttpMethod.PUT);
            super.appendToURL(url.toString());
            super.appendToEndpoint(endpoint);
        }
    }

    public class JSONPlaceholderDELETEResource extends Resource {

        public JSONPlaceholderDELETEResource() throws MalformedURLException {
            this(new URL(PropertyReader.getProp("test.env", "root")),
                    PropertyReader.getProp("endpoints", "delete"));
        }

        public JSONPlaceholderDELETEResource(URL url, String endpoint) {
            super(HttpMethod.DELETE);
            super.appendToURL(url.toString());
            super.appendToEndpoint(endpoint);

        }
    }

}