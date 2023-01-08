package com.woo.game.objects.gameobjects;

public class WorldObject extends GameObject{
    public String type = "World Object";
    WorldObject(String name, String description, boolean solid, boolean interactable, double x, double y, String spritePath) {
        super(name, description, solid, interactable, x, y, spritePath);
    }
}
