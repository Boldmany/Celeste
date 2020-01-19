package character;

import main.Timer;

public class Dash {
	
	private boolean canDash = true; // can the player dash
	private Timer dashDuration = new Timer(11); // the duration of a dash
	
	public boolean canDash() {
		return canDash;
	}
	public void setCanDash(boolean canDash) {
		this.canDash = canDash;
	}

	public Timer dashDuration() {
		return dashDuration;
	}
	public void setDashDuration(Timer dashDuration) {
		this.dashDuration = dashDuration;
	}
}
