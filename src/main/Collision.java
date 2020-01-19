package main;

import character.*;
import map.*;

public class Collision {
	
	/**
	 * Directional collision with a brick
	 * @param character
	 * @param brick
	 */
	public static void brickCollision(Watermelon character, MapObject brick) {
		if(character.coord().x() + character.width() > brick.coord().x() 
				&& character.coord().x() < brick.coord().x() + brick.width()
				&& character.coord().y() < brick.coord().y() + brick.height()
				&& character.coord().y() + character.height() > brick.coord().y()) { // if colliding with brick

			if(character.speed().y() < 0 && character.coord().y() - character.speed().y() >= brick.coord().y() + brick.height()) { //down
				character.speed().setY(0); // no longer moving in the vertical direction
				character.coord().setY(brick.coord().y() + brick.height()); // set the coordinates of the watermelon to be the bricks 
			}
			else if(character.speed().y() > 0 && character.coord().y() + character.height()  - character.speed().y() <= brick.coord().y()){ //up
				character.speed().setY(0); // no longer moving in the vertical direction
				character.coord().setY(brick.coord().y() - character.height()); // set the coordinates of the watermelon to be the bricks 
				character.setJump(false); // character can now jump
				character.dash().setCanDash(true); // character can now dash 
				character.climb().setEnergy(10); // has all the energy to climb again
			}
			else if(character.coord().x() + character.width() > brick.coord().x() + brick.width()) { // right
				character.speed().setX(0); // no longer moving in the horizontal direction
				character.coord().setX(brick.coord().x() + brick.width()); // set the coordinates of the watermelon to be the bricks 
			}
			else if(character.coord().x() < brick.coord().x()) { // left
				character.speed().setX(0); // no longer moving in the horizontal direction
				character.coord().setX(brick.coord().x() - character.width());// set the coordinates of the watermelon to be the bricks 
			}
		}
		
		final double CHANGE_HITBOX = 2;

		if(character.coord().x() + character.width() + CHANGE_HITBOX >= brick.coord().x() 
				&& character.coord().x() - CHANGE_HITBOX <= brick.coord().x() + brick.width()
				&& character.coord().y() < brick.coord().y() + brick.height()
				&& character.coord().y() + character.height() > brick.coord().y()) { // Collision with a wall in order to climb it
			
			if(character.coord().x() + character.width() - character.speed().x() <= brick.coord().x()) { // left
				character.climb().setCollision(true); // the character can climb
				if(character.climb().grab()) { // if grabbing
					character.coord().setX(brick.coord().x() - character.width()); // set the coordinates of the watermelon to be the bricks 
					character.climb().tired().update(); // get more tired over time
				}
			}
			else if(character.coord().x() + character.speed().x() >= brick.coord().x() + brick.width()) { // right
				character.climb().setCollision(true); // the character can climb
				if(character.climb().grab()) { // if grabbing
					character.coord().setX(brick.coord().x() + brick.width()); // set the coordinates of the watermelon to be the bricks 
					character.climb().tired().update(); // get more tired over time
				}
			
			}
		}
	}

	/**
	 * Collision with a crystal
	 * @param character
	 * @param crystal
	 */
	public static void crystal(Watermelon character, Crystal crystal) {
		if(character.coord().x() + character.width() > crystal.coord().x() 
				&& character.coord().x() < crystal.coord().x() + crystal.width()
				&& character.coord().y() < crystal.coord().y() + crystal.height()
				&& character.coord().y() + character.height() > crystal.coord().y()) { // if colliding with a crystal
			character.dash().setCanDash(true); // player can dash
			crystal.respawn().resetFrames(); // start respawning the crystal
			character.climb().setEnergy(10); // player has all their energy back
		}
	}

	/**
	 * Collision with a flat only for the top side
	 * @param character
	 * @param flat
	 */
	public static void flatCollision(Watermelon character, MapObject flat) {
		if(character.coord().x() + character.width() > flat.coord().x() 
				&& character.coord().x() < flat.coord().x() + flat.width()
				&& character.coord().y() < flat.coord().y() + flat.height()
				&& character.coord().y() + character.height() > flat.coord().y()
				&& character.speed().y() > 0 
				&& character.coord().y() + character.height()  - character.speed().y() <= flat.coord().y()) { // if colliding on the top part of the brick
			character.speed().setY(0); // no longer moving in the vertical direction
			character.setJump(false); // character can now jump
			character.dash().setCanDash(true); // character can now dash 
			character.climb().setEnergy(10); // has all the energy to climb again
			character.coord().setY(flat.coord().y() - character.height()); // set the coordinates of the watermelon to be the flats
		}
	}

	/**
	 * Collision with a spike
	 * @param character
	 * @param spike
	 */
	public static void spike(Watermelon character, MapObject spike) {
		if(character.coord().x() + character.width() > spike.coord().x() 
				&& character.coord().x() < spike.coord().x() + spike.width()
				&& character.coord().y() < spike.coord().y() + spike.height()
				&& character.coord().y() + character.height() > spike.coord().y()) { // if colliding with a spike
						character.death(); // kill the watermelon
		}
	}

	/**
	 * Checking to move onto the next level
	 * @param character
	 * @param level
	 */
	public static void nextLevel(Watermelon character, Level level) {
		if(character.visibleCoord().x() > 850 && level.connection()[1] != 0) { // if all the way to the right
			Map.setCurrentLevel(level.connection()[1] - 1); // this will find the next level
			level.nextLevel(character); // move onto the next level
		}
		else if(character.visibleCoord().x() + character.width() < 0 && level.connection()[3] != 0) { // if all the way to the left
			Map.setCurrentLevel(level.connection()[3] - 1); // this will find the next level
			level.nextLevel(character); // move onto the next level
		}
		else if(character.visibleCoord().y() < 0 && level.connection()[0] != 0) { // if all the way up
			Map.setCurrentLevel(level.connection()[0] - 1); // this will find the next level
			level.nextLevel(character); // move onto the next level
		}
		else if(character.visibleCoord().y() - character.height() > 550 && level.connection()[2] != 0) { // if all the way down
			Map.setCurrentLevel(level.connection()[2] - 1); // this will find the next level
			level.nextLevel(character); // move onto the next level
		}
	}
}
