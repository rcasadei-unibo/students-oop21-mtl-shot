package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import app.MetalShot;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import model.character.Character;
import util.Direction;
import util.Vector;
import view.sounds.SoundManager;
import view.sounds.SoundManager.Sounds;

/**
 * TODO: write javadoc
 *
 */
public class Controller {
	private Timeline gameLoop;
	
	//Instance of model (Stage?)
	private final MetalShot viewReference;
	private SoundManager sm;
	
	// Subcontrollers
	private BulletsController bulletsController;
	private WeaponController weaponController;
	private SoundsController soundsController;
	
	public Controller(final MetalShot viewReference) {
		this.viewReference = viewReference;
		
		this.sm = new SoundManager();
		
		// Init subcontrollers
		this.bulletsController = new BulletsController(this);
		this.weaponController = new WeaponController(this);
		this.soundsController = new SoundsController();
		
		this.gameLoop = new Timeline(
				new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						// TODO implement game loop here
							
							// Check for colliding bullets
							weaponController.controllerTick();
							bulletsController.controllerTick();
							soundsController.controllerTick();
							viewReference.displayBullets(getBullets());
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
		if (key.equals(KeyCode.E)) {
			/*
			 *     // Shooting sintax
			 * if (this.weaponController.tryToShoot(this.player)) {
			 *     this.bulletsController.addBullet(this.player);
			 * }
			 * 
			 */
			System.out.println("Shooting...");
			this.soundsController.tryToPlaySound(Sounds.BULLET_FIRING);
		} else if (key.equals(KeyCode.R)) {
			/*
			 *     // The player reloads
			 * this.player.getWeapon().reload();
			 * 
			 */
			System.out.println("Reloading...");
			this.soundsController.tryToPlaySound(Sounds.MAIN_THEME);
		}
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
	 * currently in game.
	 */
	public Set<Character> getAllCharacters() {
		// TODO
		return null;
	}
	
	/*
	 * Returns a map where every entry represents
	 * a bullet's position and direction.
	 */
	public Map<Vector, Direction> getBullets() {
		Map<Vector, Direction> ret = new HashMap<>();
		
		for (var b : this.bulletsController.getBullets()) {
			ret.put(b.getPosition(), b.getDirection());
		}
		
		return ret;
	}
}
