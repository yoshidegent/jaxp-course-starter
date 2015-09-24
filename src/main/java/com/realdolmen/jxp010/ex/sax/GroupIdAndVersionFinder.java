package com.realdolmen.jxp010.ex.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YDEAX41 on 24/09/2015.
 */
public class GroupIdAndVersionFinder implements ContentHandler {

    private Map<String, String> groupIdVersionMap = new HashMap<>();

    private boolean foundDependency = false;
    private boolean foundGroupId = false;
    private boolean foundVersion = false;

    private String tempGroupId;

    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

        if(qName.equals("dependency"))
        {
            foundDependency = true;
        }

        if(qName.equals("groupId"))
        {
            foundGroupId = true;
        }

        if(qName.equals("version"))
        {
            foundVersion = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("dependency"))
        {
            foundDependency = false;
        }

        if(qName.equals("groupId"))
        {
            foundGroupId = false;
        }

        if(qName.equals("version"))
        {
            foundVersion = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(foundDependency)
        {
            if(foundGroupId)
            {
                tempGroupId = new String(ch, start, length).trim();

                if(!groupIdVersionMap.containsKey(tempGroupId))
                {
                    groupIdVersionMap.put(tempGroupId, "");
                }
            }

            if(foundVersion)
            {
                String tempVersion = new String(ch, start, length).trim();
                groupIdVersionMap.put(tempGroupId, tempVersion);
            }
        }

    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }

    public Map<String, String> getGroupIdVersionMap() {
        return groupIdVersionMap;
    }
}
