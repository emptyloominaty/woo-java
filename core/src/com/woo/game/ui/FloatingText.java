package com.woo.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.woo.game.GlobalVars;

import static com.woo.game.Main.cam;
import static com.woo.game.Main.uiMain;

public class FloatingText {
    public float x;
    public float y;
    public String text;
    public String type;
    public float time = 0;
    public float time2 = 1.3f;
    public int id;
    public boolean destroyed = false;
    public boolean moving = false;
    public float scale = 1;
    public float scaleInc = 0.1f;
    public boolean end = false;

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
            y += GlobalVars.delta*60;
        }
        if (time<0.1) {
            scale += scaleInc;
            uiMain.floatingTextsLabel.get(id).setFontScale(scale);
        } else if (time<0.2) {
            scale -= scaleInc;
            uiMain.floatingTextsLabel.get(id).setFontScale(scale);
        } else if (time>(time2-0.2) && !end){
            this.end = true;
            ColorAction ca= new ColorAction();
            ca.setEndColor(new Color(0,0,0,0));
            ca.setDuration(0.2f);
            uiMain.floatingTextsLabel.get(id).addAction(ca);
        } else {
            uiMain.floatingTextsLabel.get(id).setFontScale(1);
        }
        float x = (Gdx.graphics.getWidth()/2f) + ((this.x - cam.position.x)) /GlobalVars.camZoom;
        float y = (Gdx.graphics.getHeight()/2f) + ((this.y - cam.position.y)) /GlobalVars.camZoom;
        uiMain.floatingTextsLabel.get(id).setPosition(x,y);
        time+= GlobalVars.delta;
        if (time>time2) {
            return true;
        }
        return false;
    }
}
