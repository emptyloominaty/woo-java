package com.woo.game.objects.gameobjects;

public class Spell extends GameObject{
    Spell(String name, String description, boolean solid, boolean interactable, double x, double y, String spritePath) {
        super(name, description, solid, interactable, x, y, spritePath,"Spell");
    }
}
