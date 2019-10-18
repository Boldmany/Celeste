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

		if(key.getCode() == KeyCode.C) {
			int speed = 9;
			
			if(!MapObjects.watermelon().jump()) {
				MapObjects.watermelon().speed().setY(-speed);
				MapObjects.watermelon().setJump(true);
			}

			if(MapObjects.watermelon().climbing()) {
				if(MapObjects.watermelon().moving()[0] == 0) {
					if(MapObjects.watermelon().moving()[1] == -1) {
						MapObjects.watermelon().speed().setY(-speed);
					}
					else{
						MapObjects.watermelon().speed().setY(-speed * Math.sin(Math.toRadians(45)));
						MapObjects.watermelon().speed().setX(speed * Math.cos(Math.toRadians(45)));
					}
				}
				else {
					if(MapObjects.watermelon().moving()[1] == -1){
						MapObjects.watermelon().speed().setY(-speed * Math.sin(Math.toRadians(50)));
						MapObjects.watermelon().speed().setX(speed * Math.cos(Math.toRadians(50)));
					}
					else {
						MapObjects.watermelon().speed().setY(-speed * Math.sin(Math.toRadians(35)));
						MapObjects.watermelon().speed().setX(speed * Math.cos(Math.toRadians(35)));
					}
				}
				MapObjects.watermelon().setClimbing(false);
			}
			
		}

		if(key.getCode() == KeyCode.X) {

			int speed = 15;

			if(MapObjects.watermelon().moving()[0]  == 0) {
				if(MapObjects.watermelon().moving()[1] == -1) {
					MapObjects.watermelon().speed().setY(-speed);
				}

				else if(MapObjects.watermelon().moving()[1] == 1) {
					MapObjects.watermelon().speed().setY(speed);
				}
			}
			else if (MapObjects.watermelon().moving()[1]  == 0) {
				MapObjects.watermelon().speed().setY(0);
				MapObjects.watermelon().speed().setX(speed);
			}
			else if(MapObjects.watermelon().moving()[1]  != 0 &&  MapObjects.watermelon().moving()[0]  != 0){
				if(MapObjects.watermelon().moving()[1] == -1) {
					MapObjects.watermelon().speed().setY(-speed * Math.sin(Math.toRadians(45)));
				}

				else if(MapObjects.watermelon().moving()[1] == 1) {
					MapObjects.watermelon().speed().setY(speed * Math.sin(Math.toRadians(45)));
				}
				MapObjects.watermelon().speed().setX(speed * Math.cos(Math.toRadians(45)));
			}

			if(MapObjects.watermelon().moving()[0]  != 0 || MapObjects.watermelon().moving()[1] != 0) {
				MapObjects.watermelon().dash().resetFrames();
			}
		}

		if(key.getCode() == KeyCode.Z) {
			MapObjects.watermelon().setGrab(true);
		}
	}
}
