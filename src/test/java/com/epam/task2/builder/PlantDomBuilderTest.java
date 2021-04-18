package com.epam.task2.builder;

import com.epam.task2.entity.PlantEntity;
import com.epam.task2.exception.PlantException;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

public class PlantDomBuilderTest {

    @Test
    public void testBuildPlant() throws PlantException {
        PlantDomBuilder plantDomBuilder=new PlantDomBuilder();
        String pth="data/Greenhouse.xml";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL pathToFileUrl = classLoader.getResource(pth);
        plantDomBuilder.buildPlant(new File(pathToFileUrl.getFile()).getAbsolutePath());
        Set<PlantEntity> setActual= plantDomBuilder.getPlants();
        System.out.println(setActual.size());

    }
}