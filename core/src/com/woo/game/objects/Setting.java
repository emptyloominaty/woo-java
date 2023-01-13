package com.woo.game.objects;

public class Setting {
    String category;
    int[] values;
    String[] names;
    String name;
    String type;
    double sliderMax;
    double sliderMin;
    double sliderStep;

    public Setting( String category, int[] values, String[] names, String name, String type, double sliderMax, double sliderMin, double sliderStep) {
        this.category = category;
        this.values = values;
        this.names = names;
        this.name = name;
        this.type = type;
        this.sliderMax = sliderMax;
        this.sliderMin = sliderMin;
        this.sliderStep = sliderStep;
    }
}
