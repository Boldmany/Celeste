package character;

import javafx.scene.canvas.GraphicsContext;
import main.Timer;

public class CharacterAnimation {
	private Images dash = new Images();
	private Images dashless = new Images();
	private Images climbing = new Images();
	private int imageIndex = 0;
	private Timer timer = new Timer(4);
	private boolean[] direction = {true, false};

	public static void draw(GraphicsContext gc, Watermelon character) {
		double xCoord = 0;
		double width = 0;
		boolean canDash = character.dash().canDash();

		if(character.speed().x() * character.moving()[0] > 0) {
			character.animation().direction()[0] = true;
		}
		else if(character.speed().x() * character.moving()[0] < 0){
			character.animation().direction()[0] = false;
		}
		
		if(character.animation().direction()[0]) {
			xCoord = character.coord().x();
			width = character.width();
		}
		else {
			xCoord = character.coord().x() + character.width();
			width = -character.width();
		}
		
		if(canDash) {
			walkingAnimation(gc, character, character.animation().dash(), xCoord, width);
		}
		else {
			walkingAnimation(gc, character, character.animation().dashless(), xCoord, width);
		}	
	}

	public static void walkingAnimation(GraphicsContext gc, Watermelon character, Images state, double xCoord, double width) {
		if(character.moving()[0] != 0) {
			if(character.animation().timer().complete()) {
				character.animation().setImageIndex((character.animation().imageIndex() + 1) % state.images().size());
				character.animation().timer().resetFrames();
			}
			else {
				character.animation().timer().update();
			}
		}
		else {
			character.animation().timer().resetFrames();
			character.animation().setImageIndex(0);
		}
		gc.drawImage(state.images().get((character.animation().imageIndex())), 0, 0, character.width(), character.height(), xCoord, character.coord().y(), width, character.height());
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
