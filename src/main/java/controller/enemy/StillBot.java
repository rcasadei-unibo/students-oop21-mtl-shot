package controller.enemy;

import model.character.Character;
import model.character.Enemy;
import model.map.Level;

/**
 * A bot that make the enemy still. Made for testing purpose
 *
 */
public class StillBot implements SimpleBot {

    private Enemy enemy;

    /**
     * The constructor of the StillBot.
     * @param enemy
     * @param level
     */
    public StillBot(final Character enemy, final Level level) {
        this.enemy = (Enemy) enemy;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub

    }

    @Override
    public void fire() {
    }

    @Override
    public void controllerTick() {
    }

}
