package com.realdolmen.jxp010.ex.sax;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Map;

/**
 * Created by YDEAX41 on 24/09/2015.
 */
public class ExtractGroupIdAndVersion
{
    public static void main(String[] args)
    {
        XMLReader reader;
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(false);
            saxParserFactory.setValidating(false);
            reader = saxParserFactory.newSAXParser().getXMLReader();
            GroupIdAndVersionFinder groupIdAndVersionFinder = new GroupIdAndVersionFinder();
            reader.setContentHandler(groupIdAndVersionFinder);
            reader.parse("./pom.xml");

            Map<String, String> map = groupIdAndVersionFinder.getGroupIdVersionMap();
            for (Map.Entry<String, String> entry : map.entrySet())
            {
                System.out.println(String.format("GroupID: %s, Version: %s", entry.getKey(), entry.getValue()));
            }

            System.out.println("Numbers of GroupID's found: " + map.size());


        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }
}
