package main;

import character.CharacterAnimation;
import character.Watermelon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
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
		
		for(int i = 0; i < Map.levels().get(Map.currentLevel()).mapObjects().size(); i++) {
			if(Map.levels().get(Map.currentLevel()).mapObjects().get(i) instanceof Brick) {
				Brick brick = (Brick) Map.levels().get(Map.currentLevel()).mapObjects().get(i);
				brick.draw(Main.gc(), Color.BLACK);
				
				Collision.brickCollision(character, brick);
				
				for(int j = 0; j < brick.spikes().size(); j++) {
					brick.spikes().get(j).draw(Main.gc(), Color.RED);
					Collision.spike(character, brick.spikes().get(j));
				}
			}
			else if(Map.levels().get(Map.currentLevel()).mapObjects().get(i) instanceof Flat) {
				Flat flat = (Flat) Map.levels().get(Map.currentLevel()).mapObjects().get(i);
				flat.draw(Main.gc(), Color.BROWN);
				
				Collision.flatCollision(character, flat);
			}
			else if(Map.levels().get(Map.currentLevel()).mapObjects().get(i) instanceof Crystal) {
				Crystal crystal = (Crystal) Map.levels().get(Map.currentLevel()).mapObjects().get(i);
				if(crystal.respawn().complete()) {
					crystal.draw(Main.gc(), Color.GREEN);
					Collision.crystal(character, crystal);
				}
				else {
					crystal.respawn().update();
				}
			}
		}

		Collision.nextLevel(character, Map.levels().get(Map.currentLevel()));
		character.updateVisible(Map.levels().get(Map.currentLevel()));
		CharacterAnimation.draw(Main.gc(), character);
		Main.gc().drawImage(character.animation().dash().images().get(0), character.coord().x(), character.coord().y());
		
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
