package model.character;

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
     */
    public Character(final Vector2D position, final Vector2D hitbox, final Health health) {
        super(position, hitbox);
        this.health = health;
        this.aim = new Aim();
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
        return super.toString() + " " + health.toString() + " " + /*weapon.toString() +*/ " " + aim.toString();
    }
}
