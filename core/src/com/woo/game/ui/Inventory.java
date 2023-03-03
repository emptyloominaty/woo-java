package com.woo.game.ui;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.woo.game.GlobalVars;
import com.woo.game.Maps;
import com.woo.game.objects.Item;
import com.woo.game.objects.itemStorage;

import java.util.HashMap;
import java.util.Map;

import static com.woo.game.Main.player;
import static com.woo.game.Main.uiMain;

public class Inventory extends itemStorage {

    public Window inventory;
    public Table inventoryTable;
    public Table characterGearTable;
    public Label gold;


    public int itemSize = 48; //px

    public Inventory() {
        super(8,8);
    }

    public void update() {
        gold.setText("Gold: "+((double)player.gold/100));
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
        characterGearTable = new Table();
        Color color = new Color(0.4f,0.4f,0.4f,1f);
        Image head = new Image(uiMain.skin.newDrawable("white",  color));
        Image chest = new Image(uiMain.skin.newDrawable("white",  color));
        Image neck = new Image(uiMain.skin.newDrawable("white",  color));

        Image hands = new Image(uiMain.skin.newDrawable("white",  color));
        Image waist = new Image(uiMain.skin.newDrawable("white",  color));
        Image finger1 = new Image(uiMain.skin.newDrawable("white",  color));
        Image finger2 = new Image(uiMain.skin.newDrawable("white",  color));
        Image trinket1 = new Image(uiMain.skin.newDrawable("white",  color));
        Image trinket2 = new Image(uiMain.skin.newDrawable("white",  color));

        Image weapon = new Image(uiMain.skin.newDrawable("white",  color));
        Image offhand = new Image(uiMain.skin.newDrawable("white",  color));

        Image legs = new Image(uiMain.skin.newDrawable("white",  color));
        Image feet = new Image(uiMain.skin.newDrawable("white",  color));


        Table fingerTable = new Table();
        Table trinketTable = new Table();
        Table legsFeetTable = new Table();

        characterGearTable.add(neck).size(40).padBottom(5);
        characterGearTable.add(head).size(48).padBottom(5);
        characterGearTable.row();
        characterGearTable.add(weapon).size(48).padBottom(5);
        characterGearTable.add(chest).size(48).padBottom(5);
        characterGearTable.add(offhand).size(48).padBottom(5);
        characterGearTable.row();
        characterGearTable.add(hands).size(48).padBottom(5);
        characterGearTable.add(waist).size(48).padBottom(5);
        characterGearTable.row();

        legsFeetTable.add(legs).size(48).padBottom(5);
        legsFeetTable.row();
        legsFeetTable.add(feet).size(48).padBottom(5);

        characterGearTable.add(fingerTable).size(110).padBottom(5);
        characterGearTable.add(legsFeetTable).size(110).padBottom(5);
        characterGearTable.add(trinketTable).size(110).padBottom(5);

        trinketTable.add(trinket1).padBottom(5).size(40); //TODO: 48
        trinketTable.row();
        trinketTable.add(trinket2).padBottom(5).size(40);

        fingerTable.add(finger1).padBottom(5).size(40);
        fingerTable.row();
        fingerTable.add(finger2).padBottom(5).size(40);
        //characterGearTable.setLayoutEnabled(false);

        Table bottomTable = new Table();
        gold = new Label("Gold: ",uiMain.skin);
        bottomTable.add(gold);
        bottomTable.align(Align.left);

        tableBorder.add(characterGearTable).expand().fill().pad(10);
        tableBorder.row();
        tableBorder.add(bottomTable).expand().fill().pad(10).colspan(2);

        final Button closeButton = new TextButton("X", uiMain.skin, "default");
        inventory.getTitleTable().add(closeButton).size(30, 20).padRight(2);
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                GlobalVars.inventory = false;
                player.inventory.inventory.setVisible(false);
                closeButton.setChecked(false);
            }
        });

        /*tableBorder.setDebug(true);
        characterGearTable.setDebug(true);*/
    }


}
