package com.woo.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.woo.game.Main;
import com.woo.game.objects.Settings;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Settings.init();

		/*File file = new File("test.ini");
		Path path = Paths.get("test.ini");
		System.out.println(file.getAbsolutePath());*/

		System.out.println(Settings.map.get("VSync").value);

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.useVsync(false);
		config.setResizable(false);
		config.setWindowedMode(1280,720);
		config.setTitle("Woo");
		//TODO:config.setBackBufferConfig(8,8,8,8,16,0,0);
		//config.setIdleFPS(30);
		new Lwjgl3Application(new Main(), config);

	}
}
