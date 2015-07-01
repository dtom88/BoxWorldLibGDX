package com.epam.my_box_world.android;

import android.os.Bundle;
import boxWorld.IFileReader;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.epam.my_box_world.MyBoxWorldGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		IFileReader reader = new AndroidFileReader();
		initialize(new MyBoxWorldGame(reader), config);
	}
}
