package com.woo.game.ui;

import com.woo.game.GlobalVars;

import static com.woo.game.Main.uiMain;

public class FloatingText {
    public float x;
    public float y;
    public String text;
    public String type;
    public float time = 0;
    public float time2 = 1.25f;
    public int id;
    public boolean destroyed = false;
    public boolean moving = false;

    public FloatingText(float x, float y, String text, String type) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.type = type;
    }

    public boolean main() {
        if (destroyed) {
            return false;
        }
        if (moving) {
            y += GlobalVars.delta*40; //TODO: globalvars floatingText speed???
        }
        //TODO: position in game!
        uiMain.floatingTextsLabel.get(id).setPosition(this.x,this.y);
        time+= GlobalVars.delta;
        if (time>time2) {
            return true;
        }
        return false;
    }
}
