package com.epam.my_box_world;

import boxWorld.Controller;
import boxWorld.Field;
import boxWorld.IFileReader;
import boxWorld.Model;
import boxWorld.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class BoxWorldStage extends Stage {

	final static int MAX_SIZE = 13;
	protected static final int GRAY_COLOR_INDEX = 2;

	public BoxWorldStage() {
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(true);
		setViewport(new ScreenViewport());
	}

	public void init(IFileReader reader) {
		final Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
		Label restartLabel = new Label("Press 'r' to restart", uiSkin);
		restartLabel.setBounds(30, 90, 30, 30);
		Label undoLabel = new Label("Press 'z' to undo", uiSkin);
		undoLabel.setBounds(30, 50, 30, 30);
		Label nextLevelLabel = new Label("Press 'space' to go to next level",
				uiSkin);
		nextLevelLabel.setBounds(30, 10, 30, 30);
		this.addActor(restartLabel);
		this.addActor(undoLabel);
		this.addActor(nextLevelLabel);

		final Controller controller = new Controller();
		final Model model = new Model(reader);
		controller.setModel(model);
		model.setUp();
		model.addListener(controller);

		final int height = MAX_SIZE;
		final int width = MAX_SIZE;

		final View view = new View() {
			final BoxWorldCell[][] _cell = new BoxWorldCell[height][width];

			@Override
			protected void drawCell(int colorIndex, int row, int col) {
				 if (_cell[row][col] == null) {
					BoxWorldCell cell = new BoxWorldCell(colorIndex, height,
							width);
					_cell[row][col] = cell;
					BoxWorldStage.this.addActor(cell);
					cell.setBounds((col) * BoxWorldCell.SIZE, (row)
							* BoxWorldCell.SIZE, BoxWorldCell.SIZE,
							BoxWorldCell.SIZE);
				 }
				_cell[row][col].setColor(colorIndex);
			}

			@Override
			public void clear() {
				for (int i = 0; i < _cell.length; i++) {
					for (int j = 0; j < _cell[0].length; j++) {
						drawCell(GRAY_COLOR_INDEX, i, j);
					}
				}
			}

			@Override
			public void showMessage() {
				final Dialog dialog = new Dialog("Well done!", uiSkin);
				dialog.text("Press 'space' to go to the next level!");
				dialog.show(BoxWorldStage.this);

				Timer.schedule(new Timer.Task() {
					@Override
					public void run() {
						dialog.setVisible(false);
					}
				}, 3.0f);
			}

		};

		controller.setView(view);
		controller.setUp();

		Gdx.input.setInputProcessor(this);

		addListener(new InputListener() {

			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				switch (keycode) {
				case Input.Keys.LEFT:
					controller.moveLeft();
					break;
				case Input.Keys.RIGHT:
					controller.moveRight();
					break;
				case Input.Keys.UP:
					controller.moveUp();
					break;
				case Input.Keys.DOWN:
					controller.moveDown();
					break;
				case Input.Keys.SPACE:
					if (model.getLevel() >= Field.FINISH_LEVEL) {
						final Dialog dialog = new Dialog("Finish level", uiSkin);
						dialog.text("It is the last level!");
						dialog.show(BoxWorldStage.this);

						Timer.schedule(new Timer.Task() {
							@Override
							public void run() {
								dialog.setVisible(false);
							}
						}, 3.0f);

					} else {
						view.clear();
						controller.goToNextLevel();
					}
					break;
				case Input.Keys.R:
					controller.restart();
					break;
				case Input.Keys.Z:
					controller.undo();
					break;

				}
				return true;
			}

		});

	}
}
