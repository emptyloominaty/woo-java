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
            //damage = (caster.stats.primary * ability.spellPower) * (1 + (caster.stats.vers / 100)) * crit;
        } else {
            //damage = (caster.stats.primary * spellPower) * (1 + (caster.stats.vers / 100)) * crit;
        }
        if (val!=0) {
            damage =  val;
        }
    }

}
