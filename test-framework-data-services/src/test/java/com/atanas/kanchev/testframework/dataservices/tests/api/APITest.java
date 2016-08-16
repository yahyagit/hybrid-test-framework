package com.atanas.kanchev.testframework.dataservices.tests.api;

import com.google.gson.JsonObject;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.junit.Test;

/**
 * Created by atanas on 15/08/16.
 */
public class APITest {

    @Test
    public void getTest() throws Exception {

        GetResource resource = new GetResource();
        resource.exec();

    }

    @Test
    public void putTest() throws Exception {

        JsonObject jsonObject = JsonBuilderFactory.buildObject()
                .addObject("data")
                .add("title", "foo")
                .add("body", "bar")
                .add("userID", 1)
                .getJson();

        PutResource resource = new PutResource();

        resource.getRequest().setBody(jsonObject.toString());
        resource.exec();

    }
}

