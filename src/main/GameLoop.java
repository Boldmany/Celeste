package main;

import character.CharacterAnimation;
import character.Watermelon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import map.*;

public class GameLoop implements EventHandler<ActionEvent> {

	/**
	 * this will run 60 times a second
	 */
	public void handle(ActionEvent ev) {	
		Main.gc().clearRect(0, 0, 1000, 650);
		
		
		Watermelon character = Map.watermelon();
		character.move();
		character.climb().setCollision(false);
		System.out.println(character.climb().energy());
		
		for(int i = 0; i < Map.levels().get(Map.currentLevel()).bricks().size(); i++) {
			Brick brick = Map.levels().get(Map.currentLevel()).bricks().get(i);
			brick.draw(Main.gc());
			
			Collision.brickCollision(character, brick);
			
			for(int j = 0; j < brick.spikes().size(); j++) {
				Collision.spike(character, brick.spikes().get(j));
			}
		}
		
		for(int i = 0; i < Map.levels().get(Map.currentLevel()).crystals().size(); i++) {
			Crystal crystal = Map.levels().get(Map.currentLevel()).crystals().get(i);
			if(crystal.respawn().complete()) {
				crystal.draw(Main.gc());
				Collision.crystal(character, crystal);
			}
			else {
				crystal.respawn().update();
			}
		}

		Collision.nextLevel(character, Map.levels().get(Map.currentLevel()));
		character.updateVisible(Map.levels().get(Map.currentLevel()));
		CharacterAnimation.draw(Main.gc(), character);
		
		if(character.climb().tired().complete()) {
			character.climb().setEnergy(character.climb().energy() - 1);
			character.climb().tired().resetFrames();
		}

		for(int i = 0; i < Map.snow().size(); i++) {
			Map.snow().get(i).move();
			Map.snow().get(i).draw(Main.gc());
		}
	}
}
