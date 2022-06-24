package controller.enemy;

import java.util.Random;

import model.character.Character;
import model.character.Enemy;
import model.character.Player;
import model.character.tools.health.SimpleHealth;
import util.Vector2D;

/**
 * A SimpleBot that make decision randomly.
 *
 */
public class RandomBot implements SimpleBot {

    private Enemy enemy = new Enemy(new Vector2D(0, 0), null, new SimpleHealth());

    @Override
    public void move() {
        boolean dir = new Random().nextBoolean();
        enemy.setRight(dir);
        enemy.setLeft(!dir);
        enemy.moveEntity();
    }

    @Override
    public Enemy getEnemy() {
        return this.enemy;
    }

    @Override
    public void setPlayer(final Player p) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setEnemy(final Character character) {
        // TODO Auto-generated method stub

    }

    @Override
    public void fire() {
        // TODO Auto-generated method stub

    }

    @Override
    public void controllerTick() {
        // TODO Auto-generated method stub

    }

}
