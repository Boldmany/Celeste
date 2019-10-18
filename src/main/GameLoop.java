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
		CharacterAnimation.draw(Main.gc(), MapObjects.watermelon());
		MapObjects.watermelon().move();
		for(int i = 0; i < MapObjects.brick().length; i++) {
			MapObjects.brick()[i].draw(Main.gc());
			Collision.brickCollision(MapObjects.watermelon(), MapObjects.brick()[i]);
		}
	}
}
