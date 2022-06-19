package controller.enemy;

import model.character.Character;
import model.character.Player;
import model.character.movableentity.MovableEntity;
/**
 * The brain of an enemy.
 */
public interface SimpleBot {
    /**
     * Communicates with the enemy (or MovableEntity) and make it move.
     */
    void move();
    /**
     * 
     * @param p
     */
    void setPlayer(Player p);

    MovableEntity getEntity();

    void setEnemy(Character character);
    
    void fire();

}
