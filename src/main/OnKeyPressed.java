package main;

import javax.sound.sampled.Clip;

import character.Watermelon;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class OnKeyPressed implements EventHandler<KeyEvent>{

	/**
	 * When you click a key
	 */
	public void handle(KeyEvent key) {
		
		Watermelon character = Map.watermelon(); // do this so I don't call Map.watermelon over and over
		
		if(key.getCode() == KeyCode.RIGHT) {
			character.moving()[0] = 1;
			if(!character.climb().climbing()) { // if you are not climbing
				character.animation().direction()[0] = true; // change the direction the watermelon is facing
			}
		}
		if(key.getCode() == KeyCode.LEFT) {
			character.moving()[0] = -1;
			if(!character.climb().climbing()) { // if you are not climbing
				character.animation().direction()[0] = false; // change the direction the watermelon is facing
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
			final int SPEED = 9;

			if(!character.jump()) { // if you havent jumped already
				character.speed().setY(-SPEED); // updates the y speed
				character.setJump(true); // updates the boolean
			}

			if(character.climb().collision() && character.climb().canClimb()) { // if the character is colliding and is able to climb
				if(character.moving()[0] != 0) { // if mvoing horizontally
					if(character.moving()[1] == -1){ // if moving up
						//this will calculate the speed for each vector so that watermleon jumps off the wall diagonally at 50 degrees
						character.speed().setY(-SPEED * Math.sin(Math.toRadians(50)));
						character.speed().setX(SPEED * Math.cos(Math.toRadians(50)));
						character.animation().direction()[0] = !character.animation().direction()[0]; // swap directions that the player is looking
					}
					else { // if not moving up
						//this will calculate the speed for each vector so that watermleon jumps off the wall diagonally at 35 degrees
						character.speed().setY(-SPEED * Math.sin(Math.toRadians(35)));
						character.speed().setX(SPEED * Math.cos(Math.toRadians(35)));
						character.animation().direction()[0] = !character.animation().direction()[0]; // swap directions that the player is looking
					}
				}
				else { //if not moving horizontally
					if(character.moving()[1] == -1 && character.climb().climbing()){ // if moving up and climbing
						character.speed().setY(-SPEED); // jump vertically 
					}
				}
				
				if(character.climb().climbing()) { // if the character is climbing
					character.climb().setEnergy(character.climb().energy() - 1); // decrease energy by one
				}
				character.climb().setCanClimb(false); // can't climb anymore
				character.climb().setClimbing(false); // is no longer climbing
			}
		}


		if(key.getCode() == KeyCode.X && character.dash().canDash()) { // if the character can dash and the player clikced the x key

			int speed = 15;

			if(character.moving()[0]  == 0) { // not moving horizontally
				if(character.moving()[1] == -1) { // moving up
					character.speed().setY(-speed); // dash up
				}
				else if(character.moving()[1] == 1) { // moving down
					character.speed().setY(speed); // dash down
				}
			}
			else if (character.moving()[1]  == 0) { // not moving vertically
				character.speed().setY(0); // no longer moving in the y axis
				character.speed().setX(speed); // dash horizontally
			}
			else if(character.moving()[1]  != 0 &&  character.moving()[0]  != 0){ // you are moving in both axis
				if(character.moving()[1] == -1) { // clicked the up arrow key
					//calculates so you jump at a 45 degree angle
					character.speed().setY(-speed * Math.sin(Math.toRadians(45)));
				}
				else if(character.moving()[1] == 1) { // clicked the down arrow key
					//calculates so you jump at a 45 degree angle
					character.speed().setY(speed * Math.sin(Math.toRadians(45)));
				}
				//calculates so you jump at a 45 degree angle
				character.speed().setX(speed * Math.cos(Math.toRadians(45)));
			}

			if(character.moving()[0]  != 0 || character.moving()[1] != 0) { // if the player was moving in one of the directions
				character.dash().setCanDash(false); // can no longer dash
				character.dash().dashDuration().resetFrames(); // dash timer starts counting
				if(character.climb().climbing()) { // if the character was climbing
					character.climb().setCanClimb(false); // character can't climb anymore
					character.climb().setClimbing(false); // watermelon is no longer climbing
				}
			}
		}
		

		if(key.getCode() == KeyCode.Z) {
			character.climb().setGrab(true);
		}
		
		if(key.getCode() == KeyCode.P && Main.gameState() == 1){ // this will be used to start the game
			Main.setGameState(0); // update game state
			Main.clip().start(); // start playing the music
			Main.clip().loop(Clip.LOOP_CONTINUOUSLY); // play the music INFINITLY
		}
	}
}
