package com.woo.game.objects;

public class Setting {
    public String category;
    public int[] values;
    public String[] names;
    public String name;
    public String type; //slider/button
    public double sliderMax;
    public double sliderMin;
    public double sliderStep;

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
