package map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Timer;
import main.Vector;

public class Crystal extends MapObject{
	private Timer respawn = new Timer(55); // this will track how long until the next crystal spawns

	/**
	 * Crystal constructor
	 * @param coord
	 * @param visibleCoord
	 */
	public Crystal(Vector coord, Vector visibleCoord) {
		super(coord, visibleCoord, 40, 40, "file:resources/MapObjects/crystal.png");
	}
	
	/**
	 * Draw the crystal onto the screen
	 * @param gc
	 * @param color
	 */
	public void draw(GraphicsContext gc) {
		gc.drawImage(this.images().images().get(0), this.visibleCoord().x(), this.visibleCoord().y());
	}
	
	public Timer respawn() {
		return respawn;
	}

	public void setRespawn(Timer respawn) {
		this.respawn = respawn;
	}
}
