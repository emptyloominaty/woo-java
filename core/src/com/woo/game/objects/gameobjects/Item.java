package com.woo.game.objects.gameobjects;

public class Item extends GameObject{
    int slot;
    int quality;
    String[][] effects;
    int price;
    double weight;

    public Item(String name, String description, boolean solid, boolean interactable, double x, double y, String spritePath) {
        super(name, description, solid, interactable, x, y, spritePath,"Item");
    }
}
