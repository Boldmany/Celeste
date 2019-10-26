package main;

import character.Watermelon;
import map.Brick;
import map.Directions;

public class MapObjects {

	private static Watermelon watermelon = new Watermelon();
	private static Brick[] brick = {new Brick(new Vector(400, 200), 100, 400, Directions.UP.getValue() | Directions.DOWN.getValue() | Directions.LEFT.getValue() | Directions.RIGHT.getValue()), new Brick(new Vector(401, 100), 100, 100)};

	public static Watermelon watermelon() {
		return watermelon;
	}

	public static void setWatermelon(Watermelon watermelon) {
		MapObjects.watermelon = watermelon;
	}

	public static Brick[] brick() {
		return brick;
	}

	public static void setBrick(Brick[] brick) {
		MapObjects.brick = brick;
	}
}
