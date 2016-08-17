/*
 * Copyright 2016 Atanas Stoychev Kanchev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atanas.kanchev.testframework.dataservices.api.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public final class XMLBuilder implements IXMLBuilder {

    private final Document document;
    private Element currentElement;
    private final Deque<Element> stack;
    private String encoding = "UTF-8";

    /**
     * <p>Constructor for XMLBuilder.</p>
     *
     * @param tagName a {@link java.lang.String} object.
     */
    public XMLBuilder(String tagName)  {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        document = documentBuilder.newDocument();
        Element element = document.createElement(tagName);
        stack = new ArrayDeque<Element>();
        stack.push(element);
    }
    
    /** {@inheritDoc} */
    public IXMLBuilder addElement(String tagName, String value) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(value));
        currentElement = element;
        stack.peek().appendChild(element);
        return this;
    }

    /** {@inheritDoc} */
    public IXMLBuilder addElement(String tagName) {
        Element element = document.createElement(tagName);
        currentElement = element;
        stack.peek().appendChild(element);
        return this;
    }

    /** {@inheritDoc} */
    public IXMLBuilder addElementGroup(String name) {
        Element element = document.createElement(name);
        stack.push(element);
        currentElement = null;
        return this;
    }

    /**
     * <p>endElementGroup.</p>
     *
     * @return a {@link com.atanas.kanchev.testframework.dataservices.api.xml.IXMLBuilder} object.
     */
    public IXMLBuilder endElementGroup() {
        Element element = stack.pop();
        stack.peek().appendChild(element);
        return this;
    }

    /**
     * <p>getXML.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getXML()  {

        document.appendChild(stack.pop());

        try {
            TransformerFactory tFact = TransformerFactory.newInstance();
            Transformer trans = tFact.newTransformer();

            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            DOMSource source = new DOMSource(document);
            trans.transform(source, result);
            return writer.toString();
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <p>getXMLAsPretty.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getXMLAsPretty()  {

        document.appendChild(stack.pop());

        try {
            TransformerFactory tFact = TransformerFactory.newInstance();
            tFact.setAttribute("indent-number", 4);
            Transformer trans = tFact.newTransformer();
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty(OutputKeys.ENCODING, encoding);

            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            DOMSource source = new DOMSource(document);
            trans.transform(source, result);
            return writer.toString();
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /** {@inheritDoc} */
    public IXMLBuilder withAttribute(String attributeName, String attributeValue) {
        if (currentElement == null)
            stack.peek().setAttribute(attributeName, attributeValue);
        else
            currentElement.setAttribute(attributeName, attributeValue);
        return this;
    }

    
    /** {@inheritDoc} */
    public IXMLBuilder withNamespace(String namespacePrefix, String namespaceURI) {
        if (currentElement == null)
            stack.peek().setAttribute("xmlns:" + namespacePrefix, namespaceURI);
        else
            currentElement.setAttribute("xmlns:" + namespacePrefix, namespaceURI);
        return this;
    }

    /** {@inheritDoc} */
    public IXMLBuilder withNamespace(String namespaceURI) {
        if (currentElement == null)
            stack.peek().setAttribute("xmlns", namespaceURI);
        else
            currentElement.setAttribute("xmlns", namespaceURI);
        return this;
    }

    /** {@inheritDoc} */
    public IXMLBuilder withEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }
}
