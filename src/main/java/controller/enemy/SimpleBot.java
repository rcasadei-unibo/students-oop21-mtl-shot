package controller.enemy;

import model.character.Character;
import model.character.Player;
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
	
	void setPlayer(Player p);
	
	MovableEntity getEntity();

	void setEnemy(Character character);
	
}
