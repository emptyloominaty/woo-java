package com.woo.game.ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.woo.game.GlobalFunctions;
import com.woo.game.GlobalVars;
import com.woo.game.objects.Keybinds;
import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.gameobjects.Creature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.woo.game.Main.*;

public class UiMain implements ApplicationListener {
    //Config
    public int healthBarWidth = 150;
    public int healthBarHeight = 90;
    public int manaBarWidth = 150;
    public int manaBarHeight = 60;
    public int secBarWidth = 150;
    public int secBarHeight = 30;
    public int castBarWidth = 150;
    public int castBarHeight = 20;

    public int creatureHealthBarWidth = 55;

    int actionSize = 48;
    //

    public Stage stage;
    public Stage stageBottom;
    public Stage stageTop;
    Table table;
    Table tableTopRight;
    Skin skin;
    public Label fpsLabel;
    public Label frameTimeLabel;
    public Label positionLabel;
    public Label areaNameLabel;
    public Label debug1;
    public Label debug2;
    public Label debug3;
    public Label debug4;
    public Label debug5;


    //health bar
    public Table tableHpBar;
    public Image healthBarA;
    public Label healthBarText;
    public Stack healthBarStack;
    public Table healthBarATable;

    //resource bar
    public Table tableMpBar;
    public Image manaBarA;
    public Label manaBarText;
    public Stack manaBarStack;
    public Table manaBarATable;

    //secondary resource bar
    public Table tableSrBar;
    public Image secBarA;
    public Label secBarText;
    public Stack secBarStack;
    public Table secBarATable;

    //cast bar
    public Table tableCastBar;
    public Image castBarA;
    public Label castBarText;
    public Stack castBarStack;
    public Table castBarATable;

    //TODO:player cast bar

    //TODO:player buffs + debuffs 16+16
    public ArrayList<Table> playerBuffsTables;
    //Icon+Duration+Stacks

    public ArrayList<Table> playerDebuffsTables;
    //Icon+Duration+Stacks

    //TODO:Buff tooltip
    //TODO:Ability tooltip
    //TODO:Windows (Inventory, Character, Spellbook, DPS/HPS(Details), Settings????)

    //TODO:Creatures texts+bars
    public Map<Integer,Table> creatureTables;
    public Map<Integer,Table> creatureTablesHealth;
    public Map<Integer,Table> creatureTablesCast;
    public Map<Integer,Stack> creatureStackHealth;
    public Map<Integer,Stack> creatureStackCast;
    public Map<Integer,Label> creatureNames;
    public Map<Integer,Label> creatureHealthText;
    public Map<Integer,Label> creatureCastName;
    public Map<Integer,Image> creatureHealthBar;
    public Map<Integer,Image> creatureCastBar;
    //TODO:Creatures Buffs+Debuffs
    //Table+Stack+Label+Image

    //TODO:Floating Combat Text TODO:HASHMAP?
    public ArrayList<Table> floatingCombatTextTables;
    public ArrayList<Label> floatingCombatTextLabels;

    //Action Bars
    public Map<Integer,Table> actionTables;
    public Map<Integer,Table> actionTablesStack;
    public Map<Integer,Stack> actionStacks;
    public Map<Integer,Label> actionKeybinds;
    public Map<Integer,Label> actionCdTimes;
    public Map<Integer,Label> actionCharges;
    public Map<Integer,Image> actionIcons;
    public Map<Integer,Table> actionGcdTimer;
    public Map<Integer,Table> actionCdTimer;
    public Map<Integer,Table> actionCost;
    public Map<Integer,Image> actionPress;
    Table actionBarMainTop;
    Table actionBarMainBottom;


    //TODO:UI (Menu)

    //font
    public FreeTypeFontGenerator generator;
    public FreeTypeFontParameter parameter = new FreeTypeFontParameter();

    Label.LabelStyle labelStyle10 = new Label.LabelStyle();
    Label.LabelStyle labelStyle12 = new Label.LabelStyle();
    Label.LabelStyle labelStyle14 = new Label.LabelStyle();
    Label.LabelStyle labelStyle16 = new Label.LabelStyle();
    Label.LabelStyle labelStyle18 = new Label.LabelStyle();
    Label.LabelStyle labelStyle20 = new Label.LabelStyle();


    Table abilityTooltip;
    Label abilityTName;
    Label abilityType;
    Label abilityCost;
    Label abilityCd;
    Label abilityRange;
    Label abilityDesc;
    Label abilityCastTime;


    public void create () {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/lsans.ttf"));

        stageBottom = new Stage();
        stageTop = new Stage();
        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        tableTopRight = new Table();
        tableTopRight.setFillParent(true);

        stage.addActor(table);
        stage.addActor(tableTopRight);
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();

        //font
        parameter.size = 10;
        parameter.borderColor = Color.BLACK;
        BitmapFont font10 = generator.generateFont(parameter);
        parameter.borderWidth = 0.5f;
        parameter.size = 12;
        BitmapFont font12 = generator.generateFont(parameter);
        parameter.size = 14;
        BitmapFont font14 = generator.generateFont(parameter);
        parameter.size = 16;
        BitmapFont font16 = generator.generateFont(parameter);
        parameter.borderWidth = 1.5f;
        parameter.size = 18;
        BitmapFont font18 = generator.generateFont(parameter);
        parameter.size = 20;
        BitmapFont font20 = generator.generateFont(parameter);

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        // Store the default libGDX font under the name "default".
        skin.add("default", new BitmapFont());
        skin.add("font10", font10);
        skin.add("font12", font12);
        skin.add("font14", font14);
        skin.add("font16", font16);
        skin.add("font18", font18);
        skin.add("font20", font20);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.RED);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        Label.LabelStyle defaultLabelStyle = new Label.LabelStyle();
        defaultLabelStyle.font = skin.getFont("default");
        skin.add("default",defaultLabelStyle);

        labelStyle10.font = skin.getFont("font10");
        skin.add("label10",labelStyle10);

        labelStyle12.font = skin.getFont("font12");
        skin.add("label12",labelStyle12);

        labelStyle14.font = skin.getFont("font14");
        skin.add("label14",labelStyle14);
        labelStyle16.font = skin.getFont("font16");
        skin.add("label16",labelStyle16);
        labelStyle18.font = skin.getFont("font18");
        skin.add("label18",labelStyle18);
        labelStyle20.font = skin.getFont("font20");
        skin.add("label20",labelStyle20);

        fpsLabel = new Label("Fps: ",skin);
        frameTimeLabel = new Label("",skin);
        positionLabel = new Label("",skin);

        table.add(fpsLabel).pad(5).left();
        table.row();

        table.add(frameTimeLabel).pad(5).left();
        table.row();

        areaNameLabel = new Label("",skin);
        tableTopRight.add(areaNameLabel).pad(5).right();
        tableTopRight.row();
        tableTopRight.add(positionLabel).pad(5).right();

        debug1 = new Label(" ",skin);
        debug2 = new Label(" ",skin);
        debug3 = new Label(" ",skin);
        debug4 = new Label(" ",skin);
        debug5 = new Label(" ",skin);

        table.add(debug1).pad(5).left();
        table.row();
        table.add(debug2).pad(5).left();
        table.row();
        table.add(debug3).pad(5).left();
        table.row();
        table.add(debug4).pad(5).left();
        table.row();
        table.add(debug5).pad(5).left();
        table.row();

        //Health Bar
        tableHpBar = new Table();
        table.addActor(tableHpBar);
        healthBarATable = new Table();
        healthBarStack = new Stack();
        Image healthBarBackground = new Image(skin.newDrawable("white", Color.BLACK));
        healthBarA = new Image(skin.newDrawable("white", Color.RED));
        healthBarText = new Label("100/100",skin);

        healthBarStack.addActor(healthBarBackground);
        healthBarATable.add(healthBarA).size(healthBarWidth,healthBarHeight);
        healthBarStack.addActor(healthBarATable);
        healthBarStack.addActor(healthBarText);
        healthBarText.setAlignment(Align.center);

        tableHpBar.add(healthBarStack).size(healthBarWidth,healthBarHeight);
        tableHpBar.align(Align.left);
        tableHpBar.setPosition(80,55);


        //Resource Bar
        tableMpBar = new Table();
        table.addActor(tableMpBar);
        manaBarATable = new Table();
        manaBarStack = new Stack();
        Image manaBarBackground = new Image(skin.newDrawable("white", Color.BLACK));
        manaBarA = new Image(skin.newDrawable("white", Color.SKY));
        manaBarText = new Label("100/100",skin);

        manaBarStack.addActor(manaBarBackground);
        manaBarATable.add(manaBarA).size(manaBarWidth,manaBarHeight);
        manaBarStack.addActor(manaBarATable);
        manaBarStack.addActor(manaBarText);
        manaBarText.setAlignment(Align.center);

        tableMpBar.add(manaBarStack).size(manaBarWidth,manaBarHeight);
        tableMpBar.setPosition(cam.viewportWidth-80,40); //TODO:
        tableMpBar.align(Align.right);
        //

        //Secondary Resource Bar
        tableSrBar = new Table();
        table.addActor(tableSrBar);
        secBarATable = new Table();
        secBarStack = new Stack();
        Image secBarBackground = new Image(skin.newDrawable("white", Color.BLACK));
        secBarA = new Image(skin.newDrawable("white", Color.LIGHT_GRAY));
        secBarText = new Label("0/100",skin);

        secBarStack.addActor(secBarBackground);
        secBarATable.add(secBarA).size(secBarWidth,secBarHeight);
        secBarStack.addActor(secBarATable);
        secBarStack.addActor(secBarText);
        secBarText.setAlignment(Align.center);

        tableSrBar.add(secBarStack).size(secBarWidth,secBarHeight);
        tableSrBar.setPosition(cam.viewportWidth-80,90); //TODO:
        tableSrBar.align(Align.right);

        //cast bar
        tableCastBar = new Table();
        table.addActor(tableCastBar);
        castBarATable = new Table();
        castBarStack = new Stack();
        Image castBarBackground = new Image(skin.newDrawable("white", Color.BLACK));
        castBarA = new Image(skin.newDrawable("white", Color.LIGHT_GRAY));
        castBarText = new Label("",skin);

        castBarStack.addActor(castBarBackground);
        castBarATable.add(castBarA).size(castBarWidth,castBarHeight);
        castBarStack.addActor(castBarATable);
        castBarStack.addActor(castBarText);
        castBarText.setAlignment(Align.center);

        tableCastBar.add(castBarStack).size(castBarWidth,castBarHeight);
        tableCastBar.setPosition(cam.viewportWidth/2,125); //TODO:
        tableCastBar.setVisible(false);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton button = new TextButton("Click me!", skin);
        table.add(button).left();

        button.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                button.setText("Good job!");
            }
        });

        // Add an image actor. Have to set the size, else it would be the size of the drawable (which is the 1x1 texture).
        //table.add(new Image(skin.newDrawable("white", Color.BLACK))).size(64);
        table.top().left();
        tableTopRight.top().right();


        //creature bars
        creatureTables = new HashMap<Integer, Table>();
        creatureTablesHealth = new HashMap<Integer, Table>();
        creatureTablesCast = new HashMap<Integer, Table>();
        creatureStackHealth = new HashMap<Integer, Stack>();
        creatureStackCast = new HashMap<Integer, Stack>();
        creatureNames = new HashMap<Integer, Label>();
        creatureHealthText = new HashMap<Integer, Label>();
        creatureCastName = new HashMap<Integer, Label>();
        creatureHealthBar = new HashMap<Integer, Image>();
        creatureCastBar = new HashMap<Integer, Image>();

        //actionbars
        actionBarMainTop = new Table();
        actionBarMainBottom = new Table();
        actionTables = new HashMap<Integer, Table>();
        actionTablesStack = new HashMap<Integer, Table>();
        actionStacks = new HashMap<Integer, Stack>();
        actionKeybinds = new HashMap<Integer, Label>();
        actionCdTimes = new HashMap<Integer, Label>();
        actionCharges = new HashMap<Integer, Label>();
        actionIcons = new HashMap<Integer, Image>();
        actionGcdTimer = new HashMap<Integer, Table>();
        actionCdTimer = new HashMap<Integer, Table>();
        actionCost = new HashMap<Integer, Table>();
        actionPress = new HashMap<Integer, Image>();

        actionBarMainTop.setPosition(cam.viewportWidth/2,actionSize+15);
        actionBarMainBottom.setPosition(cam.viewportWidth/2,10);

        actionBarMainTop.setHeight(actionSize);
        actionBarMainBottom.setHeight(actionSize);

        stage.addActor(actionBarMainTop);
        stage.addActor(actionBarMainBottom);

        //ability tooltip
        /*Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = font16;
        windowStyle.background = skin.newDrawable("white", new Color (0.3f,0.3f,0.3f,0.75f));
        skin.add("default",windowStyle);*/

        abilityTooltip = new Table(skin);
        abilityTName = new Label("",skin);
        abilityType = new Label("",skin);
        abilityType.setStyle(labelStyle12);
        abilityCost = new Label("",skin);
        abilityCd = new Label("",skin);
        abilityRange = new Label("",skin);
        abilityDesc = new Label("",skin);
        abilityDesc.setWrap(true);
        abilityCastTime = new Label("CastTime",skin);

        abilityTooltip.setBackground(skin.newDrawable("white", new Color (0.3f,0.3f,0.3f,0.75f)));
        abilityTooltip.add(abilityTName).colspan(2).left().padLeft(5).expandX().fillX();
        abilityTooltip.row();
        abilityTooltip.add(abilityType).left().padLeft(5).expandX().fillX();
        abilityTooltip.row();
        abilityTooltip.add(abilityCost).left().padLeft(5).expandX().fillX();
        abilityTooltip.add(abilityRange).right().padRight(5).expandX().fillX();
        abilityTooltip.row();
        abilityTooltip.add(abilityCastTime).left().padLeft(5).expandX().fillX();
        abilityTooltip.add(abilityCd).right().padRight(5).expandX().fillX();
        abilityTooltip.row();
        abilityTooltip.add(abilityDesc).colspan(2).padLeft(5).expandX().fillX();

        abilityRange.setAlignment(Align.right);
        abilityCd.setAlignment(Align.right);

        abilityTooltip.setPosition(400,400);
        abilityTooltip.align(Align.top);
        abilityTooltip.setSize(250,150);
        stage.addActor(abilityTooltip);

        abilityTooltip.setVisible(false);

        /*stage.setDebugAll(true);
        stageBottom.setDebugAll(true);
        stageTop.setDebugAll(true);*/

        //TODO UPDATE
        areaNameLabel.setText(areaName);
    }

    public void createActionBars(boolean top, int size) {
        //TODO:Charges, Cd, ...


        int j = 1;
        if (top) {
            j = 0;
        }
        for (int i = 0; i<size; i++) {
            Table tableAB = new Table();
            if (top) {
                actionBarMainTop.add(tableAB);
            } else {
                actionBarMainBottom.add(tableAB);
            }
            Stack stack = new Stack();
            Table stackTable = new Table();
            Table pressImgTable = new Table();


            Image backgroundImg = new Image(skin.newDrawable("white", new Color(0,0,0,0.3f)));
            stackTable.add(backgroundImg).size(actionSize).pad(1);

            Image pressImg = new Image(skin.newDrawable("white", new Color(1,1,1,0.4f)));
            pressImg.setVisible(false);
            pressImgTable.add(pressImg).size(actionSize).pad(1);

            stack.addActor(stackTable);

            Table gcdTable = new Table();
            //Table cdTable = new Table();
            Table costTable = new Table();

            if (actionBars[j].abilities[i]!=null) {
                final String abilityName = actionBars[j].abilities[i];
                final Ability ability = player.abilities.get(abilityName);
                //TODO:ImageButton + tooltip
                Image iconImg = new Image(new Texture (Gdx.files.internal(ability.iconPath)));
                stack.add(iconImg);
                Label keybind = new Label(" "+Keybinds.keys.get("ActionBar"+j+"_"+i+"")[0]+" ",skin);
                keybind.setAlignment(Align.top, Align.right);
                keybind.setStyle(labelStyle18);
                stack.add(keybind);

                Image gcdImg = new Image(skin.newDrawable("white", new Color(0,0,0,0.6f)));
                gcdTable.add(gcdImg).size(actionSize,actionSize);

                Image costImg = new Image(skin.newDrawable("white", new Color(0,0,0,0.7f)));
                costTable.add(costImg).size(actionSize,actionSize);

                tableAB.addListener(
                        new InputListener() {
                            @Override
                            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                abilityTName.setText(abilityName);

                                String cost = Math.round(ability.cost)+" "+player.resourceName;
                                if (ability.cost==0 && ability.secCost==0) {
                                    cost = "";
                                } else if (ability.cost==0 && ability.secCost!=0) {
                                    cost = ability.secCost+" "+player.secondaryResourceName;
                                }
                                abilityCost.setText(cost);

                                String cd = ability.maxCd+" s";
                                if (ability.charges>1) {
                                    cd += " ("+ability.charges+" Charges)";
                                }
                                cd += " cd";
                                if (ability.maxCd==0) {
                                    cd = "";
                                }
                                abilityCd.setText(cd);

                                String range = "Range: "+Math.round(ability.range)+"m";
                                if (ability.range==0) {
                                    range = "";
                                }
                                if (ability.range==5) {
                                    range = "Range: Melee";
                                }
                                abilityRange.setText(range);

                                String castTime = ability.castTime+"s cast";
                                if (ability.channeling) {
                                    castTime = ability.castTime+"s channel";
                                }
                                if (ability.castTime==0) {
                                    castTime = "";
                                }
                                abilityCastTime.setText(castTime);

                                abilityDesc.setText(ability.getTooltip(player));

                                String aType = "";
                                if (ability.passive) {
                                    aType = "Passive";
                                }
                                if (ability.talent) {
                                    aType = "Talent";
                                }
                                if (ability.mastery) {
                                    aType = "Mastery";
                                }
                                abilityType.setText(aType);
                                abilityTooltip.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                                //TODO MOVE?
                                abilityTooltip.setVisible(true);
                            }
                            @Override
                            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                                abilityTooltip.setVisible(false);
                            }
                            @Override
                            public boolean mouseMoved(InputEvent event, float x, float y) {
                                abilityTooltip.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                               return false;
                            }
                        }
                );
                /*iconImg.addListener(new FocusListener() {
                    @Override
                    public boolean handle(Event event) {
                        System.out.println("HELLO?");
                        return true;
                    }
                });*/
            }
            costTable.setClip(true);
            gcdTable.setClip(true);
            stack.addActor(costTable);
            stack.addActor(gcdTable);
            stack.addActor(pressImgTable);
            tableAB.add(stack);

            actionPress.put(i+(j*size),pressImg);
            //actionStacks.put(i+(j*size),stack);
            actionGcdTimer.put(i+(j*size),gcdTable);
            actionCost.put(i+(j*size),costTable);
            //actionCdTimer.put(i+(j*size),cdTable);
        }
    }

    public void setActionPress(int slot, int bar, boolean visible) {
        actionPress.get(slot+(bar*15)).setVisible(visible);
    }

    public void gcdTimerSet(int slots, int bar) {
        if (player.gcd>0 && player.gcdMax>0) {
            float height = (float) ((player.gcd/player.gcdMax)*actionSize);
            for (int i = 0; i<slots; i++) {
                actionGcdTimer.get(i+(bar*15)).setHeight(height);
            }
        } else {
            for (int i = 0; i<slots; i++) {
                actionGcdTimer.get(i + (bar * 15)).setHeight(0);
            }
        }
        //cost
        for (int i = 0; i<slots; i++) {
            if (actionBars[bar].abilities[i]!=null) {
                if (player.abilities.get(actionBars[bar].abilities[i]).checkCost(player, -9999, false, -9999)) {
                    actionCost.get(i + (bar * 15)).setHeight(0);
                } else {
                    actionCost.get(i + (bar * 15)).setHeight(actionSize);
                }
            } else {
                actionCost.get(i + (bar * 15)).setHeight(0);
            }
        }
    }


    //StageTop
    //TODO: Character screen
    //TODO: Spellbook
    //TODO: Talents
    //TODO: Inventory


    public void addCreatureBar(Creature creature) {
        if (creature.faction!=0) {
            Table tableC = new Table();
            this.stageBottom.addActor(tableC);
            Label label = new Label(creature.name+" ("+creature.level+")",skin); //TODO:
            label.setStyle(labelStyle12);
            Label labelHealth = new Label ("10/10",skin);
            labelHealth.setStyle(labelStyle10);
            tableC.add(label).size(160,18);
            tableC.row();

            Table tableH = new Table();
            Stack stackH = new Stack();

            Image healthbarBackground = new Image(skin.newDrawable("white", new Color(0,0,0,0.6f)));
            Image healthBarC = new Image(skin.newDrawable("white", new Color(1f,0.2f,0.2f,0.6f)));

            stackH.add(healthbarBackground);
            tableH.add(healthBarC).size(creatureHealthBarWidth,14);
            stackH.addActor(tableH);
            stackH.add(labelHealth);
            tableC.add(stackH);

            label.setAlignment(Align.center);
            labelHealth.setAlignment(Align.center);
            tableC.align(Align.center);
            tableC.setPosition(creature.x, creature.y);

            creatureTables.put(creature.typeId,tableC);
            creatureNames.put(creature.typeId,label);
            creatureHealthText.put(creature.typeId,labelHealth);
            creatureHealthBar.put(creature.typeId,healthBarC);

        }
    }
    public void updateCreatureBar(Creature creature) {
        if (creature.faction!=0) {
            float x = (Gdx.graphics.getWidth()/2f) + ((creature.x - cam.position.x)) /GlobalVars.camZoom;
            float y = (Gdx.graphics.getHeight()/2f) + ((creature.y - cam.position.y)) /GlobalVars.camZoom;
            creatureNames.get(creature.typeId).setText(creature.name+" ("+creature.level+")");
            double health = creature.health;
            if (health<0) {
                health = 0;
            }
            creatureHealthText.get(creature.typeId).setText(GlobalFunctions.getHealthString(health)+"/"+GlobalFunctions.getHealthString(creature.healthMax));
            //creatureHealthText.get(creature.typeId).setText(Math.round(health)+"/"+Math.round(creature.healthMax));
            creatureTables.get(creature.typeId).setPosition(x, y + 36);

            int width = (int) (creature.health/creature.healthMax*creatureHealthBarWidth);
            if (width<0) {
                width = 0;
            }
            creatureHealthBar.get(creature.typeId).setWidth(width);
        }
    }
    public void removeCreatureBar(Creature creature) {
        if (creature.faction!=0) {
            //TODO ???????????
            creatureTables.remove(creature.typeId);
        }
    }

    public void resize (int width, int height) {
        //??????????????????
        stage.getViewport().update(width, height, true);
        stageTop.getViewport().update(width, height, true);
        stageBottom.getViewport().update(width, height, true);
    }

    public void render() {
        if (player.isCasting) {
            double time1 = (double) player.casting.get("time");
            double time2 = (double) player.casting.get("time2");
            double time = time2 - time1;
            tableCastBar.setVisible(true);
            castBarText.setText(player.casting.get("name")+" - "+(Math.round(time*10.0)/10.0)+"s");
            castBarA.setWidth((float) (castBarWidth*(time1/time2)));
        } else {
            tableCastBar.setVisible(false);
        }

        fpsLabel.setText("Fps: "+Math.round(GlobalVars.fps));
        positionLabel.setText("x:"+Math.round(player.x)+" y:"+Math.round(player.y));
        frameTimeLabel.setText("Total:"+(debugPerf[62]-debugPerf[0])+", Main:"+(debugPerf[30]-debugPerf[0])+", Draw:"+(debugPerf[62]-debugPerf[30]));
        debug1.setText("Level: "+player.level);
        debug2.setText("XP: "+player.xp+"/"+Math.round(500 * (Math.pow(1.2, player.level)-1)/(1.5-1)));
        debug3.setText(player.moveToX+" | "+player.moveToY);

        stageBottom.act(GlobalVars.delta);
        stageBottom.draw();
        stage.act(GlobalVars.delta);
        stage.draw();
        stageTop.act(GlobalVars.delta);
        stageTop.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public void dispose() {
        stage.dispose();
        stageBottom.dispose();
        stageTop.dispose();
        generator.dispose();
    }

    public void setPlayerHealthBar(double health,double healthMax) {
        int width = (int) (health/healthMax*healthBarWidth);
        if (width<0) {
            width = 0;
        }
        healthBarText.setText(Math.round(health)+"/"+Math.round(healthMax));
        healthBarA.setWidth(width);
    }
    public void setPlayerManaBar(double mana,double manaMax) {
        int width = (int) (mana/manaMax*manaBarWidth);
        if (width<0) {
            width = 0;
        }
        manaBarText.setText(Math.round(mana)+"/"+Math.round(manaMax));
        manaBarA.setWidth(width);
    }
    public void setPlayerSecBar(double sec,double secMax) {
        int width = (int) (sec/secMax*secBarWidth);
        if (width<0) {
            width = 0;
        }
        secBarText.setText(Math.round(sec)+"/"+Math.round(secMax));
        secBarA.setWidth(width);
    }

}
