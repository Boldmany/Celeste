package character;

import main.Vector;

public class Character {
	private Vector coord; // the characters coordinates that will determine his position in the game
 	private Vector visibleCoord = new Vector(0,0); // the characters coordinates that will determine his position on screen
	private Vector speed = new Vector(0,0); // character speed
	private int[] moving = {0,0}; // will be used to keep track of which direction the character is facing
	private double width; // character width
	private double height; // character height
	private CharacterAnimation animation = new CharacterAnimation(); // character animation
	
	public Vector coord() {
		return coord;
	}
	public void setCoord(Vector coord) {
		this.coord = coord;
	}
	public Vector visibleCoord() {
		return visibleCoord;
	}
	public void setVisibleCoord(Vector visibleVector) {
		this.visibleCoord = visibleVector;
	}
	public CharacterAnimation animation() {
		return animation;
	}
	public void setAnimation(CharacterAnimation animation) {
		this.animation = animation;
	}
	public double width() {
		return width;
	}
	public void setWidth(double d) {
		this.width = d;
	}
	public double height() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int[] moving() {
		return moving;
	}
	public void setMoving(int[] moving) {
		this.moving = moving;
	}


	public Vector speed() {
		return speed;
	}

	public void setSpeed(Vector speed) {
		this.speed = speed;
	}
}
