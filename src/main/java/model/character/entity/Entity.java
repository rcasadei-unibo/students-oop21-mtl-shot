package model.character.entity;

import utilities.Vector;

public abstract class Entity {

	private final Vector hitbox;
	private final Vector position;
	
	public Entity(final Vector hitbox, final Vector position) {
		this.hitbox = hitbox;
		this.position = position;
	}
	
	public Vector getHitbox() {
		return this.hitbox;
	}
	
	public Vector getPosition() {
		return this.position;
	}
	/* x1 <= x2 <= (x1 + w1) && y1 <= y2 <= (y1 + h1)
	 * 					     ||
	 * x2 <= x1 <= (x2 + w2) && y2 <= y1 <= (y2 + h2)
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
