package character;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.Timer;

public class Animation {
	private ArrayList<Image> images = new ArrayList<Image>();
	private int imageIndex = 0;
	private Timer timer = new Timer(10);
	private boolean[] direction = {true, false};
	
	public static void draw(GraphicsContext gc, Character character) {
		double xCoord = 0;
		double width = 0;
		
		if(character.moving()[0] != 0) {
			if(character.animation().timer().finished()) {
				character.animation().setImageIndex((character.animation().imageIndex() + 1) % character.animation().images().size());
				character.animation().timer().resetFrames();
			}
		}
		else {
			character.animation().setImageIndex(0);
			character.animation().timer().resetFrames();
		}
		
		if(character.animation().direction()[0]) {
			xCoord = character.vector().x();
			width = character.width();
		}
		else { 
			xCoord = character.vector().x() + character.width();
			width = -character.width();
		}
		
		gc.drawImage(character.animation().images().get(character.animation().imageIndex()), 0, 0, character.width(), character.height(), xCoord, character.vector().y(), width, character.height());
	}
	
	public ArrayList<Image> images() {
		return images;
	}
	public void setImages(ArrayList<Image> images) {
		this.images = images;
	}
	public int imageIndex() {
		return imageIndex;
	}
	public void setImageIndex(int imageIndex) {
		this.imageIndex = imageIndex;
	}

	public Timer timer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public boolean[] direction() {
		return direction;
	}

	public void setDirection(boolean[] direction) {
		this.direction = direction;
	}
}
