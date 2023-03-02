package com.woo.game;

import com.badlogic.gdx.graphics.Color;

import java.util.HashMap;
import java.util.Map;

public class Maps {
    //Item Quality
    public static Color itemNormal = new Color (0xffffffff);
    public static Color itemRunic = new Color (0x8cebf8ff);
    public static Color itemMagic = new Color (0x50a3edff);
    public static Color itemEpic = new Color (0xae16b1ff);
    public static Color itemLegendary = new Color (0xf7a655ff);
    public static Color itemMythic = new Color (0xb11616ff);

    public static Color windowColorBorder = new Color(new Color(0.18f,0.12f,0.1f,1));
    public static Color windowColor = new Color(new Color(0.25f,0.20f,0.15f,1));

    public static Map<Integer, String> itemSlotMap =  new HashMap<Integer, String>();

    public static void init() {
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
