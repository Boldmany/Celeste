package character;

import javafx.scene.image.Image;

public class Watermelon extends Character{
	
	
	public Watermelon() {
		vector().setX(100);
		vector().setY(200);
		animation().images().add((new Image("file:resources/apple.png")));
		setWidth(animation().images().get(animation().imageIndex()).getWidth());
		setHeight(animation().images().get(animation().imageIndex()).getHeight());
	}

	public void move() {
	}
}
