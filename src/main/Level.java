package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import map.Brick;

public class Level {
	private int index;
	private Vector coord = new Vector(0, 0);
	private Vector length = new Vector(1000, 850);
	private ArrayList<Brick> bricks = new ArrayList<Brick>();
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
				else if(fields[0].equals("brick")){
					double x = Double.parseDouble(fields[1]);
					double y = Double.parseDouble(fields[2]);
					Brick brick = new Brick(new Vector(x + this.coord().x(), y + this.coord().y()), new Vector(x, y), Double.parseDouble(fields[3]), Double.parseDouble(fields[4]));
					brick.addSpike(line);
					this.bricks().add(brick);	
				}
				else if(fields[0].equals("right")){
					int level = Integer.parseInt(fields[1]);
					MapObjects.levels().get(level - 1).connection()[3] = this.index();
					this.connection()[1] = level;
				}
				else if(fields[0].equals("left")){
					int level = Integer.parseInt(fields[1]);
					MapObjects.levels().get(level - 1).connection()[1] = this.index();
					this.connection()[3] = level;
				}
				else if(fields[0].equals("down")){
					int level = Integer.parseInt(fields[1]);
					MapObjects.levels().get(level - 1).connection()[2] = this.index();
					this.connection()[0] = level;
				}
				else if(fields[0].equals("right")){
					int level = Integer.parseInt(fields[1]);
					MapObjects.levels().get(level - 1).connection()[0] = this.index();
					this.connection()[2] = level;
				}
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
}
