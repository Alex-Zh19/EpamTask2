package com.epam.task2.validator;


import com.epam.task2.entity.PlantOrigin;



public class AttributesValidator {//peredelat'
    public static boolean isId(String str) {
        String strUp = str.toUpperCase();
        String strLow=str.toLowerCase();
        try {
            if (strLow.equals(PlantOrigin.valueOf(strUp))) {
                return false;
            }
        }catch (IllegalArgumentException exception){

        }
        return true;
    }
}
