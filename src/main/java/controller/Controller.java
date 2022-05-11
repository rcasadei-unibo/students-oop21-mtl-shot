package controller;
import javax.swing.text.html.parser.Entity;

import app.MetalShot;
import controller.enemy.RandomBot;
import controller.enemy.SimpleBot;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import model.character.Enemy;
import model.character.Player;
import model.character.tools.health.SimpleHealth;
import util.Vector;

/**
 * TODO
 * @author sysosus
 *
 */
public class Controller {
	private final Timeline gameLoop;
	
	//Instance of model (Stage?)
	private final MetalShot viewReference;
	
	public Controller(final MetalShot viewReference) {

		//SBAGLIATO, SOLO TEMPORANEO!!!!
		SimpleBot brain = new RandomBot();
		brain.getEntity().setPosition(825, 0);

		this.viewReference = viewReference;
		this.gameLoop = new Timeline(

				new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>() {

					int num = 0;

					@Override
					public void handle(ActionEvent event) {
						brain.move();
						viewReference.setEnemyPos(brain.getEntity().getPosition());
						System.out.println("Entity POS: " + brain.getEntity().getPosition());
						System.out.println("Actual POS: " + viewReference.getX());
						//viewReference.setCirclePos(new Vector(num++,0));
					}
				}));
		gameLoop.setCycleCount(Timeline.INDEFINITE);
	}
	
	public void gameStart() {
		gameLoop.play();
		// TODO
	}
	
	public void gamePause() {		// not in UML
		gameLoop.pause();
		// TODO show pause menu
	}
	
	public void gameReset() {
		// TODO
	}
	
	public void save() {
		// TODO
	}
	
	public void load() {
		// TODO
	}
	
	public void nextLevel() {
		// TODO
	}
}
