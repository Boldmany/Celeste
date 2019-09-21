package character;

import main.Vector;

public class Character {
	private Vector vector = new Vector(0,0);
	private Vector changeHitBox = new Vector(0,0);
	private Vector visibleVector = new Vector(0,0);
	private int[] moving = {0,0};
	private double width;
	private double height;
	private Animation animation = new Animation();
	
	public Vector vector() {
		return vector;
	}
	public void setVector(Vector vector) {
		this.vector = vector;
	}
	public Vector changeHitBox() {
		return changeHitBox;
	}
	public void setChangeHitBox(Vector changeHitBox) {
		this.changeHitBox = changeHitBox;
	}
	public Vector visibleVector() {
		return visibleVector;
	}
	public void setVisibleVector(Vector visibleVector) {
		this.visibleVector = visibleVector;
	}
	public Animation animation() {
		return animation;
	}
	public void setAnimation(Animation animation) {
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
}
