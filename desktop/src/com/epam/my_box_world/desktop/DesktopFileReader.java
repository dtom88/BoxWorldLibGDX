package com.epam.my_box_world.desktop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import boxWorld.IFileReader;

public class DesktopFileReader implements IFileReader {
	
	public ArrayList<String> read(String fileName) {
		ArrayList<String> list = new ArrayList<String>(); 
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			try {
				String s;
				while ((s = in.readLine()) != null) {
					list.add(s);
				}
				in.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		return list;
	}

}
