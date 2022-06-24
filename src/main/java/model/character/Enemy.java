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
     * 
     * @return
     */
    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
