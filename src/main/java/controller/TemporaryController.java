package controller;

import app.MetalShot;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class TemporaryController {
	private Timeline gameLoop;
	
	//Instance of model (Stage?)
	private final MetalShot viewReference;
	private BulletsController bulletsController;
	
	public TemporaryController(final MetalShot viewReference) {
		this.viewReference = viewReference;
		this.gameLoop = new Timeline(
				new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						bulletsController.controllerTick();
					}
					
				}));
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		
		this.bulletsController = new BulletsController(this);
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