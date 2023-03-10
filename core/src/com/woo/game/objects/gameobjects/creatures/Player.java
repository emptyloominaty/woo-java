package com.woo.game.objects.gameobjects.creatures;

import com.badlogic.gdx.graphics.Color;
import com.woo.game.objects.gameobjects.Creature;
import com.woo.game.ui.Inventory;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Player extends Creature {
    public Inventory inventory;
    public Player(String name, String description, boolean solid, boolean interactable, float x, float y, String spritePath, int faction, String creatureClass, float direction) {
        super(name, description, solid, interactable, x, y, spritePath, faction, creatureClass, direction);
        inventory = new Inventory();
    }

    public void testPlayer() {
        System.out.println("--123--");
    }

    public void draw(ShapeDrawer shapeDrawer) {
        if (!destroyed) {
            shapeDrawer.setColor(Color.BLUE);
            shapeDrawer.filledCircle(x, y, 14);
            shapeDrawer.setColor(Color.WHITE);
            double playerDirectionRadian = (direction-180) / 180 * Math.PI;
            shapeDrawer.line(x, y, (float) (x+(10*Math.sin(playerDirectionRadian))), (float) (y+(10*Math.cos(playerDirectionRadian))));
        }
    }

    public void main2() {
        /*health -= 0.3;
        if (health<2) {
            health = 50;
        }*/
    }

}
