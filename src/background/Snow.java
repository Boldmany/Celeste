package background;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Map;
import main.Vector;

public class Snow {
	private Vector coord = new Vector(0, 0); // snow coordinates
	private Vector speed = new Vector(0, 0); // snow speed
	private int radius; // the size of a snow particle
	
	/**
	 * Snow constructor
	 * @param radius
	 * @param coord
	 * @param speed
	 */
	public Snow(int radius, Vector coord, Vector speed) {
		this.setCoord(coord);
		this.setSpeed(speed);
		this.setRadius(radius);
	}
	
	/**
	 * Dictates the motion of snow particles
	 */
	public void move(){
		this.coord().setX(this.speed().x() + this.coord().x()); // moves the x coordinate
		this.coord().setY(this.speed().y() + this.coord().y()); // moves the y coordinate
		if(this.coord().x() > 850 || this.coord().y() > 550){ // if the snow its outside the bounds
			createSnow(); // create a new piece of snow
			Map.snow().remove(this); // delete the current one
			
		}
	}
	
	/**
	 * Creates a new snow particle
	 */
	public static void createSnow(){
		int side = (int) (Math.random() + 0.5); // randomized side or top spawn point 
		int radius = (int) (Math.random() * 4 + 2); // random radius
		Vector speed = new Vector((int) (Math.random() * 10 + 7), (int) (Math.random() * 5 + 3)); // random speed
		if(side == 1){ // if left side
			Vector coord = new Vector(-radius, (int) (Math.random() * 475 + 1)); // random y coordinate
			Map.snow().add(new Snow(radius, coord, speed)); // create snow

		}
		else { // if top side
			Vector coord = new Vector((int) (Math.random() * 500 + 1), -radius); // random x coordinate
			Map.snow().add(new Snow(radius, coord, speed)); // create snow
		}
	}
	
	/**
	 * Draw the snow onto the screen
	 * @param gc
	 */
	public void draw(GraphicsContext gc){
		gc.setFill(Color.WHITE);
		gc.fillOval(this.coord().x(), this.coord().y(), this.radius(), this.radius());
	}

	public Vector coord() {
		return coord;
	}

	public void setCoord(Vector coord) {
		this.coord = coord;
	}

	public Vector speed() {
		return speed;
	}

	public void setSpeed(Vector speed) {
		this.speed = speed;
	}

	public int radius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
