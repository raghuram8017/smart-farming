package com.smartfarming.advisor;
class Rice extends Crop {
    Rice() { super("Rice", 1200, 22000, 2500); }
    public boolean isSuitable(Soil s, Season t) { return (s == Soil.CLAY || s == Soil.LOAMY) && (t == Season.KHARIF || t == Season.SUMMER); }
}
class Wheat extends Crop {
    Wheat() { super("Wheat", 450, 16000, 1800); }
    public boolean isSuitable(Soil s, Season t) { return (s == Soil.LOAMY || s == Soil.CLAY || s == Soil.BLACK) && t == Season.RABI; }
}
class Cotton extends Crop {
    Cotton() { super("Cotton", 700, 19000, 900); }
    public boolean isSuitable(Soil s, Season t) { return (s == Soil.BLACK || s == Soil.RED) && (t == Season.KHARIF || t == Season.SUMMER); }
}
class Vegetables extends Crop {
    Vegetables() { super("Vegetables", 500, 14000, 3000); }
    public boolean isSuitable(Soil s, Season t) { return s == Soil.LOAMY || s == Soil.SANDY || s == Soil.RED; }
}
