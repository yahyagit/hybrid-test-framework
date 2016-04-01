package com.atanas.kanchev.testframework.dataservices.api.json;

public final class JsonMessageBuilder {

    private JsonMessageBuilder() {
    }

    /***
     * @return - JSONObjectBuilder instance
     */
    public static JsonObjectBuilder buildObject() {
        return new JsonObjectBuilder();
    }

    /***
     * @return - JSONArrayBuilder instance
     */
    public static JsonArrayBuilder buildArray() {
        return new JsonArrayBuilder();
    }
}
