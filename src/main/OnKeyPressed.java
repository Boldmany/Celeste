package main;

import character.Watermelon;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class OnKeyPressed implements EventHandler<KeyEvent>{

	/**
	 * when you click a key
	 */
	public void handle(KeyEvent key) {
		Watermelon character = Map.watermelon();
		if(key.getCode() == KeyCode.RIGHT) {
			character.moving()[0] = 1;
			if(!character.climb().climbing()) {
				character.animation().direction()[0] = true;
			}
		}
		if(key.getCode() == KeyCode.LEFT) {
			character.moving()[0] = -1;
			if(!character.climb().climbing()) {
				character.animation().direction()[0] = false;
			}
		}
		if(key.getCode() == KeyCode.UP) {
			character.moving()[1] = -1;
			character.animation().direction()[1] = true;
		}
		if(key.getCode() == KeyCode.DOWN) {
			character.moving()[1] = 1;
			character.animation().direction()[1] = false;
		}

		if(key.getCode() == KeyCode.C) {
			int speed = 9;

			if(!character.jump()) {
				character.speed().setY(-speed);
				character.setJump(true);
			}

			if(character.climb().collision() && character.climb().canClimb()) {
				if(character.moving()[0] != 0) {
					if(character.moving()[1] == -1){
						character.speed().setY(-speed * Math.sin(Math.toRadians(50)));
						character.speed().setX(speed * Math.cos(Math.toRadians(50)));
					}
					else {
						character.speed().setY(-speed * Math.sin(Math.toRadians(35)));
						character.speed().setX(speed * Math.cos(Math.toRadians(35)));
					}
					character.animation().direction()[0] = !character.animation().direction()[0];
				}
				else {
					if(character.moving()[1] == -1 && character.climb().climbing()){
						character.speed().setY(-speed);
					}
//					else if(MapObjects.watermelon().moving()[1] == 0) {
//						MapObjects.watermelon().speed().setY(-speed * Math.sin(Math.toRadians(35)));
//						MapObjects.watermelon().speed().setX(speed * Math.cos(Math.toRadians(35)));
//						MapObjects.watermelon().moving()[0] = MapObjects.watermelon().animation().direction()[0] ? -1 : 1;  
//					}
				}
				if(character.climb().climbing()) {
					character.climb().setEnergy(character.climb().energy() - 1);
				}
				character.climb().setCanClimb(false);
				character.climb().setClimbing(false);
			}
		}


		if(key.getCode() == KeyCode.X) {

			int speed = 15;

			if(character.moving()[0]  == 0) { // not moving horizontally
				if(character.moving()[1] == -1) { // moving up
					character.speed().setY(-speed);
				}
				else if(character.moving()[1] == 1) { // moving down
					character.speed().setY(speed);
				}
			}
			else if (character.moving()[1]  == 0) { // not moving vertically
				character.speed().setY(0);
				character.speed().setX(speed);
			}
			else if(character.moving()[1]  != 0 &&  character.moving()[0]  != 0){ // you are moving in both axis
				if(character.moving()[1] == -1) {
					character.speed().setY(-speed * Math.sin(Math.toRadians(45)));
				}

				else if(character.moving()[1] == 1) {
					character.speed().setY(speed * Math.sin(Math.toRadians(45)));
				}
				character.speed().setX(speed * Math.cos(Math.toRadians(45)));
			}

			if(character.moving()[0]  != 0 || character.moving()[1] != 0) {
				character.dash().setCanDash(false);
				character.dash().dashDuration().resetFrames();
				if(character.climb().climbing()) {
					character.climb().setCanClimb(false);
					character.climb().setClimbing(false);
				}
			}
		}

		if(key.getCode() == KeyCode.Z) {
			character.climb().setGrab(true);
		}
		if(key.getCode() == KeyCode.H) {
			System.out.println(Map.levels().get(0).bricks().get(0).visibleCoord().x() + Map.levels().get(0).bricks().get(0).width() - character.visibleCoord().x());
		}
	}
}
