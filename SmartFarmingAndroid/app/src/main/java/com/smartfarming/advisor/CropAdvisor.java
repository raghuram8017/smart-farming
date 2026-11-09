package com.smartfarming.advisor;
import java.util.*;
public class CropAdvisor {
    private final List<Crop> crops = Arrays.asList(new Rice(), new Wheat(), new Cotton(), new Vegetables());
    public List<Crop> suggest(Soil soil, Season season) {
        List<Crop> result = new ArrayList<>();
        for (Crop crop : crops) if (crop.isSuitable(soil, season)) result.add(crop);
        return result;
    }
}
