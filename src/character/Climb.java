package character;

import main.Timer;

public class Climb {
	private boolean grab = false;
	private boolean climbing = false;
	private boolean canClimb = false;
	private boolean collision = false;
	private Timer energy = new Timer(10);
	
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

	public Timer energy() {
		return energy;
	}
	public void setEnergy(Timer energy) {
		this.energy = energy;
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
}
