package controller.enemy;

import java.util.Random;

import model.character.Character;
import model.character.Enemy;
import model.character.Player;
import model.character.movableentity.EntityConstants;
import model.character.movableentity.MovableEntity;
import model.map.Level;
import model.map.Segment;
import util.DirectionHorizontal;

/**
 * TODO
 * 
 * @author Matteo Susca
 *
 */
public class BasicBot implements SimpleBot {

    private Enemy enemy;
    private Player player;
    private Level level;
    private double maxDistance = EntityConstants.ENEMY_DISTANCE + 
            (new Random().nextDouble()*EntityConstants.ENEMY_VARIATON - EntityConstants.ENEMY_VARIATON/2);

    public BasicBot(Character character, Level level) {
        this.enemy = (Enemy) character;
        this.level = level;
    }

    public void setPlayer(final Player p) {
        this.player = p;
    }

    @Override
    public void setEnemy(Character character) {
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
                        this.enemy.getPosition().sum((dir ? - 0.5 : 1.5), + (enemy.getHitbox().getY() - 1))) ||
                        getCurrentCharacterSegment().isCollidableAtPosition(
                                this.enemy.getPosition().sum((dir ? - 0.5 : 1.5), 0)));
            }
        }
    }

    public Segment getCurrentCharacterSegment() {
        return level.getSegmentAtPosition(this.enemy.getPosition());
    }

    @Override
    public MovableEntity getEntity() {
        // TODO Auto-generated method stub
        return this.enemy;
    }

    @Override
    public void fire() {
        if(Math.abs(enemy.getPosition().getX() - player.getPosition().getX()) < maxDistance) {
            enemy.setFire(true);
        } else {
            enemy.setFire(false);            
        }
    }

}
