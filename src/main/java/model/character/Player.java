package model.character;

import model.character.tools.health.Health;
import utilities.Vector;

public class Player extends Character {

	private int lives;
	//private final List<Item> items;
	
	private Player(final PlayerBuilder builder) {
		super(builder.hitbox, builder.position, builder.health/*, builder.weapon*/);
		this.lives = builder.lives;
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
	
	@Override
	public String toString() {
		return super.toString() + "\n" + 
				"Lives: " + this.lives;
	}
	
	public static class PlayerBuilder {
		private Vector hitbox;
		private Vector position;
		private Health health;
		private int lives;
		//private Weapon weapon;
		
		public PlayerBuilder hitbox(final Vector hitbox) {
			this.hitbox = hitbox;
			return this;
		}
		
		public PlayerBuilder position(final Vector position) {
			this.position = position;
			return this;
		}
		
		public PlayerBuilder health(final Health health) {
			this.health = health;
			return this;
		}
		
		public PlayerBuilder lives(final int lives) {
			this.lives = lives;
			return this;
		}
		
		public Player build() {
			return new Player(this);
		}
	}

}
