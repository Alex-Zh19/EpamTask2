package com.epam.alex.task2.builder;

import com.epam.alex.task2.plantEntity.PlantEntity;
import com.epam.alex.task2.plantHandler.PlantErrorHandler;
import com.epam.alex.task2.plantHandler.PlantHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class PlantSaxBuilder {
    private Set<PlantEntity> students;
    private PlantHandler handler = new PlantHandler();
    private XMLReader reader;
    public PlantSaxBuilder() {
        // reader configuration
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace(); // log
        }
        reader.setErrorHandler(new PlantErrorHandler());
        reader.setContentHandler(handler);
    }
    public Set<PlantEntity> getStudents() {
        return students;
    }
    public void buildSetStudents(String filename) {
        try {
            reader.parse(filename);
        } catch (IOException | SAXException e) {
            e.printStackTrace(); // log
        }
       // students = handler.();
    }
}
