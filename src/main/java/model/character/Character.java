package model.character;

import model.character.movableEntity.MovableEntity;
import model.character.tools.Aim;
import model.character.tools.health.Health;
import util.Vector;

/**
 * A character is a kind of MovableEntity that also has an health, an aim and a weapon
 * */
public abstract class Character extends MovableEntity {

    /**
     * The health of the character
     * */
	private final Health health;
	/**
	 * The aim of the character
	 * */
	private final Aim aim;
	/**
	 * The weapon held by the character
	 * */
	//private Weapon weapon;
	
	public Character(final Vector hitbox, final Vector position, final Health health) {
		super(hitbox, position);
		this.health = health;
		this.aim = new Aim();
	}
	/**
	 * @return the current weapon held by the character
	 * */
	/*public Weapon getWeapon() {
		return this.weapon;
	}*/

    /**
     * Sets a new weapon for the character
     * */
	/*public void setWeapon(final Weapon weapon) {
		this.weapon = weapon;
	}*/
	
	/**
	 * @return the character health
	 * */
	public Health getHealth() {
		return this.health;
	}
	
	/**
	 * @return the character aim
	 * */
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
