package map;
import character.Images;
import javafx.scene.image.Image;
import main.Vector;


public class MapObject {
	private Vector coord = new Vector(0, 0); // coordinates
	private Vector visibleCoord = new Vector(0, 0); // the coordinates that will be on screen
	private double height = 0; // the height of the object
	private double width = 0; // the width of the object
	private Images images = new Images(); // the image / images that will be used to represent the object

	/**
	 * The constructor will set all of the fields for a map object
	 * @param coord
	 * @param visibleCoord
	 * @param width
	 * @param height
	 * @param imagePath
	 */
	public MapObject(Vector coord, Vector visibleCoord, double width, double height, String... imagePath) {
		this.setCoord(coord);
		this.setVisibleCoord(visibleCoord);
		this.setWidth(width);
		this.setHeight(height);
		for(int i  = 0; i < imagePath.length; i++) {
			this.images().images().add(new Image(imagePath[i]));
		}
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

	public Images images() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}
}
