package model.character;

import model.character.tools.health.Health;
import utilities.Vector;

/**The player is the main character of the game, it can means that can respawn if it dies,
 * has a lives counter to manage the respawn logic and can hold items (such as power ups and weapons) in its inventory*/
public class Player extends Character {

	private int lives;
	//private final List<Item> items;
	
	private Player(final PlayerBuilder builder) {
		super(builder.hitbox, builder.position, builder.health/*, builder.weapon*/);
		this.lives = builder.lives;
	}
	
	/**@return the remaining lives*/
	public int getLives() {
		return this.lives;
	}
	
	/**Sets the player position to @position only if it has at least one remaining life
	 * @throws IllegalStateException if there aren't remaining lives*/
	public void respawn(final Vector position) {
		this.lives--;
		if (this.lives >= 0) {			
			super.setPosition(position);
		} else {
		    throw new IllegalStateException();
		}
	}
	/**Picks up the item @item only if it's close enough to take it*/
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
	
	/**The player builder*/
	public static class PlayerBuilder {
		private Vector hitbox;
		private Vector position;
		private Health health;
		private int lives;
		//private Weapon weapon;
		
		/**The method that sets the player hitbox*/
		public PlayerBuilder hitbox(final Vector hitbox) {
			this.hitbox = hitbox;
			return this;
		}
		
		/**The method that sets the player position*/
		public PlayerBuilder position(final Vector position) {
			this.position = position;
			return this;
		}
		
		/**The method that sets the player health*/
		public PlayerBuilder health(final Health health) {
			this.health = health;
			return this;
		}
		
		/**The method that sets the player lives*/
		public PlayerBuilder lives(final int lives) {
			this.lives = lives;
			return this;
		}
		
		/**The method that sets the player weapon*/
		/*public PlayerBuilder weapon(final Weapon weapon) {
		 *  this.weapon = weapon;
		 *  return this;*/
		
		/**The method that builds the player with the set up values
		 * @throws IllegalStateException if at least one is null or if the lives are a negative number*/
		public Player build() {
		    this.consistencyCheck();
			return new Player(this);
		}

        private void consistencyCheck() {
            if (this.lives < 0 || this.health == null || this.hitbox == null
                    || this.position == null /*|| this.weapon == null*/) {
                throw new IllegalStateException();
            }
        }
	}

}
