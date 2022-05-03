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
	
	/*
	 * Bullet's damage (based on the weapon held by owner)
	 */
	private double damage;
	
	public Bullet(final Character owner) {
		this.owner = owner;
		this.position = owner.getPosition();
		this.direction = owner.getAim().getDirection();
		//this.damage = owner.getWeapon().getDamagePerBullet(); TODO: uncomment when Character's ready 
	}
	
	/*
	 * Moves the bullet forward. This method is
	 * called by Controller only when the bullet
	 * is not colliding with entities or tiles.
	 */
	public void tick() {
		// This implementation is in accordance with this (old) implementation of Vector class. TODO: update in the future
		if (this.direction.equals(Direction.UP)) {
			this.position = new Vector(this.position.getX(), this.position.getY() - this.speed);
		} else if (this.direction.equals(Direction.DOWN)) {
			this.position = new Vector(this.position.getX(), this.position.getY() + this.speed);
		} else if (this.direction.equals(Direction.LEFT)) {
			this.position = new Vector(this.position.getX() - this.speed, this.position.getY());
		} else if (this.direction.equals(Direction.RIGHT)) {
			this.position = new Vector(this.position.getX() + this.speed, this.position.getY());
		}
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

	/**
	 * @return the bullet's damage
	 */
	public double getDamage() {
		return this.damage;
	}

	@Override
	public String toString() {
		return "Bullet [owner=" + owner + ", position=" + position + ", direction=" + direction + ", speed=" + speed
				+ ", damage=" + damage + "]";
	}
}
