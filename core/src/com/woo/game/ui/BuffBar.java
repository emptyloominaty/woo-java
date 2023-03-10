package com.woo.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import static com.woo.game.Main.uiMain;

public class BuffBar {
    public Table bar;
    public BuffBar() {
        bar = new Table();
        Label test = new Label("TEST555",uiMain.skin);
        Label test2 = new Label("TEST1",uiMain.skin);
        bar.add(test);
        bar.row();
        bar.add(test2);
        uiMain.tableBuffs.addActor(bar);
        //TODO:FIX
    }
}
