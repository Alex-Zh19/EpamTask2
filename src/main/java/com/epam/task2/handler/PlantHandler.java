package com.epam.task2.handler;

import com.epam.task2.entity.GrowingTips;
import com.epam.task2.entity.PlantEntity;
import com.epam.task2.entity.PlantOrigin;
import com.epam.task2.entity.VisualParameter;
import com.epam.task2.tag.Tag;
import com.epam.task2.validator.AttributesValidator;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class PlantHandler extends DefaultHandler {
    private StringBuilder element;

    private Set<PlantEntity> plantEntitySet;

    private PlantEntity plantEntity;
    private GrowingTips growingTips;
    private VisualParameter visualParameter;

    @Override
    public void startDocument() {
        plantEntitySet = new HashSet<PlantEntity>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        switch (qName) {
            case Tag.PLANT:
                plantEntity = new PlantEntity();
                if (attrs.getLength() == 2) {
                    if (AttributesValidator.isId(attrs.getValue(0))) {
                        plantEntity.setId(attrs.getValue(0));
                        String str = attrs.getValue(1).toUpperCase();
                        plantEntity.setOrigin(PlantOrigin.valueOf(str));
                    } else {
                        String str = attrs.getValue(0).toUpperCase();
                        plantEntity.setOrigin(PlantOrigin.valueOf(str));
                        plantEntity.setId(attrs.getValue(1));
                    }
                } else {
                    plantEntity.setId(attrs.getValue(0));
                    plantEntity.setOrigin(PlantOrigin.DEFAULT);
                }
                break;
            case Tag.VISUAL_PARAMETERS:
                visualParameter=new VisualParameter();
                break;
            case Tag.GROWING_TIPS:
                growingTips=new GrowingTips();
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        element = new StringBuilder();
        element.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case  Tag.PLANT:
                plantEntitySet.add(plantEntity);
                break;
            case Tag.NAME:
                plantEntity.setName(element.toString());
                break;
            case Tag.SOIL:
                plantEntity.setSoil(element.toString());
                break;
            case Tag.PLANTING_TIME:
                String str=element.toString();
                LocalDateTime dateTime=LocalDateTime.parse(str);
                plantEntity.setPlantingTime(dateTime);
                break;
            case Tag.STEM_COLOR:
                visualParameter.setStemColor(element.toString());
                break;
            case Tag.LEAVES_COLOR:
                visualParameter.setLeavesColor(element.toString());
                break;
            case Tag.AVERAGE_SIZE_OF_PLANT:
                visualParameter.setAverageSizeOfPlant(element.toString());
                break;
            case Tag.VISUAL_PARAMETERS:
                plantEntity.setVisualParameter(visualParameter);
                break;
            case Tag.TEMPERATURE:
                Integer temp=Integer.parseInt(element.toString());
                growingTips.setTemperature(temp.intValue());
                break;
            case Tag.LIGHTNING:
                growingTips.setLightning(element.toString());
                break;
            case Tag.WATERING:
                Integer wat=Integer.parseInt(element.toString());
                growingTips.setWatering(wat.intValue());
                break;
            case Tag.GROWING_TIPS:
                plantEntity.setGrowingTips(growingTips);
                break;
            case Tag.MULTIPLYING:
                plantEntity.setMultiplying(element.toString());
        }
    }

    @Override
    public void endDocument() {

    }

    public Set<PlantEntity>getPlants(){
        return new HashSet<>(plantEntitySet);
    }
}
