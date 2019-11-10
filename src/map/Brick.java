package map;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Direction;
import main.Vector;

public class Brick extends Platform {
	
	private ArrayList<Spike> spikes = new ArrayList<Spike>();
	
	public Brick(Vector coord, double width, double height) {
		super(coord, width, height, Type.SAFE);
	}
	
	public Brick(Vector coord, double width, double height, int direction) {
		super(coord, width, height, Type.SAFE);
		if(Direction.has(direction, Direction.UP.getValue())) {
			this.spikes().add(new Spike(new Vector(coord.x() + 1, coord.y() - 5), width - 2, 5, Type.DEATH));
		}
		if(Direction.has(direction, Direction.RIGHT.getValue())) {
			this.spikes().add(new Spike(new Vector(coord.x() + width, coord.y() + 1), 5, height - 2, Type.DEATH));
		}
		if(Direction.has(direction, Direction.DOWN.getValue())) {
			this.spikes().add(new Spike(new Vector(coord.x() + 1, coord.y() + height), width - 2, 5, Type.DEATH));
		}
		if(Direction.has(direction, Direction.LEFT.getValue())) {
			this.spikes().add(new Spike(new Vector(coord.x() - 5, coord.y() + 1), 5, height - 2, Type.DEATH));
		}
	}
	
	public void draw(GraphicsContext gc) {
		gc.setStroke(Color.BLACK);
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y(), this.visibleCoord().x() + this.width(), this.visibleCoord().y());
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y(), this.visibleCoord().x(), this.visibleCoord().y() + this.height());
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y() + this.height(), this.visibleCoord().x() + this.width(), this.visibleCoord().y() + this.height());
		gc.strokeLine(this.visibleCoord().x() + this.width(), this.visibleCoord().y(), this.visibleCoord().x() + this.width(), this.visibleCoord().y() + this.height());
		
		for(int i = 0; i < this.spikes().size(); i++) {
			this.spikes().get(i).draw(gc);
		}
	}

	public ArrayList<Spike> spikes() {
		return spikes;
	}

	public void setSpikes(ArrayList<Spike> spikes) {
		this.spikes = spikes;
	}
}
