package com.woo.game.objects.abilities.fireMage;

import com.woo.game.AbilityFunctions;
import com.woo.game.GlobalFunctions;
import com.woo.game.objects.ParticleSystem;
import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.gameobjects.Creature;
import com.woo.game.objects.gameobjects.GOControl;
import com.woo.game.objects.gameobjects.Spell;


public class Wildfire extends Ability {
    public Wildfire() {
        super("Wildfire", 40,0,0.65, 0, false, false, false, "fire", 20, 1, 0,1);
        this.moveSpeed = 20;
        this.life = 1;
        this.spellPower = 1.35;
        this.iconPath = "icons/mage/fire2.png";
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

        float angle = 360/20;
        for (int i = 0; i<20; i++) {
            //TODO:no rng life (450-900) -> (700,700)
            ParticleSystem.add("fire2",45, caster.direction+(angle*i), caster.x, caster.y);
            /*TEST: int particleId = ParticleSystem.add("fire",25, caster.direction-180+(angle*i), caster.x, caster.y);
            Spell newSpell = new Spell("test Spell","test",false,false,caster.x,caster.y,"",caster.direction+(angle*i), particleId,true, this.moveSpeed, this.life,this);
            newSpell.caster = caster;
            GOControl.addSpell(newSpell);*/
        }

        for (Creature creature : GOControl.creatures) {
            if (!creature.destroyed && creature!=caster && GlobalFunctions.checkEnemy(caster,creature) && (this.range>GlobalFunctions.getDistance(caster.x,caster.y,creature.x,creature.y))) {
                AbilityFunctions.doDamage(caster,creature,this,0,true,false,"",0);
            }
        }

        setCd();
        caster.useEnergy(this.cost,this.secCost);
        return true;
        //return false;
    }

    public void execute(Creature caster,Creature target) {
    }

}
