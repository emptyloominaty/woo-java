package com.woo.game;

import com.woo.game.objects.gameobjects.Creature;

public class GlobalFunctions {
    public static boolean checkEnemy(Creature caster, Creature target) {
        //TODO:?
        //-1 = player pets
        //0 = player
        //1 = friendly to player
        //2 = neutral
        //3 = enemy to player
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
    public static boolean checkCollision(float x1, float y1, float w1, float h1, float x2, float y2, float w2, float h2) {
        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
    }
    public static boolean checkCollision(float x1, float y1, float size1, float x2, float y2, float size2) {
        return x1 < x2 + size2 && x1 + size1 > x2 && y1 < y2 + size2 && y1 + size1 > y2;
    }
    //-----------
    public static float getDirection(float x1, float y1, float x2, float y2) {
        return (float)(360-(Math.atan2(y2 - y1, x2 - x1)* (180 / Math.PI)+90));
    }
    //-----------
    public static String getHealthString(double health) {
        if (health>999999999) {
            return Math.round(health/1000000000)+"B";
        } else if (health>999999) {
            return Math.round(health/1000000)+"M";
        } else if (health>999) {
            return Math.round(health/1000)+"K";
        } else {
            return Math.round(health)+"";
        }
    }
    //-----------
    public static String getString(double val) {
        if (val>999999999) {
            return ((double)Math.round(val/100000000)/10)+"B";
        } else if (val>999999) {
            return ((double)Math.round(val/100000)/10)+"M";
        } else if (val>999) {
            return ((double)Math.round(val/100)/10)+"K";
        } else {
            return ((double)Math.round(val*10)/10)+"";
        }
    }
    //-----------
    public static String getTimeString(double time) {
        if (time>60) {
            return Math.round(time/60)+"m";
        } else {
            return Math.round(time)+"s";
        }
    }
    //-----------
    public static String spellPowerToNumber(double val,Creature caster,String primaryStat) {
        return Math.round(caster.stats.get(primaryStat) * val)+"";
    }
    public static String spellPowerHotToNumber(double val,Creature caster, String primaryStat) {
        return Math.round(caster.stats.get(primaryStat) * (1 + (caster.stats.get("haste") / 100)))+"";
    }
    //-----------
    public static double xpToNextLevel(int level) {
        return (500 * (Math.pow(1.2, level)-1)/(1.5-1));
    }
}
