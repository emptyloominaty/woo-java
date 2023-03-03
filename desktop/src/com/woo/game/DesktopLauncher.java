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

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(Settings.map.get("Frame Rate Limit").value);
		if (Settings.map.get("VSync").value==1) {
			config.useVsync(true);
		} else{
			config.useVsync(false);
		}
		config.setResizable(false);
		config.setWindowedMode(1600,900); //TODO:
		config.setTitle("Woo");
		if (Settings.map.get("MSAA").value>0) {
			config.setBackBufferConfig(8,8,8,8,16,0,Settings.map.get("MSAA").value);
		}
		//config.setIdleFPS(30);
		new Lwjgl3Application(new Main(), config);

	}
}
