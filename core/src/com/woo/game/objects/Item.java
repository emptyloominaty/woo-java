package com.woo.game.objects;

import java.util.HashMap;
import java.util.Map;

public class Item {
    public String name;
    public String fullName;
    public int type; //0=potion, 1=food/drink, 2=, 3=, 4=questitem, 5=, 6=, 7=, 8=, 9=, 10=head, 11=neck, 12=chest, 13=hand ,14=waist ,15=legs, 16=feet, 17=ring, 18=trinket, 19=weapon, 20=offhand, 21=
    public String quality; //Normal, Runic, Magic, Rare, Legendary, Mythic
    public int itemLevel;
    public Map<String, Map> data;
    public int price;
    public double weight;
    public String iconPath;
    public Item(String name, int type, String quality, int itemLevel, double weight, String iconPath, Map<String, Map> data) {
        this.name = name;
        this.type = type;
        this.quality = quality;
        this.itemLevel = itemLevel;
        this.weight = weight;
        this.iconPath = iconPath;
        this.data = data;
    }
}
