package com.smartfarming.advisor;

public abstract class Crop {
    private final String name;
    private final double waterMm, costPerAcre, yieldPerAcre;
    protected Crop(String name, double waterMm, double costPerAcre, double yieldPerAcre) {
        this.name = name; this.waterMm = waterMm; this.costPerAcre = costPerAcre; this.yieldPerAcre = yieldPerAcre;
    }
    public String getName() { return name; }
    public double getWaterMm() { return waterMm; }
    public double getCostPerAcre() { return costPerAcre; }
    public double getYieldPerAcre() { return yieldPerAcre; }
    public abstract boolean isSuitable(Soil soil, Season season);
}
