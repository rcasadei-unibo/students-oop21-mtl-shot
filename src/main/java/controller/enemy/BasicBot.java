package controller.enemy;

import java.util.Random;

import model.character.Character;
import model.character.Enemy;
import model.character.Player;
import model.character.movableentity.EntityConstants;
import model.map.Level;
import model.map.Segment;
import util.DirectionHorizontal;
import util.Status;

/**
 * A Basic Bot that shoots when near the player, jumps obstacles and mantains
 * the distances with the player.
 *
 */
public class BasicBot implements SimpleBot {

    private Enemy enemy;
    private Player player;
    private Level level;
    private double maxDistance = EntityConstants.ENEMY_DISTANCE
            + (new Random().nextDouble() * EntityConstants.ENEMY_VARIATON - EntityConstants.ENEMY_VARIATON / 2);
    private boolean lastDir = true;


    /**
     * the BasicBot constructor.
     * 
     * @param character
     * @param level
     */
    public BasicBot(final Character character, final Level level) {
        this.enemy = (Enemy) character;
        this.level = level;
    }

    @Override
    public void controllerTick() {
        switch (enemy.getStatus()) {
            case IDLE:
                this.randomMove();
                break;
            case ACTIVE:
                this.move();
                this.fire();
                break;
            default:
        }
    }

    private void randomMove() {
        lastDir = (new Random().nextInt(EntityConstants.CHANGE_DIR_PROBABILITY) == 0) ? !lastDir : lastDir;
        enemy.getAim().setHorizontal(lastDir ? DirectionHorizontal.LEFT : DirectionHorizontal.RIGHT);
        enemy.setLeft(lastDir);
        enemy.setRight(!lastDir);
        enemy.setJump(getCurrentCharacterSegment()
                        .isCollidableAtPosition(
                                this.enemy
                                .getPosition()
                                .sum((lastDir 
                                        ? -(EntityConstants.ENEMY_DELTA) 
                                        : enemy.getHitbox().getX() + EntityConstants.ENEMY_DELTA),
                                        +(enemy.getHitbox().getY() - 1)))
                        || getCurrentCharacterSegment()
                        .isCollidableAtPosition(
                                this.enemy.getPosition().sum((lastDir 
                                        ? -(EntityConstants.ENEMY_DELTA) 
                                        : (EntityConstants.ENEMY_DELTA + enemy.getHitbox().getX())), 
                                        0)));
        double distance = enemy.getPosition().getX() - player.getPosition().getX();
        if (Math.abs(distance) < this.maxDistance) {
            enemy.setStatus(Status.ACTIVE);
        }
    }

    /**
     * Sets the player who the enemy has to kill.
     * @param p
     */
    public void setPlayer(final Player p) {
        this.player = p;
    }

    /**
     * Sets the reference to the Enemy model.
     */
    @Override
    public void setEnemy(final Character character) {
        this.enemy = (Enemy) character;

    }

    @Override
    public void move() {
        if (this.player != null) {
            double distance = enemy.getPosition().getX() - player.getPosition().getX();
            if (Math.abs(distance) < maxDistance + EntityConstants.ENEMY_TOLERANCE
                    && Math.abs(distance) > maxDistance - EntityConstants.ENEMY_TOLERANCE) {
                enemy.setLeft(false);
                enemy.setRight(false);
            } else {
                boolean dir = distance > 0;
                enemy.getAim().setHorizontal(dir ? DirectionHorizontal.LEFT : DirectionHorizontal.RIGHT);
                dir = (Math.abs(distance) < maxDistance) ? !dir : dir;
                enemy.setLeft(dir);
                enemy.setRight(!dir);
                enemy.setJump(getCurrentCharacterSegment().isCollidableAtPosition(
                            this.enemy.getPosition().sum((dir 
                                    ? -(EntityConstants.ENEMY_DELTA) 
                                    : (EntityConstants.ENEMY_DELTA + enemy.getHitbox().getX())),
                                    (enemy.getHitbox().getY() - 1)))
                        || getCurrentCharacterSegment().isCollidableAtPosition(
                            this.enemy.getPosition().sum((dir 
                                    ? -(EntityConstants.ENEMY_DELTA) 
                                    : (EntityConstants.ENEMY_DELTA + enemy.getHitbox().getX())), 
                                    0)));
            }
        }
    }

    /**
     * Gets the Segment where the enemy is at that moment.
     * @return the Segment where the enemy is at that moment
     */
    public Segment getCurrentCharacterSegment() {
        return level.getSegmentAtPosition(this.enemy.getPosition());
    }

    @Override
    public Enemy getEnemy() {
        return this.enemy;
    }

    @Override
    public void fire() {
        if (Math.abs(enemy.getPosition().getX() - player.getPosition().getX()) < maxDistance) {
            enemy.setFire(true);
        } else {
            enemy.setFire(false);
        }
    }

}
