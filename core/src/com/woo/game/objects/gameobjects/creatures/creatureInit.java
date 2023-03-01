package com.woo.game.objects.gameobjects.creatures;

import com.woo.game.objects.abilities.fireMage.FireBall;
import com.woo.game.objects.abilities.fireMage.FireBlast;
import com.woo.game.objects.abilities.fireMage.Wildfire;
import com.woo.game.objects.gameobjects.Creature;

public class creatureInit {
    public static void main(Creature creature) {
         //Player
        if (creature.faction==0) {
            if (creature.creatureClass == "Fire Mage") { //-----------------------------Fire Mage
                creature.primaryStat = "intellect";
                creature.stats.put("intellect",20.0);
                creature.stats.put("strength",10.0);
                creature.stats.put("dexterity",10.0);
                creature.stats.put("stamina",15.0);

                creature.energy = 85 + creature.stats.get("intellect");
                creature.energyMax = 85 + creature.stats.get("intellect");
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 5;

                creature.abilities.put("Fire Blast",new FireBlast());
                creature.abilities.put("Wildfire",new Wildfire());
                creature.abilities.put("Fire Ball",new FireBall());
            } else if (creature.creatureClass == "Frost Mage") { //-----------------------------Frost Mage
                creature.primaryStat = "intellect";
                creature.stats.put("intellect",20.0);
                creature.stats.put("strength",10.0);
                creature.stats.put("dexterity",10.0);
                creature.stats.put("stamina",15.0);

                creature.energy = 85 + creature.stats.get("intellect");
                creature.energyMax = 85 + creature.stats.get("intellect");
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 5;

            } else if (creature.creatureClass == "Warrior") { //-----------------------------Warrior
                creature.primaryStat = "strength";
                creature.stats.put("intellect",10.0);
                creature.stats.put("strength",15.0);
                creature.stats.put("dexterity",10.0);
                creature.stats.put("stamina",30.0);

                creature.energy = 100 + creature.stats.get("strength");
                creature.energyMax = 100 + creature.stats.get("strength");
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 100;

            } else if (creature.creatureClass == "Ranger") { //-----------------------------Ranger
                creature.primaryStat = "dexterity";
                creature.stats.put("intellect",10.0);
                creature.stats.put("strength",10.0);
                creature.stats.put("dexterity",17.0);
                creature.stats.put("stamina",25.0);

                creature.energy = 100;
                creature.energyMax = 100;
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 6;

            } else if (creature.creatureClass == "Necromancer") { //-----------------------------Necromancer
                creature.primaryStat = "intellect";
                creature.stats.put("intellect",16.0);
                creature.stats.put("strength",10.0);
                creature.stats.put("dexterity",10.0);
                creature.stats.put("stamina",25.0);

                creature.energy = 100 + creature.stats.get("intellect");
                creature.energyMax = 100 + creature.stats.get("intellect");
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 20;

            } else if (creature.creatureClass == "Warlock") { //-----------------------------Warlock
                creature.primaryStat = "intellect";
                creature.stats.put("intellect",18.0);
                creature.stats.put("strength",10.0);
                creature.stats.put("dexterity",10.0);
                creature.stats.put("stamina",20.0);

                creature.energy = 100 + creature.stats.get("intellect");
                creature.energyMax = 100 + creature.stats.get("intellect");
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 20;

            } else if (creature.creatureClass == "Monk") { //-----------------------------Monk
                creature.primaryStat = "dexterity";
                creature.stats.put("intellect",10.0);
                creature.stats.put("strength",12.0);
                creature.stats.put("dexterity",15.0);
                creature.stats.put("stamina",25.0);
                creature.energy = 100;
                creature.energyMax = 100;
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 6;
            } else if (creature.creatureClass == "Rogue") { //-----------------------------Rogue
                creature.primaryStat = "dexterity";
                creature.stats.put("intellect",10.0);
                creature.stats.put("strength",10.0);
                creature.stats.put("dexterity",18.0);
                creature.stats.put("stamina",25.0);
                creature.energy = 100;
                creature.energyMax = 100;
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 6;
            } else if (creature.creatureClass == "Shaman") { //-----------------------------Shaman
                creature.primaryStat = "intellect";
                creature.stats.put("intellect",17.0);
                creature.stats.put("strength",10.0);
                creature.stats.put("dexterity",10.0);
                creature.stats.put("stamina",20.0);
                creature.energy = 100;
                creature.energyMax = 100;
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 6;
            } else if (creature.creatureClass == "Paladin") {  //-----------------------------Paladin
                creature.primaryStat = "strength";
                creature.stats.put("intellect",12.0);
                creature.stats.put("strength",15.0);
                creature.stats.put("dexterity",10.0);
                creature.stats.put("stamina",30.0);
                creature.energy = 100;
                creature.energyMax = 100;
                creature.secondaryResource = 0;
                creature.secondaryResourceMax = 6;
            }
        } else {  //-----------------------------------------------------------------------------------------------------------------
            if (creature.creatureClass == "Enemy1") {
                creature.stats.put("stamina",5.0);
                creature.xpLoot = 15;
            } else if (creature.creatureClass == "Enemy2") {
                creature.level = 5;
                creature.stats.put("stamina",150.0);
                creature.xpLoot = 50;
            } else if (creature.creatureClass == "Enemy3") {
                creature.stats.put("stamina",2.0);
            }
        }






        creature.health = creature.stats.get("stamina")*5;
        creature.healthMax = creature.stats.get("stamina")*5;
    }
}
