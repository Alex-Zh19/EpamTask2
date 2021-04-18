package com.epam.task2.builder;

import com.epam.task2.entity.PlantEntity;
import com.epam.task2.exception.PlantException;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.Set;

import static org.testng.Assert.*;

public class PlantStaxBuilderTest {

    @Test
    public void testBuildPlant() throws PlantException {
        PlantStaxBuilder plantStaxBuilder=new PlantStaxBuilder();
        String pth="data/Greenhouse.xml";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL pathToFileUrl = classLoader.getResource(pth);
        plantStaxBuilder.buildPlant(new File(pathToFileUrl.getFile()).getAbsolutePath());
        Set<PlantEntity> setActual= plantStaxBuilder.getPlants();
        assertEquals(setActual.size(),16);

    }
}