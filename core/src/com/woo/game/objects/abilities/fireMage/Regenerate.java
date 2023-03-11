package com.woo.game.objects.abilities.fireMage;

import com.woo.game.AbilityFunctions;
import com.woo.game.GlobalFunctions;
import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.abilities.SpellQueue;
import com.woo.game.objects.gameobjects.Creature;
import com.woo.game.objects.other.Buff;

import static com.woo.game.Main.player;


public class Regenerate extends Ability {
    public Regenerate() {
        super("Regenerate", 35,0,0.65, 0, false, true, false, "arcane", 40, 1, 0,1);
        this.duration = 8;
        this.moveSpeed = 30;
        this.life = 1.33;
        this.spellPower = 3.2;
        this.iconPath = "icons/mage/healHot.png";
    }

    @Override
    public String getTooltip(Creature caster) {
        return "Spell that heals you for "+ GlobalFunctions.spellPowerToNumber(this.spellPower,player,"intellect") +" over "+this.duration;
    }

    public String getBuffTooltip(Creature caster, Creature target, Buff buff) {
        return "Heals every "+(1 / (1+(buff.caster.stats.get("haste")/100)))+"s for "+GlobalFunctions.spellPowerToNumber(buff.spellPower,buff.caster,buff.caster.primaryStat);
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

        AbilityFunctions.applyHot(caster,caster,this,0,0,0,this.duration);

        setCd();
        caster.useEnergy(this.cost,this.secCost);
        return true;
        //return false;
    }

    public void execute(Creature caster,Creature target, float x, float y) {

    }

}
