package main;

import character.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GameLoop implements EventHandler<ActionEvent> {

	/**
	 * this will run 60 times a second
	 */
	public void handle(ActionEvent ev) {	
		Main.gc().clearRect(0, 0, 1000, 650);
		Animation.draw(Main.gc(), MapObjects.watermelon());
	}
}
