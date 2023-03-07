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
                if (GlobalVars.draggingItem) {
                    GlobalVars.draggingItem = false;
                    uiMain.dragItem.setVisible(false);
                    //TODO: drop item
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
        if (GlobalVars.draggingItem) {
            uiMain.dragItem.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
        }

        //-------------------------------
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            strafing = true;
        } else {
            strafing = false;
        }

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
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_5")[0])) && actionBars[0].abilities[5]!=null && checkMod(Keybinds.keys.get("ActionBar0_5")[1])) {
            actionBars[0].press(5);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_6")[0])) && actionBars[0].abilities[6]!=null && checkMod(Keybinds.keys.get("ActionBar0_6")[1])) {
            actionBars[0].press(6);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_7")[0])) && actionBars[0].abilities[7]!=null && checkMod(Keybinds.keys.get("ActionBar0_7")[1])) {
            actionBars[0].press(7);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_8")[0])) && actionBars[0].abilities[8]!=null && checkMod(Keybinds.keys.get("ActionBar0_8")[1])) {
            actionBars[0].press(8);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_9")[0])) && actionBars[0].abilities[9]!=null && checkMod(Keybinds.keys.get("ActionBar0_9")[1])) {
            actionBars[0].press(9);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_10")[0])) && actionBars[0].abilities[10]!=null && checkMod(Keybinds.keys.get("ActionBar0_10")[1])) {
            actionBars[0].press(10);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_11")[0])) && actionBars[0].abilities[11]!=null && checkMod(Keybinds.keys.get("ActionBar0_11")[1])) {
            actionBars[0].press(11);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_12")[0])) && actionBars[0].abilities[12]!=null && checkMod(Keybinds.keys.get("ActionBar0_12")[1])) {
            actionBars[0].press(12);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_13")[0])) && actionBars[0].abilities[13]!=null && checkMod(Keybinds.keys.get("ActionBar0_13")[1])) {
            actionBars[0].press(13);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar0_14")[0])) && actionBars[0].abilities[14]!=null && checkMod(Keybinds.keys.get("ActionBar0_14")[1])) {
            actionBars[0].press(14);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_0")[0])) && actionBars[1].abilities[0]!=null && checkMod(Keybinds.keys.get("ActionBar1_0")[1])) {
            actionBars[1].press(0);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_1")[0])) && actionBars[1].abilities[1]!=null && checkMod(Keybinds.keys.get("ActionBar1_1")[1])) {
            actionBars[1].press(1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_2")[0])) && actionBars[1].abilities[2]!=null && checkMod(Keybinds.keys.get("ActionBar1_2")[1])) {
            actionBars[1].press(2);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_3")[0])) && actionBars[1].abilities[3]!=null && checkMod(Keybinds.keys.get("ActionBar1_3")[1])) {
            actionBars[1].press(3);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_4")[0])) && actionBars[1].abilities[4]!=null && checkMod(Keybinds.keys.get("ActionBar1_4")[1])) {
            actionBars[1].press(4);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_5")[0])) && actionBars[1].abilities[5]!=null && checkMod(Keybinds.keys.get("ActionBar1_5")[1])) {
            actionBars[1].press(5);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_6")[0])) && actionBars[1].abilities[6]!=null && checkMod(Keybinds.keys.get("ActionBar1_6")[1])) {
            actionBars[1].press(6);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_7")[0])) && actionBars[1].abilities[7]!=null && checkMod(Keybinds.keys.get("ActionBar1_7")[1])) {
            actionBars[1].press(7);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_8")[0])) && actionBars[1].abilities[8]!=null && checkMod(Keybinds.keys.get("ActionBar1_8")[1])) {
            actionBars[1].press(8);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_9")[0])) && actionBars[1].abilities[9]!=null && checkMod(Keybinds.keys.get("ActionBar1_9")[1])) {
            actionBars[1].press(9);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_10")[0])) && actionBars[1].abilities[10]!=null && checkMod(Keybinds.keys.get("ActionBar1_10")[1])) {
            actionBars[1].press(10);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_11")[0])) && actionBars[1].abilities[11]!=null && checkMod(Keybinds.keys.get("ActionBar1_11")[1])) {
            actionBars[1].press(11);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_12")[0])) && actionBars[1].abilities[12]!=null && checkMod(Keybinds.keys.get("ActionBar1_12")[1])) {
            actionBars[1].press(12);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_13")[0])) && actionBars[1].abilities[13]!=null && checkMod(Keybinds.keys.get("ActionBar1_13")[1])) {
            actionBars[1].press(13);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("ActionBar1_14")[0])) && actionBars[1].abilities[14]!=null && checkMod(Keybinds.keys.get("ActionBar1_14")[1])) {
            actionBars[1].press(14);
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.valueOf(Keybinds.keys.get("Inventory")[0]))) {
            if (!GlobalVars.inventory) {
                GlobalVars.inventory = true;
                player.inventory.inventory.setVisible(true);
                player.inventory.update();
            } else {
                GlobalVars.inventory = false;
                player.inventory.inventory.setVisible(false);
            }
        }
        //
        move = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.valueOf(Keybinds.keys.get("Move_Up")[0]))) {
            player.move(10*move2,false,0,0);
            move = 0.7;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.valueOf(Keybinds.keys.get("Move_Down")[0]))) {
            player.move(-7*move2,false,0,0);
            move = 0.6;
        }
        move2 = 1;
        if (Settings.map.get("Character Movement").value>0 || strafing) {
            player.rotate(GlobalFunctions.getDirection(player.x,player.y,mouseInWorld2D.x,mouseInWorld2D.y));
            //WOO
            if (Gdx.input.isKeyPressed(Input.Keys.valueOf(Keybinds.keys.get("Move_Left")[0]))) {
                player.move(10*move,false,2,0);
                move2 = 0.7;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.valueOf(Keybinds.keys.get("Move_Right")[0]))) {
                player.move(10*move,false,1,0);
                move2 = 0.7;
            }
        } else {
            //WOW
            if (Gdx.input.isKeyPressed(Input.Keys.valueOf(Keybinds.keys.get("Move_Left")[0]))) {
                player.rotate(player.direction-2);
                move2 = 0.7;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.valueOf(Keybinds.keys.get("Move_Right")[0]))) {
                player.rotate(player.direction+2);
                move2 = 0.7;
            }
        }
    }
    static double move = 1;
    static double move2 = 1;
}
