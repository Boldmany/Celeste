package character;

import main.Timer;

public class Climb {
	private boolean grab = false;
	private boolean climbing = false;
	private boolean canClimb = false;
	private boolean collision = false;
	private int energy = 11;
	private Timer tired = new Timer(50);
	
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
