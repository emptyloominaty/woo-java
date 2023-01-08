package com.woo.game.objects.gameobjects;

public class Item extends GameObject{
    public String type = "Item";
    Item(String name, String description, boolean solid, boolean interactable, double x, double y, String spritePath) {
        super(name, description, solid, interactable, x, y, spritePath);
    }
}
