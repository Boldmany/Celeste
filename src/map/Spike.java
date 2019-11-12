package map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Vector;

public class Spike extends Platform{

	public Spike(Vector coord, Vector visibleCoord, double width, double height, Type type) {
		super(coord, visibleCoord, width, height, type);
	}
	
	public void draw(GraphicsContext gc) {
		gc.setStroke(Color.RED);
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y(), this.visibleCoord().x() + this.width(), this.visibleCoord().y());
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y(), this.visibleCoord().x(), this.visibleCoord().y() + this.height());
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y() + this.height(), this.visibleCoord().x() + this.width(), this.visibleCoord().y() + this.height());
		gc.strokeLine(this.visibleCoord().x() + this.width(), this.visibleCoord().y(), this.visibleCoord().x() + this.width(), this.visibleCoord().y() + this.height());
	}
}
