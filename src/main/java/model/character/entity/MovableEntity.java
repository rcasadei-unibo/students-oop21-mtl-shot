package model.character.entity;

import utilities.EnviromentConstants;
import utilities.Vector;

public abstract class MovableEntity extends Entity{

	private boolean left;
	private boolean right;
	private boolean jump;
	private boolean crawl;
	private boolean fall;
	
	private Vector speed;
	
	public MovableEntity(final Vector hitbox, final Vector position) {
		super(hitbox, position);
		this.speed = new Vector(0,0);
	}
	
	public MovableEntity(final Vector hitbox, final Vector position, final Vector speed) {
		super(hitbox, position);
		this.speed = speed;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(final boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(final boolean right) {
		this.right = right;
	}

	public boolean isJumping() {
		return jump;
	}

	public void setJump(final boolean jump) {
		this.jump = jump;
	}

	public boolean isCrawling() {
		return crawl;
	}

	public void setCrawl(final boolean crawl) {
		this.crawl = crawl;
	}

	public boolean isFalling() {
		return fall;
	}

	public void setFall(final boolean fall) {
		this.fall = fall;
	}

	public Vector getSpeed() {
		return speed;
	}

	public void setSpeed(final Vector speed) {
		this.speed = speed;
	}
	
	void moveEntity() {
		if (right && !left) {
			this.setSpeed(new Vector(this.speed.getX() + EnviromentConstants.getHorizontalAcceleration(), this.speed.getY()));
		} else if (left && !right) {
			this.setSpeed(new Vector(this.speed.getX() - EnviromentConstants.getHorizontalAcceleration(), this.speed.getY()));
		} else {
			this.decelerate();
		}
	}
	
}
