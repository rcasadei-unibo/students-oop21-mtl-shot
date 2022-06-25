package controller.character.enemy;

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
     * Execute the actions and checks to make the enemy shoot.
     */
    void fire();

}
