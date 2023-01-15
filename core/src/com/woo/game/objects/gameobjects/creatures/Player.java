package com.woo.game.objects.gameobjects.creatures;

import com.woo.game.objects.gameobjects.Creature;

public class Player extends Creature {

    public Player(String name, String description, boolean solid, boolean interactable, float x, float y, String spritePath,int faction) {
        super(name, description, solid, interactable, x, y, spritePath, faction);
    }

    public void testPlayer() {
        System.out.println("--123--");
    }

}
