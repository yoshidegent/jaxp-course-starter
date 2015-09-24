package com.realdolmen.jxp010.ex.sax;

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
            TitleFinder titleFinder = new TitleFinder();
            reader.setContentHandler(titleFinder);
            reader.parse("./src/main/resources/movies.xml");

            for(String s : titleFinder.getTitleList())
            {
                System.out.println(s + "; ");
            }

            System.out.println("Numbers of titles found: " + titleFinder.getTitleList().size());


        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }
}
