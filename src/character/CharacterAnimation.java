package character;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.Timer;

public class CharacterAnimation {
	private Images dash;
	private Images dashless;
	private Images climbing;
	private int imageIndex = 0;
	private Timer timer = new Timer(4);
	private boolean[] direction = {true, false};
	
	public static void draw(GraphicsContext gc, Watermelon character) {
		double xCoord = 0;
		double width = 0;
		
		if(character.moving()[0] != 0) {
			if(character.animation().timer().finished()) {
				if(character.)
				character.animation().setImageIndex((character.animation().imageIndex() + 1) % character.animation().images().size());
				character.animation().timer().resetFrames();
			}
		}
		else {
			character.animation().setImageIndex(0);
			character.animation().timer().resetFrames();
		}
		
		if(character.animation().direction()[0]) {
			xCoord = character.coord().x();
			width = character.width();
		}
		else { 
			xCoord = character.coord().x() + character.width();
			width = -character.width();
		}
		
		gc.drawImage(character.animation().images().get(character.animation().imageIndex()), 0, 0, character.width(), character.height(), xCoord, character.coord().y(), width, character.height());
	}
	
	public int imageIndex() {
		return imageIndex;
	}
	public void setImageIndex(int imageIndex) {
		this.imageIndex = imageIndex;
	}

	public Timer timer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public boolean[] direction() {
		return direction;
	}

	public void setDirection(boolean[] direction) {
		this.direction = direction;
	}

	public Images climbing() {
		return climbing;
	}

	public void setClimbing(Images climbing) {
		this.climbing = climbing;
	}

	public Images dash() {
		return dash;
	}

	public void setDash(Images dash) {
		this.dash = dash;
	}

	public Images dashless() {
		return dashless;
	}

	public void setDashless(Images dashless) {
		this.dashless = dashless;
	}
}
