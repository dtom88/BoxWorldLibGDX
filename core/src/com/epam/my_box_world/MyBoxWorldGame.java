package com.epam.my_box_world;

import boxWorld.IFileReader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class MyBoxWorldGame extends Game {

	private IFileReader _reader;
	SpriteBatch batch;
	BitmapFont font;
	Texture boxTexture;
	Texture pusherTexture;
	Sprite pusherSprite;
	
	public MyBoxWorldGame(IFileReader reader) {
		_reader = reader;
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		boxTexture = new Texture(Gdx.files.internal("box.png"));
		pusherTexture = new Texture(Gdx.files.internal("pusher.png"));
		pusherSprite = new Sprite(pusherTexture, pusherTexture.getWidth(), pusherTexture.getHeight());
		pusherSprite.setCenter(230, 225);
		createPusherAnimation();
		this.setScreen(new MainMenuScreen(this, _reader));
	}		
	
	public void createPusherAnimation(){
		Timer.schedule(new Task(){
			@Override
			public void run() {
				pusherSprite.flip(true, false);
			}	
		}, 0.5f,0.5f);
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		pusherTexture.dispose();
		boxTexture.dispose();
	}
}
