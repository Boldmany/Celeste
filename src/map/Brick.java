package map;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.Vector;

public class Brick extends MapObject {
	
	private ArrayList<Spike> spikes = new ArrayList<Spike>(); // A brick can choose to have spikes along its sides

	/**
	 * Brick constructor
	 * @param coord
	 * @param visibleCoord
	 * @param width
	 * @param height
	 */
	public Brick(Vector coord, Vector visibleCoord, double width, double height) {
		super(coord, visibleCoord, width, height, "file:resources/MapObjects/brick.png");
	}
	
	/**
	 * find the side that the brick will have spikes on
	 * @param s
	 */
	public void addSpike(String s){
		if(s.contains("up")) { // if on the top side
			spikes.add(new Spike(new Vector(this.coord().x() + 1, this.coord().y() - 5), new Vector(this.visibleCoord().x() + 1, this.visibleCoord().y() - 5), this.width() - 2, 5, Direction.UP));
		}
		if(s.contains("right")) { // if on the right side
			spikes.add(new Spike(new Vector(this.coord().x() + this.width(), this.coord().y() + 1), new Vector(this.visibleCoord().x() + this.width(), this.visibleCoord().y() + 1), 5, this.height() - 2, Direction.RIGHT));
		}
		if(s.contains("down")) { //if on the bottom side
			spikes.add(new Spike(new Vector(this.coord().x() + 1, this.coord().y() + this.height()), new Vector(this.visibleCoord().x() + 1, this.visibleCoord().y() + this.height()),this.width() - 2, 5, Direction.DOWN));
		}
		if(s.contains("left")){ // if on the left side
			spikes.add(new Spike(new Vector(this.coord().x() - 5, this.coord().y() + 1), new Vector(this.visibleCoord().x() - 5, this.visibleCoord().y() + 1), 5, this.height() - 2, Direction.LEFT));
		}
	}
	
	/**
	 * draws the brick onto the screen
	 * @param gc
	 * @param color
	 */
	public void draw(GraphicsContext gc) {
		
		Image image = this.images().images().get(0);
		
		// loop to fill the brick with an image
		for(double i = this.visibleCoord().y(); i < this.height() + this.visibleCoord().y(); i+=50) {
			for(double j = this.visibleCoord().x(); j < this.width() + this.visibleCoord().x(); j+= 50) { 
				gc.drawImage(image, j, i);
			}
		}
	}
	
	public ArrayList<Spike> spikes() {
		return spikes;
	}

	public void setSpikes(ArrayList<Spike> spikes) {
		this.spikes = spikes;
	}
}
