package utilities.entity;

import utilities.Vector;
/**An extension of the abstract class Entity which also has the ability to move in a 2D world.
 * A MovableEntity can jump, crawl, fall and go left and right.*/
public abstract class MovableEntity extends Entity {

	private boolean left;
	private boolean right;
	private boolean jump;
	private boolean crawl;
	private boolean fall;
	
	/**Field used to create an acceleration and a deceleration*/
	private Vector speed;
	
	public MovableEntity(final Vector hitbox, final Vector position) {
		super(hitbox, position);
		this.speed = new Vector(0,0);
	}
	
	/**A constructor for the movableEntity that already start with an initial speed (e.g. could be useful for bullets)*/
	public MovableEntity(final Vector hitbox, final Vector position, final Vector speed) {
		super(hitbox, position);
		this.speed = speed;
	}

	/**@return the entity intention to go left*/
	public boolean isLeft() {
		return left;
	}
	
	/**set the field left*/
	public void setLeft(final boolean left) {
		this.left = left;
	}
	
	/**@return the entity intention to go right*/
	public boolean isRight() {
		return right;
	}
	
	/**set the field right*/
	public void setRight(final boolean right) {
		this.right = right;
	}

	/**@return the entity intention to jump*/
	public boolean isJumping() {
		return jump;
	}

	/**set the field jump*/
	public void setJump(final boolean jump) {
		this.jump = jump;
	}

	/**@return the entity intention to crawl*/
	public boolean isCrawling() {
		return crawl;
	}

	/**set the field crawl*/
	public void setCrawl(final boolean crawl) {
		this.crawl = crawl;
	}

	/**@return if the entity has to fall or to stay (used to keep it from falling into the ground)*/
	public boolean isFalling() {
		return fall;
	}

	/**set the field fall*/
	public void setFall(final boolean fall) {
		this.fall = fall;
	}

	/**@return a vector that represent the actual speed of the entity*/
	public Vector getSpeed() {
		return speed;
	}

	/**set the entity speed vector*/
	public void setSpeed(final Vector speed) {
		this.speed = speed;
	}
	
	/**The main method that read all the boolean field, update the actual speed and update the current position*/
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
