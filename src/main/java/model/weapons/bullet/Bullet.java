package model.weapons.bullet;

import util.Direction;
import util.Vector;
import model.character.Character;

/*
 * Bullet class models a bullet with its
 * current position, his owner (a reference
 * to the Character who shot it), its movement
 * direction and movement speed.
 */
public class Bullet {
	/*
	 * Reference of the Character who shot
	 */
	private final Character owner;
	
	/*
	 * Bullet's position
	 */
	private Vector position;
	
	/*
	 * Bullet's movement direction
	 */
	private Direction direction;
	
	/*
	 * Space traveled in a tick's time
	 */
	private double speed;
	
	public Bullet(final Character owner) {
		this.owner = owner;
		this.position = owner.getPosition();
		this.direction = owner.getAim().getDirection();
	}
	
	/*
	 * Moves the bullet forward. This method is
	 * called by Controller only when the bullet
	 * is not colliding with entities or tiles.
	 */
	public void tick() {
		// TODO: change position coords
	}
}
