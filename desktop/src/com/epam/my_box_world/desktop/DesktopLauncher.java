package com.epam.my_box_world.desktop;

import boxWorld.IFileReader;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.epam.my_box_world.MyBoxWorldGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 700;
		config.height = 800;
		IFileReader reader = new DesktopFileReader();
		new LwjglApplication(new MyBoxWorldGame(reader), config);
	}
}
