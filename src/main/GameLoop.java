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
		
		for(int i = 0; i < MapObjects.levels().get(0).bricks().size(); i++) {
			MapObjects.levels().get(0).bricks().get(i).draw(Main.gc());
			Collision.brickCollision(MapObjects.watermelon(), MapObjects.levels().get(0).bricks().get(i), i);
		}

		MapObjects.watermelon().updateVisible();
		CharacterAnimation.draw(Main.gc(), MapObjects.watermelon());
	}
}
