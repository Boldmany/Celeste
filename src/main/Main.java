package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import background.Background;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

//Yonatan and Andrej
// June 14 2020
// This is a video game where you dodge the pink stuff
public class Main extends Application{
	
	private static Clip clip; // Audio
	private static Canvas canvas = new Canvas(850, 550); // The window size
	private static GraphicsContext gc = canvas.getGraphicsContext2D(); // Used to draw onto the screen
	private static Group group = new Group(); // USed for the window
	private static int gameState = 1; // Determines what the screen will show
	
	/**
	 * This method will launch the game
	 * @param args
	 */
	public static void main(String[] args) {
		
		Background.images().images().add(new Image("file:resources/MapObjects/background1.png")); // Setting the background images
		Background.images().images().add(new Image("file:resources/MapObjects/background2.png"));
		Background.images().images().add(new Image("file:resources/MapObjects/background3.png"));
		Background.images().images().add(new Image("file:resources/MapObjects/titleScreen.png")); // Setting the menu screen
		Background.images().images().add(new Image("file:resources/MapObjects/winScreen.png")); // Setting the win screen
		
		for(int i = 1; i <= 7; i++) {
			Map.levels().add(new Level(i)); // Making the levels
		}
		
		try {
			File soundFile = new File("resources/music.wav"); // Setting the audio
			setClip(AudioSystem.getClip());
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip().open(audioIn);
			
			
			BufferedReader fileReader = new BufferedReader(new FileReader("resources/currentLevel.txt")); // What level we are currently on
			String num = fileReader.readLine();
			Map.setCurrentLevel(Integer.parseInt(num)); // set that current level
			
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		Map.watermelon().setCoord(new Vector(Map.levels().get(Map.currentLevel()).spawnPoint().x(), Map.levels().get(Map.currentLevel()).spawnPoint().y())); // Setting the spawnpoints for the watermelon
		Map.watermelon().setDeltaCoord(new Vector(Map.watermelon().coord().x(), Map.watermelon().coord().y())); // This is so that the delta coordinates are initially aligned with the players cord
		Map.addSnow(); // This will add the snow affects
		Map.levels().get(Map.currentLevel()).nextLevel(Map.watermelon()); // This will reset the entire level to align with the characters coordinates
		launch(args); // launch the game
	}

	/**
	 * making the stage / screen
	 * set the key pressed and released methods
	 * set the gameloop
	 */
	public void start(Stage window) throws Exception {
		try {

			group.getChildren().add(canvas);
		
			Scene scene = new Scene(group);
			
			scene.setOnKeyPressed(new OnKeyPressed()); // input once clicked
			scene.setOnKeyReleased(new OnKeyReleased()); // input once released
			
			window.setScene(scene);
			window.setTitle("VeggieTales The Sequel");
			window.show();
			
			
			Timeline gameLoop = new Timeline();
		    gameLoop.setCycleCount(Timeline.INDEFINITE);
		    double interval = (double) (Math.round(((double) 1 / 60) * 1000000)) / 1000; // run 60 times a second
		    KeyFrame keyframe = new KeyFrame(Duration.millis(interval), new GameLoop());
		    gameLoop.getKeyFrames().add(keyframe);
		    gameLoop.play(); // run the game loop
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static GraphicsContext gc() {
		return gc;
	}

	public static void setGc(GraphicsContext gc) {
		Main.gc = gc;
	}
	
	public static Canvas canvas() {
		return canvas;
	}

	public static void setCanvas(Canvas canvas) {
		Main.canvas = canvas;
	}

	public static Group group() {
		return group;
	}

	public static void setGroup(Group group) {
		Main.group = group;
	}

	public static int gameState() {
		return gameState;
	}

	public static void setGameState(int gameState) {
		Main.gameState = gameState;
	}

	public static Clip clip() {
		return clip;
	}

	public static void setClip(Clip clip) {
		Main.clip = clip;
	}


}
