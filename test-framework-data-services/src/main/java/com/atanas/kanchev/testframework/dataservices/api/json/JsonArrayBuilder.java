package com.atanas.kanchev.testframework.dataservices.api.json;

import org.json.JSONArray;
import org.json.JSONObject;

public final class JsonArrayBuilder implements IJsonBuilder {

    private final JSONArray jsonArray;
    private boolean isRoot;
    private boolean isParentBuilderArray;
    private IJsonBuilder jsonBuilder;
    private String key;

    {
        jsonArray = new JSONArray();
    }

    public JsonArrayBuilder() {
        isRoot = true;
    }

    public <T> JsonArrayBuilder(T builder, String... key) {
        if (key.length > 0)
            this.key = String.valueOf(key[0]);
        if (builder instanceof JsonArrayBuilder) isParentBuilderArray = true;
        jsonBuilder = (IJsonBuilder) builder;
    }

    public IJsonBuilder endObject() {
        throw new IllegalStateException("Expected endArray() but Found endObject()");
    }

    public IJsonBuilder endArray() {
        if (isParentBuilderArray)
            jsonBuilder.addData(jsonArray);
        else
            jsonBuilder.addData(key, jsonArray);
        return jsonBuilder;
    }

    public <T> IJsonBuilder addData(String key, T value) {
        throw new IllegalArgumentException("Unexpected json key for JSON Array child. Located at : addData(" + key + "," + value + ")");
    }

    public <T> IJsonBuilder addData(T value) {
        jsonArray.put(value == null ? JSONObject.NULL : value);
        return this;
    }

    public IJsonBuilder addObject(String key) {
        throw new IllegalArgumentException("Object key should be avoided while including it under JSON Array. Located at : addData(" + key + ")");
    }

    public IJsonBuilder addArray(String key) {
        throw new IllegalArgumentException("Array key should be avoided while including it under JSON Array. Located at : addData(" + key + ")");
    }

    public IJsonBuilder addObject() {
        return new JsonObjectBuilder(this);
    }

    public IJsonBuilder addArray() {
        return new JsonArrayBuilder(this);
    }

    public String getJson() {
        if (isRoot)
            return jsonArray.toString();
        else
            throw new IllegalStateException("getJson() can only be called for root object/array");
    }

    public String getJson(int indentSize) {
        if (isRoot)
            return jsonArray.toString(indentSize);
        else
            throw new IllegalStateException("getJson() can only be called for root object/array");
    }

    public String getJsonPretty() {
        if (isRoot)
            return jsonArray.toString(4);
        else
            throw new IllegalStateException("getJson() can only be called for root object/array");
    }
}
