package character;

import javafx.scene.image.Image;
import main.Level;
import main.Map;
import main.Vector;

public class Watermelon extends Character{

	private final double MAX_SPEED = 4; // the max speed at which the watermelon can travel
	private boolean jump = false; // if the watermelon has jumped
	private Dash dash = new Dash(); // watermelon dash
	private Climb climb = new Climb(); // watermelon climb
	private Vector deltaCoord; // the previous coord

	public Watermelon() {

		//set all the images
		animation().dash().images().add(new Image("file:resources/Dash/WatsonSprite1.png"));
		animation().dash().images().add(new Image("file:resources/Dash/WatsonSprite2.png"));
		animation().dash().images().add(new Image("file:resources/Dash/WatsonSprite1.png")); 
		animation().dash().images().add(new Image("file:resources/Dash/WatsonSprite3.png"));
		animation().dash().images().add(new Image("file:resources/Dash/WatsonSprite4.png"));
		animation().dash().images().add(new Image("file:resources/Dash/WatsonSprite3.png"));

		animation().dashless().images().add(new Image("file:resources/Dashless/WatsonSprite1.png"));
		animation().dashless().images().add(new Image("file:resources/Dashless/WatsonSprite2.png"));
		animation().dashless().images().add(new Image("file:resources/Dashless/WatsonSprite1.png")); 
		animation().dashless().images().add(new Image("file:resources/Dashless/WatsonSprite3.png"));
		animation().dashless().images().add(new Image("file:resources/Dashless/WatsonSprite4.png"));
		animation().dashless().images().add(new Image("file:resources/Dashless/WatsonSprite3.png"));

		setWidth(animation().dashless().images().get(animation().imageIndex()).getWidth()); // set the width
		setHeight(animation().dashless().images().get(animation().imageIndex()).getHeight()); // set the height
	}

	/**
	 * If the character has been killed, then start the level from the beginning
	 */
	public void death() {
		Level level = Map.levels().get(Map.currentLevel());
		// reset coordinates
		this.coord().setX(level.spawnPoint().x()); 
		this.coord().setY(level.spawnPoint().y());
		// reset delta coordinates
		this.deltaCoord().setX(this.coord().x());
		this.deltaCoord().setY(this.coord().y());
		// reset speed
		this.speed().setX(0);
		this.speed().setY(0);
		// reset dash
		this.dash().dashDuration().setComplete(true);
		this.dash().setCanDash(true);
		// reset climbing
		this.climb().setEnergy(10);
		this.climb().tired().resetFrames();
		this.climb().setClimbing(false);
		this.climb().setCollision(false);
		// reset level
		level.nextLevel(Map.watermelon());
	}
	
	/**
	 * Updates the character's coordinates
	 */
	public void move() {
		if(this.finishedDashing() && !this.climb().climbing()) { // if the player is not in dash motion
			if(!this.climb().canClimb()) { // if the player cant climb
				if(this.speed().y() >= 0) { // if the speed is going down
					this.climb().setCanClimb(true); // the player can climb
				}
			}
			if(this.moving()[0] != 0) { // if the character is moving
				this.speed().setX(accelerate(this.speed().x())); // accelerate the character
			}
			else { // if the character is not supposed to be moving
				this.speed().setX(decelerate(this.speed().x())); // decelerate the character
			}
			this.speed().setY(gravity(this.speed().y())); // change the y speed so that it feels like there is gravity
		}
		if(this.climb().grab() && this.climb().collision() && this.climb().canClimb()) { // if the character is albe to climb, is grabbing and is colliding with a wall
			if(this.climb().energy() > 0) { // if the character has energy
				this.climb().setClimbing(true); // climbing is true
				this.speed().setY(0); // no longer moving
				this.speed().setX(0); // no longer moving
			}
			else { // if character doens't have enough energy
				this.climb().setClimbing(false);  // set climbing to false
			}
		}
		
		// update the coordinates with the respective speeds
		this.coord().setX(this.coord().x() + this.speed().x() * this.moving()[0]);
		this.coord().setY(this.coord().y() + this.speed().y());
	}

	/**
	 * This will update the visible coordinate so that the character moves across the screen and will move the eniter level to make it feel as though the character is moving
	 * @param level
	 */
	public void updateVisible(Level level) {
		final double HALF_X = 425.0; // this is half of the horizontal screen size 
		final double HALF_Y = 275.0; // this is half of the vertical screen size
		
		if(level.length().x() > HALF_X * 2) { // if the level is long enough that there needs to be side scrolling
			if(this.coord().x() - level.coord().x() >= HALF_X) { // if the character is more than half way into the map
				if(this.coord().x() - level.coord().x() > level.length().x() - HALF_X) {  // if the character is approaching the end of the level
					if(this.deltaCoord().x() - level.coord().x() <= level.length().x() - HALF_X) { // if the character is exiting the side scroller from the right side
						level.moveHorizontally((this.deltaCoord().x() - level.coord().x()) - (level.length().x() - HALF_X)); // move the entire level to the left by the gap between the delta coordinate and the end of the side scroller 
					}
					this.visibleCoord().setX(HALF_X * 2 + (this.coord().x() - (level.coord().x() + level.length().x()))); // this will update the visible coordinate so that the character is moving once again
				}
				else { // if the character is in the side scroller
					if(this.deltaCoord().x() - level.coord().x() < HALF_X) { // if the character is entering the side scroller from the right
						this.visibleCoord().setX(HALF_X); // the character will remain in the center of the map 
						level.moveHorizontally(HALF_X - (this.coord().x() - level.coord().x())); // the entire level will shift left by the gap between the coordinate and the beginning of the side scroller
					}
					else if(this.deltaCoord().x() - level.coord().x() >= level.length().x() - HALF_X && this.coord().x() - level.coord().x() <= level.length().x() - HALF_X) { // if the player is entering the side scroller form the left side
						this.visibleCoord().setX(HALF_X); // the character will remain in the center of the map 
						level.moveHorizontally((level.length().x() - HALF_X) - (this.coord().x() - level.coord().x())); // the entire level will shift right by the gap between the coordinate and the end of the side scroller
					}
					else { // if the character is not entering or leaving the side scroller
						level.moveHorizontally(this.deltaCoord().x() - this.coord().x()); // move the level according to the difference between the current coordinates and the coordinates from one frame ago
					}
				}
			}
			else if(this.deltaCoord().x() - level.coord().x() > HALF_X) { // if the character is exiting the side scroller from the left side
				level.moveHorizontally((this.deltaCoord().x() - level.coord().x()) - HALF_X); // move the level right by the gap between the delta coordinates and the beginning of the side scroller
			}
			if(this.coord().x() - level.coord().x() < HALF_X) { // if the character is near the left side of the map
				this.visibleCoord().setX(this.coord().x() - level.coord().x()); // this will update the visible coordinate so that the character is moving once again
			}
		}
		else { // if there no need for a side scoller
			this.visibleCoord().setX(this.coord().x() - level.coord().x()); // set the visible coordinates to be the actual coordinates minus the level coordinates
		}
		
		if(level.length().y() > HALF_Y * 2) { // if the level is long enough that there needs to be side scrolling
			if(this.coord().y() - level.coord().y() >= HALF_Y) { // if the character is more than half way into the map
				if(this.coord().y() - level.coord().y() > level.length().y() - HALF_Y) { // if the character is approaching the bottom of the level
					if(this.deltaCoord().y() - level.coord().y() <= level.length().y() - HALF_Y) { // if the character is exiting the side scroller by going down
						level.moveVertically((this.deltaCoord().y() - level.coord().y()) - (level.length().y() - HALF_Y)); // move the entire level to the up by the gap between the delta coordinate and the end of the side scroller 
					}
					this.visibleCoord().setY(HALF_Y * 2 + (this.coord().y() - (level.coord().y() + level.length().y()))); // this will update the visible coordinate so that the character is moving once again
				}
				else { // if the character is in the side scroller
					if(this.deltaCoord().y() - level.coord().y() < HALF_Y) { // if the character is entering the side scroller from above
						this.visibleCoord().setY(HALF_Y); // the character will remain in the center of the map 
						level.moveVertically(HALF_Y - (this.coord().y() - level.coord().y()));  // the entire level will up by the gap between the coordinate and the beginning of the side scroller
					}
					else if(this.deltaCoord().y() - level.coord().y() >= level.length().y() - HALF_Y && this.coord().y() - level.coord().y() <= level.length().y() - HALF_Y) { // if the character is entering the side scroller from below
						this.visibleCoord().setY(HALF_Y); // the character will remain in the center of the map 
						level.moveVertically((level.length().y() - HALF_Y) - (this.coord().y() - level.coord().y())); // the entire level will down by the gap between the coordinate and the beginning of the side scroller
					}
					else { // if the character is not entering or leaving the side scroller
						level.moveVertically(this.deltaCoord().y() - this.coord().y()); // move the level according to the difference between the current coordinates and the coordinates from one frame ago
					}
				}
			}
			else if(this.deltaCoord().y() - level.coord().y() >= HALF_Y) { // if the character is exiting the side scroller by going up
				level.moveVertically((this.deltaCoord().y() - level.coord().y()) - HALF_Y); // move the level down by the gap between the delta coordinates and the beginning of the side scroller
			}
			if(this.coord().y() - level.coord().y() <= HALF_Y) { // if the character is near the top of the map
				this.visibleCoord().setY(this.coord().y() - level.coord().y()); // this will update the visible coordinate so that the character is moving once again
			}
		}
		else { // if there no need for a side scoller
			this.visibleCoord().setY(this.coord().y() - level.coord().y()); // set the visible coordinates to be the actual coordinates minus the level coordinates
		}
		
		//update the delta coordinates
		this.deltaCoord().setX(this.coord().x());
		this.deltaCoord().setY(this.coord().y());
	}

	/**
	 * Accelerating the character
	 * @param speed
	 * @return
	 */
	public double accelerate(double speed) {
		speed += MAX_SPEED / 6; // speed up

		if(speed > MAX_SPEED) { // if you are above the max speed
			speed = MAX_SPEED; // set speed to max speed
		}

		return speed;
	}

	/**
	 * Decelerating the character
	 * @param speed
	 * @return
	 */
	public double decelerate(double speed) {
		speed -= MAX_SPEED / 3; // speed down

		if(speed < 0) { // if speed is less than 0
			speed = 0; // set speed to 0
		}

		return speed;
	}

	/**
	 * Accelerates the player downwards
	 * @param speed
	 * @return
	 */
	public double gravity(double speed) {
		final int TERMINAL_SPEED = 9; // max falling speed

		if(speed > TERMINAL_SPEED) { // if speed is greater than the max falling speed
			speed = TERMINAL_SPEED; // set speed to max falling speed
		}
		else { // if speed is not greater than terminal speed
			speed += 0.5; // increase speed
		}

		return speed;
	}

	/**
	 * Checks for if the character has finished dashing
	 * @return
	 */
	public boolean finishedDashing() {

		if(!this.dash().dashDuration().complete()) { // if not completed dash
			this.dash().dashDuration().update(); // update the timer
			if(this.dash().dashDuration().complete() && this.speed().y() < 0) { // if it completes and the y speed is less than 0
				this.speed().setY(-3); // set y speed to be -3
			}
		}
		return this.dash().dashDuration().complete();
	}

	public boolean jump() {
		return jump;
	}

	public double MAX_SPEED() {
		return MAX_SPEED;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public Climb climb() {
		return climb;
	}

	public void setCash(Climb climb) {
		this.climb = climb;
	}

	public Dash dash() {
		return dash;
	}

	public void setDash(Dash dash) {
		this.dash = dash;
	}

	public void setClimb(Climb climb) {
		this.climb = climb;
	}

	public Vector deltaCoord() {
		return deltaCoord;
	}

	public void setDeltaCoord(Vector deltaCoord) {
		this.deltaCoord = deltaCoord;
	}
}
