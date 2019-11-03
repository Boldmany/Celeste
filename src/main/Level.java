package main;

import java.util.ArrayList;

import map.Brick;

public class Level {
	private Vector coord = new Vector(0, 0);
	private Vector length = new Vector(1000, 550);
	private ArrayList<Brick> bricks = new ArrayList<Brick>();
	private int[] direction= new int[4];
	
	public void move(double speed) {
		for(int i = 0; i < bricks().size(); i++) {
			bricks().get(i).visibleCoord().setX(bricks().get(i).visibleCoord().x() + speed);
		}
	}
	
	
	public ArrayList<Brick> bricks() {
		return bricks;
	}
	public void setBricks(ArrayList<Brick> bricks) {
		this.bricks = bricks;
	}
	public Vector coord() {
		return coord;
	}
	public void setCoord(Vector coord) {
		this.coord = coord;
	}
	public int[] direction() {
		return direction;
	}
	public void setDirection(int[] direction) {
		this.direction = direction;
	}
	public Vector length() {
		return length;
	}
	public void setLength(Vector length) {
		this.length = length;
	}
}
