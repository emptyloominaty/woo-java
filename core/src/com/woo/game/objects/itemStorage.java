package com.woo.game.objects;

import java.util.HashMap;
import java.util.Map;

public class itemStorage {
    public Map<Integer, Item> items = new HashMap<Integer,Item>();
    public int sizeX;
    public int sizeY;

    public itemStorage(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public boolean itemIn(Item item, int id) {
        if (id<(sizeX*sizeY) && items.get(id)!=null) {
            items.put(id,item);
            return true;
        }
        return false;
    }

    public Item itemOut(int id) {
        Item item = items.get(id);
        items.put(id,null);
        return item;
    }
}
