package com.atanas.kanchev.testframework.dataservices.tests.api.rest.xml;


import com.atanas.kanchev.testframework.dataservices.api.rest.xml.XMLMessageBuilder;
import org.junit.Test;

public class XMlBuilderTest {

    @Test
    public void dummyRootTagTest() {
        System.out.println(XMLMessageBuilder.build("root")
                .withEncoding("UTF-8")
                .withAttribute("version", "1.0")
                .addElementGroup("request")
                .addElementGroup("loginReq")
                .addElement("user", "x")
                .addElement("password", "y")
                .endElementGroup()
                .endElementGroup()
                .getXMLAsPretty()
        );
    }
}
