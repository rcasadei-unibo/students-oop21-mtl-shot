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
import model.character.Player;
import model.character.tools.health.SimpleHealth;
import model.weapons.P2020;
import model.weapons.PeaceKeeper;
import util.Direction;
import util.Vector;

public class TemporaryController {
	private Timeline gameLoop;
	
	//Instance of model (Stage?)
	private Player player;
	private Player dummy;
	
	private final MetalShot viewReference;
	private BulletsController bulletsController;
	private WeaponController weaponController;
	
	private boolean turn = false;
	
	public TemporaryController(final MetalShot viewReference) {
		var pb = new Player.PlayerBuilder();
		this.player = pb.position(new Vector(0, 0))
				.health(new SimpleHealth())
				.lives(1)
				.hitbox(new Vector(32, 32))
				.build();
		this.player.setWeapon(new PeaceKeeper());
		System.out.println(this.player);
		System.out.println("\n");
		
		var pb2 = new Player.PlayerBuilder();
		this.dummy = pb2.position(new Vector(50, 0))
				.health(new SimpleHealth())
				.lives(1)
				.hitbox(new Vector(32, 32))
				.build();
		this.dummy.getAim().setDirection(Direction.LEFT);
		this.dummy.setWeapon(new P2020());
		System.out.println(this.dummy);
		
		this.viewReference = viewReference;
		this.gameLoop = new Timeline(
				new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						weaponController.controllerTick();
						bulletsController.controllerTick();
					}
					
				}));
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		
		this.bulletsController = new BulletsController(this);
		this.weaponController = new WeaponController(this);
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
		if (turn) {	
			if (this.weaponController.tryToShoot(this.player)) {
				this.bulletsController.addBullet(this.player);
			}
			this.turn = false;
		} else {
			if (this.weaponController.tryToShoot(this.dummy)) {
				this.bulletsController.addBullet(this.dummy);
			}
			this.turn = true;
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
	 * currently in game. TODO: needs to be implemented differently
	 */
	public Set<Character> getAllCharacters() {
		return Set.of(this.player, this.dummy);
	}
	
	/*
	 * Returns a map where every entry represents
	 * a bullet's position and direction
	 */
	public Map<Vector, Direction> getBullets() {
		Map<Vector, Direction> ret = new HashMap<>();
		
		for (var b : this.bulletsController.getBullets()) {
			ret.put(b.getPosition(), b.getDirection());
		}
		
		return ret;
	}
}