package com.atanas.kanchev.testframework.dataservices.api.json;

public final class JsonMessageBuilder {

    private JsonMessageBuilder() {
    }

    public static JsonObjectBuilder buildObject() {
        return new JsonObjectBuilder();
    }

    public static JsonArrayBuilder buildArray() {
        return new JsonArrayBuilder();
    }
}
