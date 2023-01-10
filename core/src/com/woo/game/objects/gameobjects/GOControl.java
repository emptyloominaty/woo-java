package com.woo.game.objects.gameobjects;

import java.util.ArrayList;

public class GOControl {
    public ArrayList<Integer> gameObjectsFree;
    public ArrayList<GameObject> gameObjects;

    public ArrayList<Integer> creaturesFree;
    public ArrayList<Creature> creatures;

    public ArrayList<Integer> itemsFree;
    public ArrayList<Item> items;

    public ArrayList<Integer> worldObjectsFree;
    public ArrayList<WorldObject> worldObjects;

    public ArrayList<Integer> spellsFree;
    public ArrayList<Spell> spells;

    public void reset() {
        gameObjects = new ArrayList<GameObject>();
        gameObjectsFree = new ArrayList<Integer>();
        creatures = new ArrayList<Creature>();
        creaturesFree = new ArrayList<Integer>();
        items = new ArrayList<Item>();
        itemsFree = new ArrayList<Integer>();
        worldObjects = new ArrayList<WorldObject>();
        worldObjectsFree = new ArrayList<Integer>();
        spells = new ArrayList<Spell>();
        spellsFree = new ArrayList<Integer>();
    }

    //goControl.addCreature(new Creature());
    public void addCreature(Creature object) {
        int id;
        if (creaturesFree.size()>0) {
            int idF = creaturesFree.size()-1;
            id = creaturesFree.get(idF);
            creaturesFree.remove(idF);
        } else {
            id = creatures.size();
        }
        if (creatures.size()<=id) {
            creatures.add(object);
        } else {
            creatures.set(id,object);
        }
        addGameObject(object);
        creatures.get(id).updateTypeId(id);
    }

    //TODO: addSpell
    //TODO: addItem
    //TODO: addWorldObject


    private void addGameObject(GameObject object) {
        int id;
        if (gameObjectsFree.size()>0) {
            int idF = gameObjectsFree.size()-1;
            id = gameObjectsFree.get(idF);
            gameObjectsFree.remove(idF);
        } else {
            id = gameObjects.size();
        }

        if (gameObjects.size()<=id) {
            gameObjects.add(object);
        } else {
            gameObjects.set(id,object);
        }
        gameObjects.get(id).updateId(id);
    }

    public void removeGameObject(int id) {
        gameObjects.get(id).destroyed = true;
        gameObjectsFree.add(id);
    }


}
