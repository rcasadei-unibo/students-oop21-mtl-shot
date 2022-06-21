package controller.player;

import java.util.Collection;

import controller.CharacterController;
import controller.enemy.EnemyController;
import model.character.Player;
import model.map.Level;

/**
 * The player controller. It checks if the player is colliding into the ground,
 * colliding with bullets and manages everything about the weapon.
 */
public class PlayerController extends CharacterController {

	private final Level level;
	private final Collection<EnemyController> enemies;
    /**
     * The player controller constructor.
     * 
     * @param level the level where the player is
     * @param player the player to control
     */
    public PlayerController(final Level level, final Player player, final Collection<EnemyController> enemies) {
        super(level, player);
        this.level = level;
        this.enemies = enemies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void controllerTick() {
    	super.controllerTick();
    	var offset = level.getSegmentAtPosition(super.getCharacter().getPosition()).getOrigin().getX();
    	if (offset + 0.25 > super.getCharacter().getPosition().getX()) {
    		super.getCharacter().setPosition(offset + 0.26, 
    				super.getCharacter().getPosition().getY());
    	}
    	offset = offset + level.getSegmentAtPosition(super.getCharacter().getPosition()).getTextMap().getWidth();
    	if (offset - 0.25 < super.getCharacter().getPosition().getX() + super.getCharacter().getHitbox().getX() && !this.enemies.isEmpty()) {
    		super.getCharacter().setPosition(offset - 0.26 - super.getCharacter().getHitbox().getX(),
    				super.getCharacter().getPosition().getY());
    	}
    }
}
