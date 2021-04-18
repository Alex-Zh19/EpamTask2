package com.epam.task2.builder;

import com.epam.task2.entity.GrowingTips;
import com.epam.task2.entity.PlantEntity;
import com.epam.task2.entity.PlantOrigin;
import com.epam.task2.entity.VisualParameter;
import com.epam.task2.exception.PlantException;
import com.epam.task2.tag.Tag;
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
    private DocumentBuilder docBuilder;

    public PlantDomBuilder() throws PlantException {
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
            doc.getDocumentElement().normalize();
            NodeList greenhouse = doc.getElementsByTagName("plant");
            for (int i = 0; i < greenhouse.getLength(); i++) {
                Element plantElement = (Element) greenhouse.item(i);
                PlantEntity plantEntity = buildPlantEntity(plantElement);
                plantEntitySet.add(plantEntity);
            }
        } catch (IOException | SAXException e) {
            throw new PlantException("Dom Parsing exception", e);
        }
    }
    private PlantEntity buildPlantEntity(Element plantElement) {
        PlantEntity plantEntity = new PlantEntity();
        //id
        plantEntity.setId(plantElement.getAttribute(Tag.ID));

        //origin
        StringBuilder originAttr=new StringBuilder(plantElement.getAttribute(Tag.ORIGIN));
        if(!originAttr.isEmpty()) {
           plantEntity.setOrigin(PlantOrigin.valueOf(originAttr.toString().toUpperCase()));
        }else{
            plantEntity.setOrigin(PlantOrigin.DEFAULT);
        }
        //name
        plantEntity.setName(getElementTextContent(plantElement,Tag.NAME));
        //soil
        plantEntity.setSoil(getElementTextContent(plantElement, Tag.SOIL));
        //planting time
        LocalDateTime date = LocalDateTime.parse(getElementTextContent(plantElement, Tag.PLANTING_TIME));
        plantEntity.setPlantingTime(date);

        //visual Parameters
        VisualParameter visualParameter = new VisualParameter();
        Element visualParameterElement =
                (Element) plantElement.getElementsByTagName(Tag.VISUAL_PARAMETERS).item(0);
        visualParameter.setStemColor(getElementTextContent(visualParameterElement, Tag.STEM_COLOR));
        visualParameter.setLeavesColor(getElementTextContent(visualParameterElement, Tag.LEAVES_COLOR));
        visualParameter.setAverageSizeOfPlant(getElementTextContent(visualParameterElement, Tag.AVERAGE_SIZE_OF_PLANT));
        plantEntity.setVisualParameter(visualParameter);
        //growing tips
        GrowingTips growingTips=new GrowingTips();
        Element growingTipsElement=(Element)plantElement.getElementsByTagName(Tag.GROWING_TIPS).item(0);
        growingTips.setTemperature(Integer.parseInt(getElementTextContent(growingTipsElement, Tag.TEMPERATURE)));
        growingTips.setLightning(getElementTextContent(growingTipsElement,Tag.LIGHTNING));
        growingTips.setWatering(Integer.parseInt(getElementTextContent(growingTipsElement, Tag.WATERING)));
        plantEntity.setGrowingTips(growingTips);
        //multiplying
        plantEntity.setMultiplying(getElementTextContent(plantElement, Tag.MULTIPLYING));
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

