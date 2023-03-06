package com.woo.game;

import com.badlogic.gdx.graphics.Color;

import java.util.HashMap;
import java.util.Map;

public class Maps {
    //Item Quality
    public static Map<String, Color> itemColors = new HashMap<String, Color>();

    public static Color windowColorBorder = new Color(new Color(0.18f,0.12f,0.1f,1));
    public static Color windowColor = new Color(new Color(0.25f,0.20f,0.15f,1));

    public static Map<Integer, String> itemSlotMap =  new HashMap<Integer, String>();

    public static void init() {
        itemColors.put("Normal",new Color (0xffffffff));
        itemColors.put("Runic",new Color (0x8cebf8ff));
        itemColors.put("Magic",new Color (0x50a3edff));
        itemColors.put("Epic",new Color (0xae16b1ff));
        itemColors.put("Legendary",new Color (0xf7a655ff));
        itemColors.put("Mythic",new Color (0xb11616ff));

        itemSlotMap.put(0,"potion");
        itemSlotMap.put(1,"food");
        itemSlotMap.put(2,"");
        itemSlotMap.put(3,"");
        itemSlotMap.put(4,"quest item");
        itemSlotMap.put(5,"");
        itemSlotMap.put(6,"");
        itemSlotMap.put(7,"");
        itemSlotMap.put(8,"");
        itemSlotMap.put(9,"");

        itemSlotMap.put(10,"head");
        itemSlotMap.put(11,"neck");
        itemSlotMap.put(12,"chest");
        itemSlotMap.put(13,"hand");
        itemSlotMap.put(14,"waist");
        itemSlotMap.put(15,"legs");
        itemSlotMap.put(16,"feet");
        itemSlotMap.put(17,"ring");
        itemSlotMap.put(18,"trinket");
        itemSlotMap.put(19,"weapon");
        itemSlotMap.put(20,"off-hand");
    }
}
