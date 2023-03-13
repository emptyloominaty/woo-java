package com.woo.game.objects.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.woo.game.AbilityFunctions;
import com.woo.game.GlobalFunctions;
import com.woo.game.GlobalVars;
import com.woo.game.ai.AiMain;
import com.woo.game.objects.Item;
import com.woo.game.objects.ParticleSystem;
import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.abilities.fireMage.FireBall;
import com.woo.game.objects.abilities.fireMage.FireBlast;
import com.woo.game.objects.abilities.fireMage.Wildfire;
import com.woo.game.objects.gameobjects.creatures.creatureInit;
import com.woo.game.objects.other.Buff;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.woo.game.Main.player;
import static com.woo.game.Main.uiMain;

public class Creature extends GameObject {
    public String type;
    public String primaryStat = "strength";

    public float moveToX = 0;
    public float moveToY = 0;
    public boolean moveToPoint = false;

    public double health;
    public double healthMax;
    public String resourceName = "Resource";
    public double energy;
    public double energyMax;
    public String secondaryResourceName = "Secondary Resource";
    public int secondaryResource;
    public int secondaryResourceMax;
    public Creature target;
    public String creatureClass; // FireMage, FrostMage, Warrior, Ranger, // Rogue, Necromancer, Warlock, Paladin,
    //TODO:Inventory
    //TODO:Gear

    public int statsPoints = 0;
    public int level = 1;
    public long xp;
    public int faction; //0-player 1-friendly 2-neutral 3>=enemy

    public HashMap<String, Double> stats = new HashMap<>();
    public HashMap<String, Double> statsB = new HashMap<>();

    public boolean isStunned = false;
    public boolean isRooted = false;
    public boolean isRolling = false;
    public boolean isStealthed = false;

    public boolean isCasting = false;
    public boolean isChanneling = false;
    public boolean canMoveWhileCasting = false;
    public boolean isDead = false;
    public boolean isInterrupted = false;
    public boolean cantDie = false;

    public boolean immuneToMagic = false;

    public Map<String, Item> equippedItems = new HashMap<String,Item>();
    public long gold = 0; //100

    public double gcd = 0;
    public double gcdMax = 0.65;

    public float mousePosX = 0;
    public float mousePosY = 0;

    public Map<String, Object> channeling = new HashMap<>();
    public Map<String, Object> casting = new HashMap<>();
    public Map<String, Ability> abilities = new HashMap<>();

    public ArrayList<Buff> buffs = new ArrayList<>();
    public ArrayList<Buff> debuffs = new ArrayList<>();


    public double moveSpeedIncrease = 1;
    public double reduceEnergyCost = 1;

    public int xpLoot = 10;

    public AiMain ai;

    public Creature(String name, String description, boolean solid, boolean interactable, float x, float y, String spritePath, int faction, String creatureClass, float direction) {
        super(name, description, solid, interactable, x, y, spritePath,"Creature",direction);
        ai = new AiMain(this);

        this.healthMax = 50;
        this.health = 50;
        this.energyMax = 100;
        this.energy = 100;
        this.secondaryResourceMax = 5;
        this.secondaryResource = 0;

        this.creatureClass = creatureClass;
        this.stats.put("haste", 0.0);
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

        channeling.put("name", "");
        channeling.put("time", 0.0);
        channeling.put("time2", 0.0);

        casting.put("name", "");
        casting.put("time", 0.0);
        casting.put("time2", 0.0);

        creatureInit.main(this);

        this.statsB.put("haste", this.stats.get("haste"));
        this.statsB.put("crit", this.stats.get("crit"));
        this.statsB.put("mastery", this.stats.get("mastery"));

        this.statsB.put("intellect", this.stats.get("intellect"));
        this.statsB.put("strength", this.stats.get("strength"));
        this.statsB.put("dexterity", this.stats.get("dexterity"));
        this.statsB.put("stamina", this.stats.get("stamina"));

        this.statsB.put("block", this.stats.get("block"));
        this.statsB.put("parry", this.stats.get("parry"));
        this.statsB.put("dodge", this.stats.get("dodge"));

        this.statsB.put("magicResistance", this.stats.get("magicResistance"));
        this.statsB.put("DamageTaken", this.stats.get("DamageTaken"));
        this.statsB.put("DamageDone", this.stats.get("DamageDone"));
    }

    public void updateStats() {
        health = stats.get("Stamina")*5;
    }

    public void resetStats() {
        this.stats.put("haste", this.statsB.get("haste"));
        this.stats.put("crit", this.statsB.get("crit"));
        this.stats.put("mastery", this.statsB.get("mastery"));

        this.stats.put("intellect", this.statsB.get("intellect"));
        this.stats.put("strength", this.statsB.get("strength"));
        this.stats.put("dexterity", this.statsB.get("dexterity"));
        this.stats.put("stamina", this.statsB.get("stamina"));

        this.stats.put("block", this.statsB.get("block"));
        this.stats.put("parry", this.statsB.get("parry"));
        this.stats.put("dodge", this.statsB.get("dodge"));

        this.stats.put("magicResistance", this.statsB.get("magicResistance"));
        this.stats.put("DamageTaken", this.statsB.get("DamageTaken"));
        this.stats.put("DamageDone", this.statsB.get("DamageDone"));
    }

    public void main() {
        //TEST
        /*if (Math.random()>0.8) {
            this.health-= 1;
            if (this.health<10) {
                this.health = this.healthMax;
            }
        }*/

        if (moveToPoint) {
            this.direction = GlobalFunctions.getDirection(this.x,this.y,this.moveToX,this.moveToY);
            this.move(10,false,0,0);
            if (GlobalFunctions.getDistance(this.x,this.y,this.moveToX,this.moveToY)<0.4) {
                this.moveToPoint = false;
            }
        }

        //AI
        if (this.faction!=0) {
            ai.main();
        }
        //TODO:PETS?
        //TODO:Resource
        this.health += GlobalVars.delta*0.25; //*increaseHealthRegen
        if (this.health>this.healthMax) {
            this.health = this.healthMax;
        }
        if (Objects.equals(resourceName, "Mana")) {
            this.energy += GlobalVars.delta*(this.energyMax/100);
        }
        if (energy>energyMax) {
            energy = energyMax;
        }

        if (this.gcd>0) {
            this.gcd -= GlobalVars.delta;
        }

        //Ability Cds
        for (Map.Entry<String, Ability> ability : abilities.entrySet()) {
            abilities.get(ability.getKey()).incCd(this,GlobalVars.delta,-1);
            abilities.get(ability.getKey()).run(this);
        }

        if (this.isStunned) {
            isCasting = false;
            //isCasting2 = false;
            isChanneling = false;
            gcd = 0;
        }

        //TODO:casting ability
        if (this.isCasting) {
            if ((double)this.casting.get("time")<(double)this.casting.get("time2")) {
                double time = (double) this.casting.get("time");
                time += GlobalVars.delta;
                this.casting.put("time",time);
            } else {
                this.abilities.get(this.casting.get("name")).endCast(this);
                this.isCasting = false;
            }
        }

        //TODO:channeling ability

        //TODO:aggro(Enemy)

        //---------------------------
        //TODO:Reset
        resetStats();

        //items
        for (String key : equippedItems.keySet()){
            if (equippedItems.get(key)!=null) {
                for (Object key2 : equippedItems.get(key).data.get("stats").keySet()){
                    double stat = stats.get(key2);
                    stat += (double)equippedItems.get(key).data.get("stats").get(key2);
                    stats.put((String)key2,stat);
                }
            }
        }
        //---------------------------
        //buffs
        for (int i = 0; i<this.buffs.size();i++) {
            if (this.buffs.get(i).type=="hot") {
                if (this.buffs.get(i).timer<1) {
                    this.buffs.get(i).timer+= (GlobalVars.delta)*(1 + (this.buffs.get(i).caster.stats.get("haste") / 100));
                } else {
                    AbilityFunctions.doHeal(this.buffs.get(i).caster,this,this.buffs.get(i).ability,this.buffs.get(i).spellPower,true,false,0,0,"hot");
                    this.buffs.get(i).timer = 0;
                }
            }











            if (!this.buffs.get(i).ability.permanentBuff) {
                this.buffs.get(i).duration -= GlobalVars.delta;
                if (this.buffs.get(i).duration < 0 || this.buffs.get(i).stacks <= 0) {
                    if (this.buffs.get(i).type=="hot") {
                        AbilityFunctions.doHeal(this.buffs.get(i).caster,this,this.buffs.get(i).ability,this.buffs.get(i).spellPower*this.buffs.get(i).timer,true,false,0,0,"hot");
                    }
                    this.buffs.get(i).ability.endBuff(this,i);
                    this.buffs.remove(i);
                    i--;
                } else {
                    this.buffs.get(i).ability.runBuff(this, this.buffs.get(i), i);
                }
            } else {
                if (this.buffs.get(i).duration==-1) {
                    this.buffs.get(i).ability.endBuff(this,i);
                    this.buffs.remove(i);
                    i--;
                } else {
                    this.buffs.get(i).ability.runBuff(this, this.buffs.get(i), i);
                }
            }
        }


        //debuffs
        for (int i = 0; i<this.debuffs.size(); i++) {
            if (Objects.equals(this.debuffs.get(i).type, "dot")) {
                if (this.debuffs.get(i).timer<1) {
                    this.debuffs.get(i).timer+= (GlobalVars.delta)*(1 + (this.debuffs.get(i).caster.stats.get("haste") / 100));
                } else {
                    this.debuffs.get(i).timer = 0;
                    AbilityFunctions.doDamage(this.debuffs.get(i).caster,this,this.debuffs.get(i).ability,this.debuffs.get(i).spellPower,true,false,"",0);
                    if (this.isDead) {
                        break;
                    }
                }
            }


            if (!this.debuffs.get(i).ability.permanentBuff) {
                this.debuffs.get(i).duration -= GlobalVars.delta;
                if (this.debuffs.get(i).duration < 0) {
                    if (Objects.equals(this.debuffs.get(i).type, "dot")) {
                        AbilityFunctions.doDamage(this.debuffs.get(i).caster,this,this.debuffs.get(i).ability,this.debuffs.get(i).spellPower*this.debuffs.get(i).timer,true,false,"",0);
                    }
                    this.debuffs.get(i).ability.endBuff(this,i);
                    this.debuffs.remove(i);
                    i--;
                } else {
                    this.debuffs.get(i).ability.runBuff(this, this.debuffs.get(i), i);
                }
            } else {
                if (this.debuffs.get(i).duration==-1) {
                    this.debuffs.get(i).ability.endBuff(this,i);
                    this.debuffs.remove(i);
                    i--;
                }
            }
        }

        healthMax = stats.get("stamina")*5;// * increaseHealth;

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
        if (this.faction>1/*???// && (100>GlobalFunctions.getDistance(player,this))*/) {
            player.gainXp(xpLoot+(this.level*2)); //TODO: xp table
            //TODO:loot
        }

        return true;
    }

    public void gainXp(float xp) {
        this.xp += xp;
        for (int i  = 0; i<10; i++) {
            if (this.xp>(500 * (Math.pow(1.2, this.level)-1)/(1.5-1))) {
                this.statsPoints += 5;
                this.level++;
            } else {
                return;
            }
        }
    }

    public void setMousePos(float x, float y) {
        mousePosX = x;
        mousePosY = y;
    }

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

    public void useEnergy(double val,int val2) {
        int val2Used = this.secondaryResource;
        if (this.reduceEnergyCost<0) {this.reduceEnergyCost=0;}
        this.energy -= val * this.reduceEnergyCost;
        if (val2!=0 && this.secondaryResourceMax>0) {
            this.useSec(val2);
        }
    }

    public void useSec(int val) {
        if (val==9999) {
            this.secondaryResource = 0;
        } else {
            this.secondaryResource -= val;
            if (this.secondaryResource>this.secondaryResourceMax) {
                this.secondaryResource = this.secondaryResourceMax;
            }
        }
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
    public void move(double val,boolean ability) {
        if (!ability) {
            return;
        }
        double speed = (val * GlobalVars.pxToMeter);
        double angleInRadian = (direction-180) / 180 * Math.PI;

        double vx = Math.sin(angleInRadian) * speed;
        double vy = Math.cos(angleInRadian) * speed;

        if (this.isCasting && this.abilities.get(this.casting.get("name")).castTime>0 && !this.canMoveWhileCasting) {
            this.isCasting = false;
            this.gcd = 0;
        }
        if (this.isChanneling && !this.canMoveWhileCasting) {
            this.abilities.get(this.channeling.get("name")).endChanneling(this);
            this.isChanneling = false;
        }
        this.x += vx;
        this.y += vy;

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
