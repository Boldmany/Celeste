package main;

import java.io.FileWriter;
import java.util.ArrayList;

import background.Snow;
import character.Watermelon;

public class Map {

	private static Watermelon watermelon = new Watermelon(); // this will be the watermelon
	private static ArrayList<Level> Levels = new ArrayList<Level>(); // an array list of all the levels
	private static int currentLevel; // what level you are currently on
	private static ArrayList<Snow> snow = new ArrayList<Snow>(); // an array list to store all the snow
	private static FileWriter fileWriter; // this will write the current level to the file
	
	/**
	 * Adds the snow to the array list
	 */
	public static void addSnow(){
		for(int i = 0; i < 40; i++){
			Snow.createSnow();
		}
	}

	/**
	 * Each time you go to a new level write it into the file
	 */
	public static void updateLevel() {
		try {
			Map.setFileWriter(new FileWriter("resources/currentLevel.txt")); // clears the file
			Map.fileWriter().write(Map.currentLevel() + ""); // write the current level into it
			Map.fileWriter().flush(); // actually writes it into the file
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Watermelon watermelon() {
		return watermelon;
	}

	public static void setWatermelon(Watermelon watermelon) {
		Map.watermelon = watermelon;
	}

	public static ArrayList<Snow> snow() {
		return snow;
	}

	public static void setSnow(ArrayList<Snow> snow) {
		Map.snow = snow;
	}


	public static ArrayList<Level> levels() {
		return Levels;
	}


	public static void setLevels(ArrayList<Level> levels) {
		Levels = levels;
	}


	public static int currentLevel() {
		return currentLevel;
	}


	public static void setCurrentLevel(int currentLevel) {
		Map.currentLevel = currentLevel;
	}
	public static FileWriter fileWriter() {
		return fileWriter;
	}
	public static void setFileWriter(FileWriter fileWriter) {
		Map.fileWriter = fileWriter;
	}

}
