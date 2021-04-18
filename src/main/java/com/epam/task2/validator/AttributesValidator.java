package com.epam.task2.validator;


import com.epam.task2.entity.PlantOrigin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AttributesValidator {
    public static boolean isId(String str) {
        String strLow = str.toLowerCase();
        List<String> origin = Arrays.stream(PlantOrigin.values()).map(PlantOrigin::toString).collect(Collectors.toList());
        for (String plantOrigin : origin) {
            if (strLow.equals(plantOrigin)) {
                return false;
            }
        }
        return true;
    }
}
