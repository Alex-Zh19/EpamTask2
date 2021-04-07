package com.epam.alex.task2.entity;

public enum PlantOrigin {
    BELARUS,
    RUSSIA,
    CHINA,
    USA,
    DEFAULT;

    @Override
    public String toString() {
        String result = this.name().toLowerCase();
        return result;
    }

}
