package com.smartfarming.advisor;
public class IrrigationPlanner {
    // Approximate conversion: 1 mm over 1 acre = 4,046.86 litres.
    public double calculateLitres(Crop crop, double acres, double rainfallMm) {
        return Math.max(0, crop.getWaterMm() - rainfallMm) * acres * 4046.86;
    }
}
