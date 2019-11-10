package main;

import java.util.ArrayList;

import character.Watermelon;

public class MapObjects {

	private static Watermelon watermelon = new Watermelon();
	private static ArrayList<Level> levels =  new ArrayList<Level>();
	
	public static Watermelon watermelon() {
		return watermelon;
	}

	public static void setWatermelon(Watermelon watermelon) {
		MapObjects.watermelon = watermelon;
	}

	public static ArrayList<Level> levels() {
		return levels;
	}

	public static void setLevels(ArrayList<Level> levels) {
		MapObjects.levels = levels;
	}
}
