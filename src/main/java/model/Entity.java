package model;

import util.Vector2D;

/**
 * An abstract class that represents an object with collisions, implementable in
 * any game. It's composed by an hitbox and a position, each one of them is
 * identified by a Vector.
 */
public class Entity {
    /**
     * It represents a box that has width and height.
     */
    private Vector2D hitbox;
    /**
     * It represents a point in the playable space.
     */
    private Vector2D position;

    /**
     * The entity constructor that initializes the position and the hitbox.
     * 
     * @param position
     * @param hitbox
     */
    public Entity(final Vector2D position, final Vector2D hitbox) {
        this.hitbox = hitbox;
        this.position = position;
    }

    /**
     * Gets the entity hitbox.
     * 
     * @return the hitbox
     */
    public Vector2D getHitbox() {
        return this.hitbox;
    }

    /**
     * Gets the entity position.
     * 
     * @return the position
     */
    public Vector2D getPosition() {
        return this.position;
    }

    /**
     * Sets the entity position.
     * 
     * @param position
     */
    public void setPosition(final Vector2D position) {
        this.position = position;
    }

    /**
     * Sets the position's component of the entity.
     * 
     * @param x
     * @param y
     */
    public void setPosition(final double x, final double y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    /**
     * Sets the hitbox's component of the entity.
     * 
     * @param hitbox
     */
    public void setHitbox(final Vector2D hitbox) {
        if (hitbox.getX() <= 0 || hitbox.getY() <= 0) {
            throw new IllegalArgumentException();
        }
        this.hitbox = hitbox;
    }

    /**
     * Sets the hitbox's component of the entity.
     * 
     * @param x
     * @param y
     */
    public void setHitbox(final double x, final double y) {
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException();
        }
        this.hitbox.setX(x);
        this.hitbox.setY(y);
    }

    /*
     * x1 <= x2 <= (x1 + w1) && y1 <= y2 <= (y1 + h1) 
     *                       || 
     * x2 <= x1 <= (x2 + w2) && y2 <= y1 <= (y2 + h2)
     */
    /**
     * Returns if the current entity and the passed entity are overlapped.
     * 
     * @param entity
     * @return if the entities are colliding
     */
    public boolean isColliding(final Entity entity) {
        final double x1 = this.getPosition().getX();
        final double y1 = this.getPosition().getY();
        final double w1 = this.getHitbox().getX();
        final double h1 = this.getHitbox().getY();
        final double x2 = entity.getPosition().getX();
        final double y2 = entity.getPosition().getY();
        final double w2 = entity.getHitbox().getX();
        final double h2 = entity.getHitbox().getY();
        return (x1 <= x2 && x2 <= (x1 + w1)) && (y1 <= y2 && y2 <= (y1 + h1))
                                             || 
               (x2 <= x1 && x1 <= (x2 + w2)) && (y2 <= y1 && y1 <= (y2 + h2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "POS: " + this.position + " HITBOX: " + this.hitbox;
    }
}
