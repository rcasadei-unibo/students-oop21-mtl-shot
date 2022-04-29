package controller.enemy;

import model.character.movableEntity.MovableEntity;

public interface SimpleBot {
	
	void move();
	
	MovableEntity getEntity();
	
}
