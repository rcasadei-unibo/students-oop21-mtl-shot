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
	
	/**The movableEntity constructor*/
	public MovableEntity(final Vector position, final Vector hitbox) {
		super(position, hitbox);
		this.speed = new Vector();
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
	 * Sets the input values to the current @speed
	 * */
	public void setSpeed(final double x, final double y) {
		this.speed.setX(x);
		this.speed.setY(y);
	}
	
	/**
	 * The main method that reads all the boolean fields, updates the speed and updates the current position
	 * */
	public void moveEntity() {		
		final Vector update = new Vector();
		if (right && !left) {
			update.setX(EnvironmentConstants.getHorizontalAcceleration());
		} else if (left && !right) {
			update.setX(-EnvironmentConstants.getHorizontalAcceleration());
		} else {
			this.decelerate();
		}
		if (jump && !fall) {
			this.crawl = false;
			this.fall = true;
			update.setY(EnvironmentConstants.getJump());
		}
		if (fall) {
			update.setY(EnvironmentConstants.getGravity());
		}
		this.speed.sum(update);
		super.setPosition(super.getPosition().getX() + this.speed.getX(),
				          super.getPosition().getY() + this.speed.getY());
		//super.getPosition().setX(super.getPosition().getX() + this.speed.getX());
		//super.getPosition().setY(super.getPosition().getY() + this.speed.getY());
		//super.setPosition(new Vector(super.getPosition().getX() + this.speed.getX(), super.getPosition().getY() + this.speed.getY()));
		//super.setPosition(update);
	}
	
	/**
	 * Resets the entity intentions and the @speed at zero
	 * */
	public void reset() {
		this.left = false;
		this.right = false;
		this.crawl = false;
		this.jump = false;
		this.fall = false;
		this.speed = new Vector();
	}

	private void decelerate() {
		final Vector update = new Vector();
		if (this.speed.getX() < -EnvironmentConstants.getDeceleration()) {
			update.setX(EnvironmentConstants.getDeceleration());
		} else if(this.speed.getX() > EnvironmentConstants.getDeceleration()) {
			update.setX(-EnvironmentConstants.getDeceleration());
		}
		this.speed.sum(update);
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n" + 
				"Speed: " + this.speed;
	}
}
