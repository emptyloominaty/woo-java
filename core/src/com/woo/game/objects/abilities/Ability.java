package com.woo.game.objects.abilities;

import com.woo.game.GlobalVars;
import com.woo.game.GlobalFunctions;
import com.woo.game.objects.gameobjects.Creature;

import java.util.ArrayList;
import java.util.Map;

import static com.woo.game.Main.player;


public class Ability {
    public String name;
    public double gcd;
    public double cd;
    public double maxCd;
    public boolean channeling;
    public boolean casting;
    public boolean canMove;
    public double castTime = 0;
    public String school; //fire,frost,physical,nature
    public int charges = 1;
    public int maxCharges = 1;
    public ArrayList<Map> effects;
    public boolean noGcd = false;

    public boolean poison = false;
    public boolean bleed = false;

    public boolean hasteCd = false;
    public boolean hasteGcd = true;
    public float range = 5f;

    public double cost = 0;
    public int secCost = 0;

    public double spellPower = 0;
    public double duration = 0;

    //prevents multiple uses in a row (nogcd) (nocd/2charges)
    public float abilityCd = 0.2f;
    public float abilityMaxCd = 0.2f;

    public boolean canCastWhileRooted = true;


    public boolean canUse = true;
    public boolean requiresStealth = false;
    public boolean dontBreakStealth = false;
    public boolean aoe = false;
    public boolean lessThanHealth = false;

    public boolean dispellable = false;
    public boolean hiddenBuff = false;
    public boolean permanentBuff = false;

    public boolean talent = false;
    public boolean talentSelected = true;


    //tooltip
    public boolean mastery = false;
    public boolean hiddenFromSpellBook = false;
    public boolean passive = false;

    public Ability(String name, double cost, int secCost, double gcd, double castTime, boolean channeling, boolean casting, boolean canMove,
                   String school, float range, int charges, double cd) {
        this.name = name;
        this.cost = cost;
        this.secCost = secCost;
        this.gcd = gcd;
        this.castTime = castTime;
        this.channeling = channeling;
        this.casting = casting;
        this.canMove = canMove;
        this.school = school;
        this.range = range;
        this.charges = charges;
        this.maxCharges = charges;
        this.cd = cd;
        this.maxCd = cd;

    }

    public String getTooltip(Creature caster) { //TODO: stats
        return ""; //TODO:
    }

    public String getBuffTooltip(Creature caster, Creature target) { //TODO:Buff object
        return ""; //TODO:
    }

    public boolean cast(Creature caster) {
        return true;
    }

    /*public void runBuff(Creature target, TODO Buff,int id) {

    }*/

    public void endBuff(Creature target) {

    }

    public void endChanneling(Creature caster) {

    }

    public void run(Creature caster) {

    }

    public boolean startCast(Creature caster) {
        return false;
    }

    public boolean endCast(Creature caster) {
        return false;
    }

    public boolean checkStart(Creature caster, double cost, int secCost) {
        if (talent != talentSelected) {
            return false;
        }
        if (caster.isStunned || caster.isDead || (caster.isInterrupted && school.equals("Physical"))) {
            return false;
        }
        if (canUse && checkGcd(caster) && !caster.isInterrupted && checkCost(caster,cost,true,secCost) && checkCasting(caster) && checkCd(caster,false) && abilityCd>=abilityMaxCd && checkRooted(caster)) {
            return true;
        }
        return false;
    }

    public boolean checkRooted(Creature caster) {
        if (!canCastWhileRooted && caster.isRooted) {
            return false;
        }
        return true;
    }

    public boolean checkGcd(Creature caster) {
        if (!noGcd) {
            if (caster.gcd <= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean checkCasting(Creature caster) {
        if (!noGcd) {
            if (caster.isCasting) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public void setCd() {
        if (maxCharges>1) {
            if (charges==maxCharges) {
                this.cd = 0;
            }
            charges--;
        } else {
            cd = 0;
        }
    }

    public void setGcd(Creature caster,double gcd) { //gcd = 0
        //TODO:
    }

    public boolean isEnemy(Creature caster, Creature target) {
        //TODO
        return false;
    }


    public boolean checkDistance(Creature caster, Creature target, float rangeP,boolean dontShow) { //range = 0, dontShow = false
        if (rangeP==0) {
            rangeP = this.range;
        }
        if (GlobalFunctions.getDistance(caster,target)>rangeP) {
            if (caster==player && !dontShow) {
                //TODO:_message.update("Out of range", 2, colors.error)
            }
            return false;
        } else {
            return true;
        }
    }

    //TODO:
    /*public boolean canSpellQueue(Creature caster) {
        return (caster==player && caster.gcd<spellQueueWindow && (caster.gcd>0 || caster.isCasting));
    }*/

    public boolean checkCost(Creature caster,double cost,boolean showMessage,int secCost) {
        if (cost==-9999) {
            cost = this.cost;
        }
        if (secCost==-9999) {
            secCost = this.secCost;
        }
        if (secCost==9999) { //ALL
            secCost = 1;
        }
        if (caster.energy>=cost) {
            if (secCost>0 && caster.secondaryResourceMax>0) {
                if (caster.secondaryResource>=secCost) {
                    return true;
                } else {
                    if (caster==player && showMessage) {
                        //_message.update("Not enough "+caster.secondaryResourceName, 2, colors.error); //TODO: GameMessage class
                    }
                    return false;
                }
            } else {
                return true;
            }
        } else {
            if (caster==player && showMessage) {
                //_message.update("Not enough "+caster.resourceName, 2, colors.error);
            }
            return false;
        }
    }

    public boolean checkCd(Creature caster, boolean dontShow) {
        if (this.maxCharges>1) {
            if (this.charges>0) {
                return true;
            } else {
                if (caster==player && !dontShow) {
                    //_message.update("Ability is not ready yet",2,colors.error);
                }
                return false;
            }
        } else {
            if (this.cd>=this.maxCd) {
                return true;
            } else {
                if (caster==player && !dontShow) {
                    //_message.update("Ability is not ready yet",2,colors.error);
                }
                return false;
            }
        }
    }

    public void incCd(Creature caster, double inc,boolean hasteCd) {
        //TODO: hasteCd????

        if (this.abilityCd<this.abilityMaxCd) {
            this.abilityCd += GlobalVars.delta;
        }
        if (hasteCd) {
            if (this.maxCharges>1) {
                //charges haste
                if (this.cd<this.maxCd) {
                    this.cd += inc * (1 + (caster.stats.get("haste") / 100));
                    if (this.cd>=this.maxCd) {
                        this.charges++;
                        if (this.charges!=this.maxCharges) {
                            this.cd=0;
                        }
                    }
                }
            } else {
                //cd haste
                if (this.cd<this.maxCd) {
                    TODO:this.cd += inc * (1 + (caster.stats.get("haste") / 100));
                }
            }
        } else {
            if (this.maxCharges>1) {
                //charges
                if (this.cd<this.maxCd) {
                    this.cd += inc;
                    if (this.cd>=this.maxCd) {
                        this.charges++;
                        if (this.charges!=this.maxCharges) {
                            this.cd=0;
                        }
                    }
                }
            } else {
                //cd
                if (this.cd<this.maxCd) {
                    this.cd += inc;
                }
            }
        }
    }

    public void reduceCd(double val) {
        this.cd += val;
    }

    public void setTalent() {
    }

    public void unsetTalent() {
    }

    public void onDeath(Creature caster,Creature target, ArrayList<Map> buff) {
    }

    public void killEnemy(Creature caster,Creature target) {
    }



}