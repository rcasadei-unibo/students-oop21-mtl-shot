package model.weapons.bullet;

import util.Direction;
import util.Vector;
import model.character.Character;

public class Bullet {
	private final Character owner;
	private Vector position;
	private Direction direction;
	
	/*
	 * Space traveled in a tick's time
	 */
	private double speed;
	
	public Bullet(final Character owner) {
		this.owner = owner;
		this.position = owner.getPosition();
		this.direction = owner.getAim().getDirection();
	}
	
	public void move() {
		
	}
}
