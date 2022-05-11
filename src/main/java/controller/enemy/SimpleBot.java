package controller.enemy;

import model.character.movableentity.MovableEntity;
/**
 * Bot that make enemy move and take decision
 * @author Matteo Susca
 *
 */
public interface SimpleBot {
	/**
	 * communicate with the enemy (or MovableEntity) and make it move.
	 */
	void move();
	
	MovableEntity getEntity();
	
}
