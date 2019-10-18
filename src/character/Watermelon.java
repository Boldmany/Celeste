package character;

import javafx.scene.image.Image;
import main.Collision;
import main.Timer;

public class Watermelon extends Character{

	private final double MAX_SPEED = 4;
	private boolean jump = false;
	private Timer dash = new Timer(10);
	private boolean grab = false;
	private boolean climbing = false;

	public Watermelon() {
		coord().setX(100);
		coord().setY(200);
		animation().images().add(new Image("file:resources/Dash/WatsonSprite1.png"));
		animation().images().add(new Image("file:resources/Dash/WatsonSprite2.png"));
		animation().images().add(new Image("file:resources/Dash/WatsonSprite1.png")); 
		animation().images().add(new Image("file:resources/Dash/WatsonSprite3.png"));
		animation().images().add(new Image("file:resources/Dash/WatsonSprite4.png"));
		animation().images().add(new Image("file:resources/Dash/WatsonSprite3.png"));
		
		animation().images().add(new Image("file:resources/Dashless/WatsonSprite1.png"));
		animation().images().add(new Image("file:resources/Dashless/WatsonSprite2.png"));
		animation().images().add(new Image("file:resources/Dashless/WatsonSprite1.png")); 
		animation().images().add(new Image("file:resources/Dashless/WatsonSprite3.png"));
		animation().images().add(new Image("file:resources/Dashless/WatsonSprite4.png"));
		animation().images().add(new Image("file:resources/Dashless/WatsonSprite3.png"));
		setWidth(animation().images().get(animation().imageIndex()).getWidth());
		setHeight(animation().images().get(animation().imageIndex()).getHeight());
	}

	public void move() {
		if(this.finishedDashing() && !this.climbing()) {
			if(this.moving()[0] != 0) {
				this.speed().setX(accelerate(this.speed().x()));
			}
			else {
				this.speed().setX(decelerate(this.speed().x()));
			}
			this.speed().setY(gravity(this.speed().y()));
		}

		Collision.test(this);
		this.coord().setX(this.coord().x() + this.speed().x() * this.moving()[0]);
		this.coord().setY(this.coord().y() + this.speed().y());
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
		boolean done = false;
		
		// self explanatory
		if(this.dash().frames()[0] < this.dash().frames()[1]) {
			this.dash().frames()[0] += 1;
			if(this.dash().frames()[0] == this.dash().frames()[1] && this.speed().y() < 0) {
				this.speed().setY(-3);
			}
		}
		else {
			done = true;
		}
		return done;
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
	public Timer dash() {
		return dash;
	}

	public void setDash(Timer dash) {
		this.dash = dash;
	}

	public boolean climbing() {
		return climbing;
	}

	public void setClimbing(boolean climbing) {
		this.climbing = climbing;
	}

	public boolean grab() {
		return grab;
	}

	public void setGrab(boolean grab) {
		this.grab = grab;
	}
}
