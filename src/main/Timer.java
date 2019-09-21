package main;

public class Timer {
	private int[] frames = {0,0};
	
	/**
	 * this is the constructor that will set the amount of frames for a delay
	 * @param dur duration of the delay
	 */
	public Timer(int duration) {
		frames()[1] = duration;
	}
	
	/**
	 * this will check for if the delay is over or not
	 * @return if its done or not
	 */
	public boolean finished() {
		boolean done = false;
		
		// self explanatory
		if(frames()[0] < frames()[1]) {
			frames()[0] += 1;
		}
		else {
			done = true;
		}
		return done;
	}
	
	public void resetFrames() {
		frames()[0] = 0;
	}

	public int[] frames() {
		return frames;
	}

	public void setFrames(int[] frames) {
		this.frames = frames;
	}
}
