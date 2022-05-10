package controller.enemy;

import model.character.movableentity.MovableEntity;

public interface SimpleBot {
	
	void move();
	
	MovableEntity getEntity();
	
}
