package com.atanas.kanchev.testframework.dataservices.tests;


import com.atanas.kanchev.testframework.dataservices.api.xml.XMLMessageBuilder;
import org.junit.Test;

/**
 * Created by sudhakar on 12/06/15.
 */
public class XMlBuilderTest {

    @Test
    public void dummyRootTagTest() {
        System.out.println(XMLMessageBuilder.build("oxip")
                .withEncoding("UTF-16")
                .withAttribute("version", "6.0")
                .addElementGroup("request")
                .addElementGroup("reqClientAuth")
                .addElement("user", "uname")
                .addElement("password", "password")
                .endElementGroup()
                .endElementGroup()
                .getXMLAsPretty()
        );
    }
}
