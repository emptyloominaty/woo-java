package com.woo.game.objects.abilities;

import com.woo.game.objects.gameobjects.Creature;

public class Ability {
/*    String name;
    double gcd;
    double cd;
    double maxCd;
    boolean channeling;
    boolean casting;
    boolean canMove;
    String type; //fire,frost,physical,nature
    int charges = 1;
    int maxCharges = 1;
    //TODO: effects arraylist? custom object?
    boolean noGcd = false;

    boolean poison = false;
    boolean bleed = false;

    boolean hasteCd = false;
    boolean hasteGcd = true;
    int range = 5;

    double cost = 0;
    int secCost = 0;

    double spellPower = 0;
    double duration = 0;

    //prevents multiple uses in a row (nogcd) (nocd/2charges)
    double abilityCd = 0.2;
    double abilityMaxCd = 0.2;

    boolean canCastWhileRooted = true;


    boolean canUse = true;
    boolean requiresStealth = false;
    boolean dontBreakStealth = false;
    boolean aoe = false;
    boolean lessThanHealth = false;

    boolean dispellable = false;
    boolean hiddenBuff = false;
    boolean permanentBuff = false;

    boolean talent = false;
    boolean talentSelected = true;


    //tooltip
    boolean mastery = false;
    boolean hiddenFromSpellBook = false;
    boolean passive = false;

    public Ability() {

    }

    public String getTooltip(int primaryStat) { //TODO: stats
        return ""; //TODO:
    }

    public String getBuffTooltip(Creature caster, Creature target) { //TODO:Buff object
        return ""; //TODO:
    }

    public boolean cast(Creature caster) {
        return true;
    }

    public void runBuff(Creature target, TODO Buff,int id) {

    }

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
        if (caster.isStunned || caster.isDead || (caster.isInterrupted && type.equals("Physical"))) {
            return false;
        }
        if (canUse && checkGcd(caster) && !caster.isInterrupted && checkCost(caster,cost,secCost) && checkCasting(caster && checkCd(caster) && abilityCd>=abilityMaxCd && checkRooted(caster))) {
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

    public boolean checkDistance(Creature caster, Creature target, int rangeP,boolean dontShow) { //range = 0, dontShow = false
        if (rangeP===0) {
            rangeP = this.range;
        }
        if (getDistance(caster,target)>rangeP) {
            if (caster==player && !dontShow) {
                //_message.update("Out of range", 2, colors.error)
            }
            return false;
        } else {
            return true;
        }
    }

    public boolean canSpellQueue(Creature caster) {
        return (caster==player && caster.gcd<spellQueueWindow && (caster.gcd>0 || caster.isCasting));
    }
*/
}
