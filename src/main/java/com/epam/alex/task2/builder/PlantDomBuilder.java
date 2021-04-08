package com.epam.alex.task2.builder;

import com.epam.alex.task2.entity.PlantEntity;
import com.epam.alex.task2.exception.PlantException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PlantDomBuilder extends AbstractPlantBuilder {
    private Set<PlantEntity> plantEntitySet;
    private DocumentBuilder docBuilder;

    public PlantDomBuilder() throws PlantException {
        plantEntitySet = new HashSet<PlantEntity>();
        // configuration
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new PlantException("Dom Parsing exception", e);
        }
    }

    public Set<PlantEntity> getPlants() {
        return plantEntitySet;
    }

    @Override
    public void buildPlant(String filePath) throws PlantException {
        Document doc;
        try {
            doc = docBuilder.parse(filePath);
            Element root = doc.getDocumentElement();
            NodeList plantList = root.getElementsByTagName("flower");
            for (int i = 0; i < plantList.getLength(); i++) {
                Element plantElement = (Element) plantList.item(i);
                //PlantEntity plantEntity = buildStudent(plantElement);
                //plantEntitySet.add(plantEntity);
            }
        } catch (IOException | SAXException e) {
            throw new PlantException("Dom Parsing exception", e);
        }
    }

}
