package com.woo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.woo.game.objects.Keybinds;
import com.woo.game.objects.ParticleSystem;
import com.woo.game.objects.Settings;
import com.woo.game.objects.gameobjects.GOControl;
import com.woo.game.objects.gameobjects.Spell;

import static com.woo.game.Main.*;


public class GameInput {
    static boolean strafing = false;
    public static boolean ctrl = false;
    public static boolean shift = false;

    public static void setInputProcessor(Stage stage) {
        InputProcessor inputprocessor = new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode==(Input.Keys.CONTROL_LEFT)) {
                    ctrl = true;
                } else if (keycode==(Input.Keys.SHIFT_LEFT)) {
                    shift = true;
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                if (keycode==(Input.Keys.CONTROL_LEFT)) {
                    ctrl = false;
                } else if (keycode==(Input.Keys.SHIFT_LEFT)) {
                    shift = false;
                }
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (Settings.map.get("Character Movement").value==2) {
                    player.moveToPoint = true;
                    player.moveToX = mouseInWorld2D.x;
                    player.moveToY = mouseInWorld2D.y;
                }
                if (GlobalVars.draggingAbility) {
                    GlobalVars.draggingAbility = false;
                    uiMain.dragAbility.setVisible(false);
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return true;
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

        InputMultiplexer multiplexer = new InputMultiplexer(uiMain.stageTop,stage,inputprocessor); //TODO:,uiMain.stageBottom
        Gdx.input.setInputProcessor(multiplexer);
    }

    public static boolean checkMod(String modKey) {
        if (modKey.equals("CONTROL_LEFT")) {
            if (ctrl) {
                return true;
            }
            return false;
        } else if (modKey.equals("SHIFT_LEFT")) {
            if (shift) {
                return true;
            }
            return false;
        }
        return true;
    }

    public static void handleInput() {
        //System.out.println("x: "+Gdx.input.getX()+" y:"+Gdx.input.getY()); //<-- mouse on screen

        if (GlobalVars.draggingAbility) {
            uiMain.dragAbility.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
        }

        //-------------------------------
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            strafing = true;
        } else {
            strafing = false;
        }

        //TEST
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_0")[0])) && actionBars[0].abilities[0]!=null && checkMod(Keybinds.keys.get("ActionBar0_0")[1])) {
            actionBars[0].press(0);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_1")[0])) && actionBars[0].abilities[1]!=null && checkMod(Keybinds.keys.get("ActionBar0_1")[1])) {
            actionBars[0].press(1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_2")[0])) && actionBars[0].abilities[2]!=null && checkMod(Keybinds.keys.get("ActionBar0_2")[1])) {
            actionBars[0].press(2);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_3")[0])) && actionBars[0].abilities[3]!=null && checkMod(Keybinds.keys.get("ActionBar0_3")[1])) {
            actionBars[0].press(3);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_4")[0])) && actionBars[0].abilities[4]!=null && checkMod(Keybinds.keys.get("ActionBar0_4")[1])) {
            actionBars[0].press(4);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_4")[0])) && actionBars[1].abilities[4]!=null && checkMod(Keybinds.keys.get("ActionBar1_4")[1])) {
            actionBars[1].press(4);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("Character Stats")[0]))) {
            if (!GlobalVars.characterStats) {
                GlobalVars.characterStats = true;
                uiMain.characterStats.setVisible(true);
                uiMain.updateCharacterStats();
            } else {
                GlobalVars.characterStats = false;
                uiMain.characterStats.setVisible(false);
            }
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("SpellBook")[0]))) {
            if (!GlobalVars.spellbook) {
                GlobalVars.spellbook = true;
                uiMain.spellbook.setVisible(true);
                uiMain.updateSpellbook();
            } else {
                GlobalVars.spellbook = false;
                uiMain.spellbook.setVisible(false);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("Settings")[0]))) {
            if (!GlobalVars.settings) {
                GlobalVars.settings = true;
                uiMain.settings.setVisible(true);
                uiMain.updateSettings();
            } else {
                GlobalVars.settings = false;
                uiMain.settings.setVisible(false);
            }
        }
        //



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
        if (Settings.map.get("Character Movement").value>0 || strafing) {
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
