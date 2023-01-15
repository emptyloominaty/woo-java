package com.woo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import static com.woo.game.Main.player;

public class GameInput {
    public static void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.move(10);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.move(-10);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.rotate(player.direction-2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.rotate(player.direction+2);
        }
    }
}
