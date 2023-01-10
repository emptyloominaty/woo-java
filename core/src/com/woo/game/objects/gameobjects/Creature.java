package com.woo.game.objects.gameobjects;

import java.util.HashMap;

public class Creature extends GameObject {
    public String type;

    public double health;
    public double healthMax;
    public double energy;
    public double energyMax;
    public double secondaryResource;
    public double secondaryResourceMax;
    public Creature target;
    //TODO:GCD, ABILITIES,...


    public int level;
    public long xp;
    public int faction;

    public HashMap<String, Double> stats = new HashMap<>();

    public Creature(String name, String description, boolean solid, boolean interactable, double x, double y, String spritePath) {
        super(name, description, solid, interactable, x, y, spritePath,"Creature");


        this.stats.put("haste", 10.0); //....
        //stats.get("haste)

    }



    public void updateStats() {
        health = stats.get("Stamina")*5;

    }

}
