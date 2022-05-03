package controller;

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
	private List<Bullet> bullets;
	private Timeline gameLoop;
	
	public BulletsController() {
		var pBuilder = new PlayerBuilder();
		this.player = pBuilder.health(new SimpleHealth())
				.hitbox(new Vector(10, 10))
				.position(new Vector(0, 0))
				.build();
		
		this.gameLoop = new Timeline(
				new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						System.out.println("Player [Position: " + player.getLives() + ", Life: " + "]");
					}
					
				}));
		gameLoop.setCycleCount(Timeline.INDEFINITE);
	}
}
