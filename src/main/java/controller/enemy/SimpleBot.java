package controller.enemy;

import model.character.Character;
import model.character.Enemy;
import model.character.Player;

/**
 * The brain of an enemy.
 */
public interface SimpleBot {

    /**
     * Execute all the action and checks that an enemy
     * has to do every frame.
     */
    void controllerTick();

    /**
     * Communicates with the enemy (or MovableEntity) and make it move.
     */
    void move();

    /**
     * returns the reference to the enemy it refers to.
     * @return the reference to the enemy
     */
    Enemy getEnemy();

    /**
     * Sets the enemy who owns the current brain.
     * @param character
     */
    void setEnemy(Character character);

    /**
     * Execute the actions and checks to make the enemy shoot.
     */
    void fire();

}
