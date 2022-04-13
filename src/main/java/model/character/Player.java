package model.character;

import model.character.tools.health.Health;
import utilities.Vector;

public class Player extends Character {

	private int lives;
	//private final List<Item> items;
	
	public Player(final Vector hitbox, final Vector position, final Health health, final int lives) {
		super(hitbox, position, health);
		this.lives = lives;
	}
	
	public int getLives() {
		return this.lives;
	}
	
	public void respawn(final Vector position) {
		this.lives--;
		if (this.lives >= 0) {			
			super.setPosition(position);
		}
	}
	
	/*public void interact(final Item item) {
	 *	if(this.isClose(item.getPosition)) {
	 *		this.items.add(item);
	 *  }
	 *}*/
	
	private boolean isClose(final Vector position) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (new Vector(super.getPosition().getX() + i, super.getPosition().getY() + j).equals(position)) {
					return true;
				}
			}
		}
		return false;
	}
	 

}
