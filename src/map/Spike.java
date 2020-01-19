package map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.Vector;

public class Spike extends MapObject{

	private Direction direction;

	/**
	 * Spike constructor
	 * @param coord
	 * @param visibleCoord
	 * @param width
	 * @param height
	 * @param direction
	 */
	public Spike(Vector coord, Vector visibleCoord, double width, double height, Direction direction) {
		super(coord, visibleCoord, width, height, new String[]{"file:resources/MapObjects/spikeUp.png", "file:resources/MapObjects/spikeRight.png", "file:resources/MapObjects/spikeDown.png", "file:resources/MapObjects/spikeLeft.png"});
		this.setDirection(direction);
	}

	/**
	 * Directional drawing of the spike object
	 * @param gc
	 * @param color
	 */
	public void draw(GraphicsContext gc) {
		if(this.direction() == Direction.UP) {
			Image image = this.images().images().get(0);
			for(double i = this.visibleCoord().x() - 1; i < this.width() + this.visibleCoord().x() + 1; i+=5) { // loop to cover the entire top with small spikes
				gc.drawImage(image, i, this.visibleCoord().y());	
			}
		}
		if(this.direction() == Direction.DOWN) {
			Image image = this.images().images().get(2);
			for(double i = this.visibleCoord().x() - 1; i < this.width() + this.visibleCoord().x() + 1; i+=5) { // loop to cover the entire bottom with small spikes
				gc.drawImage(image, i, this.visibleCoord().y());	
			}
		}
		if(this.direction() == Direction.RIGHT) {
			Image image = this.images().images().get(1);
			for(double i = this.visibleCoord().y() - 1; i < this.height() + this.visibleCoord().y() + 1; i+=5) { // loop to cover the entire right side with small spikes
				gc.drawImage(image, this.visibleCoord().x(), i);	
			}
		}
		if(this.direction() == Direction.LEFT) {
			Image image = this.images().images().get(3);
			for(double i = this.visibleCoord().y() - 1; i < this.height() + this.visibleCoord().y() + 1; i+=5) { // loop to cover the entire left side with small spikes
				gc.drawImage(image, this.visibleCoord().x(), i);	
			}
		}
	}

	public Direction direction() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
