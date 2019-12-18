package map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Vector;


public class MapObject {
	private Vector coord = new Vector(0, 0);
	private Vector visibleCoord = new Vector(0, 0);
	private double height = 0;
	private double width = 0;
	private Type type;
	
	public MapObject(Vector coord, Vector visibleCoord, double width, double height, Type type) {
		this.setCoord(coord);
		this.setVisibleCoord(visibleCoord);
		this.setWidth(width);
		this.setHeight(height);
		this.setType(type);
	}
	
	public void draw(GraphicsContext gc, Color color) {
		gc.setStroke(color);
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y(), this.visibleCoord().x() + this.width(), this.visibleCoord().y());
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y(), this.visibleCoord().x(), this.visibleCoord().y() + this.height());
		gc.strokeLine(this.visibleCoord().x(), this.visibleCoord().y() + this.height(), this.visibleCoord().x() + this.width(), this.visibleCoord().y() + this.height());
		gc.strokeLine(this.visibleCoord().x() + this.width(), this.visibleCoord().y(), this.visibleCoord().x() + this.width(), this.visibleCoord().y() + this.height());
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
