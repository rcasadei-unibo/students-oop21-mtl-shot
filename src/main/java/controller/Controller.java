package controller;

import java.io.IOException;

import app.MetalShot;
import controller.map.MapController;
import controller.player.PlayerController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import util.map.TextMap;

public class Controller {
    
    private final PlayerController playerController;
    private final MapController mapController;
	private final Timeline gameLoop;
	
	//Instance of model (Stage?)
	private final MetalShot viewReference;
	
	public Controller(final MetalShot viewReference) throws IOException {
        final TextMap textMap = new TextMap("src\\main\\resources\\map.txt");
	    this.mapController = new MapController(textMap, 0);
	    this.viewReference = viewReference;
	    this.playerController = new PlayerController(this.viewReference.getPlayerView(), mapController); //null -> player view
		this.gameLoop = new Timeline(
				new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(final ActionEvent event) {
						// TODO implement game loop here

							// Move player
								// Jumping and falling included
							// Shoot (player)
							
							// Move/shoot enemies (based on Susca's AI)
							
							// Check for colliding bullets
						System.out.println(viewReference.getEntityCount());
						viewReference.getCamera().setTranslateX((((viewReference.getPlayerView().getPlayerImageView().getX()-192) - viewReference.getCamera().getTranslateX())/20) + viewReference.getCamera().getTranslateX());
						viewReference.getCamera().setTranslateY((((viewReference.getPlayerView().getPlayerImageView().getY()-192) - viewReference.getCamera().getTranslateY())/20) + viewReference.getCamera().getTranslateY());
					    playerController.check();
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
		if (key == KeyCode.A) {
		    playerController.getPlayer().setLeft(true);
		}
		if (key == KeyCode.D) {
		    playerController.getPlayer().setRight(true);
		}
		if (key == KeyCode.SPACE) {
		    playerController.getPlayer().setJump(true);
		}
		if (key == KeyCode.S) {
		    playerController.getPlayer().setCrouchKey(true);
		}
	}
	
	public void keyReleased(final KeyCode key) {
		if (key == KeyCode.A) {
		    playerController.getPlayer().setLeft(false);
		}
		if (key == KeyCode.D) {
		    playerController.getPlayer().setRight(false);
		}
		if (key == KeyCode.SPACE) {
		    playerController.getPlayer().setJump(false);
		}
		if (key == KeyCode.S) {
		    playerController.getPlayer().setCrouchKey(false);
		}
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
	
	public MapController getMapController() {
	    return this.mapController;
	}
}
