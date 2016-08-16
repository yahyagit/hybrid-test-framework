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
    public void listingResources_GET_Test() throws Exception {

        JSONPlaceholderGetResource resource = new JSONPlaceholderGetResource("/posts");
        resource.appendToEndpoint("/1");
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
        resource.appendToEndpoint("/2");
        resource.exec();

        assertThat(resource.getResponse().getStatusCode(), equalTo(200));
        assertThat(resource.getResponse().getReason(), equalTo("OK"));

    }
}

