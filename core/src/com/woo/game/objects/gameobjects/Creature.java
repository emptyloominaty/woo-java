package com.woo.game.objects.gameobjects;

import com.woo.game.GlobalVars;

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

    public boolean isStunned = false;
    public boolean isRooted = false;
    public boolean isRolling = false;

    public Creature(String name, String description, boolean solid, boolean interactable, float x, float y, String spritePath, int faction) {
        super(name, description, solid, interactable, x, y, spritePath,"Creature");


        this.stats.put("haste", 10.0);
        this.stats.put("crit", 5.0);
        this.stats.put("mastery", 5.0);

        this.stats.put("intellect", 10.0);
        this.stats.put("strength", 10.0);
        this.stats.put("dexterity", 10.0);
        this.stats.put("stamina", 10.0);

        this.faction = faction;
        //stats.get("haste)

    }



    public void updateStats() {
        health = stats.get("Stamina")*5;
    }

    public void move(double moveSpeed) {
        double speed = (moveSpeed* GlobalVars.pxToMeter) * GlobalVars.delta;
        double angleInRadian = (direction-180) / 180 * Math.PI;

        double vx = Math.sin(angleInRadian) * speed;
        double vy = Math.cos(angleInRadian) * speed;

        x += vx;
        y += vy;
    }

    public void rotate(double dir) { //0-360
        if (!isStunned && !isRooted && !isRolling) {
            direction = dir;
            direction = direction % 360;
            if (direction < 0) {
                direction += 360;
            }
        }
    }

}
