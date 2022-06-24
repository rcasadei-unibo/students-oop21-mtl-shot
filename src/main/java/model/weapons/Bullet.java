package model.weapons;

import util.DirectionHorizontal;
import util.DirectionVertical;
import util.Vector2D;

import java.util.Random;

import javafx.scene.shape.Rectangle;
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

    /*
     * Reference of the Character who shot
     */
    private final Character owner;

    /*
     * Bullet's movement direction
     */
    private Aim aim;

    /*
     * Space traveled in a tick's time
     */
    private double speed;

    /*
     * Bullet's damage (based on the weapon held by owner)
     */
    private int damage;

    /*
     * It is true if the bullet has hit something
     */
    private boolean hit;
    
    private double cos;
    private double sin;

    /**
     * TODO: write javadoc.
     *
     */
    public Bullet(final Character owner) {
        super(new Vector2D(owner.getPosition().getX() + owner.getHitbox().getX() / 2,
                owner.getPosition().getY() + owner.getHitbox().getY() / 2),
                new Vector2D(0.1, 0.1)); // TODO: change magic numbers
        this.owner = owner;
        this.aim = owner.getAim();
        this.speed = 0.3;
        this.hit = false;
        this.damage = owner.getWeapon().getDamagePerBullet();
        
        var r = new Random();
        double angleInterval = 1 / ((owner.getWeapon().getAccuracy()));
        double angle = 8 * angleInterval * (r.nextDouble() - 0.5);    // r.nextDouble()*angleInterval - angleInterval/2
        
        if (this.aim.getDirection().getY().equals(DirectionVertical.UP)) {
        	angle += 90;
        } else if (this.aim.getDirection().getY().equals(DirectionVertical.DOWN)) {
        	angle += 270;
        } else if (this.aim.getDirection().getX().equals(DirectionHorizontal.LEFT)) {
        	angle += 180;
        } else if (this.aim.getDirection().getX().equals(DirectionHorizontal.RIGHT)) {
        	angle += 0;
        }
        
        /*switch (this.direction) {
    	default:
    		break;
    	case UP:
    		angle += 90;
    		break;
    	case LEFT:
    		angle += 180;
    		break;
    	case DOWN:
    		angle += 270;
    		break;
    	}*/
    	this.sin = Math.sin(Math.toRadians(angle));
    	this.cos = Math.cos(Math.toRadians(angle));
    }

    /**
     * Moves the bullet forward. This method is called by Controller only when the
     * bullet is not colliding with entities or tiles.
     */
    public void tick() {
    	/* TODO: Old code, without accuracy. For emergencies only! */
        /*if (this.direction.equals(Direction.UP)) {
            super.setPosition(new Vector2D(super.getPosition().getX(), super.getPosition().getY() - this.speed.getY()));
        } else if (this.direction.equals(Direction.DOWN)) {
            super.setPosition(new Vector2D(super.getPosition().getX(), super.getPosition().getY() + this.speed.getY()));
        } else if (this.direction.equals(Direction.LEFT)) {
            super.setPosition(new Vector2D(super.getPosition().getX() - this.speed.getX(), super.getPosition().getY()));
        } else if (this.direction.equals(Direction.RIGHT)) {
            super.setPosition(new Vector2D(super.getPosition().getX() + this.speed.getX(), super.getPosition().getY()));
        }*/
    	super.setPosition(super.getPosition().getX() + this.speed*this.cos, super.getPosition().getY() - this.speed*this.sin);
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
        return "Bullet [position=" + super.getPosition() + ", aim=" + aim + ", speed=" + speed + ", damage="
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
