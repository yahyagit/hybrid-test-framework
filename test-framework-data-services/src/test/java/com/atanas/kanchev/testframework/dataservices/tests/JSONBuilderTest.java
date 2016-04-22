package com.atanas.kanchev.testframework.dataservices.tests;

import com.atanas.kanchev.testframework.dataservices.api.json.JsonMessageBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sudhakar on 01/06/15.
 */
public class JSONBuilderTest {

    @Test
    public void testPrimitiveDataWithObject() {
        String jsonResult = JsonMessageBuilder.buildObject()
                                .addData("integer", 123)
                                .addData("float", 123.45)
                                .addData("booleanTrue", true)
                                .addData("booleanFalse", false)
                                .addData("null", null)
                                .getJson();
        System.out.println(jsonResult);
        Assert.assertEquals("{\"integer\":123,\"booleanTrue\":true,\"booleanFalse\":false,\"float\":123.45,\"null\":null}", jsonResult);
    }

    @Test
    public void testPrimitiveDataWithArray() {
        String jsonResult = JsonMessageBuilder.buildArray()
                .addData(123)
                .addData(123.45)
                .addData(true)
                .addData(false)
                .addData(null)
                .getJson();
        Assert.assertEquals("[123,123.45,true,false,null]", jsonResult);
    }

    @Test
    public void testSingleChildObjectWithinObject() {
        String jsonResult = JsonMessageBuilder.buildObject()
                                .addData("key1", "value1")
                                .addObject("obj1")
                                    .addData("key11", "value11")
                                    .endObject()
                                .getJson();
        Assert.assertEquals("{\"obj1\":{\"key11\":\"value11\"},\"key1\":\"value1\"}",jsonResult);
    }

    @Test
    public void testNestedChildObjectsWithinObject() {
        String jsonResult = JsonMessageBuilder.buildObject()
                                .addData("key1", "value1")
                                .addObject("obj1")
                                    .addData("key11", "value11")
                                    .addObject("obj2")
                                        .addData("key21", "value21")
                                        .endObject()
                                    .endObject()
                                .getJson();
        Assert.assertEquals("{\"obj1\":{\"obj2\":{\"key21\":\"value21\"},\"key11\":\"value11\"},\"key1\":\"value1\"}",jsonResult);
    }

    @Test
    public void testSingleChildArrayWithinObject() {
        String jsonResult = JsonMessageBuilder.buildObject()
                .addData("key1", "value1")
                .addArray("Array1")
                    .addData("value11")
                    .endArray()
                .getJson();
        Assert.assertEquals("{\"key1\":\"value1\",\"Array1\":[\"value11\"]}",jsonResult);
    }

    @Test
    public void testNestedChildArraysWithinObject() {
        String jsonResult = JsonMessageBuilder.buildObject()
                .addData("key1", "value1")
                .addArray("Array1")
                    .addData("value11")
                .addArray()
                    .addData("value21")
                    .endArray()
                .endArray()
                .getJson();
        Assert.assertEquals("{\"key1\":\"value1\",\"Array1\":[\"value11\",[\"value21\"]]}",jsonResult);
    }

    @Test
    public void testSingleChildObjectWithinArray() {
        String jsonResult = JsonMessageBuilder.buildArray()
                .addData("value1")
                .addObject()
                    .addData("key11", "value11")
                .endObject()
                .getJson();
        Assert.assertEquals("[\"value1\",{\"key11\":\"value11\"}]",jsonResult);
    }

    @Test
    public void testNestedChildObjectsWithinArray() {
        String jsonResult = JsonMessageBuilder.buildArray()
                .addData("value1")
                .addObject()
                    .addData("key11", "value11")
                    .addObject("Obj2")
                        .addData("key21", "value21")
                        .endObject()
                    .endObject()
                .getJson();
        Assert.assertEquals("[\"value1\",{\"Obj2\":{\"key21\":\"value21\"},\"key11\":\"value11\"}]",jsonResult);
    }

    @Test(expected = IllegalStateException.class)
    public void testEndArrayForObject() {
        JsonMessageBuilder.buildObject()
                .addObject("obj1")
                    .addData("key1", "value1")
                    .endArray()
                .getJson();
    }

    @Test(expected = IllegalStateException.class)
    public void testEndObjectForArray() {
        JsonMessageBuilder.buildObject()
                .addObject("obj1")
                .addData("key1", "value1")
                .endArray()
                .getJson();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDataWithoutKeyToObject() {
        JsonMessageBuilder.buildObject()
                .addData("Value")
                .getJson();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDataWithKeyToArray() {
        JsonMessageBuilder.buildArray()
                .addData("key","Value")
                .getJson();
    }

    //TODO: Add more negative test cases
}
