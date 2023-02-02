package com.woo.game.objects.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.woo.game.GlobalVars;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Spell extends GameObject{
    public double life = 1.8;
    public double moveSpeed = 18;
    public int faction;

    //Visual
    public Spell(String name, String description, boolean solid, boolean interactable, float x, float y, String spritePath, float direction) {
        super(name, description, solid, interactable, x, y, spritePath,"Spell",direction);
    }

    public void main() {
        life -= GlobalVars.delta;
        if (life<0) {
            destroy();
        }
        move(moveSpeed);

        //TODO: WorldObject Loop
        //TODO: Creature Loop (+faction check)
    }

    public void draw(ShapeDrawer shapeDrawer) {
        if (!destroyed) {
            shapeDrawer.setColor(Color.RED);
            shapeDrawer.filledCircle(x, y, 10);
        }
    }

}
