package com.smartfarming.advisor;
public class Farm {
    private final String farmerName, farmName;
    private final Soil soil;
    private final Season season;
    private final double area;
    public Farm(String farmerName, String farmName, Soil soil, Season season, double area) {
        this.farmerName=farmerName; this.farmName=farmName; this.soil=soil; this.season=season; this.area=area;
    }
    public String getFarmerName(){return farmerName;} public String getFarmName(){return farmName;}
    public Soil getSoil(){return soil;} public Season getSeason(){return season;} public double getArea(){return area;}
}
