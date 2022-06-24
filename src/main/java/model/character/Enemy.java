package model.character;

import model.character.tools.health.Health;
import model.weapons.R99;
import util.Status;
import util.Vector2D;
/**
 * TODO: javadoc.
 *
 */
public class Enemy extends Character {
    
    private Status status = Status.IDLE;
	
	public Enemy(final Vector2D position, final Vector2D hitbox, final Health health) {
		super(position, hitbox, health, new R99());
	}
	
	public Status getStatus() {
	    return this.status;
	}

    public void setStatus(Status status) {
        this.status = status;
    }	
}
