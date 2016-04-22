package com.atanas.kanchev.testframework.dataservices.api.xml;

public final class XMLMessageBuilder {

    private XMLMessageBuilder() {
    }

    public static XMLBuilder build(String rootTagName) {
        return new XMLBuilder(rootTagName);
    }
}
