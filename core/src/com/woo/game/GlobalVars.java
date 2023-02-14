package com.woo.game;
import java.util.HashMap;

public class GlobalVars {
    public static float delta;
    public static float fps;
    public static float camZoom = 1.0f;

    public static int pxToMeter = 11;
    public static float spellQueueWindow = 0.3f;

    public static boolean characterStats = false;

    public static HashMap<String, Integer> settings = new HashMap<>(); //TODO:MOVE TO Settings + Setting Class (Category,values,names,name,type:buttons/slider,sliderMax,sliderMin,sliderStep)

    public static void init() {
        settings.put("test",0);
        settings.put("VideoParticles",2);
        //settings.get("test");
        //TODO:LOAD
    }

    public static void reset() {
    }
}
