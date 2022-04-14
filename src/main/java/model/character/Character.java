package model.character;

import model.character.tools.Aim;
import model.character.tools.health.Health;
import utilities.Vector;
import utilities.entity.MovableEntity;

/**A character is a specific MovableEntity that also has an health, an aim and a weapon*/
public abstract class Character extends MovableEntity {

	private final Health health;
	private final Aim aim = new Aim();
	//private Weapon weapon;
	
	public Character(final Vector hitbox, final Vector position, final Health health) {
		super(hitbox, position);
		this.health = health;
	}
	/**@return the current weapon held by the character*/
	/*public Weapon getWeapon() {
		return this.weapon;
	}*/

    /**set a new weapon for the character*/
	/*public void setWeapon(final Weapon weapon) {
		this.weapon = weapon;
	}*/
	
	/**@return the character health*/
	public Health getHealth() {
		return this.health;
	}
	
	/**@return the character aim*/
	public Aim getAim() {
		return this.aim;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n" + 
				health.toString() + "\n" + 
				//weapon.toString() + "\n" +
				aim.toString();
	}
}
