package model.character.movableEntity;

import model.Entity;
import util.Vector;
/**
 * An extension of the abstract class Entity which also has the ability to move in a 2D world.
 * A MovableEntity can jump, crawl, fall and go left and right.
 * */
public abstract class MovableEntity extends Entity {

    /**
     * Represent the entity intention to go left
     * */
	private boolean left;
	/**
	 * Represent the entity intention to go right
	 * */
	private boolean right;
	/**
	 * Represent the entity intention to jump
	 * */
	private boolean jump;
	/**
	 * Represent the entity intention to crawl
	 * */
	private boolean crawl;
	/**
	 * Represent if the entity has to fall or not
	 * */
	private boolean fall;
	
	/**
	 * Field used to create an acceleration and a deceleration
	 * */
	private Vector speed;
	
	public MovableEntity(final Vector position, final Vector hitbox) {
		super(position, hitbox);
		this.speed = new Vector(0,0);
	}
	
	/**
	 * A constructor for the movableEntity that already starts with an initial speed (e.g. could be useful for bullets)
	 * */
	public MovableEntity(final Vector position, final Vector hitbox, final Vector speed) {
		super(position, hitbox);
		this.speed = speed;
	}

	/**
	 * @return the entity's intention to go left
	 * */
	public boolean isLeft() {
		return left;
	}
	
	/**
	 * Sets the field left to @left
	 * */
	public void setLeft(final boolean left) {
		this.left = left;
	}
	
	/**
	 * @return the entity's intention to go right
	 * */
	public boolean isRight() {
		return right;
	}
	
	/**
	 * Sets the field right to @right
	 * */
	public void setRight(final boolean right) {
		this.right = right;
	}

	/**
	 * @return the entity's intention to jump
	 * */
	public boolean isJumping() {
		return jump;
	}

	/**
	 * Sets the field jump to @jump
	 * */
	public void setJump(final boolean jump) {
		this.jump = jump;
	}

	/**
	 * @return the entity's intention to crawl
	 * */
	public boolean isCrawling() {
		return crawl;
	}

	/**
	 * Sets the field crawl to @crawl
	 * */
	public void setCrawl(final boolean crawl) {
		this.crawl = crawl;
	}

	/**
	 * @return if the entity has to fall or not (used to keep it from falling through the ground)
	 * */
	public boolean isFalling() {
		return fall;
	}

	/**
	 * Sets the field fall to @fall
	 * */
	public void setFall(final boolean fall) {
		this.fall = fall;
	}

	/**
	 * @return a vector that represents the actual speed of the entity
	 * */
	public Vector getSpeed() {
		return speed;
	}

	/**
	 * Sets the entity speed vector to @speed
	 * */
	public void setSpeed(final Vector speed) {
		this.speed = speed;
	}
	
	/**
	 * The main method that reads all the boolean fields, updates the speed and updates the current position
	 * */
	public void moveEntity() {
		if (right && !left) {
			this.setSpeed(new Vector(this.speed.getX() + EnvironmentConstants.getHorizontalAcceleration(), this.speed.getY()));
		} else if (left && !right) {
			this.setSpeed(new Vector(this.speed.getX() - EnvironmentConstants.getHorizontalAcceleration(), this.speed.getY()));
		} else {
			this.decelerate();
		}
		if (jump && !fall) {
			this.crawl = false;
			this.fall = true;
			this.setSpeed(new Vector(this.speed.getX(), EnvironmentConstants.getJump()));
		}
		if (fall) {
			this.setSpeed(new Vector(this.speed.getX(), this.speed.getY() + EnvironmentConstants.getGravity()));
		}
		super.setPosition(new Vector(super.getPosition().getX() + this.speed.getX(), super.getPosition().getY() + this.speed.getY()));
	}

	private void decelerate() {
		double update = 0;
		if (this.speed.getX() < -EnvironmentConstants.getDeceleration()) {
			update = this.speed.getX() + EnvironmentConstants.getDeceleration();
		} else if(this.speed.getX() > EnvironmentConstants.getDeceleration()) {
			update = this.speed.getX() - EnvironmentConstants.getDeceleration();
		}
		this.setSpeed(new Vector(update, this.speed.getY()));
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n" + 
				"Speed: " + this.speed;
	}
}
