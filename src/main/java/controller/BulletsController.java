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
	//private Controller controllerReference; TODO UNCOMMENT AFTER MERGING
	private List<Bullet> bullets;
	private static final double EPSILON = 0.01d;
	
	public BulletsController(/* final Controller controllerReference TODO UNCOMMENT AFTER MERGING */) {
		//this.controllerReference = controllerReference; TODO UNCOMMENT AFTER MERGING
		this.bullets = new LinkedList<>();
	}
	
	public void controllerTick() {
		bullets.forEach(b -> {
			if (true) /* TODO Insert collision conditions here */ {
				bullets.remove(b);
			} else {
				b.tick();
			}
		});
		
		//System.out.print("Player [Position: " + player.getLives() + ", Life: " + "] ");
		System.out.print("Bullets: " + bullets.stream().map(b -> b.getPosition()).collect(Collectors.toList()));
		System.out.print("\n");
	}
	
	public void addBullet() {
		//this.bullets.add(new Bullet(this.controllerReference.getPlayer())); TODO UNCOMMENT AFTER MERGING
	}
	
	/*
	 * Tool to compare doubles given a certain tolerance.
	 * Should be put in an utility class (?)
	 */
	private boolean equalsForDouble(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }
}
