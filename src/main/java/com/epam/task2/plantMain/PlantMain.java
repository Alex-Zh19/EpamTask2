package com.epam.task2.plantMain;


public class PlantMain {
    final static String PATH_TO_FILE = "data/greenhouseXml/Greenhouse.xml";

    public static void main(String[] args) {
        /*try {
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
        }*/
    }
}
