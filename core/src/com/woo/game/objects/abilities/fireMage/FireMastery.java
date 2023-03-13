package com.woo.game.objects.abilities.fireMage;

import com.woo.game.AbilityFunctions;
import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.gameobjects.Creature;



public class FireMastery extends Ability {
    public FireMastery() {
        super("Fire Mastery", 0,0,0, 0, false, false, false, "fire", 0, 1, 0,1);
        this.moveSpeed = 30;
        this.life = 1.33;
        this.spellPower = 0;
        this.duration = 4;
        this.iconPath = "icons/mage/fireMastery.png";
        this.mastery = true;
        this.passive = true;
    }

    @Override //((damageDone x 1.5 x (mastery / 100) x 4)
    public String getTooltip(Creature caster) {
        return "Your Fire spells apply DoT that deals "+(1.5*(caster.stats.get("mastery"))*4)+"% of the damage dealt over "+this.duration+"s";
    }

    public boolean startCast(Creature caster) {
        return false;
    }

    public boolean endCast(Creature caster) {
        return false;
    }

    public void execute(Creature caster,Creature target, float x, float y, double val) {
        double spellPowerDot = val / caster.stats.get("intellect");
        spellPowerDot = spellPowerDot * 1.5 * (caster.stats.get("mastery")/100) *4;
        AbilityFunctions.applyDot(caster,target,this,0,0,spellPowerDot,0);
    }


}
