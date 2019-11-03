package map;
import main.Vector;


public class Platform {
	private Vector coord = new Vector(0, 0);
	private Vector visibleCoord = new Vector(0, 0);
	private double height = 0;
	private double width = 0;
	private Type type;
	
	public Platform(Vector coord, double width, double height, Type type) {
		this.setCoord(coord);
		this.setVisibleCoord(new Vector(coord.x(), coord.y()));
		this.setWidth(width);
		this.setHeight(height);
		this.setType(type);
	}

	public void collision(Character character) {
		
	}

	public double height() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double width() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}
	
	public Vector visibleCoord() {
		return visibleCoord;
	}

	public void setVisibleCoord(Vector visibleCoord) {
		this.visibleCoord = visibleCoord;
	}

	public Vector coord() {
		return coord;
	}

	public void setCoord(Vector coord) {
		this.coord = coord;
	}

	public Type type() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
