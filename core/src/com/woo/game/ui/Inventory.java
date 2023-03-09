package com.woo.game.ui;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.woo.game.GameInput;
import com.woo.game.GlobalVars;
import com.woo.game.Maps;
import com.woo.game.objects.Item;
import com.woo.game.objects.Settings;
import com.woo.game.objects.itemStorage;

import java.util.HashMap;
import java.util.Map;

import static com.woo.game.Main.*;

public class Inventory extends itemStorage {

    public Window inventory;
    public Table inventoryTable;
    public Table characterGearTable;
    public Label gold;
    public Table tableBorder;


    public int itemSize = 48; //px

    public Inventory() {
        super(8,8);
    }

    public void update() {
        gold.setText("Gold: "+((double)player.gold/100));
    }

    public void init() {
        inventory = new Window(" Inventory",uiMain.skin);
        inventory.setSize(800,500);
        inventory.padTop(25);
        inventory.setPosition(40, Gdx.graphics.getHeight()-560);
        uiMain.stageTop.addActor(inventory);
        inventory.setMovable(true);
        //inventory.setVisible(false);

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
        tableBorder = new Table();
        inventory.add(tableBorder).expand().fill().pad(2).padTop(0);;
        tableBorder.background(uiMain.skin.newDrawable("white", Maps.windowColor));

        inventory.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                    }
                }
        );

        //TEST--------
        Map test1 = new HashMap<String, Map>();
        Map test2 = new HashMap<String, Map>();
        Map test3 = new HashMap<String, Map>();
        Map test4 = new HashMap<String, Map>();

        Map test5 = new HashMap<String, Map>();
        Map test6 = new HashMap<String, Map>();
        Map test7 = new HashMap<String, Map>();

        Map stats1 = new HashMap<String, Double>();
        stats1.put("haste",40.0);
        test1.put("stats",stats1);

        Map stats2 = new HashMap<String, Double>();
        stats2.put("stamina",5.0);
        stats2.put("intellect",4.0);
        test2.put("stats",stats2);

        Map stats3 = new HashMap<String, Double>();
        stats3.put("haste",100.0);
        test3.put("stats",stats3);

        Map stats4 = new HashMap<String, Double>();
        stats4.put("haste",25.0);
        stats4.put("intellect",50.0);
        test4.put("stats",stats4);

        Map stats5 = new HashMap<String, Double>();
        stats5.put("haste",40.0);
        test5.put("stats",stats5);

        Map stats6 = new HashMap<String, Double>();
        stats6.put("crit",40.0);
        test6.put("stats",stats6);

        Map stats7 = new HashMap<String, Double>();
        stats7.put("stamina",1.0);
        test7.put("stats",stats7);

        items.put(0,new Item("Test",19,"Legendary",1,1,"icons/items/weapon/staff/staff_1.png",test1));
        items.put(1,new Item("Test2",19,"Magic",1,1,"icons/items/weapon/staff/staff_8.png",test2));
        items.put(2,new Item("Test3",19,"Mythic",1,1,"icons/items/weapon/staff/staff_9.png",test3));
        items.put(3,new Item("Test4",19,"Mythic",1,1,"icons/items/weapon/staff/staff_15.png",test4));

        items.put(4,new Item("Ring5",17,"Epic",1,0.1,"icons/items/ring/Ring_01.png",test5));
        items.put(5,new Item("Ring6",17,"Epic",1,0.1,"icons/items/ring/Ring_08.png",test6));
        items.put(6,new Item("Ring7",17,"Normal",1,0.1,"icons/items/ring/Ring_11.png",test7));
        //-----------
    }

    public void createUi() {
        inventoryTable = new Table();
        inventoryTable.align(Align.topLeft);
        tableBorder.clear();
        tableBorder.add(inventoryTable).expand().fill().pad(10);

        for (int i = 0; i<sizeX; i++) {
            for (int j = 0; j<sizeY; j++) {
                Table table = new Table();
                Image image = new Image(uiMain.skin.newDrawable("white", new Color(0.4f,0.4f,0.4f,1f)));
                table.add(image).size(itemSize);
                inventoryTable.add(table).size(itemSize).pad(1);
                final int finalI = i;
                final int finalJ = j;
                if (items.get(i+(j*sizeY))!=null) {
                    final Item item = items.get(i+(j*sizeY));
                    image.setDrawable((new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(item.iconPath))))));

                    table.addListener(
                            new InputListener() {
                                @Override
                                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                    uiMain.itemTooltip.clear();

                                    Label name = new Label(item.name,uiMain.skin);
                                    name.setColor(Maps.itemColors.get(item.quality));
                                    uiMain.itemTooltip.add(name).left().padLeft(5);
                                    uiMain.itemTooltip.row();

                                    Label ilvl = new Label("Item Level "+item.itemLevel,uiMain.skin);
                                    ilvl.setColor(new Color(0.9f,0.9f,0.25f,1f));
                                    uiMain.itemTooltip.add(ilvl).left().padLeft(5);
                                    uiMain.itemTooltip.row();
                                    uiMain.itemTooltip.add(new Label("",uiMain.skin)).expandX().fillX().height(2);
                                    uiMain.itemTooltip.row();

                                    String str = Maps.itemSlotMap.get(item.type);
                                    String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
                                    uiMain.itemTooltip.add(new Label(cap,uiMain.skin)).left().padLeft(5);
                                    uiMain.itemTooltip.add(new Label(item.quality,uiMain.skin)).right().padRight(5);
                                    uiMain.itemTooltip.row();

                                    Map stats = item.data.get("stats");
                                    for (Object key : stats.keySet()){
                                        String str2 = (String)key;
                                        String key2 = str2.substring(0, 1).toUpperCase() + str2.substring(1);

                                        Label stat = new Label("+"+stats.get(key)+" "+key2,uiMain.skin);
                                        stat.setColor(new Color(0.1f,0.95f,0.1f,1f));
                                        uiMain.itemTooltip.add(stat).left().padLeft(5);
                                        uiMain.itemTooltip.row();
                                    }

                                    uiMain.itemTooltip.add(new Label(item.weight+"kg",uiMain.skin)).left().padLeft(5);
                                    uiMain.itemTooltip.add(new Label((item.price/100)+"g",uiMain.skin)).right().padRight(5);


                                    uiMain.itemTooltip.row();
                                    uiMain.itemTooltip.add(new Label("",uiMain.skin)).expandX().fillX().height(5);


                                    uiMain.itemTooltip.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                                    uiMain.itemTooltip.pack();
                                    uiMain.itemTooltip.setWidth(180);
                                    uiMain.itemTooltip.setVisible(true);
                                }
                                @Override
                                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                                    uiMain.itemTooltip.setVisible(false);
                                }
                                @Override
                                public boolean mouseMoved(InputEvent event, float x, float y) {
                                    uiMain.itemTooltip.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                                    return false;
                                }
                                @Override
                                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                    if (GameInput.ctrl && !GlobalVars.draggingItem) {
                                        uiMain.dragItem.setVisible(true);
                                        uiMain.dragItem.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                                        uiMain.dragItem.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(item.iconPath)))));
                                        GlobalVars.draggingItem = true;
                                        GlobalVars.dragItem = item;
                                        GlobalVars.dragItemName = item.name;
                                        items.put(finalI+(finalJ *sizeY),null);
                                        createUi();
                                    } else if (GlobalVars.draggingItem) {
                                        items.put(finalI+(finalJ *sizeY),GlobalVars.dragItem);
                                    } else {
                                        if (item.type>9) {
                                            //TODO:ring2, trinket2
                                            Item item2 = null;
                                            if (item.type!=17 || player.equippedItems.get("ring")==null) {
                                                if (player.equippedItems.get(Maps.itemSlotMap.get(item.type))!=null) {
                                                    item2 = player.equippedItems.get(Maps.itemSlotMap.get(item.type));
                                                }
                                                player.equippedItems.put(Maps.itemSlotMap.get(item.type), item);
                                            } else {
                                                if (player.equippedItems.get("ring2")!=null) {
                                                    item2 = player.equippedItems.get("ring2");
                                                }
                                                player.equippedItems.put("ring2", item);
                                            }


                                            if (item2!=null) {
                                                items.put(finalI+(finalJ *sizeY),item2);
                                            } else {
                                                items.put(finalI+(finalJ *sizeY),null);
                                            }

                                            createUi();
                                        }
                                    }
                                    return true;
                                }
                            }
                    );
                } else {
                    table.addListener(
                            new InputListener() {
                                @Override
                                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                    if (GlobalVars.draggingItem) {
                                        GlobalVars.draggingItem = false;
                                        uiMain.dragItem.setVisible(false);
                                        items.put(finalI+(finalJ *sizeY),GlobalVars.dragItem);
                                        createUi();
                                    }
                                    return true;
                                }
                            }
                    );
                }
            }
            inventoryTable.row();
        }

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

        if (player.equippedItems.get("head")!=null) {
            head.setDrawable((new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(player.equippedItems.get("head").iconPath))))));
            addImgListener(head,player.equippedItems.get("head"),"head");
        } else {
            addImgListener(head,null,"head");
        }
        if (player.equippedItems.get("weapon")!=null) {
            weapon.setDrawable((new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(player.equippedItems.get("weapon").iconPath))))));
            addImgListener(weapon,player.equippedItems.get("weapon"),"weapon");
        } else {
            addImgListener(weapon,null,"weapon");
        }

        if (player.equippedItems.get("ring")!=null) {
            finger1.setDrawable((new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(player.equippedItems.get("ring").iconPath))))));
            addImgListener(finger1,player.equippedItems.get("ring"),"ring");
        } else {
            addImgListener(finger1,null,"ring");
        }

        if (player.equippedItems.get("ring2")!=null) {
            finger2.setDrawable((new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(player.equippedItems.get("ring2").iconPath))))));
            addImgListener(finger2,player.equippedItems.get("ring2"),"ring2");
        } else {
            addImgListener(finger2,null,"ring2");
        }

        Table bottomTable = new Table();
        gold = new Label("Gold: ",uiMain.skin);
        bottomTable.add(gold);
        bottomTable.align(Align.left);

        tableBorder.add(characterGearTable).expand().fill().pad(10);
        tableBorder.row();
        tableBorder.add(bottomTable).expand().fill().pad(10).colspan(2);
        /*tableBorder.setDebug(true);
        characterGearTable.setDebug(true);*/
        update();
    }

    protected void addImgListener(Image image, final Item item, final String slot) {
        if (item!=null) {
            image.addListener(
                    new InputListener() {
                        @Override
                        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                            uiMain.itemTooltip.clear();

                            Label name = new Label(item.name,uiMain.skin);
                            name.setColor(Maps.itemColors.get(item.quality));
                            uiMain.itemTooltip.add(name).left().padLeft(5);
                            uiMain.itemTooltip.row();

                            Label ilvl = new Label("Item Level "+item.itemLevel,uiMain.skin);
                            ilvl.setColor(new Color(0.9f,0.9f,0.25f,1f));
                            uiMain.itemTooltip.add(ilvl).left().padLeft(5);
                            uiMain.itemTooltip.row();
                            uiMain.itemTooltip.add(new Label("",uiMain.skin)).expandX().fillX().height(2);
                            uiMain.itemTooltip.row();

                            String str = Maps.itemSlotMap.get(item.type);
                            String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
                            uiMain.itemTooltip.add(new Label(cap,uiMain.skin)).left().padLeft(5);
                            uiMain.itemTooltip.add(new Label(item.quality,uiMain.skin)).right().padRight(5);
                            uiMain.itemTooltip.row();

                            Map stats = item.data.get("stats");
                            for (Object key : stats.keySet()){
                                String str2 = (String)key;
                                String key2 = str2.substring(0, 1).toUpperCase() + str2.substring(1);

                                Label stat = new Label("+"+stats.get(key)+" "+key2,uiMain.skin);
                                stat.setColor(new Color(0.1f,0.95f,0.1f,1f));
                                uiMain.itemTooltip.add(stat).left().padLeft(5);
                                uiMain.itemTooltip.row();
                            }

                            uiMain.itemTooltip.add(new Label(item.weight+"kg",uiMain.skin)).left().padLeft(5);
                            uiMain.itemTooltip.add(new Label((item.price/100)+"g",uiMain.skin)).right().padRight(5);


                            uiMain.itemTooltip.row();
                            uiMain.itemTooltip.add(new Label("",uiMain.skin)).expandX().fillX().height(5);


                            uiMain.itemTooltip.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                            uiMain.itemTooltip.pack();
                            uiMain.itemTooltip.setWidth(180);
                            uiMain.itemTooltip.setVisible(true);
                        }
                        @Override
                        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                            uiMain.itemTooltip.setVisible(false);
                        }
                        @Override
                        public boolean mouseMoved(InputEvent event, float x, float y) {
                            uiMain.itemTooltip.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                            return false;
                        }
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            if (GameInput.ctrl && !GlobalVars.draggingItem) {
                                uiMain.dragItem.setVisible(true);
                                uiMain.dragItem.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                                uiMain.dragItem.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(item.iconPath)))));
                                GlobalVars.draggingItem = true;
                                GlobalVars.dragItem = item;
                                GlobalVars.dragItemName = item.name;
                                player.equippedItems.put(slot,null);
                                createUi();
                            } else if (GlobalVars.draggingItem) {
                                Item item2 = null;
                                if (player.equippedItems.get(slot)!=null) {
                                    item2 = player.equippedItems.get(slot);
                                }
                                player.equippedItems.put(slot,GlobalVars.dragItem);
                                if (item2!=null) {
                                    GlobalVars.dragItem = item2;
                                    GlobalVars.dragItemName = item2.name;
                                    uiMain.dragItem.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(item2.iconPath)))));
                                } else {
                                    GlobalVars.draggingItem = false;
                                }
                                createUi();
                            }
                            return true;
                        }
                    }
            );
        } else {
            image.addListener(
                    new InputListener() {
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            if (GlobalVars.draggingItem) {
                                GlobalVars.draggingItem = false;
                                uiMain.dragItem.setVisible(false);
                                player.equippedItems.put(slot,GlobalVars.dragItem);
                                createUi();
                            }
                            return true;
                        }
                    }
            );
        }
    }


}
