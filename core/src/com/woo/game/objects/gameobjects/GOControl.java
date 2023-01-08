package com.woo.game.objects.gameobjects;

import java.util.ArrayList;

public class GOControl {
    static public ArrayList<Integer> gameObjectsFree;
    static public ArrayList<GameObject> gameObjects;


    static public void reset() {
        gameObjects = new ArrayList<GameObject>();
        gameObjectsFree = new ArrayList<Integer>();

    }

    //  GOControl.addGameObject(new Spell/Item/Creature())
    static public void addGameObject(GameObject object) {
        int id;
        if (gameObjectsFree.size()>0) {
            int idF = gameObjectsFree.size()-1;
            id = gameObjectsFree.get(idF);
            gameObjectsFree.remove(idF);
        } else {
            id = gameObjects.size();
        }
        gameObjects.add(object);
        //TODO

        //gameObjects.get(id); //READ
    }

    static public void removeGameObject(int id) {
        //TODO
        gameObjectsFree.add(id);
    }
}
