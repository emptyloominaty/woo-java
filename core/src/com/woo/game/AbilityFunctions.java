package com.woo.game;

import com.woo.game.objects.Settings;
import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.gameobjects.Creature;
import com.woo.game.objects.other.Buff;

import static com.woo.game.Main.uiMain;

public class AbilityFunctions {
    //TODO: doHeal, applyDot, applyBuff, applyDebuff, applyHot,
    public static void doDamage(Creature caster, Creature target, Ability ability,double spellPower,boolean canCrit, boolean crit100, String school2, double val) {
        //caster,target,ability, 0,true,false,"",0
        int crit = 1;
        if (canCrit) {
            crit = GlobalFunctions.critChance(caster);
        }
        if (crit100) {
            crit = 2;
        }
        double damage = 0;
        String school = ability.school;
        if (!school2.equals("")) {
            school = school2;
        }
        if (spellPower == 0) {
            damage = (caster.stats.get(caster.primaryStat) * ability.spellPower) * crit;
        } else {
            damage = (caster.stats.get(caster.primaryStat) * spellPower) * crit;
        }
        if (val!=0) {
            damage =  val;
        }
        if (caster.faction>0) { //TODO: >0 / 1????
            damage = damage*(Settings.map.get("Difficulty").value/100);
        }

        //TODO:DR, DI, idk

        target.health -= damage;

        if (Settings.map.get("Floating Combat Text").value>0) {
            if (caster.faction == 0) {
                if (crit==1) {
                    uiMain.addFloatingText((float) (target.x-5+(Math.random()*10)), (float) (target.y-5+(Math.random()*10)), String.valueOf(Math.round(damage)), "damage");
                } else {
                    uiMain.addFloatingText((float) (target.x-5+(Math.random()*10)), (float) (target.y-5+(Math.random()*10)), String.valueOf(Math.round(damage)), "crit");
                }
            } else if (caster.faction == -1) {
                //TODO:PET DMG FT
            }
        }
    }



    public static void doHeal(Creature caster,Creature target, Ability ability, double spellPower,boolean canCrit,boolean crit100,double val,double incCrit,String t) {
        //caster,target,ability, 0, true, false, 0 ,0 ,""/"hot"
        if (!target.isDead) {
            double crit = GlobalFunctions.critChance(caster,incCrit);
            if (!canCrit) { //0% crit chance
                crit = 1;
            }
            if (crit100) { //100% crit chance
                crit = 2;
            }
            double heal = 0;
            if (spellPower == 0) {
                heal = (caster.stats.get(caster.primaryStat) * ability.spellPower)* crit;
            } else {
                heal = (caster.stats.get(caster.primaryStat) * spellPower) * crit;
            }

            if (val!=0) {
                heal = val;
            }



            double overhealing = (target.health + heal) - target.healthMax;

            //leech
            /*if (caster.stats.get("leech")>0 && ability.name!="Leech") {
                caster.abilities["Leech"].startCast(caster,heal);
            }*/
            target.health += heal;
            if (target.health > target.healthMax) {
                target.health = target.healthMax;
            }
        }
    }

    public static boolean applyHot(Creature caster, Creature target, Ability ability, double duration, double extDuration, double spellPowerHot, double maxDuration) {
        //caster, target, ability, 0, 0, 0, ability.duration

        if (!target.isDead) {
            for (int i = 0; i<target.buffs.size(); i++) {
                if (target.buffs.get(i).name == ability.name && target.buffs.get(i).caster == caster) {
                    target.buffs.get(i).duration += ability.duration;
                    target.buffs.get(i).extendedDuration += 0;
                    if (target.buffs.get(i).duration>ability.duration*1.3) {
                        target.buffs.get(i).duration = ability.duration*1.3;
                    }
                    return true;
                }
            }
            double spellPower = ability.spellPower;
            if (spellPowerHot!=0) {
                spellPower = spellPowerHot;
            }
            if (duration == 0) {
                target.buffs.add(new Buff(ability.name,ability.school,"hot",ability.effects,0,ability.duration,maxDuration,0,spellPower/ability.duration,caster,ability));
                //target.buffs.push({name:ability.name,school:ability.school, type:"hot", effect:JSON.parse(JSON.stringify(ability.effect)), timer:0, duration:ability.duration, maxDuration:maxDuration, extendedDuration:0, spellPower:spellPower/ability.duration, caster:caster,ability:ability })
            } else {
                target.buffs.add(new Buff(ability.name,ability.school,"hot",ability.effects,0,duration,maxDuration,extDuration,spellPower/ability.duration,caster,ability));
                //target.buffs.push({name:ability.name,school:ability.school, type:"hot", effect:JSON.parse(JSON.stringify(ability.effect)), timer:0, duration:duration, maxDuration:maxDuration, extendedDuration:extDuration, spellPower:spellPower/ability.duration, caster:caster,ability:ability })
            }
        }
        return false;
    }



}
