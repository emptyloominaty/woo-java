package com.woo.game.ui;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.woo.game.Maps;
import com.woo.game.objects.Item;
import com.woo.game.objects.itemStorage;

import java.util.HashMap;
import java.util.Map;

import static com.woo.game.Main.uiMain;

public class Inventory extends itemStorage {

    public Window inventory;
    public Table inventoryTable;
    public Table characterGearTable;


    public int itemSize = 48; //px

    public Inventory() {
        super(8,8);
    }

    public void update() {

    }

    public void createUi() {
        inventory = new Window(" Inventory",uiMain.skin);
        inventoryTable = new Table();
        inventoryTable.align(Align.topLeft);
        inventory.setSize(800,500);
        inventory.padTop(25);
        inventory.setPosition(40, Gdx.graphics.getHeight()-560);

        Table tableBorder = new Table();
        tableBorder.add(inventoryTable).expand().fill().pad(10);

        inventory.add(tableBorder).expand().fill().pad(2).padTop(0);;
        inventory.setMovable(true);
        //inventory.setVisible(false);
        uiMain.stageTop.addActor(inventory);

        tableBorder.background(uiMain.skin.newDrawable("white", Maps.windowColor));

        for (int i = 0; i<sizeX; i++) {
            for (int j = 0; j<sizeY; j++) {
                Table table = new Table();
                Image image = new Image(uiMain.skin.newDrawable("white", new Color(0.4f,0.4f,0.4f,1f)));
                table.add(image).size(itemSize);
                inventoryTable.add(table).size(itemSize).pad(1);
            }
            inventoryTable.row();
        }

        //TODO:
        Image image = new Image(uiMain.skin.newDrawable("white",  new Color(0.4f,0.4f,0.4f,1f)));
        characterGearTable = new Table();
        characterGearTable.add(image).size(itemSize);
        characterGearTable.align(Align.topRight);
        tableBorder.add(characterGearTable).expand().fill().pad(10);



    }

}
