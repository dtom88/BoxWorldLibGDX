package com.epam.my_box_world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BoxWorldCell extends Actor {

	static Texture[] textures = new Texture[7];

	static Color[] colors = { Color.LIGHT_GRAY, Color.ORANGE, Color.GRAY };

	static {

		for (int i = 0; i < colors.length; i++) {
			textures[i] = createTexture(colors[i]);
		}

		textures[3] = new Texture(Gdx.files.internal("box.png"));
		textures[4] = createFinishTexture(Color.ORANGE);
		textures[5] = new Texture(Gdx.files.internal("boxFinished.png"));
		textures[6] = new Texture(Gdx.files.internal("pusher.png"));
	}

	private static Texture createTexture(Color color) {
		Texture texture = null;
		Pixmap image = new Pixmap(SIZE, SIZE, Pixmap.Format.RGBA8888);
		image.setColor(color);
		image.fill();
		texture = new Texture(image);
		return texture;
	}

	private static Texture createFinishTexture(Color color) {
		Pixmap image = new Pixmap(SIZE, SIZE, Format.RGBA8888);
		image.setColor(color);
		image.fill();
		image.setColor(Color.RED);
		image.fillCircle(22, 22, 15);
		Texture finishTexture = new Texture(image);
		return finishTexture;
	}

	protected static final int SIZE = 45;

	static final int PUSHER_INDEX = 6;

	private Texture _texture;

	private int _height;

	private int _width;

	public BoxWorldCell(int colorIndex, int height, int width) {
		_height = height;
		_width = width;
		_texture = textures[colorIndex];
	}

	@Override
	public void draw(final Batch batch, float parentAlpha) {
		float offsetX = (Gdx.graphics.getWidth() - _width
				* BoxWorldStage.MAX_SIZE) / 4.0f;
		float offsetY = (Gdx.graphics.getHeight() - _height
				* BoxWorldStage.MAX_SIZE) / 4.0f;
		batch.draw(_texture, getX() + offsetX, getY() + offsetY,
				this.getOriginX(), this.getOriginY(), _texture.getWidth(),
				_texture.getHeight(), this.getScaleX(), this.getScaleY(),
				this.getRotation(), 0, 0, _texture.getWidth(),
				_texture.getHeight(), false, false);
	}

	public void setColor(int colorIndex) {
		_texture = textures[colorIndex];
	}
}
