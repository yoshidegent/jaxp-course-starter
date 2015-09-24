package com.realdolmen.jxp010.ex.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.List;

/**
 * Created by YDEAX41 on 24/09/2015.
 */
public class AddDependencyToPom {
    public static void main(String[] args)
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            document = documentBuilderFactory.newDocumentBuilder().parse("./pom.xml");

            addDependencyToDocument(document, "org.slf4j", "slf4j-api", "1.7.12");

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(System.out));

        } catch (SAXException | TransformerException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void addDependencyToDocument(Document document, String groupId, String artifactId, String version)
    {
        Element documentElement = document.getDocumentElement();

        NodeList dependenciesNodeList = documentElement.getElementsByTagName("dependencies");

        Node dependenciesNode = dependenciesNodeList.item(0);

        Node dependencyNode = document.createElement("dependency");

        Node groupIdNode = document.createElement("groupId");
        groupIdNode.setTextContent("org.slf4j");

        Node artifactIdNode = document.createElement("artifactId");
        artifactIdNode.setTextContent("slf4j-api");

        Node versionNode = document.createElement("version");
        versionNode.setTextContent("1.7.12");

        dependencyNode.appendChild(groupIdNode);
        dependencyNode.appendChild(artifactIdNode);
        dependencyNode.appendChild(versionNode);

        dependenciesNode.appendChild(dependencyNode);
    }
}
