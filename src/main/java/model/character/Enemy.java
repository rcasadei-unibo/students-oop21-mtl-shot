package model.character;

import model.character.tools.health.Health;
import model.weapons.P2020;
import util.Vector2D;
/**
 * TODO: javadoc.
 *
 */
public class Enemy extends Character{
	
	public Enemy(final Vector2D position, final Vector2D hitbox, final Health health) {
		super(position, hitbox, health, new P2020());
	}
}
