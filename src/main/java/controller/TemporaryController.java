package controller;

import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import model.character.Player;
import model.weapons.bullet.Bullet;

/*
 * This is a temporary class made to test the
 * bullet's behavior.
 */
public class TemporaryController {
	private Player player;
	private List<Bullet> bullets;
	private Timeline gameLoop;
	
	public TemporaryController() {
		this.gameLoop = new Timeline(
				new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						
					}
					
				}));
		gameLoop.setCycleCount(Timeline.INDEFINITE);
	}
}
