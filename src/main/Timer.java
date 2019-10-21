package main;

public class Timer {
	private int[] frames = {0,0};
	private boolean complete = true;
	/**
	 * this is the constructor that will set the amount of frames for a delay
	 * @param dur duration of the delay
	 */
	public Timer(int duration) {
		frames()[1] = duration;
		frames()[0] = duration;
	}
	
	/**
	 * this will check for if the delay is over or not
	 * @return 
	 * @return if its done or not
	 */
	public void update() {
		// self explanatory
		if(frames()[0] < frames()[1]) {
			frames()[0] += 1;
		}
		else {
			setComplete(true);
		}
	}
	
	public void resetFrames() {
		frames()[0] = 0;
		setComplete(false);
	}

	public int[] frames() {
		return frames;
	}

	public void setFrames(int[] frames) {
		this.frames = frames;
	}
	
	public boolean complete() {
		return complete;
	}
	
	public void setComplete(boolean complete) {
		this.complete = complete;
		if(complete) {
			frames()[0] = frames()[1];
		}
	}
}
