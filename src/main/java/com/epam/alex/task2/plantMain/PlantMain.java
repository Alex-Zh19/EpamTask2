package com.epam.alex.task2.plantMain;


import com.epam.alex.task2.entity.PlantEntity;
import com.epam.alex.task2.entity.PlantOrigin;
import com.epam.alex.task2.plantHandler.PlantErrorHandler;
import com.epam.alex.task2.plantHandler.PlantHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PlantMain {
    final static String PATH_TO_FILE = "data/greenhouseXml/Greenhouse.xml";

    public static void main(String[] args) {
        try {

            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            URL pathToFileUrl = classLoader.getResource(PATH_TO_FILE);

            // SAX parser creating & configuring
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(new PlantHandler());
            reader.setErrorHandler(new PlantErrorHandler());
            reader.parse(new File(pathToFileUrl.getFile()).getAbsolutePath());


        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
