package com.woo.game.objects.abilities.fireMage;

import com.woo.game.objects.ParticleSystem;
import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.gameobjects.Creature;
import com.woo.game.objects.gameobjects.GOControl;
import com.woo.game.objects.gameobjects.Spell;

import static com.woo.game.Main.player;

public class FireBlast extends Ability {
    public FireBlast() {
        super("Fire Blast", 5,0,0.65, 0.65, false, true, false, "fire", 40, 1, 0,1);
        this.moveSpeed = 30;
        this.life = 1.5;
        this.spellPower = 0.85;
    }

    @Override
    public String getTooltip(Creature caster) {
        return "Test"; //TODO:
    }

    public boolean startCast(Creature caster) {
        if (checkStart(caster,this.cost,this.secCost)) {
            casterStartCasting(caster);
            setGcd(caster,0);
            return true;
        }
        //TODO:SpellQueue
        return false;
    }

    public boolean endCast(Creature caster) {
        caster.isCasting = false;

        //TODO: Fireblast particlefile?
        int particleId = ParticleSystem.add("fire",25, caster.direction-180, caster.x, caster.y);
        Spell newSpell = new Spell("test Spell","test",false,false,player.x,player.y,"",player.direction, particleId,true, this.moveSpeed, this.life,this);
        newSpell.caster = caster;
        GOControl.addSpell(newSpell);

        setCd();
        caster.useEnergy(this.cost,this.secCost);
        return true;
        //return false;
    }

    public void execute(Creature caster,Creature target) {
        //TODO:doDamage(target,spellPower....);

        //Test
        target.health -= 15+(Math.random()*15);
    }

}
