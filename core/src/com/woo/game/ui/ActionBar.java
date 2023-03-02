package com.woo.game.ui;

import com.woo.game.GlobalVars;

import java.util.Arrays;

import static com.woo.game.Main.*;
import static com.woo.game.Main.player;

public class ActionBar {
    public int slots;
    public String[] abilities;
    public float[] pressTime;
    public int id;
    public ActionBar(int slots,String[] abilities, int id) {
        this.slots = slots;
        this.abilities = abilities;
        pressTime = new float[slots];
        Arrays.fill(pressTime,0);
        this.id = id;
    }

    public void press(int slot) {
        player.abilities.get(actionBars[this.id].abilities[slot]).startCast(player);
        uiMain.setActionPress(slot, this.id,true);
        pressTime[slot] = 0.1f;
        player.moveToPoint = false;
    }

    public void main() {
        for (int i = 0; i<pressTime.length; i++) {
            if (pressTime[i]>0) {
                pressTime[i]-= GlobalVars.delta;
                if (pressTime[i]<=0) {
                    uiMain.setActionPress(i, this.id,false);
                }
            }
        }
    }

}
