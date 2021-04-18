package com.epam.task2.builder;

import com.epam.task2.entity.GrowingTips;
import com.epam.task2.entity.PlantEntity;
import com.epam.task2.entity.PlantOrigin;
import com.epam.task2.entity.VisualParameter;
import com.epam.task2.exception.PlantException;
import com.epam.task2.tag.Tag;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class PlantStaxBuilder extends AbstractPlantBuilder {
    private Set<PlantEntity> plantEntitySet;
    private XMLInputFactory inputFactory;

    public PlantStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        plantEntitySet = new HashSet<PlantEntity>();
    }


    @Override
    public void buildPlant(String filename) throws PlantException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            // StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(Tag.NAME.toLowerCase())) {
                        PlantEntity plantEntity = buildPlantEntity(reader);
                        plantEntitySet.add(plantEntity);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new PlantException("XML stream Exception", e);
        } catch (IOException e) {
            throw new PlantException("Stax parsing exception", e);
        }
    }

    private PlantEntity buildPlantEntity(XMLStreamReader reader)
            throws XMLStreamException {
        PlantEntity plantEntity = new PlantEntity();
        plantEntity.setId(reader.getAttributeValue(null, "identifierName"));
        // null check
        plantEntity.setOrigin(PlantOrigin.valueOf(reader.getAttributeValue(null, "origin")));
        StringBuilder name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = new StringBuilder(reader.getLocalName());
                    switch (Tag.findTag(name.toString())) {
                        case "name" -> plantEntity.setName(getXMLText(reader));
                        case "soil" -> plantEntity.setSoil(getXMLText(reader));
                        case "planting-time" -> plantEntity.setPlantingTime(LocalDateTime.parse(getXMLText(reader)));
                        case "visual-parameters"->plantEntity.setVisualParameter(getXmlVisualParameter(reader));
                        case "growing-tips"->plantEntity.setGrowingTips(getXmlGrowingTips(reader));
                        case "multiplying" -> plantEntity.setMultiplying(getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = new StringBuilder( reader.getLocalName());
                    if (Tag.findTag(name.toString()).equals(Tag.PLANT)) {
                        return plantEntity;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <student>");
    }

    private VisualParameter getXmlVisualParameter(XMLStreamReader reader)
            throws XMLStreamException {
        VisualParameter visualParameter=new VisualParameter();
        int type;
        StringBuilder name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name =new StringBuilder(reader.getLocalName());
                    switch (Tag.findTag(name.toString())) {
                        case "stem-color" -> visualParameter.setStemColor(getXMLText(reader));
                        case "leaves-color" -> visualParameter.setLeavesColor(getXMLText(reader));
                        case "average-size-of-plant" -> visualParameter.setAverageSizeOfPlant(getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name =new StringBuilder(reader.getLocalName());
                    if (Tag.findTag(name.toString()).equals(Tag.VISUAL_PARAMETERS) ) {
                        return visualParameter;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <address>");
    }

    private GrowingTips getXmlGrowingTips(XMLStreamReader reader)
            throws XMLStreamException {
        GrowingTips growingTips=new GrowingTips();
        int type;
        StringBuilder name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name =new StringBuilder(reader.getLocalName());
                    switch (Tag.findTag(name.toString())) {
                        case "temperature" -> growingTips.setTemperature(Integer.parseInt( getXMLText(reader)));
                        case "lightning" -> growingTips.setLightning(getXMLText(reader));
                        case "watering" -> growingTips.setWatering(Integer.parseInt(getXMLText(reader)));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name =new StringBuilder(reader.getLocalName());
                    if (Tag.findTag(name.toString()).equals(Tag.GROWING_TIPS) ) {
                        return growingTips;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <address>");
    }


    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

