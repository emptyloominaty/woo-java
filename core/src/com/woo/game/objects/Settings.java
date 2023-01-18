package com.woo.game.objects;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    public static Map<String,Setting> map;
    public static String[] categories = {"Game","Video","Audio"};

    public static void init() {
        map = new HashMap<String,Setting>();

        //Game
        //Difficulty Easy / Medium / Hard
        //Floating Combat Text - Off / Static / On
        map.put("Floating Combat Text",new Setting("Game", new int[]{0, 1, 2},new String[]{"Off","Static","On"},"Floating Combat Text","button",0,0,0,2));
        map.put("Character Movement",new Setting("Game", new int[]{0, 1},new String[]{"Wow","Woo"},"Character Movement","button",0,0,0,1));

        //Video
        map.put("Particle Quality",new Setting("Video", new int[]{0, 1, 2, 3},new String[]{"Off","Low","Medium","High"},"Particle Quality","button",0,0,0,2));

        //Audio


        //TODO:Load Settings from File

    }

    public void changeSetting(String name, int value) {
        map.get(name).value = value;
    }
    public void changeSetting(String name, double value) {
        map.get(name).sliderValue = value;
    }

    //TODO: Store Settings to File


}
