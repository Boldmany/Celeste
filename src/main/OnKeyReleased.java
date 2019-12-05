package main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class OnKeyReleased implements EventHandler<KeyEvent>{

	/**
	 * when you release a key
	 */
	public void handle(KeyEvent key) {
		
		if(key.getCode() == KeyCode.RIGHT && Map.watermelon().moving()[0] == 1) {
			Map.watermelon().moving()[0] = 0;
		}
		if(key.getCode() == KeyCode.LEFT && Map.watermelon().moving()[0] == -1) {
			Map.watermelon().moving()[0] = 0;
		}
		if(key.getCode() == KeyCode.UP && Map.watermelon().moving()[1] == -1) {
			Map.watermelon().moving()[1] = 0;
		}
		if(key.getCode() == KeyCode.DOWN && Map.watermelon().moving()[1] == 1) {
			Map.watermelon().moving()[1] = 0;
		}
		if(key.getCode() == KeyCode.C) {
		}
		if(key.getCode() == KeyCode.Z) {
			Map.watermelon().climb().setGrab(false);
			Map.watermelon().climb().setClimbing(false);
			Map.watermelon().climb().setCanClimb(true);
		}
	}
}
