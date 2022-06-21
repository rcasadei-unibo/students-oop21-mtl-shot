package model.weapons;

import util.Direction;
import util.Vector2D;
import javafx.scene.shape.Rectangle;
import model.Entity;
import model.character.Character;

/**
 * Bullet class models a bullet with its current position, his owner (a
 * reference to the Character who shot it), its movement direction and movement
 * speed.
 * 
 */
public class Bullet extends Entity {

    /*
     * Reference of the Character who shot
     */
    private final Character owner;

    /*
     * Bullet's movement direction
     */
    private Direction direction;

    /*
     * Space traveled in a tick's time
     */
    private Vector2D speed;

    /*
     * Bullet's damage (based on the weapon held by owner)
     */
    private int damage;

    /*
     * It is true if the bullet has hit something
     */
    private boolean hit;

    /**
     * TODO: write javadoc.
     *
     */
    public Bullet(final Character owner) {
        super(new Vector2D(owner.getPosition().getX() + owner.getHitbox().getX() / 2,
                owner.getPosition().getY() + owner.getHitbox().getY() / 2),
                new Vector2D(0.1, 0.1)); // TODO: change magic
                                                                                             // numbers
        System.out.println(owner.getPosition().toString());
        this.owner = owner;
        this.direction = owner.getAim().getDirection();
        this.speed = new Vector2D(0.05, 0.05);
        this.hit = false;
        this.damage = owner.getWeapon().getDamagePerBullet();
    }

    /**
     * Moves the bullet forward. This method is called by Controller only when the
     * bullet is not colliding with entities or tiles.
     */
    public void tick() {
        // This implementation is in accordance with this (old) implementation of Vector
        // class. TODO: update in the future
        if (this.direction.equals(Direction.UP)) {
            super.setPosition(new Vector2D(super.getPosition().getX(), super.getPosition().getY() - this.speed.getY()));
        } else if (this.direction.equals(Direction.DOWN)) {
            super.setPosition(new Vector2D(super.getPosition().getX(), super.getPosition().getY() + this.speed.getY()));
        } else if (this.direction.equals(Direction.LEFT)) {
            super.setPosition(new Vector2D(super.getPosition().getX() - this.speed.getX(), super.getPosition().getY()));
        } else if (this.direction.equals(Direction.RIGHT)) {
            super.setPosition(new Vector2D(super.getPosition().getX() + this.speed.getX(), super.getPosition().getY()));
        }
    }

    /**
     * @return the bullet's owner
     */
    public Character getOwner() {
        return this.owner;
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
    public Vector2D getSpeed() {
        return this.speed;
    }

    /**
     * @return the bullet's damage
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * @return true if the bullet has hit something
     */
    public boolean hasHit() {
        return hit;
    }

    /**
     * Sets hit to true.
     */
    public void hitSomething() {
        this.hit = true;
    }

    @Override
    public String toString() {
        return "Bullet [position=" + super.getPosition() + ", direction=" + direction + ", speed=" + speed + ", damage="
                + damage + "]";
    }

    @Override
    public boolean isColliding(final Entity entity) {
        if (entity.equals(this.owner)) {
            return false;
        }
        return super.isColliding(entity);
    }

}
