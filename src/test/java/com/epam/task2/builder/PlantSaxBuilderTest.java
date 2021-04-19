package com.epam.task2.builder;

import com.epam.task2.entity.GrowingTips;
import com.epam.task2.entity.PlantEntity;
import com.epam.task2.entity.PlantOrigin;
import com.epam.task2.entity.VisualParameter;
import com.epam.task2.exception.PlantException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Set;

import static org.testng.Assert.*;

public class PlantSaxBuilderTest {

    @Test
    public void testBuildPlant() throws PlantException {
        PlantSaxBuilder plantSaxBuilder=new PlantSaxBuilder();
        String pth="data/Greenhouse.xml";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL pathToFileUrl = classLoader.getResource(pth);
        plantSaxBuilder.buildPlant(new File(pathToFileUrl.getFile()).getAbsolutePath());
        Set<PlantEntity> setActual= plantSaxBuilder.getPlants();

        PlantEntity oneOfThePlants=new PlantEntity();
        oneOfThePlants.setId("hcnth1");
        oneOfThePlants.setOrigin(PlantOrigin.USA);
        oneOfThePlants.setName("Hyacinth");
        oneOfThePlants.setSoil("podzolic");
        LocalDateTime dateTime=LocalDateTime.parse("2011-07-01T00:00:02");
        oneOfThePlants.setPlantingTime(dateTime);
        VisualParameter visualParameter=new VisualParameter();
        visualParameter.setStemColor("green");
        visualParameter.setLeavesColor("green");
        visualParameter.setAverageSizeOfPlant("average");
        oneOfThePlants.setVisualParameter(visualParameter);
        GrowingTips growingTips=new GrowingTips();
        growingTips.setTemperature(12);
        growingTips.setLightning("no");
        growingTips.setWatering(45);
        oneOfThePlants.setGrowingTips(growingTips);
        oneOfThePlants.setMultiplying("cuttings");

        assertTrue(setActual.contains(oneOfThePlants));
    }
}