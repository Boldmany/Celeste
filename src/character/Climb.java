package character;

import main.Timer;

public class Climb {
	private boolean grab = false; // if the player is grabbing / wants to climb
	private boolean climbing = false; // if the character is climbing
	private boolean canClimb = false; // if the character can climb
	private boolean collision = false; // if the character is colliding with can be climbed
	private int energy = 11; // how much climbing energy the character has
	private Timer tired = new Timer(50); // this will be used to decrease the players energy based on how long the character has been holding to a wall
	
	public boolean grab() {
		return grab;
	}
	public void setGrab(boolean grab) {
		this.grab = grab;
	}
	public boolean climbing() {
		return climbing;
	}
	public void setClimbing(boolean climbing) {
		this.climbing = climbing;
	}

	public boolean canClimb() {
		return canClimb;
	}
	public void setCanClimb(boolean canClimb) {
		this.canClimb = canClimb;
	}
	public boolean collision() {
		return collision;
	}
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	public int energy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public Timer tired() {
		return tired;
	}
	public void setTired(Timer tired) {
		this.tired = tired;
	}
}
