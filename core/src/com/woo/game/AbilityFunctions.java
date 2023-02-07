package com.woo.game;

import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.gameobjects.Creature;

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

        //TODO:DR, DI, idk

        target.health -= damage;

    }

}
