package map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Timer;
import main.Vector;

public class Crystal extends MapObject{
	private Timer respawn = new Timer(50);

	public Crystal(Vector coord, Vector visibleCoord) {
		super(coord, visibleCoord, 50, 50, Type.CRYSTAL);
	}
	
	public void draw(GraphicsContext gc) {
		gc.setStroke(Color.GREEN);
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y(), this.visibleCoord().x() + this.width(), this.visibleCoord().y());
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y(), this.visibleCoord().x(), this.visibleCoord().y() + this.height());
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y() + this.height(), this.visibleCoord().x() + this.width(), this.visibleCoord().y() + this.height());
		gc.strokeLine(this.visibleCoord().x() + this.width(), this.visibleCoord().y(), this.visibleCoord().x() + this.width(), this.visibleCoord().y() + this.height());
	}

	public Timer respawn() {
		return respawn;
	}

	public void setRespawn(Timer respawn) {
		this.respawn = respawn;
	}
}
