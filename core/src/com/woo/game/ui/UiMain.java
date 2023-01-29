package com.woo.game.ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.woo.game.GlobalVars;

import java.util.ArrayList;

public class UiMain implements ApplicationListener {
    //Config
    public int healthBarWidth = 150;
    public int healthBarHeight = 60;
    public int manaBarWidth = 150;
    public int manaBarHeight = 60;
    public int secBarWidth = 120;
    public int secBarHeight = 30;
    //

    public Stage stage;
    Table table;
    Skin skin;
    public Label fpsLabel;

    //healthbar
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
    public ArrayList<Table> creatureTables;
    public ArrayList<Table> creatureTablesHealth;
    public ArrayList<Table> creatureTablesCast;
    public ArrayList<Stack> creatureStackHealth;
    public ArrayList<Stack> creatureStackCast;
    public ArrayList<Label> creatureNames;
    public ArrayList<Label> creatureCastName;
    public ArrayList<Image> creatureHealthBar;
    public ArrayList<Image> creatureCastBar;
    //TODO:Creatures Buffs+Debuffs
    //Table+Stack+Label+Image


    //TODO:Floating Combat Text
    public ArrayList<Table> floatingCombatTextTables;
    public ArrayList<Label> floatingCombatTextLabels;

    //TODO:Action Bar
    public ArrayList<Table> actionTables;
    public ArrayList<Table> actionTablesStack;
    public ArrayList<Stack> actionStacks;
    public ArrayList<Label> actionKeybinds;
    public ArrayList<Label> actionCdTimes;
    public ArrayList<Label> actionCharges;
    public ArrayList<Image> actionIcons;
    public ArrayList<Image> actionGcdTimer;
    public ArrayList<Image> actionCdTimer;
    public ArrayList<Image> actionBorders;

    //TODO:UI (Menu)



    public void create () {
        stage = new Stage();
        table = new Table();
        table.setFillParent(true);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        // Store the default libGDX font under the name "default".
        skin.add("default", new BitmapFont());

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

        fpsLabel = new Label("Fps: ",skin);
        table.add(fpsLabel).pad(5);


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
        tableHpBar.setPosition(80,40);

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
        tableMpBar.setPosition(1200,40); //TODO:
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
        tableSrBar.setPosition(640,40); //TODO:






        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton button = new TextButton("Click me!", skin);
        table.add(button);

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

        //stage.setDebugAll(true);
        //table.setDebug(true);
        //tableHpBar.setDebug(true);
        //healthBarATable.setDebug(true);

    }

    public void resize (int width, int height) {
        //??????????????????
        stage.getViewport().update(width, height, true);
    }

    public void render() {
        fpsLabel.setText("Fps: "+Math.round(GlobalVars.fps));
        stage.act(GlobalVars.delta);
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public void dispose() {
        stage.dispose();
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
