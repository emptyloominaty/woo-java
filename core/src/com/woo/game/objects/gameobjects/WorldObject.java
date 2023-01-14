package com.woo.game.objects.gameobjects;

public class WorldObject extends GameObject{
    public boolean flamable = false;
    public boolean destroyable = false;
    public WorldObject(String name, String description, boolean solid, boolean interactable, double x, double y, String spritePath) {
        super(name, description, solid, interactable, x, y, spritePath,"World Object");
    }
}
