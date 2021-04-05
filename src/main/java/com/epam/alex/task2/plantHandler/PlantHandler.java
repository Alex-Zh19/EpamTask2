package com.epam.alex.task2.plantHandler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class PlantHandler extends DefaultHandler {
    Logger logger= LogManager.getLogger();
    @Override
    public void startDocument(){
        logger.log(Level.INFO,"Parsing start");
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        String tagData = qName + " ";
        for (int i = 0; i < attrs.getLength(); i++) {
            tagData += " " + attrs.getQName(i) + "=" + attrs.getValue(i);
        }
        logger.log(Level.INFO,tagData);
    }
    @Override
    public void characters(char[] ch, int start, int length) {
        String str=new String(ch, start, length);
        logger.log(Level.INFO,str);
    }
    @Override
    public void endElement(String uri, String localName, String qName) {
        logger.log(Level.INFO,qName);
    }
    @Override
    public void endDocument() {
        logger.log(Level.INFO,"\nParsing ended");
    }
}
