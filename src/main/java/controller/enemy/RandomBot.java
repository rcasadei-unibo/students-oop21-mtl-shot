package controller.enemy;

import java.util.Random;

import model.character.Enemy;
import model.character.movableEntity.MovableEntity;
import model.character.tools.health.SimpleHealth;
import util.Vector;

public class RandomBot implements SimpleBot {
	
	private MovableEntity entity = new Enemy(new Vector(0, 0), null, new SimpleHealth());

	@Override
	public void move() {
		boolean dir = new Random().nextBoolean();
		System.out.println(dir ? "right" : "left");
		entity.setRight(dir);
		entity.setLeft(!dir);
		entity.moveEntity();
	}
	
	public MovableEntity getEntity() {
		return this.entity;
	}

}
