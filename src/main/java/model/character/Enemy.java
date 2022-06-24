package model.character;

import model.character.tools.health.Health;
import model.weapons.Flatline;
import util.Status;
import util.Vector2D;

/**
 * The model containing the basic information of the Enemy.
 */
public class Enemy extends Character {

    private Status status = Status.IDLE;

    /**
     * The constructor for the Enemy.
     * @param position
     * @param hitbox
     * @param health
     */
    public Enemy(final Vector2D position, final Vector2D hitbox, final Health health) {
        super(position, hitbox, health, new Flatline());
    }

    /**
     * Returns the current status of the enemy.
     * @return current Status of the enemy
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Allows to set the status of the enemy.
     * @param status
     */
    public void setStatus(final Status status) {
        this.status = status;
    }
}
