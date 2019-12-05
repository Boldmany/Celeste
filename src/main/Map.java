package main;

import java.util.ArrayList;

import background.Snow;
import character.Watermelon;

public class Map {

	private static Watermelon watermelon = new Watermelon();
	private static ArrayList<Level> Levels = new ArrayList<Level>();
	private static int currentLevel = 0;
	private static ArrayList<Snow> snow = new ArrayList<Snow>();
	
	
	public static void addSnow(){
		for(int i = 0; i < 40; i++){
			Snow.createSnow();
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
}
