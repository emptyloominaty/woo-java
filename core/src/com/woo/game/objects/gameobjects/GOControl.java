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

    //goControl.addSpell(new Spell());
    public void addSpell(Spell object) {
        int id;
        if (spellsFree.size()>0) {
            int idF = spellsFree.size()-1;
            id = spellsFree.get(idF);
            spellsFree.remove(idF);
        } else {
            id = spells.size();
        }
        if (spells.size()<=id) {
            spells.add(object);
        } else {
            spells.set(id,object);
        }
        addGameObject(object);
        spells.get(id).updateTypeId(id);
    }

    //goControl.addItem(new Item());
    public void addItem(Item object) {
        int id;
        if (itemsFree.size()>0) {
            int idF = itemsFree.size()-1;
            id = itemsFree.get(idF);
            itemsFree.remove(idF);
        } else {
            id = items.size();
        }
        if (items.size()<=id) {
            items.add(object);
        } else {
            items.set(id,object);
        }
        addGameObject(object);
        items.get(id).updateTypeId(id);
    }

    //goControl.addWorldObject(new WorldObject());
    public void addWorldObject(WorldObject object) {
        int id;
        if (worldObjectsFree.size()>0) {
            int idF = worldObjectsFree.size()-1;
            id = worldObjectsFree.get(idF);
            worldObjectsFree.remove(idF);
        } else {
            id = worldObjects.size();
        }
        if (worldObjects.size()<=id) {
            worldObjects.add(object);
        } else {
            worldObjects.set(id,object);
        }
        addGameObject(object);
        worldObjects.get(id).updateTypeId(id);
    }


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

    //--------------------------------------------------------------------------------------------
   /* public void addCreature(Creature creature) {
        addObject(creature, creaturesFree, creatures);
    }

    public void addSpell(Spell spell) {
        addObject(spell, spellsFree, spells);
    }

    public <T> void addObject(T object, ArrayList<Integer> freeIds, ArrayList<T> objects) {
        int id;
        if (freeIds.size() > 0) {
            int idF = freeIds.size() - 1;
            id = freeIds.get(idF);
            freeIds.remove(idF);
        } else {
            id = objects.size();
        }
        if (objects.size() <= id) {
            objects.add(object);
        } else {
            objects.set(id, object);
        }
        addGameObject((GameObject) object);
        objects.get(id).updateTypeId(id); //<--???
    }*/
    //--------------------------------------------------------------------------------------------
    /*
    private void addObjectHelper(Object object, ArrayList<Integer> freeIds, ArrayList<Object> objects) {
    int id;
    if (freeIds.size() > 0) {
        int idF = freeIds.size() - 1;
        id = freeIds.get(idF);
        freeIds.remove(idF);
    } else {
        id = objects.size();
    }
    if (objects.size() <= id) {
        objects.add(object);
    } else {
        objects.set(id, object);
    }
    addGameObject(object);
}

public void addCreature(Creature creature) {
    addObjectHelper(creature, creaturesFree, creatures);
    creature.updateTypeId(creature.getTypeId());
}

public void addSpell(Spell spell) {
    addObjectHelper(spell, spellsFree, spells);
    spell.updateTypeId(spell.getTypeId());
}

public void addItem(Item item) {
    addObjectHelper(item, itemsFree, items);
    item.updateTypeId(item.getTypeId());
}

public void addWorldObject(WorldObject worldObject) {
    addObjectHelper(worldObject, worldObjectsFree, worldObjects);
    worldObject.updateTypeId(worldObject.getTypeId());
}*/

}
