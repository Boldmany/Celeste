package main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class OnKeyPressed implements EventHandler<KeyEvent>{

	/**
	 * when you click a key
	 */
	public void handle(KeyEvent key) {

		if(key.getCode() == KeyCode.RIGHT) {
			MapObjects.watermelon().moving()[0] = 1;
			MapObjects.watermelon().animation().direction()[0] = true;
		}
		if(key.getCode() == KeyCode.LEFT) {
			MapObjects.watermelon().moving()[0] = -1;
			MapObjects.watermelon().animation().direction()[0] = false;
		}
		if(key.getCode() == KeyCode.UP) {
			MapObjects.watermelon().moving()[1] = -1;
			MapObjects.watermelon().animation().direction()[1] = true;
		}
		if(key.getCode() == KeyCode.DOWN) {
			MapObjects.watermelon().moving()[1] = 1;
			MapObjects.watermelon().animation().direction()[1] = false;
		}
	}
}
