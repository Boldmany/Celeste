package map;
import javafx.scene.image.Image;
import main.Vector;


public class Platform {
	private Vector vector = new Vector(0, 0);
	private Image image = new Image("");

	public void collision(Character character) {
		
	}

	public Vector vector() {
		return vector;
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}

	public Image image() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
