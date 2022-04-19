package model;

import util.Vector;
/**
 * An abstract class that represents an object with collisions, implementable in any game.
 * It's composed of an hitbox and a position, each one of them is identified by a Vector. 
 * */
public abstract class Entity {

    /**
     * It represents a box that has width and height
     * */
	private Vector hitbox;
	/**
	 * It represents a point in the playable space
	 * */
	private Vector position;
	
	public Entity(final Vector position, final Vector hitbox) {
		this.hitbox = hitbox;
		this.position = position;
	}
	
	/**
	 * @return the hitbox of the entity
	 * */
	public Vector getHitbox() {
		return this.hitbox;
	}
	
	/**
	 * @return the position of the entity
	 * */
	public Vector getPosition() {
		return this.position;
	}
	
	/**
	 * set the position of the entity to @position
	 * */
	public void setPosition(final Vector position) {
		this.position = position;
	}
	
	/**
	 * set the hitbox of the entity to @hitbox
	 * */
	public void setHitbox(final Vector hitbox) {
	    this.consistencyCheck(hitbox);
		this.hitbox = hitbox;
	}
	
	private void consistencyCheck(final Vector vec) {
	    if (vec.getX() <= 0 || vec.getY() <= 0 || vec == null) {
            throw new IllegalArgumentException();
        }
	}
	
	/* x1 <= x2 <= (x1 + w1) && y1 <= y2 <= (y1 + h1)
	 * 					     ||
	 * x2 <= x1 <= (x2 + w2) && y2 <= y1 <= (y2 + h2)
	 */
	/**
	 * a method that @return if this entity and the given @entity are colliding
	 * */
	public boolean isColliding(final Entity entity) {
		return this.getPosition().getX() <= entity.getPosition().getX() && entity.getPosition().getX() <= (this.getPosition().getX() + this.getHitbox().getX()) &&
			   this.getPosition().getY() <= entity.getPosition().getY() && entity.getPosition().getY() <= (this.getPosition().getY() + this.getHitbox().getY()) ||
			   entity.getPosition().getX() <= this.getPosition().getX() && this.getPosition().getX() <= (entity.getPosition().getX() + entity.getHitbox().getX()) &&
			   entity.getPosition().getY() <= this.getPosition().getY() && this.getPosition().getY() <= (entity.getPosition().getY() + entity.getHitbox().getY());
	}
	
	@Override
	public String toString() {
		return "pos: " + this.position + " hitbox: " + this.hitbox;
	}
}
