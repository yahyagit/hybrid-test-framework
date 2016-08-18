/*
 * Copyright 2016 Atanas Stoychev Kanchev
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.atanas.kanchev.testframework.dataservices.api.rest.xml;

public interface IXMLBuilder {

    /**
     * <p>addElement.</p>
     *
     * @param tagName a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.xml.IXMLBuilder} object.
     */
    IXMLBuilder addElement(String tagName, String value);

    /**
     * <p>addElement.</p>
     *
     * @param tagName a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.xml.IXMLBuilder} object.
     */
    IXMLBuilder addElement(String tagName);

    /**
     * <p>addElementGroup.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.xml.IXMLBuilder} object.
     */
    IXMLBuilder addElementGroup(String name);

    /**
     * <p>endElementGroup.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.xml.IXMLBuilder} object.
     */
    IXMLBuilder endElementGroup();

    /**
     * <p>getXML.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getXML();

    /**
     * <p>getXMLAsPretty.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getXMLAsPretty();

    /**
     * <p>withAttribute.</p>
     *
     * @param attributeName a {@link java.lang.String} object.
     * @param attributeValue a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.xml.IXMLBuilder} object.
     */
    IXMLBuilder withAttribute(String attributeName, String attributeValue);

    /**
     * <p>withNamespace.</p>
     *
     * @param namespacePrefix a {@link java.lang.String} object.
     * @param namespaceURI a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.xml.IXMLBuilder} object.
     */
    IXMLBuilder withNamespace(String namespacePrefix, String namespaceURI);

    /**
     * <p>withNamespace.</p>
     *
     * @param namespaceURI a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.xml.IXMLBuilder} object.
     */
    IXMLBuilder withNamespace(String namespaceURI);

    /**
     * <p>withEncoding.</p>
     *
     * @param encoding a {@link java.lang.String} object.
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.rest.xml.IXMLBuilder} object.
     */
    IXMLBuilder withEncoding(String encoding);
}
