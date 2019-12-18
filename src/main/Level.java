package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import character.Watermelon;
import map.*;

public class Level {
	private int index;
	private Vector coord = new Vector(0, 0);
	private Vector length = new Vector(1000, 850);
	private Vector spawnPoint = new Vector(0, 0);
	private ArrayList<MapObject> mapObjects = new ArrayList<>();
	private int[] connection = new int[4];

	public Level(int level){
		this.setIndex(level);
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader("resources/Levels/Level" + level + ".txt"));
			createLevel(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createLevel(BufferedReader br){
		String line;
		try {
			while((line = br.readLine()) != null){
				String[] fields = line.split(" ");
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
					Brick brick = new Brick(new Vector(x + this.coord().x(), y + this.coord().y()), new Vector(x, y), Double.parseDouble(fields[3]), Double.parseDouble(fields[4]));
					brick.addSpike(line);
					this.mapObjects().add(brick);
				}
				else if(fields[0].equals("flat")){
					double x = Double.parseDouble(fields[1]);
					double y = Double.parseDouble(fields[2]);
					Flat flat = new Flat(new Vector(x + this.coord().x(), y + this.coord().y()), new Vector(x, y), Double.parseDouble(fields[3]));
					this.mapObjects().add(flat);
				}
				else if(fields[0].equals("crystal")){
					double x = Double.parseDouble(fields[1]);
					double y = Double.parseDouble(fields[2]);
					Crystal crystal = new Crystal(new Vector(x + this.coord().x(), y + this.coord().y()), new Vector(x, y));
					this.mapObjects().add(crystal);
				}
				else if(fields[0].equals("right")){
					int level = Integer.parseInt(fields[1]);
					Map.levels().get(level - 1).connection()[3] = this.index();
					this.connection()[1] = level;
				}
				else if(fields[0].equals("left")){
					int level = Integer.parseInt(fields[1]);
					Map.levels().get(level - 1).connection()[1] = this.index();
					this.connection()[3] = level;
				}
				else if(fields[0].equals("down")){
					int level = Integer.parseInt(fields[1]);
					Map.levels().get(level - 1).connection()[0] = this.index();
					this.connection()[2] = level;
				}
				else if(fields[0].equals("up")){
					int level = Integer.parseInt(fields[1]);
					Map.levels().get(level - 1).connection()[2] = this.index();
					this.connection()[0] = level;
				}
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void moveHorizontally(double speed) {
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

	public void moveVertically(double speed) {
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

	public void resetVertically() {
		for(int i = 0; i < this.mapObjects().size(); i++) {
			this.mapObjects().get(i).visibleCoord().setY(this.mapObjects().get(i).coord().y() - this.coord().y());
			if(mapObjects().get(i) instanceof Brick) {
				Brick brick = (Brick) mapObjects().get(i);
				for(int j = 0; j < brick.spikes().size(); j++) {
					brick.spikes().get(j).visibleCoord().setY(brick.spikes().get(j).coord().y() - this.coord().y());
				}
			}
		}
	}

	public void resetHorizontally() {
		for(int i = 0; i < this.mapObjects().size(); i++) {
			this.mapObjects().get(i).visibleCoord().setY(this.mapObjects().get(i).coord().y() - this.coord().y());
			if(mapObjects().get(i) instanceof Brick) {
				Brick brick = (Brick) mapObjects().get(i);
				for(int j = 0; j < brick.spikes().size(); j++) {
					brick.spikes().get(j).visibleCoord().setY(brick.spikes().get(j).coord().y() - this.coord().y());
				}
			}
		}
	}

	public void nextHorizontalLevel(Watermelon character) {
		this.resetVertically();
		character.deltaCoord().setY(character.coord().y());

		Level newLevel = Map.levels().get(Map.currentLevel());

		character.coord().setX(character.coord().x() + (50 * character.moving()[0]));

		if(character.coord().y() - newLevel.coord().y() >= newLevel.length().y() - 275) {
			newLevel.moveVertically(550 - newLevel.length().y());
		}
		else if(character.coord().y() - newLevel.coord().y() > 275 
				&& character.coord().y() - newLevel.coord().y() < newLevel.length().y() - 275){
			newLevel.moveVertically(((newLevel.coord().y() + 275) - (character.coord().y())));
			character.visibleCoord().setY(275);
		}
	}

	public void nextVerticalLevel(Watermelon character) {
		this.resetHorizontally();
		character.deltaCoord().setX(character.coord().x());

		Level newLevel = Map.levels().get(Map.currentLevel());

		character.coord().setY(character.coord().y() + (100 * character.moving()[1]));

		if(character.coord().x() - newLevel.coord().x() >= newLevel.length().x() - 425) {
			newLevel.moveVertically(850 - newLevel.length().y());
		}
		else if(character.coord().x() - newLevel.coord().x() > 425
				&& character.coord().x() - newLevel.coord().x() < newLevel.length().x() - 425){
			newLevel.moveVertically(((newLevel.coord().x() + 425) - (character.coord().x())));
			character.visibleCoord().setY(425);
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
