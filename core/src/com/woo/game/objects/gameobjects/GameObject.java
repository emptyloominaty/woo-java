package com.woo.game.objects.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.woo.game.GlobalVars;
import space.earlygrey.shapedrawer.ShapeDrawer;

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
    public int lightDistance;

    //collision box
    public float sizeX = 30;
    public float sizeY = 30;

    public float direction = 0;

    public float x;
    public float y;


    public int id = 0;
    public int typeId = 0;
    public String type;

    public void setCollisionBox(float x, float y) {
        sizeX = x;
        sizeY = y;
    }
    public void setCollisionBox(float size) {
        sizeX = size;
        sizeY = size;
    }

    GameObject(String name, String description, boolean solid, boolean interactable, float x, float y, String spritePath, String type, float direction) {
        this.name = name;
        this.description = description;
        this.solid = solid;
        this.interactable = interactable;
        this.x = x;
        this.y = y;
        this.spritePath = spritePath;
        this.type = type;
        this.direction = direction;

        //this.id = getGameObjectIdx();
    }

    public void attachLight(int[] color, int intensity, int distance) {
        lightEmitting = true;
        lightColors = color;
        lightIntensity = intensity;
        lightDistance = distance;
    }

    public void move(double val) {
        double speed = (val * GlobalVars.pxToMeter) * GlobalVars.delta;
        double angleInRadian = 0;
        angleInRadian = (direction-180) / 180 * Math.PI;
        double vx = Math.sin(angleInRadian) * speed;
        double vy = Math.cos(angleInRadian) * speed;
        this.x += vx;
        this.y += vy;
    }

    public void destroy() {
        GOControl.removeGameObject(this.id);
    }

    public void test() {
        System.out.println(name+" | "+type+" | "+destroyed+" | id:"+id+" | typeId:"+typeId);
    }

    public void updateId(int id) {
        this.id = id;
    }

    public void updateTypeId(int id) {
        this.typeId = id;
    }

    public void draw(ShapeDrawer shapeDrawer) {
        if (!destroyed) {
            shapeDrawer.setColor(Color.RED);
            shapeDrawer.filledCircle(x, y, 4);
        }
    }

}
