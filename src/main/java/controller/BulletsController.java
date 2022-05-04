package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.character.Character;
import model.weapons.bullet.Bullet;

/*
 * This is a temporary class made to test the
 * bullet's behavior.
 * 
 * THIS COMMENTS NEEDS TO BE CLEANED!
 */
public class BulletsController {
	//private Controller controllerReference; TODO UNCOMMENT AFTER MERGING
	private TemporaryController controllerReference;
	private List<Bullet> bullets;
	private static final double EPSILON = 0.01d;
	
	public BulletsController(final TemporaryController controllerReference /* final Controller controllerReference TODO UNCOMMENT AFTER MERGING */) {
		this.controllerReference = controllerReference;
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
	
	public void addBullet(final Character owner) {
		this.bullets.add(new Bullet(owner));
	}
	
	/*
	 * This method checks if Bullet b is colliding with
	 * any of the current Characters in game
	 */
	private Optional<Character> checkCharactersColliding(Bullet b) {
		/*for (var c : this.controllerReference.getAllCharacters()) {
			if (b.isColliding(this.controllerReference.getPlayer())) {	TODO UNCOMMENT AFTER MERGING
				return Optional.of(this.controllerReference.getPlayer());
			}
		}*/
		return Optional.empty();
	}
	
	/*
	 * Tool to compare doubles given a certain tolerance.
	 * Should be put in an utility class (?)
	 */
	private boolean equalsForDouble(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }
}
