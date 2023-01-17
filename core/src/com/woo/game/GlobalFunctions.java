package com.woo.game;

import com.woo.game.objects.gameobjects.Creature;

public class GlobalFunctions {
    public static boolean getChance(double chance) {
        double rng = (Math.random()*100);
        if (rng < chance) {
            return true;
        }
        return false;
    }
    //-----------
    public static int critChance(Creature caster) {
        double critChance = (Math.random()*100);
        double crit = caster.stats.get("crit");
        if (critChance < crit) {
            return 2;
        }
        return 1;
    }
    public static int critChance(Creature caster, double incCrit) {
        double critChance = (Math.random()*100);
        double crit = caster.stats.get("crit")+incCrit;
        if (critChance < crit) {
            return 2;
        }
        return 1;
    }
    //-----------
    public static double getDistance(Creature target1, Creature target2) {
        double a = target1.x - target2.x;
        double b = target1.y - target2.y;
        return (Math.sqrt( a*a + b*b))/GlobalVars.pxToMeter;
    }
}
