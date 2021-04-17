package com.epam.task2.tag;

import org.apache.logging.log4j.core.util.DummyNanoClock;

public class Tag {
    public static final String PLANT = "plant";
    public static final String NAME = "name";
    public static final String SOIL = "soil";
    public static final String PLANTING_TIME = "planting-time";
    public static final String VISUAL_PARAMETERS = "visual-parameters";
    public static final String STEM_COLOR = "stem-color";
    public static final String LEAVES_COLOR = "leaves-color";
    public static final String AVERAGE_SIZE_OF_PLANT = "average-size-of-plant";
    public static final String GROWING_TIPS = "growing-tips";
    public static final String TEMPERATURE = "temperature";
    public static final String LIGHTNING = "lightning";
    public static final String WATERING = "watering";
    public static final String MULTIPLYING = "multiplying";

    public static String findTag(String whatFind){
        switch (whatFind){
            case PLANT:
                return PLANT;
            case NAME:
                return NAME;
            case SOIL:
                return SOIL;
            case PLANTING_TIME:
                return PLANTING_TIME;
            case VISUAL_PARAMETERS:
                return VISUAL_PARAMETERS;
            case STEM_COLOR:
                return STEM_COLOR;
            case LEAVES_COLOR:
                return LEAVES_COLOR;
            case AVERAGE_SIZE_OF_PLANT:
                return AVERAGE_SIZE_OF_PLANT;
            case GROWING_TIPS:
                return GROWING_TIPS;
            case TEMPERATURE:
                return TEMPERATURE;
            case LIGHTNING:
                return LIGHTNING;
            case WATERING:
                return WATERING;
            case MULTIPLYING:
                return MULTIPLYING;
            default:
                return whatFind;

        }
    }
}