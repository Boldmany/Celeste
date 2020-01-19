package main;

import background.Background;
import character.CharacterAnimation;
import character.Watermelon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import map.*;

public class GameLoop implements EventHandler<ActionEvent> {
	private int frames = 0; // this is used to calculate the frames per second
	private long time = System.currentTimeMillis(); // this is used to calculate the frames per second
	
	/**
	 * this will run 60 times a second
	 */
	public void handle(ActionEvent ev) {
		
		frames++; // increase frames
		if(System.currentTimeMillis() - time >= 1000) { // if a second passed
			time = System.currentTimeMillis(); // reset the initial time
			System.out.println("Frames: " + frames); // print out the FPS
			frames = 0; // reset frames
		}
		
		Main.gc().clearRect(0, 0, 1000, 650); // clear the screen
		
		//for every 2 levels there will be a new background until you reached level 7 and win the game
		if(Map.currentLevel() == 6) {
			Main.setGameState(2); 
		}
		else if(Map.currentLevel() >= 4){
			Main.gc().drawImage(Background.images().images().get(2), 0, 0); // draw the title background
		}
		else if(Map.currentLevel() >= 2){
			Main.gc().drawImage(Background.images().images().get(1), 0, 0); // draw the title background
		}
		else if(Map.currentLevel() >= 0){
			Main.gc().drawImage(Background.images().images().get(0), 0, 0); // draw the title background
		}
		
		if(Main.gameState() == 1) {
			Main.gc().drawImage(Background.images().images().get(3), 0, 0); // draw the title screen
		}
		else if(Main.gameState() == 2) {
			Main.gc().drawImage(Background.images().images().get(4), 0, 0); // draw the win screen
			Map.setCurrentLevel(0); // set the current level to the first level
			Map.updateLevel(); // write it into the file
		}
		else if(Main.gameState() == 0) {
			Watermelon character = Map.watermelon(); // do this so i dont call Map.watermelon over and over
			character.move(); // this will move the character
			character.climb().setCollision(false); // reset climbing collision

			for(int i = 0; i < Map.levels().get(Map.currentLevel()).mapObjects().size(); i++) { // draw and check for collision for each type of mapObject
				if(Map.levels().get(Map.currentLevel()).mapObjects().get(i) instanceof Brick) { // for a brick
					Brick brick = (Brick) Map.levels().get(Map.currentLevel()).mapObjects().get(i);
					brick.draw(Main.gc()); // draw

					Collision.brickCollision(character, brick); //collision

					for(int j = 0; j < brick.spikes().size(); j++) { // for the bricks spikes
						brick.spikes().get(j).draw(Main.gc()); //draw
						Collision.spike(character, brick.spikes().get(j)); //collision
					}
				}
				else if(Map.levels().get(Map.currentLevel()).mapObjects().get(i) instanceof Flat) { // for a flat
					Flat flat = (Flat) Map.levels().get(Map.currentLevel()).mapObjects().get(i);
					flat.draw(Main.gc()); //draw
					Collision.flatCollision(character, flat); // collision
				}
				else if(Map.levels().get(Map.currentLevel()).mapObjects().get(i) instanceof Crystal) { // for a crystal
					Crystal crystal = (Crystal) Map.levels().get(Map.currentLevel()).mapObjects().get(i);
					if(crystal.respawn().complete()) { // draw it only when the timer is finished
						crystal.draw(Main.gc()); //draw
						Collision.crystal(character, crystal); //collision
					}
					else { // if the timer is not finished than update the timer
						crystal.respawn().update();
					}
				}
			}


			
			Collision.nextLevel(character, Map.levels().get(Map.currentLevel())); // check if the player is moving onto the next level
			character.updateVisible(Map.levels().get(Map.currentLevel())); // update the character's visible coordinates
			CharacterAnimation.draw(Main.gc(), character); // draw the animation
			if(character.climb().tired().complete()) { // check the timer for if the character has reached the timer limit
				character.climb().setEnergy(character.climb().energy() - 1); // decrease the energy by one
				character.climb().tired().resetFrames(); // start counting again
			}

			for(int i = 0; i < Map.snow().size(); i++) { // for every snow peice
				Map.snow().get(i).move(); // move the snow
 				Map.snow().get(i).draw(Main.gc()); // draw the snow 
			}
		}
	}
}
