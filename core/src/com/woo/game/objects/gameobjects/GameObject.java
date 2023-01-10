package com.woo.game.objects.gameobjects;

import com.woo.game.Init;

public class GameObject {
    public String name;
    public String description;
    public String spritePath; //String????
    public boolean solid; //A boolean indicating whether the game object is solid and can block movement.
    public boolean interactable; //A boolean indicating whether the player can interact with the game object (e.g. by picking it up or talking to it).

    public boolean visible = true;
    public boolean destroyed = false;
    public boolean lightEmitting = false;
    public int[] lightColors = new int[3];
    public int lightIntensity;

    public int sizeX = 64;
    public int sizeY = 64;

    public int rotation = 0;

    public double x;
    public double y;


    public int id = 0;
    public int typeId = 0;
    public String type;

    GameObject(String name, String description, boolean solid, boolean interactable, double x, double y, String spritePath, String type) {
        this.name = name;
        this.description = description;
        this.solid = solid;
        this.interactable = interactable;
        this.x = x;
        this.y = y;
        this.spritePath = spritePath;
        this.type = type;

        //this.id = getGameObjectIdx();
    }

    public void attachLight(int[] color, int intensity) {
        lightEmitting = true;
        lightColors = color;
        lightIntensity = intensity;
    }

    public void test() {
        System.out.println(name+" "+type+" "+destroyed);
    }

    public void updateId(int id) {
        this.id = id;
    }

    public void updateTypeId(int id) {
        this.typeId = id;
    }
}
