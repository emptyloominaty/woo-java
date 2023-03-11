package com.woo.game.objects.gameobjects.creatures;

import com.woo.game.GlobalVars;
import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.abilities.fireMage.*;
import com.woo.game.objects.gameobjects.Creature;

import java.util.Map;
import java.util.Objects;

public class creatureInit {
    public static void main(Creature creature) {
         //Player
        if (creature.faction==0) {
            if (Objects.equals(creature.creatureClass, "Fire Mage")) { //-----------------------------Fire Mage
                creature.resourceName = "Mana";
                creature.secondaryResourceName = "Energy Rune";

                creature.primaryStat = "intellect";
                creature.stats.put("intellect",20.0);
                creature.stats.put("strength",10.0);
                creature.stats.put("dexterity",10.0);
                creature.stats.put("stamina",15.0);

                creature.energy = 185 + creature.stats.get("intellect");
                creature.energyMax = 185 + creature.stats.get("intellect");
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 5;

                creature.abilities.put("Blink",new Blink());
                creature.abilities.put("Healing",new Healing());
                creature.abilities.put("Regenerate",new Regenerate());

                creature.abilities.put("Fire Blast",new FireBlast());
                creature.abilities.put("Wildfire",new Wildfire());
                creature.abilities.put("Fire Ball",new FireBall());
            } else if (Objects.equals(creature.creatureClass, "Frost Mage")) { //-----------------------------Frost Mage
                creature.resourceName = "Mana";
                creature.secondaryResourceName = "Energy Rune";

                creature.primaryStat = "intellect";
                creature.stats.put("intellect",20.0);
                creature.stats.put("strength",10.0);
                creature.stats.put("dexterity",10.0);
                creature.stats.put("stamina",15.0);

                creature.energy = 185 + creature.stats.get("intellect");
                creature.energyMax = 185 + creature.stats.get("intellect");
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 5;

            } else if (Objects.equals(creature.creatureClass, "Warrior")) { //-----------------------------Warrior
                creature.resourceName = "Energy";
                creature.secondaryResourceName = "Rage";

                creature.primaryStat = "strength";
                creature.stats.put("intellect",10.0);
                creature.stats.put("strength",15.0);
                creature.stats.put("dexterity",10.0);
                creature.stats.put("stamina",30.0);

                creature.energy = 100 + creature.stats.get("strength");
                creature.energyMax = 100 + creature.stats.get("strength");
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 100;

            } else if (Objects.equals(creature.creatureClass, "Ranger")) { //-----------------------------Ranger
                creature.resourceName = "Focus";
                creature.secondaryResourceName = "Combo Point";

                creature.primaryStat = "dexterity";
                creature.stats.put("intellect",10.0);
                creature.stats.put("strength",10.0);
                creature.stats.put("dexterity",17.0);
                creature.stats.put("stamina",25.0);

                creature.energy = 100;
                creature.energyMax = 100;
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 6;

            } else if (Objects.equals(creature.creatureClass, "Necromancer")) { //-----------------------------Necromancer
                creature.resourceName = "Corruption";
                creature.secondaryResourceName = "Soul";

                creature.primaryStat = "intellect";
                creature.stats.put("intellect",16.0);
                creature.stats.put("strength",10.0);
                creature.stats.put("dexterity",10.0);
                creature.stats.put("stamina",25.0);

                creature.energy = 100 + creature.stats.get("intellect");
                creature.energyMax = 100 + creature.stats.get("intellect");
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 20;

            } else if (Objects.equals(creature.creatureClass, "Warlock")) { //-----------------------------Warlock
                creature.resourceName = "Corruption";
                creature.secondaryResourceName = "Soul";

                creature.primaryStat = "intellect";
                creature.stats.put("intellect",18.0);
                creature.stats.put("strength",10.0);
                creature.stats.put("dexterity",10.0);
                creature.stats.put("stamina",20.0);

                creature.energy = 100 + creature.stats.get("intellect");
                creature.energyMax = 100 + creature.stats.get("intellect");
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 20;

            } else if (Objects.equals(creature.creatureClass, "Monk")) { //-----------------------------Monk
                creature.resourceName = "Energy";
                creature.secondaryResourceName = "Chi";

                creature.primaryStat = "dexterity";
                creature.stats.put("intellect",10.0);
                creature.stats.put("strength",12.0);
                creature.stats.put("dexterity",15.0);
                creature.stats.put("stamina",25.0);
                creature.energy = 100;
                creature.energyMax = 100;
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 5;
            } else if (Objects.equals(creature.creatureClass, "Rogue")) { //-----------------------------Rogue
                creature.resourceName = "Energy";
                creature.secondaryResourceName = "Combo Point";

                creature.primaryStat = "dexterity";
                creature.stats.put("intellect",10.0);
                creature.stats.put("strength",10.0);
                creature.stats.put("dexterity",18.0);
                creature.stats.put("stamina",25.0);
                creature.energy = 100;
                creature.energyMax = 100;
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 5;
            } else if (Objects.equals(creature.creatureClass, "Shaman")) { //-----------------------------Shaman
                creature.resourceName = "Mana";
                creature.secondaryResourceName = "Elemental Energy";

                creature.primaryStat = "intellect";
                creature.stats.put("intellect",17.0);
                creature.stats.put("strength",10.0);
                creature.stats.put("dexterity",10.0);
                creature.stats.put("stamina",20.0);
                creature.energy = 200;
                creature.energyMax = 200;
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 100;
            } else if (Objects.equals(creature.creatureClass, "Paladin")) {  //-----------------------------Paladin
                creature.resourceName = "Energy";
                creature.secondaryResourceName = "Holy Power";

                creature.primaryStat = "strength";
                creature.stats.put("intellect",12.0);
                creature.stats.put("strength",15.0);
                creature.stats.put("dexterity",10.0);
                creature.stats.put("stamina",30.0);
                creature.energy = 100;
                creature.energyMax = 100;
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 5;
            }
        } else {  //-----------------------------------------------------------------------------------------------------------------
            if (Objects.equals(creature.creatureClass, "Enemy1")) {
                creature.stats.put("stamina",5.0);
                creature.xpLoot = 15;
            } else if (Objects.equals(creature.creatureClass, "Enemy2")) {
                creature.level = 5;
                creature.stats.put("stamina",150.0);
                creature.xpLoot = 50;
            } else if (Objects.equals(creature.creatureClass, "Enemy3")) {
                creature.stats.put("stamina",2.0);
            }
        }





        for (Map.Entry<String, Ability> ability : creature.abilities.entrySet()) {
            creature.abilities.get(ability.getKey()).generateTexture();
        }


        creature.health = creature.stats.get("stamina")*5;
        creature.healthMax = creature.stats.get("stamina")*5;
    }
}
