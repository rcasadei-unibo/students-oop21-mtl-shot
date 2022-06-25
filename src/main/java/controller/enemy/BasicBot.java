package controller.enemy;

import java.util.Random;

import model.character.Enemy;
import model.character.Player;
import model.character.movableentity.EntityConstants;
import model.map.Level;
import model.map.Segment;
import util.Status;
import util.direction.DirectionHorizontal;

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
     * @param enemy
     * @param level
     * @param player
     */
    public BasicBot(final Enemy enemy, final Level level, final Player player) {
        this.enemy = enemy;
        this.level = level;
        this.player = player;
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
        movementLogic(lastDir);
        double distance = enemy.getPosition().getX() - player.getPosition().getX();
        if (Math.abs(distance) < this.maxDistance) {
            enemy.setStatus(Status.ACTIVE);
        }
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
                movementLogic(dir);
            }
        }
    }

    private void movementLogic(final boolean dir) {
        enemy.setLeft(dir);
        enemy.setRight(!dir);
        double nearTileX = dir ? -(EntityConstants.ENEMY_DELTA)
                : (EntityConstants.ENEMY_DELTA + enemy.getHitbox().getX());
        enemy.setJump(getCurrentCharacterSegment()
                .isCollidableAtPosition(this.enemy.getPosition().sum((nearTileX), (enemy.getHitbox().getY() - 1)))
                || getCurrentCharacterSegment().isCollidableAtPosition(this.enemy.getPosition().sum((nearTileX), 0)));
    }

    /**
     * Gets the Segment where the enemy is at that moment.
     * 
     * @return the Segment where the enemy is at that moment
     */
    public Segment getCurrentCharacterSegment() {
        return level.getSegmentAtPosition(this.enemy.getPosition());
    }

    @Override
    public void fire() {
        enemy.setFire(Math.abs(enemy.getPosition().getX() - player.getPosition().getX()) < maxDistance);
    }

}
