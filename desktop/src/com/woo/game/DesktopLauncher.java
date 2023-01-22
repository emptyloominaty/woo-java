package com.woo.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.woo.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.useVsync(false);
		config.setWindowedMode(1280,720);
		config.setTitle("Woo");
		//TODO:config.setBackBufferConfig(8,8,8,8,16,0,0);
		//config.setIdleFPS(30);
		new Lwjgl3Application(new Main(), config);
	}
}
