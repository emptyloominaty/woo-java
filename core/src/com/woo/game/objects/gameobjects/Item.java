package com.woo.game.objects.gameobjects;

public class Item extends GameObject{
    int slot;
    int quality; //Normal, Runic, Magic, Rare, Legendary, Mythic
    int itemLevel;
    String[][] effects;
    int price;
    double weight;

    public Item(String name, String description, boolean solid, boolean interactable, float x, float y, String spritePath) {
        super(name, description, solid, interactable, x, y, spritePath,"Item");
    }
}
