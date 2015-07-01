package com.epam.my_box_world.client;

import boxWorld.IFileReader;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.epam.my_box_world.MyBoxWorldGame;

public class HtmlLauncher extends GwtApplication {
	
	private IFileReader reader = new HtmlReader();

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(700, 800);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new MyBoxWorldGame(reader);
        }
}