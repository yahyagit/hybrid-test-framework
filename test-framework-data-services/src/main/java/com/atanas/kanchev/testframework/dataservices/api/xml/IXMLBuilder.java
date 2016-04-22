package com.atanas.kanchev.testframework.dataservices.api.xml;

public interface IXMLBuilder {

    IXMLBuilder addElement(String tagName, String value);

    IXMLBuilder addElement(String tagName);

    IXMLBuilder addElementGroup(String name);

    IXMLBuilder endElementGroup();

    String getXML();

    String getXMLAsPretty();

    IXMLBuilder withAttribute(String attributeName, String attributeValue);

    IXMLBuilder withNamespace(String namespacePrefix, String namespaceURI);

    IXMLBuilder withNamespace(String namespaceURI);

    IXMLBuilder withEncoding(String encoding);
}
