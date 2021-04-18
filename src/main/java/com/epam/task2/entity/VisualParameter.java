package com.epam.task2.entity;


public class VisualParameter {
    private String stemColor;
    private String leavesColor;
    private String averageSizeOfPlant;

    public String getStemColor() {
        return stemColor;
    }

    public void setStemColor(String stemColor) {
        this.stemColor = stemColor;
    }

    public String getLeavesColor() {
        return leavesColor;
    }

    public void setLeavesColor(String leavesColor) {
        this.leavesColor = leavesColor;
    }

    public String getAverageSizeOfPlant() {
        return averageSizeOfPlant;
    }

    public void setAverageSizeOfPlant(String averageSizeOfPlant) {
        this.averageSizeOfPlant = averageSizeOfPlant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualParameter that = (VisualParameter) o;
        return stemColor.equals(that.stemColor) &&
                leavesColor.equals(that.leavesColor) &&
                averageSizeOfPlant.equals(that.averageSizeOfPlant);
    }

    @Override
    public int hashCode() {
        int buff = 19;
        int result = 10;
        result = buff * result + stemColor.hashCode();
        result = buff * result + leavesColor.hashCode();
        result = buff * result + averageSizeOfPlant.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("VisualParameter{ stemColor=").append(stemColor).append(", leavesColor=").
                append(leavesColor).append(", averageSizeOfPlant=").append(averageSizeOfPlant).append('}');
        return result.toString();
    }
}
