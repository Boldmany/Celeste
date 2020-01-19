package character;

import javafx.scene.canvas.GraphicsContext;
import main.Timer;

public class CharacterAnimation {
	private Images dash = new Images(); // the character's dash images
	private Images dashless = new Images(); // the character's dashless images
	private int imageIndex = 0; // the index in the animation loop
	private Timer timer = new Timer(4); // how often the spite will change
	private boolean[] direction = {true, false}; // which direction the player is facing

	public static void draw(GraphicsContext gc, Watermelon character) {
		double xCoord = 0; // this will be used to reverse the image if the character is walking in the other direction
		double width = 0; // this will be used to reverse the image if the character is walking in the other direction
		boolean canDash = character.dash().canDash(); // if the character can dash

		if(character.speed().x() * character.moving()[0] > 0) { // if moving to the right
			character.animation().direction()[0] = true;
		}
		else if(character.speed().x() * character.moving()[0] < 0){ // if moving to the left
			character.animation().direction()[0] = false;
		}
		
		if(character.animation().direction()[0]) { // if moving to the right
			xCoord = character.visibleCoord().x();
			width = character.width();
		}
		else { // if moving to the left
			xCoord = character.visibleCoord().x() + character.width();
			width = -character.width();
		}
		
		if(canDash) { // if the character can dash
			walkingAnimation(gc, character, character.animation().dash(), xCoord, width); // animations with indication that the character has a dash
		}
		else { // if the character cant dash
			walkingAnimation(gc, character, character.animation().dashless(), xCoord, width); // animations with indication that the character has no dash
		}	
	}

	public static void walkingAnimation(GraphicsContext gc, Watermelon character, Images state, double xCoord, double width) {
		if(character.moving()[0] != 0) { // if the player is moving
			if(character.animation().timer().complete()) { // if the sprite change timer is complete
				character.animation().setImageIndex((character.animation().imageIndex() + 1) % state.images().size()); // update the image that will be drawn
				character.animation().timer().resetFrames(); // reset the sprite change timer
			}
			else { // if the sprite change timer is not complete
				character.animation().timer().update();
			}
		}
		else { // if the player is not moving
			character.animation().timer().resetFrames(); // reset the sprite change timer
			character.animation().setImageIndex(0); // set the current image to be the first one
		}
		gc.drawImage(state.images().get((character.animation().imageIndex())), 0, 0, character.width(), character.height(), xCoord, character.visibleCoord().y(), width, character.height()); // this will draw the character facing the right way
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
