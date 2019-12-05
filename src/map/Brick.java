package map;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Vector;

public class Brick extends MapObject {
	
	private ArrayList<Spike> spikes = new ArrayList<Spike>();

	public Brick(Vector coord, Vector visibleCoord, double width, double height) {
		super(coord, visibleCoord, width, height, Type.SAFE);
	}
	
	public void addSpike(String s){
		if(s.contains("up")) {
			spikes.add(new Spike(new Vector(this.coord().x() + 1, this.coord().y() - 5), new Vector(this.visibleCoord().x() + 1, this.visibleCoord().y() - 5), this.width() - 2, 5, Type.DEATH));
		}
		if(s.contains("right")) {
			spikes.add(new Spike(new Vector(this.coord().x() + this.width(), this.coord().y() + 1), new Vector(this.visibleCoord().x() + this.width(), this.visibleCoord().y() + 1), 5, this.height() - 2, Type.DEATH));
		}
		if(s.contains("down")) {
			spikes.add(new Spike(new Vector(this.coord().x() + 1, this.coord().y() + this.height()), new Vector(this.visibleCoord().x() + 1, this.visibleCoord().y() + this.height()),this.width() - 2, 5, Type.DEATH));
		}
		if(s.contains("left")){
			spikes.add(new Spike(new Vector(this.coord().x() - 5, this.coord().y() + 1), new Vector(this.visibleCoord().x() - 5, this.visibleCoord().y() + 1), 5, this.height() - 2, Type.DEATH));
		}
	}
	
	public void draw(GraphicsContext gc) {
		gc.setStroke(Color.BLACK);
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y(), this.visibleCoord().x() + this.width(), this.visibleCoord().y());
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y(), this.visibleCoord().x(), this.visibleCoord().y() + this.height());
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y() + this.height(), this.visibleCoord().x() + this.width(), this.visibleCoord().y() + this.height());
		gc.strokeLine(this.visibleCoord().x() + this.width(), this.visibleCoord().y(), this.visibleCoord().x() + this.width(), this.visibleCoord().y() + this.height());
		
		for(int i = 0; i < spikes.size(); i++) {
			spikes.get(i).draw(gc);
		}
	}
	
	public ArrayList<Spike> spikes() {
		return spikes;
	}

	public void setSpikes(ArrayList<Spike> spikes) {
		this.spikes = spikes;
	}
}
