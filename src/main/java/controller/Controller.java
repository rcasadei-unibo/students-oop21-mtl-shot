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
import model.character.tools.health.SimpleHealth;
import util.Vector;

public class Controller {
	private Timeline gameLoop;
	
	//Instance of model (Stage?)
	private final MetalShot viewReference;	
	
	public Controller(final MetalShot viewReference) {
		
		//SBAGLIATO, SOLO TEMPORANEO!!!!
		SimpleBot brain = new RandomBot();
		
		this.viewReference = viewReference;
		this.gameLoop = new Timeline(
				new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						brain.move();
						System.out.println(brain.getEntity().getPosition());
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
	
	public void keyPressed(final KeyCode key) {
		// TODO
	}
	
	public void keyReleased(final KeyCode key) {
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
