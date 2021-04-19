package com.epam.task2.builder;

import com.epam.task2.entity.PlantEntity;
import com.epam.task2.exception.PlantException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPlantBuilder {

    Set<PlantEntity> plantEntitySet;

    protected AbstractPlantBuilder() {
        plantEntitySet = new HashSet<>();
    }

    public Set<PlantEntity> getPlants() {
        return new HashSet<>(plantEntitySet);
    }

    public abstract void buildPlant(String filePath) throws PlantException;
}
