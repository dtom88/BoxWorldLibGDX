package com.epam.my_box_world;

import boxWorld.IFileReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {

	final MyBoxWorldGame _game;
	final IFileReader _reader;
	OrthographicCamera camera;

	public MainMenuScreen(final MyBoxWorldGame game, IFileReader reader) {
		_game = game;
		_reader = reader;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 350, 400);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		_game.batch.setProjectionMatrix(camera.combined);

		_game.batch.begin();
		_game.font.draw(_game.batch, "Welcome to Box Word!!! ", 50, 150);
		_game.font.draw(_game.batch, "Click to screen to begin!", 50, 100);
		_game.pusherSprite.draw(_game.batch);
		_game.batch.draw(_game.boxTexture, 208, 248);
		_game.batch.draw(_game.boxTexture, 253, 248);
		_game.batch.draw(_game.boxTexture, 253, 203);
		_game.batch.draw(_game.boxTexture, 253, 158);
		_game.batch.draw(_game.boxTexture, 208, 158);
		_game.batch.draw(_game.boxTexture, 163, 158);
		_game.batch.draw(_game.boxTexture, 163, 203);
		_game.batch.draw(_game.boxTexture, 163, 248);
		_game.batch.end();
		if (Gdx.input.isTouched()) {
			_game.setScreen(new BoxWorldScreen(_game, _reader));
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
