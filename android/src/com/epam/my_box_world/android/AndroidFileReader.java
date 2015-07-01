package com.epam.my_box_world.android;

import java.util.ArrayList;

import boxWorld.IFileReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class AndroidFileReader implements IFileReader {

	@Override
	public ArrayList<String> read(String fileName) {
		ArrayList<String> list = new ArrayList<String>();
		FileHandle file = Gdx.files.internal(fileName);
		String text = file.readString();
		for(String s :text.split(String.format("%n")))
		{
			list.add(s);
		}
		return list;
	}

}
