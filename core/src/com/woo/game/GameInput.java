package com.woo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.woo.game.objects.Keybinds;
import com.woo.game.objects.ParticleSystem;
import com.woo.game.objects.Settings;

import static com.woo.game.Main.mouseInWorld2D;
import static com.woo.game.Main.player;


public class GameInput {
    static boolean strafing = false;

    public static void setInputProcessor(Stage stage) {
        InputProcessor inputprocessor = new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                //zoom
                GlobalVars.camZoom += amountY/60;
                if (GlobalVars.camZoom<0.5) {
                    GlobalVars.camZoom = 0.5f;
                } else if (GlobalVars.camZoom>1.3) {
                    GlobalVars.camZoom = 1.3f;
                }
                return false;
            }
        };

        InputMultiplexer multiplexer = new InputMultiplexer(stage,inputprocessor);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public static void handleInput() {

        //System.out.println("x: "+Gdx.input.getX()+" y:"+Gdx.input.getY()); //<-- mouse on screen

        //-------------------------------
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            strafing = true;
        } else {
            strafing = false;
        }

        //TEST
        if (Gdx.input.isKeyPressed(Input.Keys.valueOf("T"))) {
            ParticleSystem.add("fire2",90, player.direction, player.x, player.y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.valueOf("R"))) {
            ParticleSystem.add("fire2",45, player.direction, player.x, player.y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.valueOf("Z"))) {
            ParticleSystem.add("fire2",18, player.direction, player.x, player.y);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.valueOf(Keybinds.keys.get("Move_Up")[0]))) {
            player.move(10,false,0,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.valueOf(Keybinds.keys.get("Move_Down")[0]))) {
            player.move(-7,false,0,0);
        }

        //TODO:FIX SPEED ("strafe")
        if (Settings.map.get("Character Movement").value==1 || strafing) {
            player.rotate(GlobalFunctions.getDirection(player.x,player.y,mouseInWorld2D.x,mouseInWorld2D.y));
            //WOO
            if (Gdx.input.isKeyPressed(Input.Keys.valueOf(Keybinds.keys.get("Move_Left")[0]))) {
                player.move(10,false,2,0);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.valueOf(Keybinds.keys.get("Move_Right")[0]))) {
                player.move(10,false,1,0);
            }
        } else {
            //WOW
            if (Gdx.input.isKeyPressed(Input.Keys.valueOf(Keybinds.keys.get("Move_Left")[0]))) {
                player.rotate(player.direction-2);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.valueOf(Keybinds.keys.get("Move_Right")[0]))) {
                player.rotate(player.direction+2);
            }
        }



    }
}
