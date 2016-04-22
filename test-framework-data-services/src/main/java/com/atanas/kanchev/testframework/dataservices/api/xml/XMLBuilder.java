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
    
    public IXMLBuilder addElement(String tagName, String value) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(value));
        currentElement = element;
        stack.peek().appendChild(element);
        return this;
    }

    public IXMLBuilder addElement(String tagName) {
        Element element = document.createElement(tagName);
        currentElement = element;
        stack.peek().appendChild(element);
        return this;
    }

    public IXMLBuilder addElementGroup(String name) {
        Element element = document.createElement(name);
        stack.push(element);
        currentElement = null;
        return this;
    }

    public IXMLBuilder endElementGroup() {
        Element element = stack.pop();
        stack.peek().appendChild(element);
        return this;
    }

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
    
    public IXMLBuilder withAttribute(String attributeName, String attributeValue) {
        if (currentElement == null)
            stack.peek().setAttribute(attributeName, attributeValue);
        else
            currentElement.setAttribute(attributeName, attributeValue);
        return this;
    }

    
    public IXMLBuilder withNamespace(String namespacePrefix, String namespaceURI) {
        if (currentElement == null)
            stack.peek().setAttribute("xmlns:" + namespacePrefix, namespaceURI);
        else
            currentElement.setAttribute("xmlns:" + namespacePrefix, namespaceURI);
        return this;
    }

    public IXMLBuilder withNamespace(String namespaceURI) {
        if (currentElement == null)
            stack.peek().setAttribute("xmlns", namespaceURI);
        else
            currentElement.setAttribute("xmlns", namespaceURI);
        return this;
    }

    public IXMLBuilder withEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }
}