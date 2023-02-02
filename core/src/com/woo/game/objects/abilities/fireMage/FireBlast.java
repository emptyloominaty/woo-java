package com.woo.game.objects.abilities.fireMage;

import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.gameobjects.Creature;
import com.woo.game.objects.gameobjects.GOControl;
import com.woo.game.objects.gameobjects.Spell;

import static com.woo.game.Main.player;

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

            //TODO: setgcd
            return true;
        }
        //TODO:SpellQueue
        return false;
    }

    public boolean endCast(Creature caster) {
        caster.isCasting = false;
        Spell newSpell = new Spell("test Spell","test",false,false,player.x,player.y,"",player.direction);
        newSpell.faction = caster.faction;
        GOControl.addSpell(newSpell);
        //TODO: setCd, UseEnergy
        return true;

        //return false;
    }



}
