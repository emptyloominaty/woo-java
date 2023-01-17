package com.woo.game.objects.abilities.fireMage;

import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.gameobjects.Creature;

public class FireBlast extends Ability {
    public FireBlast() {
        super("FireBlast", 10,0,0.65, 0.65, false, true, false, "fire", 40, 1, 0);
    }

    @Override
    public String getTooltip(Creature caster) {
        return "Test"; //TODO:
    }

    public boolean startCast(Creature caster) {
        if (checkStart(caster,this.cost,this.secCost)) {


            return true;
        }
        return false;
    }

    public boolean endCast(Creature caster) {
        return false;
    }

}
