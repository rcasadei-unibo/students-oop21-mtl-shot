package controller.enemy;

import model.character.Character;
import model.character.Enemy;
import model.character.Player;
import model.character.movableentity.EntityConstants;
import model.character.movableentity.MovableEntity;
import model.map.Level;
import model.map.Segment;

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
            if (Math.abs(distance) < EntityConstants.ENEMY_DISTANCE + 0.5
                    && Math.abs(distance) > EntityConstants.ENEMY_DISTANCE - 0.5) {
                enemy.setLeft(false);
                enemy.setRight(false);
            } else {
                boolean dir = distance > 0;
                dir = (Math.abs(distance) < EntityConstants.ENEMY_DISTANCE) ? !dir : dir;
                enemy.setLeft(dir);
                enemy.setRight(!dir);
                enemy.setJump(getCurrentCharacterSegment().isCollidableAtPosition(
                        this.enemy.getPosition().sum((dir ? -0.5 : 1.5), 0)));
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

}
