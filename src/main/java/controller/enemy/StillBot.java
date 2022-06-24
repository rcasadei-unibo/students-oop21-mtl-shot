package controller.enemy;

import model.character.Character;
import model.character.Enemy;
import model.character.Player;
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
    public Enemy getEnemy() {
        // TODO Auto-generated method stub
        return this.enemy;
    }

    @Override
    public void setEnemy(final Character character) {
        this.enemy = (Enemy) character;

    }

    @Override
    public void fire() {
    }

    @Override
    public void controllerTick() {
    }

}
