package com.woo.game.objects.gameobjects;

import com.woo.game.objects.Item;

public class ItemW extends GameObject{
    public Item item;
    public ItemW(String name, String description, boolean solid, boolean interactable, float x, float y, String spritePath, float direction) {
        super(name, description, solid, interactable, x, y, spritePath,"Item", direction);
    }
}
