package com.woo.game.ui;

import static com.woo.game.Main.actionBars;

public class Action {
    public String name;
    public int bar;
    public int slot;
    public String[] keybind;
    public Action(String ability, int bar, int slot) {
        this.name = ability;
        this.bar = bar;
        this.slot = slot;
        actionBars[bar].abilities[slot] = ability;

        //TODO: keybind


    }
}
