package character;

import javafx.scene.image.Image;
import main.Collision;
import main.MapObjects;
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

	public void updateVisible() {
		final double HALF_X = 425.0;
		final double HALF_Y = 275.0;

		if(MapObjects.levels().get(0).length().x() > 850) {
			if(this.visibleCoord().x() >= HALF_X) {
				if(MapObjects.levels().get(0).coord().x() + MapObjects.levels().get(0).length().x() - this.coord().x() <= HALF_X) {
					if(MapObjects.levels().get(0).coord().x() + MapObjects.levels().get(0).length().x() - this.coord().x() + (this.moving()[0] * this.speed().x())  >= HALF_X) {
						MapObjects.levels().get(0).moveHorizontally(this.deltaCoord().x() - (MapObjects.levels().get(0).length().x() - HALF_X));
					}
					this.visibleCoord().setX(850 - (MapObjects.levels().get(0).coord().x() + MapObjects.levels().get(0).length().x() - this.coord().x()));
				}
				else {
					if(this.visibleCoord().x() > HALF_X) {
						this.coord().setX(MapObjects.levels().get(0).coord().x() + MapObjects.levels().get(0).length().x() - HALF_X);
						this.visibleCoord().setX(HALF_X);
					}
					else if(this.coord().x() - MapObjects.levels().get(0).coord().x() < HALF_X) {

						MapObjects.levels().get(0).moveHorizontally(this.deltaCoord().x() - HALF_X);
					}
					else {
						MapObjects.levels().get(0).moveHorizontally(this.deltaCoord().x() - this.coord().x());
					}
				}
			}

			if(this.coord().x() - MapObjects.levels().get(0).coord().x() <= HALF_X) {
				this.visibleCoord().setX(this.coord().x() - MapObjects.levels().get(0).coord().x());
			}
			else if(this.deltaCoord().x() - MapObjects.levels().get(0).coord().x() < HALF_X && this.coord().x() - MapObjects.levels().get(0).coord().x() > HALF_X) {
				this.coord().setX(MapObjects.levels().get(0).coord().x() + HALF_X);
				this.visibleCoord().setX(HALF_X);
			}
		}
		else {
			this.visibleCoord().setX(this.coord().x() - MapObjects.levels().get(0).coord().x());
		}
		
		if(MapObjects.levels().get(0).length().y() > 550) {
			if(this.visibleCoord().y() >= HALF_Y) {
				if(MapObjects.levels().get(0).coord().y() + MapObjects.levels().get(0).length().y() - this.coord().y() <= HALF_Y) {
					if(MapObjects.levels().get(0).coord().y() + MapObjects.levels().get(0).length().y() - this.coord().y() + this.speed().y()  >= HALF_Y) {
						MapObjects.levels().get(0).moveVertically(this.deltaCoord().y() - (MapObjects.levels().get(0).length().y() - HALF_Y));
					}
					this.visibleCoord().setY(550 + (this.coord().y() - (MapObjects.levels().get(0).coord().y() + MapObjects.levels().get(0).length().y())));
				}
				else {
					if(this.visibleCoord().y() > HALF_Y) {
						this.coord().setY(MapObjects.levels().get(0).coord().y() + MapObjects.levels().get(0).length().y() - HALF_Y);
						this.visibleCoord().setY(HALF_Y);
					}
					else if(this.coord().y() - MapObjects.levels().get(0).coord().y() < HALF_Y) {
						MapObjects.levels().get(0).moveVertically(this.deltaCoord().y() - HALF_Y);
					}
					else {
						MapObjects.levels().get(0).moveVertically(this.deltaCoord().y() - this.coord().y());
					}
				}
			}
			if(this.coord().y() - MapObjects.levels().get(0).coord().y() <= HALF_Y) {
				this.visibleCoord().setY(this.coord().y() - MapObjects.levels().get(0).coord().y());
			}
			else if(this.deltaCoord().y() - MapObjects.levels().get(0).coord().y() < HALF_Y && this.coord().y() - MapObjects.levels().get(0).coord().y() > HALF_Y) {
				this.coord().setY(MapObjects.levels().get(0).coord().y() + HALF_Y);
				this.visibleCoord().setY(HALF_Y);
			}
		}
		else {
			this.visibleCoord().setY(this.coord().y() - MapObjects.levels().get(0).coord().y());
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
