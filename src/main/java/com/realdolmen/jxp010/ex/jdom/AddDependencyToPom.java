package com.realdolmen.jxp010.ex.jdom;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddDependencyToPom {
    public static void main(String[] args)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = null;
        try {
            document = saxBuilder.build(new FileInputStream("./pom.xml"));

            addDependencyToDocument(document, "org.slf4j", "slf4j-api", "1.7.12");

            XMLOutputter xmlOutputter = new XMLOutputter();
            xmlOutputter.output(document, new FileOutputStream("./src/main/java/com/realdolmen/jxp010/ex/jdom/result/pom.xml"));

        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void addDependencyToDocument(Document document, String groupId, String artifactId, String version)
    {
        Element rootElement = document.getRootElement();

        Element dependenciesElement = rootElement.getChild("dependencies", rootElement.getNamespace());

        Element dependency = new Element("dependency");
        dependency.addContent(new Element("groupId").setText("org.slf4j"));
        dependency.addContent(new Element("artifactId").setText("slf4j-api"));
        dependency.addContent(new Element("version").setText("1.7.12"));

        dependenciesElement.addContent(dependency);
    }
}
