package map;

import main.Vector;

public class Flat extends MapObject{

	public Flat(Vector coord, Vector visibleCoord, double width, double height) {
		super(coord, visibleCoord, width, height, Type.SAFE);
	}
}
