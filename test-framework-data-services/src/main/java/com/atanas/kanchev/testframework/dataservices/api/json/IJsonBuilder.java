package com.atanas.kanchev.testframework.dataservices.api.json;

public interface IJsonBuilder {

    IJsonBuilder addObject(String key);

    IJsonBuilder addArray(String key);

    IJsonBuilder addObject();

    IJsonBuilder addArray();

    String getJson();

    String getJson(int indentSize);

    String getJsonPretty();

    IJsonBuilder endObject();

    IJsonBuilder endArray();

    <T> IJsonBuilder addData(String key, T value);

    <T> IJsonBuilder addData(T value);
}
