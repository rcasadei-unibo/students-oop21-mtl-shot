package controller;

import java.util.LinkedList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import model.weapons.bullet.Bullet;
import util.Vector;

/*
 * This is a temporary class made to test the
 * bullet's behavior.
 */
public class BulletsController {
	private Player player;
	private Player dummy;
	private List<Bullet> bullets;
	private Timeline gameLoop;
	
	public BulletsController() {
		/**
		 * This part is for tests!!
		 */
		var pBuilder = new PlayerBuilder();
		this.player = pBuilder.health(new SimpleHealth())
				.hitbox(new Vector(10, 10))
				.position(new Vector(0, 0))
				.build();
		
		var dBuilder = new PlayerBuilder();
		this.dummy = dBuilder.health(new SimpleHealth())
				.hitbox(new Vector(10, 10))
				.position(new Vector(50, 50))
				.build();
		
		this.bullets = new LinkedList<>();
		
		this.gameLoop = new Timeline(
				new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						System.out.print("Player [Position: " + player.getLives() + ", Life: " + "] ");
						System.out.print("Bullets: " + bullets);
						System.out.print("\n");
					}
					
				}));
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		
		this.gameLoop.play();
	}
	
	public void addBullet() {
		this.bullets.add(new Bullet(this.player));
	}
}
