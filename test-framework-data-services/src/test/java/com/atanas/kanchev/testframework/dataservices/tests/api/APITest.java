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

import com.google.gson.JsonObject;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


/**
 * Created by atanas on 15/08/16.
 */
public class APITest {

    @Test
    public void showingResource_GET_Test() throws Exception {

        JSONPlaceholderGetResource resource = new JSONPlaceholderGetResource();
        resource.exec();

        assertThat(resource.getResponse().getStatusCode(), equalTo(200));
        assertThat(resource.getResponse().getReason(), equalTo("OK"));

    }


    @Test
    public void showingResource_GET_Add_QueryTest() throws Exception {

        JSONPlaceholderGetResource resource = new JSONPlaceholderGetResource();
        resource.getRequest().addQueryParameter("query", "value");
        resource.exec();

        assertThat(resource.getResponse().getStatusCode(), equalTo(200));
        assertThat(resource.getResponse().getReason(), equalTo("OK"));

    }



    @Test
    public void listingResources_GET_Test() throws Exception {

        JSONPlaceholderGetResource resource = new JSONPlaceholderGetResource("/posts");
        resource.setEndpoint("/1");
        resource.exec();

        assertThat(resource.getResponse().getStatusCode(), equalTo(200));
        assertThat(resource.getResponse().getReason(), equalTo("OK"));

    }

    @Test
    public void putTest() throws Exception {

        JsonObject jsonObject = JsonBuilderFactory.buildObject()
                .addObject("data")
                .add("title", "foo")
                .add("body", "bar")
                .add("userID", 1)
                .getJson();

        JSONPlaceholderPutResource resource = new JSONPlaceholderPutResource();

        resource.getRequest().setBody(jsonObject.toString());
        resource.setEndpoint("/2");
        resource.exec();

        assertThat(resource.getResponse().getStatusCode(), equalTo(200));
        assertThat(resource.getResponse().getReason(), equalTo("OK"));

    }
}

