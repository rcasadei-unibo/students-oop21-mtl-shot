package model.character;

import model.character.tools.Aim;
import model.character.tools.Health;
import utilities.Vector;
import utilities.entity.MovableEntity;

public abstract class Character extends MovableEntity {

	private final Health health;
	private final Aim aim;
	//private Weapon weapon;
	
	public Character(final Vector hitbox, final Vector position, final Health health) {
		super(hitbox, position);
		this.health = health;
		this.aim = new Aim();
	}
	
	/*public Weapon getWeapon() {
		return this.weapon;
	}

	public void setWeapon(final Weapon weapon) {
		this.weapon = weapon;
	}*/
	
	public Health getHealth() {
		return this.health;
	}
	
	public Aim getAim() {
		return this.aim;
	}
}
