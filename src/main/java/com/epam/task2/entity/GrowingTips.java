package com.epam.task2.entity;

public class GrowingTips {
    private int temperature;
    private String lightning;
    private int watering;

    private final int DEFAULT_WATERING=10;
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getLightning() {
        return lightning;
    }

    public void setLightning(String lightning) {
        this.lightning = lightning;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        if(watering<0){
           this.watering=DEFAULT_WATERING;
        }
        this.watering = watering;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrowingTips that = (GrowingTips) o;
        return temperature == that.temperature &&
                watering == that.watering &&
                lightning.equals(that.lightning);
    }

    @Override
    public int hashCode() {
        int buff=19;
        int result=10;
        result=buff*result+lightning.hashCode()+temperature+watering;
        return result;
    }

    @Override
    public String toString() {
        return "GrowingTips{" +
                "temperature=" + temperature +
                ", lightning='" + lightning +
                ", watering=" + watering +
                '}';
    }
}
