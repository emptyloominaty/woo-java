package com.woo.game.objects.other;

import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.gameobjects.Creature;

import java.util.ArrayList;
import java.util.Map;

public class Buff {
    public double duration;
    public double maxDuration;
    public double extendedDuration;

    public String name;
    public String school;
    public String type;
    public ArrayList<Map> effects;
    public double timer;
    public double spellPower;
    public Creature caster;
    public Ability ability;

    public int stacks = 1;

    public Buff(String name, String school, String type, ArrayList<Map> effects, double timer, double duration, double maxDuration, double extendedDuration, double spellPower, Creature caster, Ability ability) {
        this.name = name;
        this.school = school;
        this.type = type;
        this.effects = effects;
        this.timer = timer;
        this.duration = duration;
        this.maxDuration = maxDuration;
        this.extendedDuration = extendedDuration;
        this.spellPower = spellPower;
        this.caster = caster;
        this.ability = ability;
    }


}
