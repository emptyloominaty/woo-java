package com.woo.game.objects.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.woo.game.GlobalVars;
import com.woo.game.ai.AiMain;
import com.woo.game.objects.abilities.Ability;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Creature extends GameObject {
    public String type;

    public double health;
    public double healthMax;
    public String resourceName;
    public double energy;
    public double energyMax;
    public String secondaryResourceName;
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
    public boolean isInterrupted = false;
    public boolean cantDie = false;

    public double gcd = 0;

    public float mousePosX = 0;
    public float mousePosY = 0;

    public Map<String, Object> channeling = new HashMap<>();
    public Map<String, Object> casting = new HashMap<>();
    public Map<String, Ability> abilities = new HashMap<>();

    public double moveSpeedIncrease = 1;
    public AiMain ai;

    public Creature(String name, String description, boolean solid, boolean interactable, float x, float y, String spritePath, int faction, String creatureClass, float direction) {
        super(name, description, solid, interactable, x, y, spritePath,"Creature",direction);

        ai = new AiMain(this);

        //TEST
        this.healthMax = 50;
        this.health = 50;
        this.energyMax = 100;
        this.energy = 100;
        this.secondaryResourceMax = 5;
        this.secondaryResource = 0;
        //-----------------------

        this.creatureClass = creatureClass;
        this.stats.put("haste", 10.0);
        this.stats.put("crit", 5.0);
        this.stats.put("mastery", 5.0);

        this.stats.put("intellect", 10.0);
        this.stats.put("strength", 10.0);
        this.stats.put("dexterity", 10.0);
        this.stats.put("stamina", 10.0);

        this.stats.put("block", 0.0); //chance to reduce damage by 70% (magic,melee,ranged)
        this.stats.put("parry", 0.0); //chance to reduce damage by 100% (melee)
        this.stats.put("dodge", 0.0); //chance to reduce damage by 100% (melee+ranged)

        this.stats.put("magicResistance", 0.0); //-% magic damage taken
        this.stats.put("DamageTaken", 0.0); //-% damage taken
        this.stats.put("DamageDone", 0.0); //+% damage done

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

    public void main() {
        //TEST
        /*if (Math.random()>0.8) {
            this.health-= 1;
            if (this.health<10) {
                this.health = this.healthMax;
            }
        }*/

        //TODO:Floating Texts?
        //AI
        if (this.faction!=0) {
            ai.main();
        }
        //TODO:PETS?
        //TODO:Resource

        if (energy>energyMax) {
            energy = energyMax;
        }

        if (this.gcd>0) {
            this.gcd -= GlobalVars.delta;
        }

        //TODO:Ability Cds

        if (this.isStunned) {
            isCasting = false;
            //isCasting2 = false;
            isChanneling = false;
            gcd = 0;
        }

        //TODO:casting ability

        //TODO:channeling ability

        //TODO:aggro(Enemy)

        //---------------------------
        //TODO:Reset
        //healthMax = stats.get("stamina")*5 * increaseHealth;


        //---------------------------
        //TODO:buffs
        //TODO:debuffs

        //death
        if (health<0) {
            die();
        }

    }

    public boolean die() {
        if (cantDie) {
            return false;
        }
        //TODO:floatingTexts.removeAll()
        isDead = true;

        //TODO:remove pets
        //TODO:buffs.ability.endBuff()
        //TODO:buffs.ability.onDeath()
        //TODO:debuffs.ability.endBuff()
        //TODO:debuffs.ability.onDeath()

        //TODO:debuffs = [];
        //TODO:buffs = [];

        return true;
    }

    public void setMousePos(float x, float y) {
        mousePosX = x;
        mousePosY = y;
    }

    //TODO: useResource
    //TODO: useSecondaryResource
    //TODO: updateHealth?
    //TODO: changeTalent

    public boolean interrupt() {
        if (this.isChanneling) {
            this.isChanneling = false;
            return true;
        } else if (this.isCasting) {
            this.isCasting = false;
            //TODO:this.abilities[this.casting.name].setCd();
            return true;
        }
        return false;
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
        double speed = (moveSpeed * GlobalVars.pxToMeter) * GlobalVars.delta;
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
            this.x += vx;
            this.y += vy;
        }
    }

    public void rotate(float dir) { //0-360
        if (!isStunned && !isRooted && !isRolling) {
            direction = dir;
            direction = direction % 360;
            if (direction < 0) {
                direction += 360;
            }
        }
    }

    public void draw(ShapeDrawer shapeDrawer) {
        if (!destroyed) {
            shapeDrawer.setColor(Color.RED);
            shapeDrawer.filledCircle(x, y, 10);
        }
    }

}
