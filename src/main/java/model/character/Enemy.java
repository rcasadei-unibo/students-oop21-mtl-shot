package model.character;

import model.character.tools.health.Health;
import model.weapons.P2020;
import util.Vector2D;
/**
 * TODO: javadoc.
 *
 */
public class Enemy extends Character{
	
	public Enemy(Vector2D position, Vector2D hitbox, Health health) {
		super(position, hitbox, health, new P2020());
		// TODO Auto-generated constructor stub
	}
}