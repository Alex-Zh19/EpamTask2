package com.epam.task2.builder;

import com.epam.task2.exception.PlantException;
import com.epam.task2.handler.PlantErrorHandler;
import com.epam.task2.handler.PlantHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class PlantSaxBuilder extends AbstractPlantBuilder {
    private PlantHandler plantHandler;
    private XMLReader xmlReader;

    public PlantSaxBuilder() throws PlantException {
        plantHandler = new PlantHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            throw new PlantException("Parse configuration Error", e);
        }
        xmlReader.setErrorHandler(new PlantErrorHandler());
        xmlReader.setContentHandler(plantHandler);
    }

    @Override
    public void buildPlant(String filename) throws PlantException {
        try {
            xmlReader.parse(filename);
        } catch (IOException | SAXException e) {
            throw new PlantException("SAX parsing error", e);
        }
        plantEntitySet = plantHandler.getPlants();
    }
}
