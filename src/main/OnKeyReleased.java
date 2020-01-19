package main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class OnKeyReleased implements EventHandler<KeyEvent>{

	/**
	 * when you release a key
	 */
	public void handle(KeyEvent key) {
		
		if(key.getCode() == KeyCode.RIGHT && Map.watermelon().moving()[0] == 1) { // if the character was moving to the right and released the right key
			Map.watermelon().moving()[0] = 0; // no longer moving to the right
		}
		if(key.getCode() == KeyCode.LEFT && Map.watermelon().moving()[0] == -1) { // if the character was moving to the left and released the left key
			Map.watermelon().moving()[0] = 0; // no longer moving to the left
		} 
		if(key.getCode() == KeyCode.UP && Map.watermelon().moving()[1] == -1) { // if the character was "moving" up and released the up key
			Map.watermelon().moving()[1] = 0; // no longer "moving" up
		}
		if(key.getCode() == KeyCode.DOWN && Map.watermelon().moving()[1] == 1) { // if the character was "moving" down and released the down key
			Map.watermelon().moving()[1] = 0; // no longer "moving" down
		}
		
		if(key.getCode() == KeyCode.Z) {
			Map.watermelon().climb().setGrab(false); // character is not grabbing anymore
			Map.watermelon().climb().setClimbing(false); // character is not climbing anymore
			Map.watermelon().climb().setCanClimb(true); // character can climb again
		}
	}
}
