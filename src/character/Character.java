package character;

import main.Vector;

public class Character {
	private Vector coord = new Vector(0,0);
	private Vector changeHitBox = new Vector(0,0);
	private Vector visibleCoord = new Vector(0,0);
	private Vector speed = new Vector(0,0);
	private int[] moving = {0,0};
	private double width;
	private double height;
	private CharacterAnimation animation = new CharacterAnimation();
	
	
	public Vector coord() {
		return coord;
	}
	public void setCoord(Vector coord) {
		this.coord = coord;
	}
	public Vector changeHitBox() {
		return changeHitBox;
	}
	public void setChangeHitBox(Vector changeHitBox) {
		this.changeHitBox = changeHitBox;
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
