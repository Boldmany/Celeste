package map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Vector;

public class Spike extends Platform{

	public Spike(Vector coord, double width, double height, Type type) {
		super(coord, width, height, type);
	}
	
	public void draw(GraphicsContext gc) {
		gc.setStroke(Color.RED);
		gc.strokeLine(this.coord().x(), this.coord().y(), this.coord().x() + this.width(), this.coord().y());
		gc.strokeLine(this.coord().x(), this.coord().y(), this.coord().x(), this.coord().y() + this.height());
		gc.strokeLine(this.coord().x(), this.coord().y() + this.height(), this.coord().x() + this.width(), this.coord().y() + this.height());
		gc.strokeLine(this.coord().x() + this.width(), this.coord().y(), this.coord().x() + this.width(), this.coord().y() + this.height());
	}
}
