package main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class OnKeyReleased implements EventHandler<KeyEvent>{

	/**
	 * when you release a key
	 */
	public void handle(KeyEvent key) {
		
		if(key.getCode() == KeyCode.RIGHT) {
			MapObjects.watermelon().moving()[0] = 0;
		}
		if(key.getCode() == KeyCode.LEFT) {
			MapObjects.watermelon().moving()[0] = 0;
		}
		if(key.getCode() == KeyCode.UP) {
			MapObjects.watermelon().moving()[1] = 0;
		}
		if(key.getCode() == KeyCode.DOWN) {
			MapObjects.watermelon().moving()[1] = 0;
		}
	}
}
