package map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Vector;

public class Flat extends MapObject{

	public Flat(Vector coord, Vector visibleCoord, double width) {
		super(coord, visibleCoord, width, 10, Type.SAFE);
	}
}
