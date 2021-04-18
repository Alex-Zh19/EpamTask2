package com.epam.task2.builder;

import com.epam.task2.entity.GrowingTips;
import com.epam.task2.entity.PlantEntity;
import com.epam.task2.entity.PlantOrigin;
import com.epam.task2.entity.VisualParameter;
import com.epam.task2.exception.PlantException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
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



    @Override
    public void buildPlant(String filePath) throws PlantException {
        Document doc;
        try {
            doc = docBuilder.parse(filePath);
            Element root = doc.getDocumentElement();
            NodeList plantList = root.getElementsByTagName("greenhouse");
            for (int i = 0; i < plantList.getLength(); i++) {
                Element plantElement = (Element) plantList.item(i);
                PlantEntity plantEntity = buildPlantEntity(plantElement);
                plantEntitySet.add(plantEntity);
            }
        } catch (IOException | SAXException e) {
            throw new PlantException("Dom Parsing exception", e);
        }
    }
    private PlantEntity buildPlantEntity(Element plantElement) {
        PlantEntity plantEntity = new PlantEntity();
        // add null check
        plantEntity.setId(plantElement.getAttribute("identifierName"));
        StringBuilder originAttr=new StringBuilder(plantElement.getAttribute("origin"));
        if(!originAttr.isEmpty()) {
           plantEntity.setOrigin(PlantOrigin.valueOf(originAttr.toString().toUpperCase()));
        }
        plantEntity.setName(getElementTextContent(plantElement,"name"));
        plantEntity.setSoil(getElementTextContent(plantElement, "soil"));
        LocalDateTime date = LocalDateTime.parse(getElementTextContent(plantElement, "planting-time"));
        plantEntity.setPlantingTime(date);

        VisualParameter visualParameter = new VisualParameter();
        Element visualParameterElement =
                (Element) plantElement.getElementsByTagName("visual-parameters").item(0);
        visualParameter.setStemColor(getElementTextContent(visualParameterElement, "stem-color"));
        visualParameter.setLeavesColor(getElementTextContent(visualParameterElement, "leaves-color"));
        visualParameter.setAverageSizeOfPlant(getElementTextContent(visualParameterElement, "average-size-of-plant"));
        plantEntity.setVisualParameter(visualParameter);

        GrowingTips growingTips=new GrowingTips();
        Element growingTipsElement=(Element)plantElement.getElementsByTagName("growing-tips");
        growingTips.setTemperature(Integer.parseInt(getElementTextContent(growingTipsElement,"temperature")));
        growingTips.setLightning(getElementTextContent(growingTipsElement,"lightning"));
        growingTips.setWatering(Integer.parseInt(getElementTextContent(growingTipsElement,"watering")));
        plantEntity.setGrowingTips(growingTips);

        plantEntity.setMultiplying(getElementTextContent(plantElement,"multiplying"));
        return plantEntity;
    }
    // get the text content of the tag
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}

