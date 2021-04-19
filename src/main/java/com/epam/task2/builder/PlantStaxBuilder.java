package com.epam.task2.builder;

import com.epam.task2.entity.GrowingTips;
import com.epam.task2.entity.PlantEntity;
import com.epam.task2.entity.PlantOrigin;
import com.epam.task2.entity.VisualParameter;
import com.epam.task2.exception.PlantException;
import com.epam.task2.tag.Tag;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;


public class PlantStaxBuilder extends AbstractPlantBuilder {
    private XMLInputFactory inputFactory;
    private static final Logger logger= LogManager.getLogger();

    public PlantStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }


    @Override
    public void buildPlant(String filename) throws PlantException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(Tag.PLANT)) {
                        PlantEntity plantEntity = buildPlantEntity(reader);
                        plantEntitySet.add(plantEntity);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            logger.log(Level.ERROR,"XML stream Exception", e);
            throw new PlantException("XML stream Exception", e);
        } catch (IOException e) {
            logger.log(Level.ERROR,"Stax parsing exception", e);
            throw new PlantException("Stax parsing exception", e);
        }
    }

    private PlantEntity buildPlantEntity(XMLStreamReader reader)
            throws XMLStreamException {
        PlantEntity plantEntity = new PlantEntity();
        plantEntity.setId(reader.getAttributeValue(null, Tag.ID));
        String origin=reader.getAttributeValue(null, Tag.ORIGIN);
        if(origin==null){
            plantEntity.setOrigin(PlantOrigin.DEFAULT);
        }else{
            plantEntity.setOrigin(PlantOrigin.valueOf(origin.toUpperCase()));
        }

        StringBuilder name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = new StringBuilder(reader.getLocalName());
                    switch (Tag.findTag(name.toString())) {
                        case Tag.NAME -> plantEntity.setName(getXMLText(reader));
                        case Tag.SOIL -> plantEntity.setSoil(getXMLText(reader));
                        case Tag.PLANTING_TIME -> plantEntity.setPlantingTime(LocalDateTime.parse(getXMLText(reader)));
                        case Tag.VISUAL_PARAMETERS->plantEntity.setVisualParameter(getXmlVisualParameter(reader));
                        case Tag.GROWING_TIPS->plantEntity.setGrowingTips(getXmlGrowingTips(reader));
                        case Tag.MULTIPLYING -> plantEntity.setMultiplying(getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = new StringBuilder( reader.getLocalName());
                    if (Tag.findTag(name.toString()).equals(Tag.PLANT)) {
                        return plantEntity;
                    }
            }
        }
        logger.log(Level.ERROR,"Unknown element in tag <plant>");
        throw new XMLStreamException("Unknown element in tag <plant>");
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
                        case Tag.STEM_COLOR -> visualParameter.setStemColor(getXMLText(reader));
                        case Tag.LEAVES_COLOR -> visualParameter.setLeavesColor(getXMLText(reader));
                        case Tag.AVERAGE_SIZE_OF_PLANT -> visualParameter.setAverageSizeOfPlant(getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name =new StringBuilder(reader.getLocalName());
                    if (Tag.findTag(name.toString()).equals(Tag.VISUAL_PARAMETERS) ) {
                        return visualParameter;
                    }
            }
        }
        logger.log(Level.ERROR,"Unknown element in tag <visual-parameter>");
        throw new XMLStreamException("Unknown element in tag <visual-parameter>");
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
                        case Tag.TEMPERATURE -> growingTips.setTemperature(Integer.parseInt( getXMLText(reader)));
                        case Tag.LIGHTNING -> growingTips.setLightning(getXMLText(reader));
                        case Tag.WATERING -> growingTips.setWatering(Integer.parseInt(getXMLText(reader)));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name =new StringBuilder(reader.getLocalName());
                    if (Tag.findTag(name.toString()).equals(Tag.GROWING_TIPS) ) {
                        return growingTips;
                    }
            }
        }
        logger.log(Level.ERROR,"Unknown element in tag <growing-tips>");
        throw new XMLStreamException("Unknown element in tag <growing-tips>");
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

