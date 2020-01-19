package map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.Vector;

public class Flat extends MapObject{

	/**
	 * Flat constructor
	 * @param coord
	 * @param visibleCoord
	 * @param width
	 */
	public Flat(Vector coord, Vector visibleCoord, double width) {
		super(coord, visibleCoord, width, 10, new String[]{"file:resources/MapObjects/flat.png"});
	}
	
	/**
	 * Draw a flat onto the screen
	 * @param gc
	 * @param color
	 */
	public void draw(GraphicsContext gc) {
		Image image = this.images().images().get(0);
			for(double i = this.visibleCoord().x(); i < this.width() + this.visibleCoord().x(); i+= 20) { // loop to fill in the flat with an image
				gc.drawImage(image, i, this.visibleCoord().y());
			}
		
	}
}
