package model.weapons;

import util.Vector2D;
import util.direction.DirectionHorizontal;
import util.direction.DirectionVertical;

import java.util.Random;

import model.Entity;
import model.character.Character;
import model.character.tools.Aim;

/**
 * Bullet class models a bullet with its current position, his owner (a
 * reference to the Character who shot it), its movement direction and movement
 * speed.
 * 
 */
public class Bullet extends Entity {

    private static final double DEFAULT_SPEED = 0.3;
    private static final double DEFAULT_HITBOX_SIZE = 0.1;
    private static final int ANGLE_RIGHT = 0;
    private static final int ANGLE_UP = 90;
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_DOWN = 270;
    private static final double ACCURACY_AMPLIFIER = 8.0;

    /**
     * Reference of the Character who shot.
     */
    private final Character owner;

    /**
     * Bullet's movement direction.
     */
    private final Aim aim;

    /**
     * Space traveled in a tick's time.
     */
    private final double speed;

    /**
     * Bullet's damage (based on the weapon held by owner).
     */
    private final int damage;

    /**
     * It is true if the bullet has hit something.
     */
    private boolean hit;

    /**
     * Cos of the angle based on accuracy.
     */
    private final double cos;

    /**
     * Sin of the angle based on accuracy.
     */
    private final double sin;

    /**
     * Creates a bullet based on owner's position, weapon and aim, adding an angle
     * calculated by the weapon's accuracy.
     * 
     * @param owner
     */
    public Bullet(final Character owner) {
        super(new Vector2D(owner.getPosition().getX() + owner.getHitbox().getX() / 2,
                owner.getPosition().getY() + owner.getHitbox().getY() / 2),
                new Vector2D(DEFAULT_HITBOX_SIZE, DEFAULT_HITBOX_SIZE));
        this.owner = owner;
        this.aim = owner.getAim();
        this.speed = DEFAULT_SPEED;
        this.hit = false;
        this.damage = owner.getWeapon().getDamagePerBullet();

        final var r = new Random();
        final double angleInterval = 1 / ((owner.getWeapon().getAccuracy()));
        double angle = ACCURACY_AMPLIFIER * angleInterval * (r.nextDouble() - 0.5);

        if (this.aim.getDirection().getY().equals(DirectionVertical.UP)) {
            angle += ANGLE_UP;
        } else if (this.aim.getDirection().getY().equals(DirectionVertical.DOWN)) {
            angle += ANGLE_DOWN;
        } else if (this.aim.getDirection().getX().equals(DirectionHorizontal.LEFT)) {
            angle += ANGLE_LEFT;
        } else if (this.aim.getDirection().getX().equals(DirectionHorizontal.RIGHT)) {
            angle += ANGLE_RIGHT;
        }

        this.sin = Math.sin(Math.toRadians(angle));
        this.cos = Math.cos(Math.toRadians(angle));
    }

    /**
     * Moves the bullet forward. This method is called by Controller only when the
     * bullet is not colliding with entities or tiles.
     */
    public void tick() {
        super.setPosition(super.getPosition().getX() + this.speed * this.cos,
                super.getPosition().getY() - this.speed * this.sin);
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
    public Aim getAim() {
        return this.aim;
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
        return "Bullet [position=" + super.getPosition() + ", aim=" + aim + ", speed=" + speed + ", damage=" + damage
                + "]";
    }

    @Override
    public boolean isColliding(final Entity entity) {
        if (entity.equals(this.owner)) {
            return false;
        }
        return super.isColliding(entity);
    }

}
