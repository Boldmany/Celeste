package main;

import character.*;
import map.*;

public class Collision {
	public static int counter = 0;
	public static void test(Watermelon character) {
		if(character.coord().y() + character.height() + character.speed().y() > Map.levels().get(Map.currentLevel()).length().y() + Map.levels().get(Map.currentLevel()).coord().y()) {
			character.setJump(false);
			character.speed().setY(0);
			character.coord().setY(550 + ((Map.levels().get(Map.currentLevel()).length().y() + Map.levels().get(Map.currentLevel()).coord().y() - character.height()) - 550));
			character.dash().setCanDash(true);
			character.climb().tired().resetFrames();
			character.climb().setEnergy(10);
		}
	}

	public static void brickCollision(Watermelon character, MapObject brick) {
		if(character.coord().x() + character.width() > brick.coord().x() 
				&& character.coord().x() < brick.coord().x() + brick.width()
				&& character.coord().y() < brick.coord().y() + brick.height()
				&& character.coord().y() + character.height() > brick.coord().y()) {

			if(character.speed().y() < 0 && character.coord().y() - character.speed().y() >= brick.coord().y() + brick.height()) { //down
				character.speed().setY(0);
				character.coord().setY(brick.coord().y() + brick.height());
			}
			else if(character.speed().y() > 0 && character.coord().y() + character.height()  - character.speed().y() <= brick.coord().y()){ //up
				character.speed().setY(0);
				character.coord().setY(brick.coord().y() - character.height());
				character.setJump(false);
				character.dash().setCanDash(true);
				character.climb().setEnergy(10);
			}
			else if(character.coord().x() + character.width() > brick.coord().x() + brick.width()) {
				character.speed().setX(0);
				character.coord().setX(brick.coord().x() + brick.width());
			}
			else if(character.coord().x() < brick.coord().x()) {
				character.speed().setX(0);
				character.coord().setX(brick.coord().x() - character.width());
			}
		}
		double changeHitbox = 2;
		if(character.coord().x() + character.width() + changeHitbox >= brick.coord().x() 
				&& character.coord().x() - changeHitbox <= brick.coord().x() + brick.width()
				&& character.coord().y() <= brick.coord().y() + brick.height()
				&& character.coord().y() + character.height() >= brick.coord().y()) {
			if(character.coord().x() + character.width() - character.speed().x() <= brick.coord().x()) {
				character.climb().setCollision(true);
				if(character.climb().grab()) {
					character.coord().setX(brick.coord().x() - character.width());
					character.climb().tired().update();
				}
			}
			else if(character.coord().x() + character.speed().x() >= brick.coord().x() + brick.width()) {
				character.climb().setCollision(true);
				if(character.climb().grab()) {
					character.coord().setX(brick.coord().x() + brick.width());
					character.climb().tired().update();
				}
			}
		}
	}
	
	public static void crystal(Watermelon character, Crystal crystal) {
		if(character.coord().x() + character.width() > crystal.coord().x() 
				&& character.coord().x() < crystal.coord().x() + crystal.width()
				&& character.coord().y() < crystal.coord().y() + crystal.height()
				&& character.coord().y() + character.height() > crystal.coord().y()) {
			character.dash().setCanDash(true);
			crystal.respawn().resetFrames();
			character.climb().setEnergy(10);
		}
	}
	
	public static void flatCollision(Watermelon character, MapObject flat) {
		if(character.coord().x() + character.width() > flat.coord().x() 
				&& character.coord().x() < flat.coord().x() + flat.width()
				&& character.coord().y() < flat.coord().y() + flat.height()
				&& character.coord().y() + character.height() > flat.coord().y()
				&& character.speed().y() > 0 
				&& character.coord().y() + character.height()  - character.speed().y() <= flat.coord().y()) {
			
				character.speed().setY(0);
				character.coord().setY(flat.coord().y() - character.height());
				character.setJump(false);
				character.dash().setCanDash(true);
		}
	}
	
	
	public static void spike(Watermelon character, MapObject spike) {
		if(character.coord().x() + character.width() > spike.coord().x() 
				&& character.coord().x() < spike.coord().x() + spike.width()
				&& character.coord().y() < spike.coord().y() + spike.height()
				&& character.coord().y() + character.height() > spike.coord().y()) {
//			character.death(Map.levels().get(Map.currentLevel()));
		}
	}
	
	public static void nextLevel(Watermelon character, Level level) {
		if(character.visibleCoord().x() > 850 && level.connection()[1] != 0) {
			Map.setCurrentLevel(level.connection()[1] - 1);
			character.visibleCoord().setX(0);
			level.nextHorizontalLevel(character);
		}
		else if(character.visibleCoord().x() + character.width() < 0 && level.connection()[3] != 0) {
			Map.setCurrentLevel(level.connection()[3] - 1);
			character.visibleCoord().setX(850);
			level.nextHorizontalLevel(character);
		}
		else if(character.visibleCoord().y() < 0 && level.connection()[0] != 0) {
			Map.setCurrentLevel(level.connection()[0] - 1);
			character.visibleCoord().setY(505);
			level.nextVerticalLevel(character);
		}
		else if(character.visibleCoord().y() - character.height() > 550 && level.connection()[2] != 0) {
			Map.setCurrentLevel(level.connection()[0] - 1);
			character.visibleCoord().setY(0);
			level.nextVerticalLevel(character);
		}
	}
}
