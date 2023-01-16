package com.woo.game.objects;

import java.util.ArrayList;

public class Settings {
    public static ArrayList<Setting> settingsList;
    public static String[] categories = {"Game","Video","Audio","Control"};

    public static void init() {
        settingsList = new ArrayList<Setting>();

        settingsList.add(new Setting("Video", new int[]{1, 2, 3},new String[]{"Low","Medium","High"},"Particle Quality","button",0,0,0));


    }

}
