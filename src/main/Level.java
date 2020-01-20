package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import character.Watermelon;
import map.*;

public class Level {
	private int index; // used for connecting levels
	private Vector coord = new Vector(0, 0); // coordinates of the top right of a level
	private Vector length = new Vector(1000, 850); // the length of a level
	private Vector spawnPoint = new Vector(0, 0); // where the character will spawn in relation to the levels coordinates
	private ArrayList<MapObject> mapObjects = new ArrayList<>(); // All the levels map objects
	private int[] connection = new int[4]; // connections to the levels connecting to the right, left, up or down part of the level

	public Level(int level){
		this.setIndex(level); // this is the levels index
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader("resources/Levels/Level" + level + ".txt")); // this will find the level file to read
			createLevel(fileReader); // create the level
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reades a file to generate map objects
	 * @param br
	 */
	public void createLevel(BufferedReader br){
		String line; // is a line in a code
		try {
			while((line = br.readLine()) != null){ // while there is a line to read
				String[] fields = line.split(" "); // spite the information by a space so that we get a command and the parameters for that command
				if(fields[0].equals("coord")){
					this.coord().setX(Double.parseDouble(fields[1]));
					this.coord().setY(Double.parseDouble(fields[2]));		
				}
				else if(fields[0].equals("length")){
					this.length().setX(Double.parseDouble(fields[1]));
					this.length().setY(Double.parseDouble(fields[2]));		
				}
				else if(fields[0].equals("spawnPoint")){
					this.spawnPoint().setX(Double.parseDouble(fields[1]) + this.coord().x());
					this.spawnPoint().setY(Double.parseDouble(fields[2]) + this.coord().y());
				}
				else if(fields[0].equals("brick")){
					double x = Double.parseDouble(fields[1]);
					double y = Double.parseDouble(fields[2]);
					Brick brick = new Brick(new Vector(x + this.coord().x(), y + this.coord().y()), new Vector(x, y), Double.parseDouble(fields[3]), Double.parseDouble(fields[4])); // create a brick
					brick.addSpike(line); // this will create the spikes along the brick
					this.mapObjects().add(brick); // add the brick
				}
				else if(fields[0].equals("flat")){
					double x = Double.parseDouble(fields[1]);
					double y = Double.parseDouble(fields[2]);
					Flat flat = new Flat(new Vector(x + this.coord().x(), y + this.coord().y()), new Vector(x, y), Double.parseDouble(fields[3])); // create a flat 
					this.mapObjects().add(flat); // add the flat
				}
				else if(fields[0].equals("crystal")){
					double x = Double.parseDouble(fields[1]);
					double y = Double.parseDouble(fields[2]);
					Crystal crystal = new Crystal(new Vector(x + this.coord().x(), y + this.coord().y()), new Vector(x, y)); // create a crystal
					this.mapObjects().add(crystal); // add the crystal
				}
				else if(fields[0].equals("right")){ // if there is a lever to the right 
					int level = Integer.parseInt(fields[1]);
					Map.levels().get(level - 1).connection()[3] = this.index(); // this will set the the level to the right of this one to have this level as its index for the left field
					this.connection()[1] = level; // connect this level to the right one
				}
				else if(fields[0].equals("left")){
					int level = Integer.parseInt(fields[1]);
					Map.levels().get(level - 1).connection()[1] = this.index(); // this will set the the level to the left of this one to have this level as its index for the right field
					this.connection()[3] = level; // connect this level to the left one
				}
				else if(fields[0].equals("down")){
					int level = Integer.parseInt(fields[1]);
					Map.levels().get(level - 1).connection()[0] = this.index();  // this will set the the level below it to have this level as its index for the up field
					this.connection()[2] = level; // connect this level to the one below it
				}
				else if(fields[0].equals("up")){
					int level = Integer.parseInt(fields[1]);
					Map.levels().get(level - 1).connection()[2] = this.index(); // this will set the the level ubove it to have this level as its index for the down field
					this.connection()[0] = level; // connect this level to the one above it
				}
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Shifts every map object horizontally
	 * @param speed
	 */
	public void moveHorizontally(double speed) {
		// moves all the map objects by a set speed
		for(int i = 0; i < this.mapObjects().size(); i++) { 
			this.mapObjects().get(i).visibleCoord().setX(this.mapObjects().get(i).visibleCoord().x() + speed);
			if(this.mapObjects().get(i) instanceof Brick) {
				Brick brick = (Brick) this.mapObjects().get(i);
				for(int j = 0; j < brick.spikes().size(); j++) {
					brick.spikes().get(j).visibleCoord().setX(brick.spikes().get(j).visibleCoord().x() + speed);
				}
			}
		}
	}

	/**
	 * Shifts every map object vertically
	 * @param speed
	 */
	public void moveVertically(double speed) {
		// moves all the map objects by a set speed
		for(int i = 0; i < this.mapObjects().size(); i++) {
			this.mapObjects().get(i).visibleCoord().setY(this.mapObjects().get(i).visibleCoord().y() + speed);
			if(this.mapObjects().get(i) instanceof Brick) {
				Brick brick = (Brick) this.mapObjects().get(i);
				for(int j = 0; j < brick.spikes().size(); j++) {
					brick.spikes().get(j).visibleCoord().setY(brick.spikes().get(j).visibleCoord().y() + speed);
				}
			}
		}
	}
	
	/**
	 * Resets the map so that the character is aligned with the map
	 * @param character
	 */
	public void nextLevel(Watermelon character) {
		Map.updateLevel(); // Updates the file that stores the current level
		
		// moves all the map objects back to their original positions
		for(int i = 0; i < this.mapObjects().size(); i++) { 
			this.mapObjects().get(i).visibleCoord().setX(this.mapObjects().get(i).coord().x() - this.coord().x());
			if(mapObjects().get(i) instanceof Brick) {
				Brick brick = (Brick) mapObjects().get(i);
				for(int j = 0; j < brick.spikes().size(); j++) {
					brick.spikes().get(j).visibleCoord().setX(brick.spikes().get(j).coord().x() - this.coord().x());
				}
			}
		}
		for(int i = 0; i < this.mapObjects().size(); i++) {
			this.mapObjects().get(i).visibleCoord().setY(this.mapObjects().get(i).coord().y() - this.coord().y());
			if(mapObjects().get(i) instanceof Brick) {
				Brick brick = (Brick) mapObjects().get(i);
				for(int j = 0; j < brick.spikes().size(); j++) {
					brick.spikes().get(j).visibleCoord().setY(brick.spikes().get(j).coord().y() - this.coord().y());
				}
			}
		}
		
		Level newLevel = Map.levels().get(Map.currentLevel()); // temporary variable

		// update the visible coordinates of the map objects based on the way the character enters the level
		if(character.coord().y() - newLevel.coord().y() >= newLevel.length().y() - 275) { // if the character is at the top of the level
			newLevel.moveVertically(550 - newLevel.length().y()); // move all the objects down
		}
		else if(character.coord().y() - newLevel.coord().y() > 275 
				&& character.coord().y() - newLevel.coord().y() < newLevel.length().y() - 275) { // if the player is in the side scroller
			newLevel.moveVertically(((newLevel.coord().y() + 275) - (character.coord().y()))); // move the level up according to the players current possition
			character.visibleCoord().setY(275); // set the characters visible coordinates to the center of screen
		}
		
		if(character.coord().x() - newLevel.coord().x() >= newLevel.length().x() - 425) { // if the player is all the way to the right of the level
			newLevel.moveHorizontally(850 - newLevel.length().x()); // move all the map objects to the left
		}
		else if(character.coord().x() - newLevel.coord().x() > 425
				&& character.coord().x() - newLevel.coord().x() < newLevel.length().x() - 425) { // if the character is in the side scroller
			newLevel.moveHorizontally(((newLevel.coord().x() + 425) - (character.coord().x()))); // move all the objects to the left according to the players current possition
			character.visibleCoord().setX(425); // set the characters visible coordinates to the center of screen
		}
	}


	public Vector coord() {
		return coord;
	}
	public void setCoord(Vector coord) {
		this.coord = coord;
	}
	public Vector length() {
		return length;
	}
	public void setLength(Vector length) {
		this.length = length;
	}
	public int index() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int[] connection() {
		return connection;
	}

	public void setConnection(int[] connection) {
		this.connection = connection;
	}

	public Vector spawnPoint() {
		return spawnPoint;
	}

	public void setSpawnPoint(Vector spawnPoint) {
		this.spawnPoint = spawnPoint;
	}

	public ArrayList<MapObject> mapObjects() {
		return mapObjects;
	}

	public void setMapObjects(ArrayList<MapObject> mapObjects) {
		this.mapObjects = mapObjects;
	}
}
