package com.woo.game;

import com.woo.game.objects.gameobjects.Creature;

public class GlobalFunctions {
    public static boolean checkEnemy(Creature caster, Creature target) {
        if (caster.faction<2) {
            if (target.faction>1) {
                return true;
            }
        } else {
            if (target.faction<2) {
                return true;
            }
        }
        return false;
    }
    //-----------
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
    public static float getDistance(Creature target1, Creature target2) {
        float a = target1.x - target2.x;
        float b = target1.y - target2.y;
        return (float) ((Math.sqrt( a*a + b*b))/GlobalVars.pxToMeter);
    }
    public static float getDistance(float x1,float y1,float x2,float y2) {
        float a = x1 - x2;
        float b = y1 - y2;
        return (float) ((Math.sqrt( a*a + b*b))/GlobalVars.pxToMeter);
    }
    //-----------
    public static float getDirection(float x1, float y1, float x2, float y2) {
        return (float)(360-(Math.atan2(y2 - y1, x2 - x1)* (180 / Math.PI)+90));
    }
}
