package com.woo.game.objects.gameobjects;

public class WorldObject extends GameObject{
    public boolean flammable = false;
    public boolean destroyable = false;
    public boolean canStopSpells = false;
    public WorldObject(String name, String description, boolean solid, boolean interactable, float x, float y, String spritePath, float direction) {
        super(name, description, solid, interactable, x, y, spritePath,"World Object", 0);
    }
}
