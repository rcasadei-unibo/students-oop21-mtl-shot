package controller.enemy;

import controller.CharacterController;
import model.character.Character;
import model.map.Level;
import util.Pair;

/**
 * The controller of the Enemy.
 *
 */
public class EnemyController extends CharacterController {

    private final SimpleBot brain;
    private boolean isActive = true;

    /**
     * Constructor for the controller of the enemy.
     * 
     * @param level
     * @param character
     */
    public EnemyController(final Level level, final Character character) {
        super(level, character);
        this.brain = new BasicBot(character, level);
    }

    @Override
    public void controllerTick(final Pair<Double, Double> bounds, final boolean canAdvance) {
        brain.controllerTick();
        super.controllerTick(bounds, canAdvance);
    }

    /**
     * Returns the Brain of the Enemy.
     * 
     * @return the brain that makes the enemy move
     */
    public SimpleBot getBrain() {
        return this.brain;
    }

    /**
     * Return the status of the enemy.
     * 
     * @return true if the enemy is able to move, false otherwise
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Sets the activation status of the enemy.
     * 
     * @param status
     */
    public void setActive(final boolean status) {
        this.isActive = status;
    }

}
