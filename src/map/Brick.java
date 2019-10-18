package map;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Vector;

public class Brick extends Platform {
	
	private ArrayList<Spike> spikes = new ArrayList<Spike>();
	
	public Brick(Vector coord, double width, double height) {
		super(coord, width, height, Type.SAFE);
	}
	
	public Brick(Vector coord, double width, double height, int direction) {
		super(coord, width, height, Type.SAFE);
		if(Directions.has(direction, Directions.UP.getValue())) {
			spikes.add(new Spike(new Vector(coord.x() + 1, coord.y() - 5), width - 2, 5, Type.DEATH));
		}
		if(Directions.has(direction, Directions.RIGHT.getValue())) {
			spikes.add(new Spike(new Vector(coord.x() + width, coord.y() + 1), 5, height - 2, Type.DEATH));
		}
		if(Directions.has(direction, Directions.DOWN.getValue())) {
			spikes.add(new Spike(new Vector(coord.x() + 1, coord.y() + height), width - 2, 5, Type.DEATH));
		}
		if(Directions.has(direction, Directions.LEFT.getValue())) {
			spikes.add(new Spike(new Vector(coord.x() - 5, coord.y() + 1), 5, height - 2, Type.DEATH));
		}
		
	}
	
	public void draw(GraphicsContext gc) {
		gc.setStroke(Color.BLACK);
		gc.strokeLine(this.coord().x(), this.coord().y(), this.coord().x() + this.width(), this.coord().y());
		gc.strokeLine(this.coord().x(), this.coord().y(), this.coord().x(), this.coord().y() + this.height());
		gc.strokeLine(this.coord().x(), this.coord().y() + this.height(), this.coord().x() + this.width(), this.coord().y() + this.height());
		gc.strokeLine(this.coord().x() + this.width(), this.coord().y(), this.coord().x() + this.width(), this.coord().y() + this.height());
		
		for(int i = 0; i < spikes.size(); i++) {
			spikes.get(i).draw(gc);
		}
	}
}
