package main;

import character.*;
import map.Brick;

public class Collision {
	public static int counter = 0;
	public static void test(Watermelon character) {
		if(character.coord().y() + character.height() + character.speed().y() > 550) {
			character.setJump(false);
			character.speed().setY(0);
			character.coord().setY(550 - character.height());
			character.dash().setCanDash(true);
		}
	}

	public static void brickCollision(Watermelon character, Brick brick, int i) {
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
		
		if(character.coord().x() + character.width() >= brick.coord().x() 
				&& character.coord().x() <= brick.coord().x() + brick.width()
				&& character.coord().y() <= brick.coord().y() + brick.height()
				&& character.coord().y() + character.height() >= brick.coord().y()
				&& character.climb().grab() && character.climb().canClimb()) {
			character.climb().setClimbing(true);
			character.speed().setY(0);
			character.speed().setX(0);
		}
	}
	
	public void println(String s) {
		// native code
	}
	
	public void println(int a) {
		println(a + "");
	}
	
	public void println(Object a) {
		println(a.toString());
	}
}
