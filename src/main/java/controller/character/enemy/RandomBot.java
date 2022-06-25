package controller.character.enemy;

import java.util.Random;

import model.character.Enemy;
import model.character.tools.health.SimpleHealth;
import util.Vector2D;

/**
 * A SimpleBot that makes decision randomly.
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
    public void fire() {
    }

    @Override
    public void controllerTick() {
    }

}
