package com.atanas.kanchev.testframework.dataservices.api.json;

import org.json.JSONObject;

public final class JsonObjectBuilder implements IJsonBuilder {

    private final JSONObject jsonObject;
    private boolean isRoot;
    private boolean isParentBuilderArray;
    private IJsonBuilder jsonBuilder;
    private String key;

    {
        jsonObject = new JSONObject();
    }

    public <T> JsonObjectBuilder(T builder, String... key) {
        if (key.length > 0)
            this.key = String.valueOf(key[0]);
        if (builder instanceof JsonArrayBuilder) isParentBuilderArray = true;
        jsonBuilder = (IJsonBuilder) builder;
    }

    public JsonObjectBuilder() {
        isRoot = true;
    }

    public IJsonBuilder endObject() {
        if (isParentBuilderArray)
            jsonBuilder.addData(jsonObject);
        else
            jsonBuilder.addData(key, jsonObject);
        return jsonBuilder;
    }

    public IJsonBuilder endArray() {
        throw new IllegalStateException("Expected endObject() but Found endArray()");
    }

    public <T> IJsonBuilder addData(String key, T value) {
        jsonObject.put(key, value == null ? JSONObject.NULL : value);
        return this;
    }

    public <T> IJsonBuilder addData(T value) {
        throw new IllegalArgumentException("Json key must be provided for JSON object child. Located at : addData(" + value + ")");
    }

    public IJsonBuilder addObject(String key) {
        return new JsonObjectBuilder(this, key);
    }

    public IJsonBuilder addArray(String key) {
        return new JsonArrayBuilder(this, key);
    }

    public IJsonBuilder addObject() {
        throw new IllegalArgumentException("Object key must be provided while adding an object to JSON");
    }

    public IJsonBuilder addArray() {
        throw new IllegalArgumentException("Array key must be provided while adding an object to JSON");
    }

    public String getJson() {
        if (isRoot)
            return jsonObject.toString();
        else
            throw new IllegalStateException("getJson() can only be called for root object/array");
    }

    public String getJson(int indentSize) {
        if (isRoot)
            return jsonObject.toString(indentSize);
        else
            throw new IllegalStateException("getJson() can only be called for root object/array");
    }

    public String getJsonPretty() {
        if (isRoot)
            return jsonObject.toString(4);
        else
            throw new IllegalStateException("getJson() can only be called for root object/array");
    }
}
