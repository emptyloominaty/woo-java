package com.woo.game.objects.abilities.fireMage;

import com.woo.game.AbilityFunctions;
import com.woo.game.GlobalFunctions;
import com.woo.game.objects.ParticleSystem;
import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.abilities.SpellQueue;
import com.woo.game.objects.gameobjects.Creature;
import com.woo.game.objects.gameobjects.GOControl;
import com.woo.game.objects.gameobjects.Spell;

import static com.woo.game.Main.player;


public class FireBall extends Ability {
    public FireBall() {
        super("Fire Ball", 10,0,0.65, 0.65, false, true, false, "fire", 40, 1, 4,1);
        this.hasteCd = true;
        this.moveSpeed = 30;
        this.life = 1.33;
        this.spellPower = 1.5;
        this.spellPowerSec = 0.7;
        this.rangeSec = 8;
        this.iconPath = "icons/mage/fire1.png";
    }

    @Override
    public String getTooltip(Creature caster) {
        return "Deal "+ GlobalFunctions.spellPowerToNumber(this.spellPower,player,"intellect") +" Fire damage and "+ GlobalFunctions.spellPowerToNumber(this.spellPowerSec,player,"intellect") +" Fire damage to enemies within 8m";
    }

    public boolean startCast(Creature caster) {
        if (checkStart(caster,this.cost,this.secCost)) {
            casterStartCasting(caster);
            setGcd(caster,0);
            return true;
        } else if (this.canSpellQueue(caster)) {
            SpellQueue.add(this,(float)caster.gcd);
        }
        return false;
    }

    public boolean endCast(Creature caster) {
        caster.isCasting = false;

        int particleId = ParticleSystem.add("fire",25, caster.direction-180, caster.x, caster.y);
        Spell newSpell = new Spell("test Spell","test",false,false,caster.x,caster.y,"",caster.direction, particleId,true, this.moveSpeed, this.life,this);
        newSpell.caster = caster;
        GOControl.addSpell(newSpell);

        setCd();
        caster.useEnergy(this.cost,this.secCost);
        return true;
        //return false;
    }

    public void execute(Creature caster,Creature target, float x, float y) {
        AbilityFunctions.doDamage(caster,target,this,0,true,false,"",0);

        float angle = 360/20;
        for (int i = 0; i<19; i++) {
            ParticleSystem.add("fireBallExplosion",45, caster.direction+(angle*i), x, y);
        }


        for (Creature creature : GOControl.creatures) {
            if (!creature.destroyed && creature!=caster && GlobalFunctions.checkEnemy(caster,creature) && (this.rangeSec>GlobalFunctions.getDistance(x,y,creature.x,creature.y))) {
                AbilityFunctions.doDamage(caster,creature,this,this.spellPowerSec,true,false,"",0);
            }
        }
    }

}
