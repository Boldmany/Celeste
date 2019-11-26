package character;

import javafx.scene.image.Image;
import main.Collision;
import main.Level;
import main.Vector;

public class Watermelon extends Character{

	private final double MAX_SPEED = 4;
	private boolean jump = false;
	private Dash dash = new Dash();
	private Climb climb = new Climb();
	private Vector deltaCoord = new Vector(0, 0);

	public Watermelon() {
		coord().setX(0);
		coord().setY(0);
		visibleCoord().setX(100);
		visibleCoord().setY(200);
		deltaCoord().setX(100);
		deltaCoord().setY(200);

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

		setWidth(animation().dashless().images().get(animation().imageIndex()).getWidth());
		setHeight(animation().dashless().images().get(animation().imageIndex()).getHeight());
	}

	public void move() {
		if(this.finishedDashing() && !this.climb().climbing()) {
			if(!this.climb().canClimb()) {
				if(this.speed().y() >= 0) {
					this.climb().setCanClimb(true);
				}
			}
			if(this.moving()[0] != 0) {
				this.speed().setX(accelerate(this.speed().x()));
			}
			else {
				this.speed().setX(decelerate(this.speed().x()));
			}
			this.speed().setY(gravity(this.speed().y()));
		}
		if(this.climb().grab() && this.climb().collision() && this.climb().canClimb()) {
			this.climb().setClimbing(true);
			this.speed().setY(0);
			this.speed().setX(0);
		}

		Collision.test(this);
		this.coord().setX(this.coord().x() + this.speed().x() * this.moving()[0]);
		this.coord().setY(this.coord().y() + this.speed().y());
	}

	public void updateVisible(Level level) {
		final double HALF_X = 425.0;
		final double HALF_Y = 275.0;

//		if(level.length().x() > 850) {
//			if(this.visibleCoord().x() >= HALF_X) {
//				if(level.coord().x() + level.length().x() - this.coord().x() <= HALF_X) {
//					if(level.coord().x() + level.length().x() - this.coord().x() + (this.moving()[0] * this.speed().x())  >= HALF_X) {
//						level.moveHorizontally((this.deltaCoord().x() - level.coord().x()) - ((level.length().x()) - HALF_X));
//					}
//					this.visibleCoord().setX(850 - (level.coord().x() + level.length().x() - this.coord().x()));
//				}
//				else {
//					if(this.visibleCoord().x() > HALF_X) {
//						this.coord().setX(level.coord().x() + level.length().x() - HALF_X);
//						this.visibleCoord().setX(HALF_X);
//					}
//					else if(this.coord().x() - level.coord().x() < HALF_X) {
////						System.out.println("???????????????????????????????????????????????????????????????????????????????????");
//						level.moveHorizontally((this.deltaCoord().x() - level.coord().x()) - HALF_X);
//					}
//					else {
//						level.moveHorizontally(this.deltaCoord().x() - this.coord().x());
//					}
//				}
//			}
//
//			if(this.coord().x() - level.coord().x() <= HALF_X) {
//				this.visibleCoord().setX(this.coord().x() - level.coord().x());
//			}
//			else if(this.deltaCoord().x() - level.coord().x() < HALF_X && this.coord().x() - level.coord().x() > HALF_X) {
//				this.coord().setX(level.coord().x() + HALF_X);
//				this.visibleCoord().setX(HALF_X);
//			}
//		}
//		else {
//			this.visibleCoord().setX(this.coord().x() - level.coord().x());
//		}
		
		if(level.length().x() > HALF_X * 2) {
			if(this.coord().x() - level.coord().x() >= HALF_X) {
				if(this.coord().x() - level.coord().x() > level.length().x() - HALF_X) { // exiting right
					if(this.deltaCoord().x() - level.coord().x() < level.length().x() - HALF_X) {
						System.out.println(level.bricks().get(0).coord().x() - level.bricks().get(0).visibleCoord().x());
						level.moveHorizontally((this.deltaCoord().x() - level.coord().x()) - ((level.length().x() ) - HALF_X));
						System.out.println(level.bricks().get(0).coord().x() - level.bricks().get(0).visibleCoord().x());
//						System.out.println("yes");
					}
					this.visibleCoord().setX(HALF_X * 2 + (this.coord().x() - (level.coord().x() + level.length().x())));
				}
				else { // in the point of no return
					if(this.deltaCoord().x() - level.coord().x() < HALF_X) { // entering right
						this.visibleCoord().setX(HALF_X);
						level.moveHorizontally(HALF_X - (this.coord().x() - level.coord().x()));
					}
					else if(this.deltaCoord().x() - level.coord().x() > level.length().x() - HALF_X && this.coord().x() - level.coord().x() < level.length().x() - HALF_X) { // entering left
						this.visibleCoord().setX(HALF_X);
						level.moveHorizontally((this.deltaCoord().x() - level.coord().x()) - (level.length().x() - HALF_X));
					}
					else {
						level.moveHorizontally(this.deltaCoord().x() - this.coord().x());
					}
				}
			}
			else if(this.deltaCoord().x() - level.coord().x() > HALF_X) { // exiting left
				level.moveHorizontally((this.deltaCoord().x() - level.coord().x()) - HALF_X);
			}
			if(this.coord().x() - level.coord().x() <= HALF_X) { // you somehow made it past the point of no return
				this.visibleCoord().setX(this.coord().x() - level.coord().x());
			}
		}
		else {
			this.visibleCoord().setX(this.coord().x() - level.coord().x());
		}
		
		
		if(level.length().y() > HALF_Y * 2) {
			if(this.coord().y() - level.coord().y() >= HALF_Y) {
				if(level.coord().y() + level.length().y() - this.coord().y() <= HALF_Y) { // exiting down
					if(level.coord().y() + level.length().y() - this.coord().y() + this.speed().y()  >= HALF_Y) {
						level.moveVertically(this.deltaCoord().y() - ((level.length().y() + level.coord().y()) - HALF_Y));
					}
					this.visibleCoord().setY(HALF_Y * 2 + (this.coord().y() - (level.coord().y() + level.length().y())));
				}
				else { // in the point of no return
					if(this.deltaCoord().y() - level.coord().y() < HALF_Y && this.coord().y() - level.coord().y() > HALF_Y) { // entering down
						this.coord().setY(level.coord().y() + HALF_Y);
						this.visibleCoord().setY(HALF_Y);
					}
					else if(this.deltaCoord().y() - level.coord().y() > level.length().y() - HALF_Y && this.coord().y() - level.coord().y() < level.length().y() - HALF_Y) { // entering up
						this.coord().setY(level.coord().y() + level.length().y() - HALF_Y);
						this.visibleCoord().setY(HALF_Y);
					}
					else {
						level.moveVertically(this.deltaCoord().y() - this.coord().y());
					}
				}
			}
			else if(this.deltaCoord().y() - level.coord().y() > HALF_Y) { // exiting up
				level.moveVertically(this.deltaCoord().y() - (level.coord().y() + HALF_Y));
			}
			if(this.coord().y() - level.coord().y() <= HALF_Y) { // you some how made it past the point of no return
				this.visibleCoord().setY(this.coord().y() - level.coord().y());
			}
			else if(this.deltaCoord().y() - level.coord().y() < HALF_Y && this.coord().y() - level.coord().y() > HALF_Y) {
				this.coord().setY(level.coord().y() + HALF_Y);
				this.visibleCoord().setY(HALF_Y);
			}
		}
		else {
			this.visibleCoord().setY(this.coord().y() - level.coord().y());
		}
		
		this.deltaCoord().setX(this.coord().x());
		this.deltaCoord().setY(this.coord().y());
	}

	public double accelerate(double speed) {
		speed += MAX_SPEED / 6;

		if(speed > MAX_SPEED) {
			speed = MAX_SPEED;
		}

		return speed;
	}

	public double decelerate(double speed) {
		speed -= MAX_SPEED / 3;

		if(speed < 0) {
			speed = 0;
		}

		return speed;
	}

	public double gravity(double speed) {
		int terminalSpeed = 9;

		if(speed > terminalSpeed) {
			speed = terminalSpeed;
		}
		else {
			speed += 0.5;
		}

		return speed;
	}

	public boolean finishedDashing() {

		// self explanatory
		if(!this.dash().dashDuration().complete()) {
			this.dash().dashDuration().update();
			if(this.dash().dashDuration().complete() && this.speed().y() < 0) {
				this.speed().setY(-3);
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
