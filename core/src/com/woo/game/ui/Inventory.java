package com.woo.game.ui;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.woo.game.objects.Item;

import java.util.HashMap;
import java.util.Map;

import static com.woo.game.Main.uiMain;

public class Inventory {
    public Map<Integer, Item> items = new HashMap<Integer,Item>();
    public Window inventory;
    Table inventoryTable;

    int sizeX = 8;
    int sizeY = 8;

    public int itemSize = 48; //px

    public Inventory() {
    }


    public void itemIn() {
    }

    public void itemOut() {
    }

    public void createUi() {
        inventory = new Window("Inventory",uiMain.skin);
        inventoryTable = new Table();
        inventoryTable.align(Align.topLeft);
        inventory.setSize(600,500);
        inventory.padTop(25);
        inventory.setPosition(40, Gdx.graphics.getHeight()-560);
        inventory.add(inventoryTable).expand().fill();
        inventory.setMovable(true);
        //inventory.setVisible(false);
        uiMain.stageTop.addActor(inventory);

        for (int i = 0; i<sizeX; i++) {
            for (int j = 0; j<sizeY; j++) {
                Table table = new Table();
                Label label = new Label(" "+i+"-"+j+" |",uiMain.skin); //TODO: Image + Tooltip
                table.add(label);
                inventoryTable.add(table).size(itemSize);
            }
            inventoryTable.row();
        }

    }

}
