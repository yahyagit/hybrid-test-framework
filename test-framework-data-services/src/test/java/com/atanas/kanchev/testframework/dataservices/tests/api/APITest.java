package com.atanas.kanchev.testframework.dataservices.tests.api;

import com.atanas.kanchev.testframework.dataservices.wrappers.IAPIResource;
import com.google.gson.JsonObject;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by atanas on 15/08/16.
 */
public class APITest implements IAPIResource {

    @After public void tearDown() throws Exception {
        context().tearDownContexts();
    }

    @Test public void showingResource_GET_Test() throws Exception {

        apiResource(new JSONPlaceholderGetResource()).exec();

        assertThat(apiResource().getResponse().getStatusCode(), equalTo(200));
        assertThat(apiResource().getResponse().getReason(), equalTo("OK"));

    }

    @Test public void listingResources_GET_Test() throws Exception {

        apiResource(new JSONPlaceholderGetResource("/posts"));
        apiResource().appendToEndpoint("/1").exec();

        assertThat(apiResource().getResponse().getStatusCode(), equalTo(200));
        assertThat(apiResource().getResponse().getReason(), equalTo("OK"));

    }

    @Test public void putTest() throws Exception {

        JsonObject jsonObject =
            JsonBuilderFactory.buildObject()
                .addObject("data")
                .add("title", "foo")
                .add("body", "bar")
                .add("userID", 1)
                .getJson();

        apiResource(new JSONPlaceholderPutResource())
            .getRequest()
            .setBody(jsonObject.toString());

        apiResource().appendToEndpoint("/2").exec();

        assertThat(apiResource().getResponse().getStatusCode(), equalTo(200));
        assertThat(apiResource().getResponse().getReason(), equalTo("OK"));

    }
}

