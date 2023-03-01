package com.woo.game.objects.abilities.fireMage;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.woo.game.AbilityFunctions;
import com.woo.game.GlobalFunctions;
import com.woo.game.objects.ParticleSystem;
import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.abilities.SpellQueue;
import com.woo.game.objects.gameobjects.Creature;
import com.woo.game.objects.gameobjects.GOControl;
import com.woo.game.objects.gameobjects.Spell;

import static com.woo.game.Main.player;


public class FireBlast extends Ability {
    public FireBlast() {
        super("Fire Blast", 5,0,0.65, 0.65, false, true, false, "fire", 40, 1, 0,1);
        this.moveSpeed = 30;
        this.life = 1.33;
        this.spellPower = 0.85;
        this.iconPath = "icons/mage/fire4.png";
    }

    @Override
    public String getTooltip(Creature caster) {
        return "Deal "+ GlobalFunctions.spellPowerToNumber(this.spellPower,player,"intellect") +" Fire damage";
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

        //fireSpell, 30
        int particleId = ParticleSystem.add("fire",30, caster.direction-180, caster.x, caster.y);
        ParticleEffect pe = ParticleSystem.particleList.get(particleId);
        pe.scaleEffect(0.5f); //0.9f
        Spell newSpell = new Spell("test Spell","test",false,false,caster.x,caster.y,"",caster.direction, particleId,true, this.moveSpeed, this.life,this);
        //newSpell.newP = true;
        newSpell.caster = caster;
        GOControl.addSpell(newSpell);

        setCd();
        caster.useEnergy(this.cost,this.secCost);
        return true;
        //return false;
    }

    public void execute(Creature caster,Creature target, float x, float y) {
        AbilityFunctions.doDamage(caster,target,this,0,true,false,"",0);
    }

}
