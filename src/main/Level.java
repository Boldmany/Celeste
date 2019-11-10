package main;

import java.util.ArrayList;

import map.Brick;

public class Level {
	private Vector coord = new Vector(0, 0);
	private Vector length = new Vector(850, 850);
	private ArrayList<Brick> bricks = new ArrayList<Brick>();
	private int[] direction= new int[4];
	
	public void moveHorizontally(double speed) {
		for(int i = 0; i < bricks().size(); i++) {
			bricks().get(i).visibleCoord().setX(bricks().get(i).visibleCoord().x() + speed);
			for(int j = 0; j < bricks().get(i).spikes().size(); j++) {
				bricks().get(i).spikes().get(j).visibleCoord().setX(bricks().get(i).spikes().get(j).visibleCoord().x() + speed);
			}
		}
	}
	
	public void moveVertically(double speed) {
		for(int i = 0; i < bricks().size(); i++) {
			bricks().get(i).visibleCoord().setY(bricks().get(i).visibleCoord().y() + speed);
			for(int j = 0; j < bricks().get(i).spikes().size(); j++) {
				bricks().get(i).spikes().get(j).visibleCoord().setY(bricks().get(i).spikes().get(j).visibleCoord().y() + speed);
			}
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
