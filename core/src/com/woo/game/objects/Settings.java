package com.woo.game.objects;

import com.woo.game.objects.file.IniLoadStore;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    public static Map<String,Setting> map;
    public static String[] categories = {"Game","Video","Audio"};
    public static String defaultSettings;

    public static void init() {
        defaultSettings = "";
        map = new HashMap<String,Setting>();
        //Game
        //TODO:??? Difficulty slider 50-200
        map.put("Difficulty",new Setting("Game", new int[]{50, 100, 150, 200},new String[]{"Easy","Normal","Hard","Mythic"},"Difficulty","button",0,0,0,100));
        map.put("Floating Combat Text",new Setting("Game", new int[]{0, 1, 2},new String[]{"Off","Static","On"},"Floating Combat Text","button",0,0,0,2));
        map.put("Character Movement",new Setting("Game", new int[]{0, 1, 2},new String[]{"Wow","Woo","Mouse"},"Character Movement","button",0,0,0,1));
        map.put("Show Realtime Clock",new Setting("Game", new int[]{0, 1},new String[]{"No","Yes"},"Show Realtime Clock","button",0,0,0,1));

        //Video
        //TODO: map.put("Resolution",new Setting("Video", new int[]{0, 1, 2},new String[]{"1280x720","1600x900","1920x1080"},"Resolution","button",0,0,0,0));
        map.put("Frame Rate Limit",new Setting("Video", new int[]{30, 45, 60, 75, 90, 120, 144, 0},new String[]{"30","45","60","75","90","120","144","Unlimited"},"Frame Rate Limit","button",0,0,0,60));
        map.put("VSync",new Setting("Video", new int[]{0, 1},new String[]{"Off","On"},"VSync","button",0,0,0,0));
        map.put("MSAA",new Setting("Video", new int[]{0, 2, 4, 8},new String[]{"Off","2x","4x","8x"},"MSAA","button",0,0,0,0));
        //TODO: SMAA/FXAA

        map.put("Particle Quality",new Setting("Video", new int[]{0, 1, 2, 3},new String[]{"Off","Low","Medium","High"},"Particle Quality","button",0,0,0,2));
        map.put("GrassGFX",new Setting("Video", new int[]{0, 1, 2},new String[]{"Off","Low","High"},"GrassGFX","button",0,0,0,2));
        map.put("NxtPhy",new Setting("Video", new int[]{0, 1, 2,3},new String[]{"Off","Low","Medium","High"},"NxtPhy","button",0,0,0,2));
        map.put("Number of Corpses",new Setting("Video", new int[]{},new String[]{},"Number of Corpses","slider",1000,1,1,100));

        //Audio


        //generate default data for creating new setting file
        for(Map.Entry<String, Setting> entry : map.entrySet()) {
            String key = entry.getKey();
            Setting test = map.get(key);
            defaultSettings+= key+"="+(test.value)+"\n";
        }

        Map<String,String> loadedSettings = IniLoadStore.load("settings.ini",defaultSettings);

        for(Map.Entry<String, String> entry : loadedSettings.entrySet()) {
            String key = entry.getKey();
            try{
                map.get(key).value = Integer.parseInt(loadedSettings.get(key));
            } catch(NumberFormatException e){
                System.out.println("Invalid input string:"+loadedSettings.get(key));
            }
        }

    }

    public static void changeSetting(String name, int value) {
        map.get(name).value = value;
        saveSettings();
    }
    public static void changeSetting(String name, double value) {
        map.get(name).sliderValue = value;
        saveSettings();
    }

    public static void saveSettings() {
        String storeString = "";
        for(Map.Entry<String, Setting> entry : map.entrySet()) {
            String key = entry.getKey();
            Setting test = map.get(key);
            storeString += key+"="+(test.value)+"\n";
        }
        IniLoadStore.store("settings.ini",storeString,false);
    }


}
