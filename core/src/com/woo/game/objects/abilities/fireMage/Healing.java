package com.woo.game.objects.abilities.fireMage;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.woo.game.AbilityFunctions;
import com.woo.game.GlobalFunctions;
import com.woo.game.objects.ParticleSystem;
import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.abilities.SpellQueue;
import com.woo.game.objects.gameobjects.Creature;
import com.woo.game.objects.gameobjects.GOControl;
import com.woo.game.objects.gameobjects.Spell;

import static com.woo.game.Main.player;


public class Healing extends Ability {
    public Healing() {
        super("Healing", 10,0,0.65, 1, false, true, false, "arcane", 40, 1, 0,1);
        this.moveSpeed = 30;
        this.life = 1.33;
        this.spellPower = 1.5;
        this.iconPath = "icons/mage/heal1.png";
    }

    @Override
    public String getTooltip(Creature caster) {
        return "Spell that heals you for "+ GlobalFunctions.spellPowerToNumber(this.spellPower,player,"intellect") +"";
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

        ////caster,target,ability, 0, true, false, 0 ,0 ,""/"hot"
        AbilityFunctions.doHeal(caster,caster,this,0,true,false,0,0,"");

        setCd();
        caster.useEnergy(this.cost,this.secCost);
        return true;
        //return false;
    }

    public void execute(Creature caster,Creature target, float x, float y) {

    }

}
