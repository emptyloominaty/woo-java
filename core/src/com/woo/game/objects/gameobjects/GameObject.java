package com.woo.game.objects.gameobjects;

public class GameObject {
    public String name;
    public String description;
    public String spritePath; //String????
    public boolean solid; //A boolean indicating whether the game object is solid and can block movement.
    public boolean interactable; //A boolean indicating whether the player can interact with the game object (e.g. by picking it up or talking to it).

    public double x;
    public double y;

    GameObject(String name, String description, boolean solid, boolean interactable, double x, double y, String spritePath) {
        this.name = name;
        this.description = description;
        this.solid = solid;
        this.interactable = interactable;
        this.x = x;
        this.y = y;
        this.spritePath = spritePath;
    }

}
