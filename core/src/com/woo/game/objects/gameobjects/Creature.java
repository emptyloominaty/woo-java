package com.woo.game.objects.gameobjects;

import com.woo.game.GlobalVars;
import com.woo.game.objects.abilities.Ability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Creature extends GameObject {
    public String type;

    public double health;
    public double healthMax;
    public double energy;
    public double energyMax;
    public double secondaryResource;
    public double secondaryResourceMax;
    public Creature target;
    public String creatureClass; // FireMage, FrostMage, Warrior, Ranger, // Rogue, Necromancer, Warlock, Paladin,
    //TODO:Inventory
    //TODO:Gear



    public int level;
    public long xp;
    public int faction; //0-player 1-friendly 2-neutral 3>=enemy

    public HashMap<String, Double> stats = new HashMap<>();

    public boolean isStunned = false;
    public boolean isRooted = false;
    public boolean isRolling = false;

    public boolean isCasting = false;
    public boolean isChanneling = false;
    public boolean canMoveWhileCasting = false;
    public boolean isDead = false;
    public double gcd = 0;

    public Map<String, Object> channeling = new HashMap<>();
    public Map<String, Object> casting = new HashMap<>();
    public Map<String, Ability> abilities = new HashMap<>();

    public double moveSpeedIncrease = 1;

    public Creature(String name, String description, boolean solid, boolean interactable, float x, float y, String spritePath, int faction, String creatureClass) {
        super(name, description, solid, interactable, x, y, spritePath,"Creature");

        this.creatureClass = creatureClass;
        this.stats.put("haste", 10.0);
        this.stats.put("crit", 5.0);
        this.stats.put("mastery", 5.0);

        this.stats.put("intellect", 10.0);
        this.stats.put("strength", 10.0);
        this.stats.put("dexterity", 10.0);
        this.stats.put("stamina", 10.0);

        this.faction = faction;
        //stats.get("haste)

        //{name:"", time:0, time2:0}
        channeling.put("name", "");
        channeling.put("time", 0.0);
        channeling.put("time2", 0.0);

        casting.put("name", "");
        casting.put("time", 0.0);
        casting.put("time2", 0.0);
    }



    public void updateStats() {
        health = stats.get("Stamina")*5;
    }

    public void move(double val, boolean noInc, int strafe, double forceVal) {
        double moveSpeed;
        if (!noInc) {
            moveSpeed = val * moveSpeedIncrease;
        } else {
            moveSpeed = val;
        }

        if (forceVal!=0) {
            moveSpeed = forceVal;
        }
        double speed = (moveSpeed* GlobalVars.pxToMeter) * GlobalVars.delta;
        double angleInRadian = 0;
        if (strafe==0) {
            angleInRadian = (direction-180) / 180 * Math.PI;
        } else if (strafe==1) {
            angleInRadian = (direction-90) / 180 * Math.PI;
        } else if (strafe==2) {
            angleInRadian = (direction-270) / 180 * Math.PI;
        }

        double vx = Math.sin(angleInRadian) * speed;
        double vy = Math.cos(angleInRadian) * speed;

        //x += vx;
        //y += vy;



        if (this.isCasting && this.abilities.get(this.casting.get("name")).castTime>0 && !this.canMoveWhileCasting) {
            this.isCasting = false;
            this.gcd = 0;
        }
        /*if (this.isCasting2 && this.abilities[this.casting2.name].castTime>0 && !this.canMoveWhileCasting) {
            this.isCasting2 = false;
            this.gcd = 0;
        }*/

        if (this.isChanneling && !this.canMoveWhileCasting) {
            this.abilities.get(this.channeling.get("name")).endChanneling(this);
            this.isChanneling = false;
        }
        if (!this.isStunned && !this.isRooted && !this.isDead) {
            this.x += vx * (GlobalVars.delta*60);
            this.y += vy * (GlobalVars.delta*60);
        }
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
