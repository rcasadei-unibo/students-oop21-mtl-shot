package controller;

import java.util.Set;

import app.MetalShot;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import model.character.Character;
import model.character.Player;
import model.character.tools.health.SimpleHealth;
import util.Vector;

public class TemporaryController {
	private Timeline gameLoop;
	
	//Instance of model (Stage?)
	private Player player;
	private Player dummy;
	
	private final MetalShot viewReference;
	private BulletsController bulletsController;
	
	public TemporaryController(final MetalShot viewReference) {
		var pb = new Player.PlayerBuilder();
		this.player = pb.position(new Vector(0, 0))
				.health(new SimpleHealth())
				.lives(1)
				.hitbox(new Vector(32, 32))
				.build();
		
		var pb2 = new Player.PlayerBuilder();
		this.dummy = pb2.position(new Vector(50, 0))
				.health(new SimpleHealth())
				.lives(1)
				.hitbox(new Vector(32, 32))
				.build();
		
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
		this.bulletsController.addBullet(this.player);
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
	
	/*
	 * Returns every Character (the player, enemies, ...)
	 * currently in game. TODO: needs to be implemented differently
	 */
	public Set<Character> getAllCharacters() {
		return Set.of(this.player, this.dummy);
	}
}