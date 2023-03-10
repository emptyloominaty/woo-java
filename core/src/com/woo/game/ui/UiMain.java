package com.woo.game.ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.TouchableAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.woo.game.GameInput;
import com.woo.game.GlobalFunctions;
import com.woo.game.GlobalVars;
import com.woo.game.Maps;
import com.woo.game.objects.Keybinds;
import com.woo.game.objects.Setting;
import com.woo.game.objects.Settings;
import com.woo.game.objects.abilities.Ability;
import com.woo.game.objects.gameobjects.Creature;
import com.woo.game.objects.other.Buff;

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

    public int xpBarWidth = 600;
    public int xpBarHeight = 6;

    int actionSize = 48;
    //

    public Stage stage;
    public Stage stageBottom;
    public Stage stageTop;
    public Stage stageTop2;

    public Table tableBuffs;

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

    //xp bar
    public Table tableXpBar;
    public Image xpBarA;
    public Label xpBarText;
    public Stack xpBarStack;
    public Table xpBarATable;

    public Table xpBarLabelTable;
    public Label xpBarLabel;

    //TODO:player cast bar

    //TODO:player buffs + debuffs 16+16
    public ArrayList<Table> playerBuffsTables;
    //Icon+Duration+Stacks

    public ArrayList<Table> playerDebuffsTables;
    //Icon+Duration+Stacks

    //TODO:Buff tooltip
    //TODO:Windows (Talent,DPS/HPS(Details))

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

    public Window characterStats;
    Table characterStatsTable;

    public Window spellbook;
    Table spellbookTable;

    public Window settings;
    Table settingsTable;
    Map<String,Table> settingsCategories;


    //TODO:UI (Menu)

    //font
    public FreeTypeFontGenerator generator;
    public FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    BitmapFont font10;
    BitmapFont font12;
    BitmapFont font14;
    BitmapFont font16;
    BitmapFont font18;
    BitmapFont font20;
    BitmapFont fontTt;

    Label.LabelStyle labelStyle10 = new Label.LabelStyle();
    Label.LabelStyle labelStyle12 = new Label.LabelStyle();
    Label.LabelStyle labelStyle14 = new Label.LabelStyle();
    Label.LabelStyle labelStyle16 = new Label.LabelStyle();
    Label.LabelStyle labelStyle18 = new Label.LabelStyle();
    Label.LabelStyle labelStyle20 = new Label.LabelStyle();
    Label.LabelStyle labelStyleTooltip = new Label.LabelStyle();

    Table abilityTooltip;
    Label abilityTName;
    Label abilityType;
    Label abilityCost;
    Label abilityCd;
    Label abilityRange;
    Label abilityDesc;
    Label abilityCastTime;

    public Table itemTooltip;

    //Borders
    Texture borderCharacterStats1;


    public Image dragAbility;
    public Image dragItem;


    public void create () {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/lsans.ttf"));

        stageBottom = new Stage();
        stageTop = new Stage();
        stageTop2 = new Stage();
        stage = new Stage();

        tableBuffs = new Table();

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
        font10 = generator.generateFont(parameter);
        parameter.borderWidth = 0.5f;
        parameter.size = 12;
        font12 = generator.generateFont(parameter);
        parameter.size = 14;
        font14 = generator.generateFont(parameter);
        parameter.size = 16;
        font16 = generator.generateFont(parameter);
        parameter.borderWidth = 1.5f;
        parameter.size = 18;
        font18 = generator.generateFont(parameter);
        parameter.size = 20;
        font20 = generator.generateFont(parameter);

        parameter.size = 15;
        parameter.color = new Color(0.85f,0.85f,0.6f,1.0f);
        parameter.borderWidth = 0;
        fontTt = generator.generateFont(parameter);

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
        skin.add("fontTt",fontTt);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.over = skin.newDrawable("white", Color.GRAY);
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

        labelStyleTooltip.font = skin.getFont("fontTt");
        skin.add("labelTt",labelStyleTooltip);


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
        tableTopRight.row();


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
        abilityTooltip = new Table(skin);
        abilityTName = new Label("",skin);
        abilityTName.setStyle(labelStyle18);
        abilityType = new Label("",skin);
        abilityType.setStyle(labelStyle12);
        abilityCost = new Label("",skin);
        abilityCd = new Label("",skin);
        abilityRange = new Label("",skin);
        abilityDesc = new Label("",skin);
        abilityDesc.setStyle(labelStyleTooltip);
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
        abilityTooltip.setSize(250,50);
        stageTop2.addActor(abilityTooltip);
        abilityTooltip.setVisible(false);


        //ItemTooltip
        itemTooltip = new Table(skin);
        itemTooltip.setBackground(skin.newDrawable("white", new Color (0.3f,0.3f,0.3f,0.75f)));
        itemTooltip.setPosition(400,400);
        itemTooltip.align(Align.top);
        itemTooltip.setSize(250,50);
        stageTop2.addActor(itemTooltip);
        itemTooltip.setVisible(false);


        //StageTop
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = font14;//#a47012
        //windowStyle.background = new TextureRegionDrawable(new Texture(Gdx.files.internal("textures/ui/paper.jpg")));
        windowStyle.background = skin.newDrawable("white", Maps.windowColorBorder);
        skin.add("default",windowStyle);

        //Character stats
        characterStats = new Window(" Character Stats",skin);
        characterStatsTable = new Table();
        characterStats.setSize(600,500);
        characterStats.padTop(25);
        characterStats.setPosition(40,Gdx.graphics.getHeight()-540);
        Table tableBorder2 = new Table();
        tableBorder2.add(characterStatsTable).expand().fill().pad(10);
        tableBorder2.background(uiMain.skin.newDrawable("white", Maps.windowColor));
        characterStats.add(tableBorder2).expand().fill().pad(2).padTop(0);
        characterStats.setMovable(true);
        characterStats.setVisible(false);

        final Button closeButton1 = new TextButton("X", uiMain.skin, "default");
        characterStats.getTitleTable().add(closeButton1).size(30, 20).padRight(2);
        closeButton1.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                GlobalVars.characterStats = false;
                characterStats.setVisible(false);
                closeButton1.setChecked(false);
            }
        });

        stageTop.addActor(characterStats);

        //Border
        Pixmap pixmapCS1  = new Pixmap(80, 24, Pixmap.Format.RGBA8888);
        // Set the border color and thickness
        pixmapCS1.setColor(Color.WHITE);
        pixmapCS1.drawRectangle(0, 0, 80, 24);

        pixmapCS1.setColor(Color.BLACK);
        pixmapCS1.drawRectangle(1, 1, 78, 22);
        // Create the Texture
        borderCharacterStats1 = new Texture(pixmapCS1);

        //Spellbook
        spellbook = new Window(" Spellbook",skin);
        spellbookTable = new Table();
        spellbookTable.align(Align.topLeft);
        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        skin.add("default",scrollPaneStyle);
        ScrollPane spellbookScrollPane = new ScrollPane(spellbookTable,skin);
        spellbook.setSize(700,500);
        spellbook.padTop(25);
        spellbook.setPosition(40,Gdx.graphics.getHeight()-560);
        Table tableBorder3 = new Table();
        tableBorder3.add(spellbookScrollPane).expand().fill().pad(10);
        tableBorder3.background(uiMain.skin.newDrawable("white", Maps.windowColor));
        spellbook.add(tableBorder3).expand().fill().pad(2).padTop(0);
        spellbook.setMovable(true);
        spellbook.setVisible(false);
        spellbook.setZIndex(40);
        stageTop.addActor(spellbook);

        final Button closeButton2 = new TextButton("X", uiMain.skin, "default");
        spellbook.getTitleTable().add(closeButton2).size(30, 20).padRight(2);
        closeButton2.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                GlobalVars.spellbook = false;
                spellbook.setVisible(false);
                closeButton2.setChecked(false);
            }
        });


        dragAbility = new Image(new Texture(Gdx.files.internal("icons/default.png")));
        dragAbility.setSize(48,48);
        dragAbility.setVisible(false);
        stageTop2.addActor(dragAbility);

        dragItem = new Image(new Texture(Gdx.files.internal("icons/default.png")));
        dragItem.setSize(48,48);
        dragItem.setVisible(false);
        stageTop2.addActor(dragItem);

        settings = new Window(" Settings",skin);
        settingsTable = new Table();
        settings.setSize(600,500);
        settings.padTop(25);
        settings.setPosition(40,Gdx.graphics.getHeight()-540);
        Table tableBorder = new Table();
        tableBorder.add(settingsTable).expand().fill().pad(10);
        tableBorder.background(uiMain.skin.newDrawable("white", Maps.windowColor));
        settings.add(tableBorder).expand().fill().pad(2).padTop(0);
        settings.setMovable(true);
        settingsTable.align(Align.topLeft);
        settings.setVisible(false);

        final Button closeButton3 = new TextButton("X", uiMain.skin, "default");
        settings.getTitleTable().add(closeButton3).size(30, 20).padRight(2);
        closeButton3.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                GlobalVars.settings = false;
                settings.setVisible(false);
                closeButton3.setChecked(false);
            }
        });

        settingsCategories = new HashMap<String,Table>();

        settingsCategories.put("Game",new Table());
        settingsCategories.put("Video",new Table());
        settingsCategories.put("Audio",new Table());

        for(Map.Entry<String, Table> entry : settingsCategories.entrySet()) {
            String key = entry.getKey();
            settingsTable.add(settingsCategories.get(key)).expand().fill();
        }

        stageTop.addActor(settings);
        updateSettings();

        //xp bar
        tableXpBar = new Table();
        table.addActor(tableXpBar);
        xpBarATable = new Table();
        xpBarStack = new Stack();
        Image xpBarBackground = new Image(skin.newDrawable("white", Color.BLACK));
        xpBarA = new Image(skin.newDrawable("white", Color.GOLD));
        xpBarText = new Label("",skin);

        xpBarStack.addActor(xpBarBackground);
        xpBarATable.add(xpBarA).size(xpBarWidth,xpBarHeight);
        xpBarStack.addActor(xpBarATable);
        xpBarStack.addActor(xpBarText);
        xpBarText.setAlignment(Align.center);

        tableXpBar.add(xpBarStack).size(xpBarWidth,xpBarHeight);
        tableXpBar.align(Align.center);
        tableXpBar.setPosition((Gdx.graphics.getWidth()/2f),6);

        //xp
        xpBarLabelTable = new Table(skin);
        xpBarLabelTable.background(skin.newDrawable("white",Color.DARK_GRAY));
        xpBarLabel = new Label("",skin);
        xpBarLabelTable.add(xpBarLabel);
        xpBarLabelTable.setVisible(false);
        stageTop2.addActor(xpBarLabelTable);
        tableXpBar.addListener(
                new InputListener() {
                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        xpBarLabel.setText(GlobalFunctions.getString(player.xp)+"/"+GlobalFunctions.getString(GlobalFunctions.xpToNextLevel(player.level)));
                        xpBarLabelTable.setPosition(Gdx.input.getX()-90,Gdx.graphics.getHeight()-Gdx.input.getY());
                        xpBarLabelTable.pack();
                        xpBarLabelTable.setWidth(180);
                        xpBarLabelTable.setVisible(true);
                    }
                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        xpBarLabelTable.setVisible(false);
                    }
                    @Override
                    public boolean mouseMoved(InputEvent event, float x, float y) {
                        xpBarLabel.setText(GlobalFunctions.getString(player.xp)+"/"+GlobalFunctions.getString(GlobalFunctions.xpToNextLevel(player.level)));
                        xpBarLabelTable.setPosition(Gdx.input.getX()-90,Gdx.graphics.getHeight()-Gdx.input.getY());
                        return false;
                    }
                }
        );

        /*stage.setDebugAll(true);
        stageBottom.setDebugAll(true);
        stageTop.setDebugAll(true);*/

        init2();

        areaNameLabel.setText(areaName);
    }



    public void updateSettings() {
        settingsTable.clear();
        for(Map.Entry<String, Table> entry : settingsCategories.entrySet()) {
            String key = entry.getKey();
            settingsCategories.get(key).clear();
            Image line = new Image(skin.newDrawable("white", new Color(1,1,1,0.5f)));
            settingsTable.add(line).height(1).expandX().fillX().padBottom(5);
            settingsTable.row();
            Label categoryLabel = new Label(""+key,skin);
            categoryLabel.setStyle(labelStyle18);
            categoryLabel.setAlignment(Align.center);
            settingsCategories.get(key).add(categoryLabel).colspan(8).expandX().fillX();
            settingsCategories.get(key).align(Align.topLeft);
            settingsCategories.get(key).row();

            settingsTable.add(settingsCategories.get(key)).expand().fill();
            settingsTable.row();
        }
        for(Map.Entry<String, Setting> entry : Settings.map.entrySet()) {
            String key = entry.getKey();
            final Setting setting = Settings.map.get(key);
           //TODO:
            if (!(setting.type.equals("slider"))) {
                Table nameTable = new Table();
                Table optionsTable = new Table();

                Label name = new Label(setting.name,skin);
                nameTable.add(name);

                for (int i = 0; i<setting.values.length; i++) {
                    TextButton button = new TextButton(setting.names[i], skin);

                    final int finalI = i;
                    button.addListener(new ClickListener() {
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            setting.value = setting.values[finalI];
                            updateSettings();
                            Settings.saveSettings();
                            return super.touchDown(event, x, y, pointer, button);
                        }
                    });
                    if (setting.value==setting.values[i]) {
                        button.setChecked(true);
                    }
                    optionsTable.add(button).padLeft(5).padRight(5);
                }

                settingsCategories.get(setting.category).add(nameTable).left().expandX().padLeft(5);
                settingsCategories.get(setting.category).add(optionsTable).right().expandX().padLeft(5).pad(1);
            } else {
                //TODO:slider
            }
            settingsCategories.get(setting.category).row();
        }
    }

    public void updateSpellbook() {
        spellbookTable.clear();
        int i = 0;
        Label l = new Label("",skin);
        spellbookTable.add(l).colspan(4).fillX().expandX();
        spellbookTable.row();
        for (Map.Entry<String, Ability> ability : player.abilities.entrySet()) {
            Ability ab = player.abilities.get(ability.getKey());
            Table table = new Table();
            //TODO:mastery,talent,passive
            Image icon = new Image(new Texture (Gdx.files.internal(ab.iconPath)));
            Label label = new Label(ab.name,skin);

            Label passive = new Label("Passive",skin);
            Label talent = new Label("Talent",skin);
            Label mastery = new Label("Mastery",skin);

            passive.setColor(0.5f,0.5f,0.5f,1f);
            passive.setStyle(labelStyle14);
            talent.setColor(0.5f,0.5f,0.5f,1f);
            talent.setStyle(labelStyle14);
            mastery.setColor(0.5f,0.5f,0.5f,1f);
            mastery.setStyle(labelStyle14);

            Table tableText = new Table();
            tableText.add(label).top();

            if (ab.talent) {
                tableText.row();
                tableText.add(talent);
            }
            if (ab.passive) {
                tableText.row();
                tableText.add(passive);
            }
            if (ab.mastery) {
                tableText.row();
                tableText.add(mastery);
            }
            table.add(icon).size(48);
            table.add(tableText).pad(5);

            spellbookTable.add(table).pad(5).padRight(15).colspan(1);
            addTooltip(table, ab, ab.name,false,-1,-1);
            i++;
            if (i>3) {
                spellbookTable.row();
                i=0;
            }
        }
    }

    ArrayList<Label> floatingTextsLabel = new ArrayList<Label>();
    ArrayList<FloatingText> floatingTexts = new ArrayList<FloatingText>();
    ArrayList<Integer> floatingTextsFree = new ArrayList<Integer>();

    public void addFloatingText(float x, float y, String text, String type) {
        int id;
        if (floatingTextsFree.size()>0) {
            int idF = floatingTextsFree.size()-1;
            id = floatingTextsFree.get(idF);
            floatingTextsFree.remove(idF);
        } else {
            id = floatingTexts.size();
        }
        Label label = new Label(text,skin);
        if (type=="crit") {
            label.setColor(Color.YELLOW);
            label.setStyle(labelStyle18);
        }

        if (floatingTexts.size()<=id) {
            floatingTexts.add(new FloatingText(x,y,text,type));
            floatingTextsLabel.add(label);
        } else {
            floatingTexts.set(id,new FloatingText(x,y,text,type));
            floatingTextsLabel.set(id,label);
        }
        float x2 = (Gdx.graphics.getWidth()/2f) + ((x - cam.position.x)) /GlobalVars.camZoom;
        float y2 = (Gdx.graphics.getHeight()/2f) + ((y - cam.position.y)) /GlobalVars.camZoom;
        label.setPosition(x2,y2);
        stageBottom.addActor(label);
        floatingTexts.get(id).id = id;
        if (Settings.map.get("Floating Combat Text").value>1) {
            floatingTexts.get(id).moving = true;
        }
    }

    public void updateFloatingTexts() {
        for (int i = 0; i<floatingTexts.size(); i++) {
            if (floatingTexts.get(i).main()) {
                removeFloatingText(i);
            }
        }
    }

    public void removeFloatingText(int id) {
        floatingTexts.get(id).destroyed = true;
        floatingTextsLabel.get(id).setPosition(-999,-999);
        floatingTextsFree.add(id);
    }

    public void updateCharacterStats() {
        //TODO:optimize?
        characterStatsTable.clear();
        Label.LabelStyle borderLabelStyle = new Label.LabelStyle();
        borderLabelStyle.background = new NinePatchDrawable(new NinePatch(borderCharacterStats1,4,4,4,4));
        borderLabelStyle.font = font16;

        Label playerName = new Label(player.name,borderLabelStyle);
        Label playerClass = new Label(player.creatureClass,borderLabelStyle);

        playerName.setAlignment(Align.center);
        playerClass.setAlignment(Align.center);

        Label playerLevel = new Label(""+player.level, borderLabelStyle);
        Label playerXp = new Label(""+player.xp, borderLabelStyle);
        Label playerXpNext = new Label(""+Math.round(500 * (Math.pow(1.2, player.level)-1)/(1.5-1)), borderLabelStyle);

        Label levelLabel = new Label("Level",skin);
        Label xpLabel = new Label("Experience",skin);
        Label xpNextLabel = new Label("XP to next level",skin);

        playerLevel.setAlignment(Align.center);
        playerXp.setAlignment(Align.center);
        playerXpNext.setAlignment(Align.center);
        //----------------------------
        Label playerIntellect = new Label(""+Math.round(player.stats.get("intellect")),skin);
        Label playerStrength = new Label(""+Math.round(player.stats.get("strength")),skin);
        Label playerDexterity = new Label(""+Math.round(player.stats.get("dexterity")),skin);
        Label playerStamina = new Label(""+Math.round(player.stats.get("stamina")),skin);

        Label playerHaste = new Label(player.stats.get("haste")+" %",skin);
        Label playerCrit = new Label(player.stats.get("crit")+" %",skin);
        Label playerMastery = new Label(player.stats.get("mastery")+" %",skin);

        Label playerBlock = new Label(player.stats.get("block")+" %",skin);
        Label playerParry = new Label(player.stats.get("parry")+" %",skin);
        Label playerDodge = new Label(player.stats.get("dodge")+" %",skin);

        Label playerMagicResistance = new Label(player.stats.get("magicResistance")+" %",skin);
        Label playerDamageTaken = new Label(player.stats.get("DamageTaken")+" %",skin);
        Label playerDamageDone = new Label(player.stats.get("DamageDone")+" %",skin);
        //----------------------------

        Label intellect = new Label("Intellect",skin);
        Label strength = new Label("Strength",skin);
        Label dexterity = new Label("Dexterity",skin);
        Label stamina = new Label("Stamina",skin);

        Label haste = new Label("Haste",skin);
        Label crit = new Label("Critical Strike",skin);
        Label mastery = new Label("Mastery",skin);

        Label block = new Label("Block",skin);
        Label parry = new Label("Parry",skin);
        Label dodge = new Label("Dodge",skin);

        Label magicResistance = new Label("Magic Resistance",skin);
        Label damageTaken = new Label("Damage Taken",skin);
        Label damageDone = new Label("Damage Done",skin);
        //----------------------------

        characterStatsTable.top().left();
        characterStatsTable.setPosition(50,0);

        characterStatsTable.add(new Label("",skin)).colspan(6).expandX().expandX().fillX();
        characterStatsTable.row();
        characterStatsTable.add(playerName).colspan(3);
        characterStatsTable.add(playerClass).colspan(3);
        characterStatsTable.row().height(18);
        characterStatsTable.add(new Label("",skin));
        characterStatsTable.row().height(18);

        characterStatsTable.add(levelLabel).colspan(2);
        characterStatsTable.add(xpLabel).colspan(2);
        characterStatsTable.add(xpNextLabel).colspan(2);
        characterStatsTable.row();
        characterStatsTable.add(playerLevel).colspan(2);
        characterStatsTable.add(playerXp).colspan(2);
        characterStatsTable.add(playerXpNext).colspan(2);
        characterStatsTable.row().height(18);
        characterStatsTable.add(new Label("",skin));
        characterStatsTable.row().height(18);

        characterStatsTable.add(intellect).colspan(1);
        characterStatsTable.add(playerIntellect).colspan(1);

        Table addIntellectTable = new Table();
        addIntellectTable.background(skin.newDrawable("white", new Color(1,1,1,0.2f)));
        Label addIntellect = new Label("+",skin);
        addIntellectTable.add(addIntellect);
        addIntellectTable.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        double stat = player.stats.get("intellect");
                        stat += 1;
                        player.statsPoints --;
                        player.statsB.put("intellect",stat);
                        uiMain.updateCharacterStats();
                        return true;
                    }
                });
        characterStatsTable.add(addIntellectTable).size(25,16).colspan(1);
        if (player.statsPoints>0) {
            characterStatsTable.add(new Label(" /"+player.statsPoints,skin));
        }


        characterStatsTable.row();
        characterStatsTable.add(strength).colspan(1);
        characterStatsTable.add(playerStrength).colspan(1);

        Table addStrengthTable = new Table();
        addStrengthTable.background(skin.newDrawable("white", new Color(1,1,1,0.2f)));
        Label addStrength = new Label("+",skin);
        addStrengthTable.add(addStrength);
        addStrengthTable.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        double stat = player.stats.get("strength");
                        stat += 1;
                        player.statsPoints --;
                        player.statsB.put("strength",stat);
                        uiMain.updateCharacterStats();
                        return true;
                    }
                });
        characterStatsTable.add(addStrengthTable).size(25,16).colspan(1);
        if (player.statsPoints>0) {
            characterStatsTable.add(new Label(" /" + player.statsPoints, skin));
        }
        characterStatsTable.row();
        characterStatsTable.add(dexterity).colspan(1);
        characterStatsTable.add(playerDexterity).colspan(1);

        Table addDexterityTable = new Table();
        addDexterityTable.background(skin.newDrawable("white", new Color(1,1,1,0.2f)));
        Label addDexterity = new Label("+",skin);
        addDexterityTable.add(addDexterity);
        addDexterityTable.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        double stat = player.stats.get("dexterity");
                        stat += 1;
                        player.statsPoints --;
                        player.statsB.put("dexterity",stat);
                        uiMain.updateCharacterStats();
                        return true;
                    }
                });
        characterStatsTable.add(addDexterityTable).size(25,16).colspan(1);
        if (player.statsPoints>0) {
            characterStatsTable.add(new Label(" /" + player.statsPoints, skin));
        }
        characterStatsTable.row();
        characterStatsTable.add(stamina).colspan(1);
        characterStatsTable.add(playerStamina).colspan(1);

        Table addStaminaTable = new Table();
        addStaminaTable.background(skin.newDrawable("white", new Color(1,1,1,0.2f)));
        Label addStamina = new Label("+",skin);
        addStaminaTable.add(addStamina);
        addStaminaTable.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        double stat = player.stats.get("stamina");
                        stat += 1;
                        player.statsPoints --;
                        player.statsB.put("stamina",stat);
                        uiMain.updateCharacterStats();
                        return true;
                    }
                });
        if (player.statsPoints==0) {
            addStaminaTable.setVisible(false);
            addDexterityTable.setVisible(false);
            addIntellectTable.setVisible(false);
            addStrengthTable.setVisible(false);
        }
        characterStatsTable.add(addStaminaTable).size(25,16).colspan(1);
        if (player.statsPoints>0) {
            characterStatsTable.add(new Label(" /" + player.statsPoints, skin));
        }
        characterStatsTable.row();
        characterStatsTable.add(new Label("",skin));
        characterStatsTable.row();
        characterStatsTable.add(haste).colspan(1);
        characterStatsTable.add(playerHaste).colspan(1);
        characterStatsTable.add(block).colspan(1);
        characterStatsTable.add(playerBlock).colspan(1);
        characterStatsTable.add(magicResistance).colspan(1);
        characterStatsTable.add(playerMagicResistance).colspan(1);
        characterStatsTable.row();
        characterStatsTable.add(crit).colspan(1);
        characterStatsTable.add(playerCrit).colspan(1);
        characterStatsTable.add(parry).colspan(1);
        characterStatsTable.add(playerParry).colspan(1);
        characterStatsTable.add(damageTaken).colspan(1);
        characterStatsTable.add(playerDamageTaken).colspan(1);
        characterStatsTable.row();
        characterStatsTable.add(mastery).colspan(1);
        characterStatsTable.add(playerMastery).colspan(1);
        characterStatsTable.add(dodge).colspan(1);
        characterStatsTable.add(playerDodge).colspan(1);
        characterStatsTable.add(damageDone).colspan(1);
        characterStatsTable.add(playerDamageDone).colspan(1);
    }


    public void createActionBars(boolean top, int size) {
        int j = 1;
        if (top) {
            j = 0;
            actionBarMainTop.clear();
        } else {
            actionBarMainBottom.clear();
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
            Table cdTable = new Table();
            Table costTable = new Table();
            Table test = new Table(); //cdTimeTable
            Table chargesTable = new Table();

            if (actionBars[j].abilities[i]!=null) {
                String abilityName = actionBars[j].abilities[i];
                Ability ability = player.abilities.get(abilityName);
                Image iconImg = new Image(new Texture (Gdx.files.internal(ability.iconPath)));
                Table iconTable = new Table();
                iconTable.add(iconImg).pad(1).padTop(2);
                stack.add(iconTable);

                Label keybind;
                if (Keybinds.keys.get("ActionBar"+j+"_"+i+"")[1].equals("SHIFT_LEFT")) {
                    keybind = new Label(" S"+Keybinds.keys.get("ActionBar"+j+"_"+i+"")[0]+" ",skin);
                } else if (Keybinds.keys.get("ActionBar"+j+"_"+i+"")[1].equals("CONTROL_LEFT")) {
                    keybind = new Label(" C"+Keybinds.keys.get("ActionBar"+j+"_"+i+"")[0]+" ",skin);
                } else {
                    keybind = new Label(" "+Keybinds.keys.get("ActionBar"+j+"_"+i+"")[0]+" ",skin);
                }

                keybind.setAlignment(Align.top, Align.right);
                keybind.setStyle(labelStyle18);

                Label cdText = new Label("",skin);
                cdText.setStyle(labelStyle20);
                cdText.setColor(0.9f,0.9f,0.4f,1f);
                Label chargesText = new Label("",skin);
                stack.add(keybind);

                chargesTable.add(chargesText);

                test.add(cdText);

                actionCdTimes.put(i+(j*size),cdText);
                actionCharges.put(i+(j*size),chargesText);

                Image cdImg = new Image(skin.newDrawable("white", new Color(0,0,0,0.6f)));
                cdTable.add(cdImg).size(actionSize,actionSize);

                Image gcdImg = new Image(skin.newDrawable("white", new Color(0,0,0,0.6f)));
                //Image gcdImgBorder = new Image(skin.newDrawable("white", new Color(1,1,1,1)));
                //gcdTable.add(gcdImgBorder).size(actionSize,1).padRight(-actionSize).padTop(-actionSize);
                //gcdTable.align(Align.top);
                gcdTable.add(gcdImg).size(actionSize,actionSize);


                Image costImg = new Image(skin.newDrawable("white", new Color(0,0,0,0.7f)));
                costTable.add(costImg).size(actionSize,actionSize);

                addTooltip(tableAB, ability, abilityName, true, j, i);

            }
            cdTable.setClip(true);
            costTable.setClip(true);
            gcdTable.setClip(true);
            stack.addActor(cdTable);
            stack.addActor(costTable);
            stack.addActor(gcdTable);
            stack.addActor(pressImgTable);

            tableAB.add(stack);
            tableAB.add(test).padLeft(-48);
            tableAB.add(chargesTable).padLeft(-24).bottom();
            final int finalJ = j;
            final int finalI = i;
            tableAB.addListener(
                    new InputListener() {
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            if (GlobalVars.draggingAbility && !GameInput.ctrl) {
                                dragAbility.setVisible(false);
                                GlobalVars.draggingAbility = false;
                                //GlobalVars.dragAbility
                                //GlobalVars.dragAbilityName
                                actions.put(GlobalVars.dragAbilityName,new Action(GlobalVars.dragAbilityName, finalJ, finalI));
                                uiMain.createActionBars(true,15);
                                uiMain.createActionBars(false,15);
                            }
                            return true;
                        }
                    });

            actionPress.put(i+(j*size),pressImg);
            actionStacks.put(i+(j*size),stack);
            actionGcdTimer.put(i+(j*size),gcdTable);
            actionCost.put(i+(j*size),costTable);
            actionCdTimer.put(i+(j*size),cdTable);
        }
    }

    public void gcdTimerSet(int slots, int bar) {

        //CD, Charges
        for (int i = 0; i<slots; i++) {
            if (actionBars[bar].abilities[i]!=null) {
                Ability ability = player.abilities.get(actionBars[bar].abilities[i]);
                if (ability.maxCd>0) {
                    if (ability.cd<ability.maxCd) {
                        double cd = ability.maxCd-ability.cd;
                        if (ability.hasteCd) {
                            cd = (ability.maxCd-ability.cd) / (1 + (player.stats.get("haste") / 100));
                        }

                        actionCdTimes.get(i + (bar * 15)).setText((GlobalFunctions.getTimeString(cd))+"");
                    } else {
                        actionCdTimes.get(i + (bar * 15)).setText("");
                    }

                    if (ability.maxCharges>1) {
                        actionCharges.get(i + (bar * 15)).setText(""+ability.charges);
                    }

                    float height = (float) ((1-(ability.cd / ability.maxCd)) * actionSize);
                    actionCdTimer.get(i + (bar * 15)).setHeight(height);
                } else {
                    actionCdTimer.get(i + (bar * 15)).setHeight(0);
                }
            }
        }
        //GCD
        if (player.gcd>0 && player.gcdMax>0) {
            float height = (float) ((player.gcd/player.gcdMax)*actionSize);
            for (int i = 0; i<slots; i++) {
                if (actionBars[bar].abilities[i]!=null && !player.abilities.get(actionBars[bar].abilities[i]).noGcd) {
                    actionGcdTimer.get(i+(bar*15)).setHeight(height);
                }
            }
        } else {
            for (int i = 0; i<slots; i++) {
                actionGcdTimer.get(i + (bar * 15)).setHeight(0);
            }
        }
        //Cost
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

    public void addTooltip(Table tableAB, final Ability ability, final String abilityName, final boolean actionBar, final int j, final int i) {
        tableAB.addListener(
                new InputListener() {
                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        abilityTName.setText(abilityName);

                        String cost = Math.round(ability.cost)+" "+player.resourceName;
                        abilityCost.setFontScale(1f);
                        if (ability.cost==0 && ability.secCost==0) {
                            cost = "";
                            abilityCost.setFontScale(0.01f);
                        } else if (ability.cost==0 && ability.secCost!=0) {
                            cost = ability.secCost+" "+player.secondaryResourceName;
                        }
                        abilityCost.setText(cost);



                        //     double castTimeA = ability.castTime;
                        //                        if (ability.hasteCd) {
                        //                            castTimeA = castTimeA * (1 + (player.stats.get("haste") / 100));
                        //                        }
                        double cdd = ability.maxCd;
                        if (ability.hasteCd) {
                            cdd = (double)Math.round((cdd / (1 + (player.stats.get("haste") / 100)))*10)/10;
                        }
                        String cd = cdd+"s";
                        if (ability.charges>1) {
                            cd += " ("+ability.charges+" Charges)";
                        }
                        cd += " cd";
                        abilityCd.setFontScale(1f);
                        if (ability.maxCd==0) {
                            cd = "";
                            abilityCd.setFontScale(0.01f);
                        }
                        abilityCd.setText(cd);

                        String range = "Range: "+Math.round(ability.range)+"m";
                        abilityRange.setFontScale(1f);
                        if (ability.range==0) {
                            range = "";
                            abilityRange.setFontScale(0.01f);
                        }
                        if (ability.range==5) {
                            range = "Range: Melee";
                        }
                        abilityRange.setText(range);

                        String castTime = ((double)Math.round((ability.castTime / (1 + (player.stats.get("haste") / 100)))*100)/100)+"s cast";
                        abilityCastTime.setFontScale(1f);
                        if (ability.channeling) {
                            castTime = ability.castTime+"s channel";
                        }
                        if (ability.castTime==0) {
                            castTime = "";
                            abilityCastTime.setFontScale(0.01f);
                        }
                        abilityCastTime.setText(castTime);

                        abilityDesc.setText(ability.getTooltip(player));

                        String aType = "";
                        abilityType.setFontScale(1);
                        if (ability.passive) {
                            aType = "Passive";
                        }
                        if (ability.talent) {
                            aType = "Talent";
                        }
                        if (ability.mastery) {
                            aType = "Mastery";
                        }
                        if (aType.equals("")) {
                            abilityType.setFontScale(0.01f);
                        }
                        abilityType.setText(aType);
                        abilityTooltip.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                        abilityTooltip.pack();
                        abilityTooltip.setWidth(180);
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
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (GameInput.ctrl && !GlobalVars.draggingAbility) {
                            dragAbility.setVisible(true);
                            dragAbility.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
                            dragAbility.setDrawable(ability.textureRegionDrawable);
                            GlobalVars.draggingAbility = true;
                            GlobalVars.dragAbility = ability;
                            GlobalVars.dragAbilityName = ability.name;
                            if (actionBar) {
                                actionBars[j].abilities[i] = null;
                                uiMain.createActionBars(true,15);
                                uiMain.createActionBars(false,15);
                            }
                        }
                        return true;
                    }
                }
        );

    }

    public void setActionPress(int slot, int bar, boolean visible) {
        actionPress.get(slot+(bar*15)).setVisible(visible);
    }

    public void addCreatureBar(final Creature creature) {
        if (creature.faction!=0) {
            Table tableC = new Table();
            this.stageBottom.addActor(tableC);
            /*Label label = new Label(creature.name+" ("+creature.level+")",skin); //TODO:
            label.setStyle(labelStyle12);*/
            Label labelHealth = new Label ("10/10",skin);
            labelHealth.setStyle(labelStyle10);
            //tableC.add(label).size(160,18);
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

            //label.setAlignment(Align.center);
            labelHealth.setAlignment(Align.center);
            tableC.align(Align.center);
            tableC.setPosition(creature.x, creature.y);
            tableC.addListener(
                    new InputListener() {
                        @Override
                        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                            updateCreatureInfoTable(creature.name,Math.round(creature.health)+" ("+Math.round((creature.health/creature.healthMax)*100)+"%)","Level: "+creature.level,"",Color.WHITE);
                        }
                        @Override
                        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                            updateCreatureInfoTable("","","","",Color.WHITE);
                        }
                        @Override
                        public boolean mouseMoved(InputEvent event, float x, float y) {
                            updateCreatureInfoTable(creature.name,Math.round(creature.health)+" ("+Math.round((creature.health/creature.healthMax)*100)+"%)","Level: "+creature.level,"",Color.WHITE);
                            return false;
                        }
                    }
            );

            creatureTables.put(creature.typeId,tableC);
            //creatureNames.put(creature.typeId,label);
            creatureHealthText.put(creature.typeId,labelHealth);
            creatureHealthBar.put(creature.typeId,healthBarC);
        }
    }

    public Table creatureInfoTable;
    public Label[] creatureInfoLabels;

    public void init2() {
        creatureInfoTable = new Table();
        creatureInfoLabels = new Label[4];
        for (int i = 0; i<creatureInfoLabels.length; i++) {
            creatureInfoLabels[i] = new Label("",skin);
        }

        creatureInfoLabels[0].setStyle(labelStyle16);
        creatureInfoLabels[1].setStyle(labelStyle14);
        creatureInfoLabels[2].setStyle(labelStyle14);
        creatureInfoLabels[3].setStyle(labelStyle14);

        creatureInfoLabels[1].setColor(Color.LIGHT_GRAY);
        creatureInfoLabels[2].setColor(Color.LIGHT_GRAY);
        creatureInfoLabels[3].setColor(Color.LIGHT_GRAY);

        creatureInfoTable.add(creatureInfoLabels[0]).colspan(2).center();
        creatureInfoTable.row();
        creatureInfoTable.add(creatureInfoLabels[1]).padRight(5);
        creatureInfoTable.add(creatureInfoLabels[2]).padLeft(5);
        creatureInfoTable.row();
        creatureInfoTable.add(creatureInfoLabels[3]).colspan(2).center();

        creatureInfoTable.top();
        creatureInfoTable.setPosition(uiMain.stage.getWidth()/2, Gdx.graphics.getHeight()-10);
        stageTop.addActor(creatureInfoTable);
    }

    public void updateCreatureInfoTable(String string0, String string1, String string2, String string3, Color color) {
        creatureInfoLabels[0].setColor(color);
        creatureInfoLabels[0].setText(string0);
        creatureInfoLabels[1].setText(string1);
        creatureInfoLabels[2].setText(string2);
        creatureInfoLabels[3].setText(string3);
    }

    public void updateCreatureBar(Creature creature) {
        if (creature.faction!=0) {
            float x = (Gdx.graphics.getWidth()/2f) + ((creature.x - cam.position.x)) /GlobalVars.camZoom;
            float y = (Gdx.graphics.getHeight()/2f) + ((creature.y - cam.position.y)) /GlobalVars.camZoom;
            //creatureNames.get(creature.typeId).setText(creature.name+" ("+creature.level+")");
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
        stageTop2.getViewport().update(width, height, true);
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
        stageTop2.act(GlobalVars.delta);
        stageTop2.draw();
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
        stageTop2.dispose();
        generator.dispose();
        font10.dispose();
        font12.dispose();
        font14.dispose();
        font16.dispose();
        font18.dispose();
        font20.dispose();
        fontTt.dispose();
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
    public void setPlayerXpBar(double xp,double xpMax) {
        int width = (int) (xp/xpMax*xpBarWidth);
        if (width<0) {
            width = 0;
        }
        xpBarA.setWidth(width);
    }

}
