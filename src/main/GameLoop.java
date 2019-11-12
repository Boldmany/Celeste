package main;

import character.CharacterAnimation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GameLoop implements EventHandler<ActionEvent> {

	/**
	 * this will run 60 times a second
	 */
	public void handle(ActionEvent ev) {	
		Main.gc().clearRect(0, 0, 1000, 650);
		
		MapObjects.watermelon().move();
		MapObjects.watermelon().climb().setCollision(false);
		
		for(int i = 0; i < MapObjects.levels().get(MapObjects.currentLevel()).bricks().size(); i++) {
			MapObjects.levels().get(MapObjects.currentLevel()).bricks().get(i).draw(Main.gc());
			Collision.brickCollision(MapObjects.watermelon(), MapObjects.levels().get(MapObjects.currentLevel()).bricks().get(i));
		}
		
		for(int i = 0; i < MapObjects.snow().size(); i++) {
			MapObjects.snow().get(i).move();
			MapObjects.snow().get(i).draw(Main.gc());
		}
		
		MapObjects.watermelon().updateVisible(MapObjects.levels().get(MapObjects.currentLevel()));
		Collision.nextLevel(MapObjects.watermelon(), MapObjects.levels().get(MapObjects.currentLevel()));
		CharacterAnimation.draw(Main.gc(), MapObjects.watermelon());
	}
}
