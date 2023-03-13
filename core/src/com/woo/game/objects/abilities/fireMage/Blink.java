package com.woo.game.objects.abilities.fireMage;

import com.woo.game.AbilityFunctions;
import com.woo.game.GlobalFunctions;
import com.woo.game.GlobalVars;
import com.woo.game.objects.ParticleSystem;
import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.abilities.SpellQueue;
import com.woo.game.objects.gameobjects.Creature;
import com.woo.game.objects.gameobjects.GOControl;

import static com.woo.game.Main.player;


public class Blink extends Ability {
    public Blink() {
        super("Blink", 10,0,0, 0, false, false, false, "arcane", 20, 1, 12,1);
        this.moveSpeed = 20;
        this.life = 1;
        this.spellPower = 0;
        this.noGcd = true;
        this.hasteCd = true;
        this.iconPath = "icons/mage/blink.png";
    }

    @Override
    public String getTooltip(Creature caster) {
        return "Teleports you forward 20m";
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

    public boolean endCast(final Creature caster) {
        caster.isCasting = false;
        final float range = this.range;

        ParticleSystem.add("blink",360, 0, caster.x, caster.y);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        caster.move(range,true);
                    }
                },
                100
        );

        double speed = (range * GlobalVars.pxToMeter);
        double angleInRadian = (caster.direction-180) / 180 * Math.PI;

        double vx = Math.sin(angleInRadian) * speed;
        double vy = Math.cos(angleInRadian) * speed;
        double x = caster.x + vx;
        double y = caster.y + vy;
        ParticleSystem.add("blink2",360, 0, (float)x, (float)y);



        setCd();
        caster.useEnergy(this.cost,this.secCost);
        return true;
        //return false;
    }

    public void execute(Creature caster,Creature target, float x, float y) {
    }

}
