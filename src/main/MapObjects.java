package main;

import character.Watermelon;

public class MapObjects {

	private static Watermelon watermelon = new Watermelon();

	public static Watermelon watermelon() {
		return watermelon;
	}

	public static void setWatermelon(Watermelon watermelon) {
		MapObjects.watermelon = watermelon;
	}
}
