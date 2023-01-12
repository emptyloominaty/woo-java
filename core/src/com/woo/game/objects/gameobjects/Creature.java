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
    public int faction; //0-player 1-friendly 2-neutral 3>=enemy

    public HashMap<String, Double> stats = new HashMap<>();

    public Creature(String name, String description, boolean solid, boolean interactable, double x, double y, String spritePath, int faction) {
        super(name, description, solid, interactable, x, y, spritePath,"Creature");


        this.stats.put("haste", 10.0); //....
        this.faction = faction;
        //stats.get("haste)

    }



    public void updateStats() {
        health = stats.get("Stamina")*5;

    }

}
