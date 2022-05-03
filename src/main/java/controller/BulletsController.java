package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
 * 
 * THIS COMMENTS NEEDS TO BE CLEANED!
 */
public class BulletsController {
	private Player player;
	//private Player dummy;
	private List<Bullet> bullets;
	//private Timeline gameLoop;
	
	private static final double EPSILON = 0.01d;
	
	public BulletsController() {
		/**
		 * This part is for tests!!
		 */
		var pBuilder = new PlayerBuilder();
		this.player = pBuilder.health(new SimpleHealth())
				.hitbox(new Vector(10, 10))
				.position(new Vector(0, 0))
				.build();
		
		/*var dBuilder = new PlayerBuilder();
		this.dummy = dBuilder.health(new SimpleHealth())
				.hitbox(new Vector(10, 10))
				.position(new Vector(50, 50))
				.build();*/
		
		this.bullets = new LinkedList<>();
		
		/*this.gameLoop = new Timeline(
				new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						bullets.forEach(b -> {
							if (equalsForDouble(b.getPosition().getX(), dummy.getPosition().getX())) {
								bullets.remove(b);
							} else {
								b.tick();
							}
						});
						System.out.println("");
						
						//System.out.print("Player [Position: " + player.getLives() + ", Life: " + "] ");
						System.out.print("Bullets: " + bullets.stream().map(b -> b.getPosition()).collect(Collectors.toList()));
						System.out.print("\n");
					}
					
				}));
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		
		this.gameLoop.play();*/
	}
	
	public void controllerTick() {
		bullets.forEach(b -> {
			if (true) /* Insert collision conditions here */ {
				bullets.remove(b);
			} else {
				b.tick();
			}
		});
		System.out.println("");
		
		//System.out.print("Player [Position: " + player.getLives() + ", Life: " + "] ");
		System.out.print("Bullets: " + bullets.stream().map(b -> b.getPosition()).collect(Collectors.toList()));
		System.out.print("\n");
	}
	
	public void addBullet() {
		this.bullets.add(new Bullet(this.player));
	}
	
	private boolean equalsForDouble(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }
}
