package com.realdolmen.jxp010.sax.ex;

import com.sun.deploy.xml.XMLParser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Created by YDEAX41 on 24/09/2015.
 */
public class ExtractMovieTitles
{
    public static void main(String[] args)
    {
        XMLReader reader;
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(false);
            saxParserFactory.setValidating(false);
            reader = saxParserFactory.newSAXParser().getXMLReader();
            reader.setContentHandler(new FindTitles());
            reader.parse("movies.xml");

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }
}
