package com.woo.game.objects.abilities;

import com.woo.game.GlobalVars;

import static com.woo.game.Main.player;

public class SpellQueue {
    public static boolean done = true;
    public static Ability ability;
    public static float time = 0;

    public static void main() {
        if (SpellQueue.time>0) {
            SpellQueue.time -= GlobalVars.delta;
        } else if (SpellQueue.done==false) {
            if (!player.isCasting) {
                if (player.gcd<0.01) {
                    SpellQueue.done = true;
                    ability.startCast(player);
                    return;
                }

            }
            SpellQueue.time+=((double)player.casting.get("time2")-(double)player.casting.get("time"))+0.005+(GlobalVars.delta);
        }
    }

    public static void add(Ability ability, float time) {
        SpellQueue.ability = ability;
        SpellQueue.time = time+GlobalVars.delta;
        SpellQueue.done = false;
    }
}
