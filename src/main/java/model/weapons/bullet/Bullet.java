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

	/**
	 * @return the bullet's owner
	 */
	public Character getOwner() {
		return this.owner;
	}

	/**
	 * @return the bullet's position
	 */
	public Vector getPosition() {
		return this.position;
	}

	/**
	 * @return the bullet's direction
	 */
	public Direction getDirection() {
		return this.direction;
	}

	/**
	 * @return the bullet's speed
	 */
	public double getSpeed() {
		return this.speed;
	}
}
