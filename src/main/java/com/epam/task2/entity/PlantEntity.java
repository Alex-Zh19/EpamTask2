package com.epam.task2.entity;

import java.time.LocalDateTime;

public class PlantEntity {
    private String id;
    private PlantOrigin origin;
    private String name;
    private String soil;
    private LocalDateTime plantingTime;
    private VisualParameter visualParameter;
    private GrowingTips growingTips;
    private String multiplying;

    public String getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(String multiplying) {
        this.multiplying = multiplying;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public VisualParameter getVisualParameter() {
        return visualParameter;
    }

    public void setVisualParameter(VisualParameter visualParameter) {
        this.visualParameter = visualParameter;
    }

    public LocalDateTime getPlantingTime() {
        return plantingTime;
    }

    public void setPlantingTime(LocalDateTime plantingTime) {
        this.plantingTime = plantingTime;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlantOrigin getOrigin() {
        return origin;
    }

    public void setOrigin(PlantOrigin origin) {
        this.origin = origin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlantEntity entity = (PlantEntity) o;
        return id.equals(entity.id) && origin.equals(entity.origin) &&
                name.equals(entity.name) &&
                soil.equals(entity.soil) &&
                plantingTime.equals(entity.plantingTime) &&
                visualParameter.equals(entity.visualParameter) &&
                growingTips.equals(entity.growingTips) &&
                multiplying.equals(entity.multiplying);
    }

    @Override
    public int hashCode() {
        int buff=19;
        int result=10;
        result=buff*result+id.hashCode();
        result=buff*result+ origin.hashCode();
        result=buff*result+name.hashCode();
        result=buff*result+soil.hashCode();
        result=buff*result+plantingTime.hashCode();
        result=buff*result+visualParameter.hashCode();
        result=buff*result+growingTips.hashCode();
        result=buff*result+multiplying.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result=new StringBuilder( "PlantEntity{ id=").append(id).append(", origin=").append(origin).
                append(", name=").append(name).append(", soil=").append(soil).append( ", plantingTime=").
                append(plantingTime).append( ", visualParameter=").append(visualParameter).append( ", growingTips=").
                append(growingTips).append(", multiplying=").append(multiplying).append('}');
        return result.toString();
    }
}
