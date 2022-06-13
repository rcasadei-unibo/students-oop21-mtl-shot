package model.character.movableentity;

import model.Entity;
import util.Vector2D;

/**
 * An extension of the abstract class Entity which also has the ability to move
 * in a 2D world. A MovableEntity can jump, crawl, fall and go left and right.
 */
public abstract class MovableEntity extends Entity {
    /**
     * Represent the entity intention to go left.
     */
    private boolean left;
    /**
     * Represent the entity intention to go right.
     */
    private boolean right;
    /**
     * Represent the entity intention to jump.
     */
    private boolean jump;
    /**
     * Represent the current crouch state.
     */
    private boolean crouch;
    /**
     * Represent if the entity has to fall or not.
     */
    private boolean fall;
    /**
     * Field used to create an acceleration and a deceleration.
     */
    private final Vector2D speed;

    /**
     * The MovableEntity constructor that sets the position and the hitbox.
     * 
     * @param position
     * @param hitbox
     */
    public MovableEntity(final Vector2D position, final Vector2D hitbox) {
        super(position, hitbox);
        this.speed = new Vector2D();
    }

    /**
     * A constructor for the movableEntity that already starts with an initial speed.
     * 
     * @param position
     * @param hitbox
     * @param speed
     */
    public MovableEntity(final Vector2D position, final Vector2D hitbox, final Vector2D speed) {
        super(position, hitbox);
        this.speed = speed;
    }

    /**
     * Gets the entity's intention to go left.
     * 
     * @return left
     */
    public boolean isLeft() {
        return this.left;
    }

    /**
     * Sets the field left.
     * 
     * @param left
     */
    public void setLeft(final boolean left) {
        this.left = left;
    }

    /**
     * Gets the entity's intention to go right.
     * 
     * @return right
     */
    public boolean isRight() {
        return this.right;
    }

    /**
     * Sets the field right.
     * 
     * @param right
     */
    public void setRight(final boolean right) {
        this.right = right;
    }

    /**
     * Gets the entity's intention to jump.
     * 
     * @return jump
     */
    public boolean isJumping() {
        return this.jump;
    }

    /**
     * Sets the field jump.
     * 
     * @param jump
     */
    public void setJump(final boolean jump) {
        this.jump = jump;
    }

    /**
     * Gets the entity's crouch state.
     * 
     * @return crouch
     */
    public boolean isCrouching() {
        return this.crouch;
    }

    /**
     * Sets the field crouch.
     * 
     * @param crouch
     */
    public void setCrouch(final boolean crouch) {
        if (this.crouch != crouch && crouch) {
            this.decreaseHitbox();
        } else if (this.crouch != crouch && !crouch) {
            this.increaseHitbox();
        }
        this.crouch = crouch;
    }

    /**
     * Gets the falling state.
     * 
     * @return if the entity is falling
     */
    public boolean isFalling() {
        return this.fall;
    }

    /**
     * Sets the field fall.
     * 
     * @param fall
     */
    public void setFall(final boolean fall) {
        this.fall = fall;
    }

    /**
     * Gets the entity speed as a Vector2D.
     * 
     * @return the current speed
     */
    public Vector2D getSpeed() {
        return this.speed;
    }

    /**
     * Sets the input values to the current speed.
     * 
     * @param x
     * @param y
     */
    public void setSpeed(final double x, final double y) {
        this.speed.setX(x);
        this.speed.setY(y);
    }

    /**
     * The main method that reads all the boolean fields, updates the speed and
     * updates the current position.
     */
    public void moveEntity() {
        final Vector2D update = new Vector2D();
        if (this.right && !this.left) {
            update.setX(EntityConstants.ACCELERATION);
        } else if (this.left && !this.right) {
            update.setX(-EntityConstants.ACCELERATION);
        } else {
            update.setX(this.decelerate());
        }
        if (this.jump && !this.fall) {
            this.fall = true;
            update.setY(EntityConstants.JUMP);
        }
        if (this.fall) {
            update.setY(update.getY() + EntityConstants.GRAVITY);
        }
        this.speed.add(update);
        this.maxSpeedCheck();
        super.setPosition(super.getPosition().getX() + this.speed.getX(),
                super.getPosition().getY() + this.speed.getY());
    }

    /**
     * Resets the entity's fields.
     */
    public void reset() {
        this.left = false;
        this.right = false;
        this.crouch = false;
        this.jump = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + " SPEED: " + this.speed;
    }

    private void increaseHitbox() {
        super.setPosition(
                new Vector2D(this.getPosition().getX(), this.getPosition().getY() - super.getHitbox().getY()));
        super.getHitbox().setY(super.getHitbox().getY() * 2);
    }

    private void decreaseHitbox() {
        super.getHitbox().setY(super.getHitbox().getY() / 2);
        super.setPosition(
                new Vector2D(this.getPosition().getX(), this.getPosition().getY() + super.getHitbox().getY()));
    }

    private void maxSpeedCheck() {
        if (this.speed.getX() > EntityConstants.MAXHORIZONTALSPEED) {
            this.speed.setX(EntityConstants.MAXHORIZONTALSPEED);
        } else if (this.speed.getX() < -EntityConstants.MAXHORIZONTALSPEED) {
            this.speed.setX(-EntityConstants.MAXHORIZONTALSPEED);
        }
        if (this.speed.getY() > EntityConstants.MAXVERTICALSPEED) {
            this.speed.setY(EntityConstants.MAXVERTICALSPEED);
        } else if (this.speed.getY() < EntityConstants.JUMP) {
            this.speed.setY(EntityConstants.JUMP);
        }
    }

    private double decelerate() {
        double update = -this.speed.getX();
        if (this.speed.getX() < -EntityConstants.DECELERATION) {
            update = EntityConstants.DECELERATION;
        } else if (this.speed.getX() > EntityConstants.DECELERATION) {
            update = -EntityConstants.DECELERATION;
        }
        return update;
    }
}
