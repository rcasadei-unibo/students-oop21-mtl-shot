package model.character;

import javafx.scene.shape.Rectangle;
import model.character.movableentity.MovableEntity;
import model.character.tools.Aim;
import model.character.tools.health.Health;
import util.Vector2D;
import model.weapons.Weapon;

/**
 * A character is a kind of MovableEntity that also has an health, an aim and a
 * weapon.
 */
public abstract class Character extends MovableEntity {

    /**
     * Delineates the conditions which limit the entity's crouching capabilities.
     */
    public enum Crouch {
        /** Crouch could be everything. */
        FREE,
        /** Crouch has to be false. */
        UP,
        /** Crouch has to be true. */
        DOWN;
    }

    /**
     * Delineates the conditions which limit the entity's crouching capabilities.
     */
    private Crouch crouchCondition = Crouch.FREE;
    /**
     * Represent the entity intention to crouch.
     */
    private boolean crouchKey;

    /**
     * The health of the character.
     */
    private final Health health;
    /**
     * The aim of the character.
     */
    private final Aim aim;
    /**
     * The weapon held by the character.
     */
    private Weapon weapon;

    /**
     * Character constructor.
     * 
     * @param position
     * @param hitbox
     * @param health
     * @param weapon
     */
    public Character(final Vector2D position, final Vector2D hitbox, final Health health, final Weapon weapon) {
        super(position, hitbox);
        this.health = health;
        this.weapon = weapon;
        this.aim = new Aim();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveEntity() {
        super.moveEntity();
        if (this.crouchCondition == Crouch.FREE) {
            super.setCrouch(crouchKey);
        } else if (this.crouchCondition == Crouch.DOWN) {
            super.setCrouch(true);
        } else {
            super.setCrouch(false);
        }
    }

    /**
     * Sets the crouch condition.
     * 
     * @param crouchCond
     */
    public void setCrouchCondition(final Crouch crouchCond) {
        this.crouchCondition = crouchCond;
    }

    /**
     * Gets the crouch condition.
     * 
     * @return the crouch condition
     */
    public Crouch getCrouchCondition() {
        return this.crouchCondition;
    }

    /**
     * Sets the crouch key.
     * 
     * @param crouchKey
     */
    public void setCrouchKey(final boolean crouchKey) {
        this.crouchKey = crouchKey;
    }

    /**
     * Return if the crouch key is pressed.
     * 
     * @return crouch key
     */
    public boolean isCrouchKey() {
        return this.crouchKey;
    }

    /**
     * Gets the current held weapon.
     * 
     * @return weapon
     */
    public Weapon getWeapon() { 
        return this.weapon; 
    }

    /**
     * Sets a new weapon for the character.
     * 
     * @param weapon
     */
    public void setWeapon(final Weapon weapon) { 
        this.weapon = weapon; 
    }

    /**
     * Gets the character health.
     * 
     * @return health
     */
    public Health getHealth() {
        return this.health;
    }

    /**
     * Gets the character aim.
     * 
     * @return Aim
     */
    public Aim getAim() {
        return this.aim;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + " " + health.toString() + " " + weapon.toString() + " " + aim.toString();
    }
}
