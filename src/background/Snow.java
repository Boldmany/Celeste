package background;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Main;
import main.MapObjects;
import main.Vector;

public class Snow {
	private Vector coord = new Vector(0, 0);
	private Vector speed = new Vector(0, 0);
	private int radius;
	
	
	public Snow(int radius, Vector coord, Vector speed) {
		this.setCoord(coord);
		this.setSpeed(speed);
		this.setRadius(radius);
	}
	
	public void move(){
		this.coord().setX(this.speed().x() + this.coord().x());
		this.coord().setY(this.speed().y() + this.coord().y());
		if(this.coord().x() > 850 || this.coord().y() > 550){
			createSnow();
			MapObjects.snow().remove(this);
			
		}
	}
	
	public static void createSnow(){
		int side = (int) (Math.random() + 0.5);
		int radius = (int) (Math.random() * 4 + 2);
		Vector speed = new Vector((int) (Math.random() * 10 + 7), (int) (Math.random() * 5 + 3));
		if(side == 1){
			Vector coord = new Vector(-radius, (int) (Math.random() * 475 + 1));
			MapObjects.snow().add(new Snow(radius, coord, speed));

		}
		else {
			Vector coord = new Vector((int) (Math.random() * 500 + 1), -radius);
			MapObjects.snow().add(new Snow(radius, coord, speed));
		}
	}
	
	
	public void draw(GraphicsContext gc){
		gc.setFill(Color.BLACK);
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
