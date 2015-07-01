package com.epam.my_box_world;

import boxWorld.IFileReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class BoxWorldScreen implements Screen {

	BoxWorldStage _stage;
	private IFileReader _reader;
	private MyBoxWorldGame _game;
	OrthographicCamera camera;


	public BoxWorldScreen(MyBoxWorldGame game, IFileReader reader) {
		_game = game;
		_reader = reader;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 350, 400);
	}

	@Override
	public void show() {
		_stage = new BoxWorldStage();
		_stage.init(_reader);
	}

	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		_game.batch.setProjectionMatrix(camera.combined);
		_stage.act(delta);
		_stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		_stage.getViewport().update(width, height, true);
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
		_stage.dispose();

	}
}
