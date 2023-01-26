package com.woo.game.ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.woo.game.GlobalVars;

public class UiMain implements ApplicationListener {
    //Config
    public int healthBarWidth = 120;
    public int healthBarHeight = 40;
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
        tableHpBar.setPosition(200,40);

        //Resource Bar

        //Secondary Resource Bar






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
}
