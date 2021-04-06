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
        System.out.println("start");
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        String tagData = qName + " \"startElFunc\" ";
        for (int i = 0; i < attrs.getLength(); i++) {
            tagData += " " + attrs.getQName(i) + "=" + attrs.getValue(i);
        }
        System.out.println(tagData);
    }
    @Override
    public void characters(char[] ch, int start, int length) {
        String str=new String(ch, start, length);
        System.out.println(str+" \"CharactersFunc\" ");
    }
    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.println(qName+" \"endElFunc\" ");    }
    @Override
    public void endDocument() {
        System.out.println("end");
    }
}
