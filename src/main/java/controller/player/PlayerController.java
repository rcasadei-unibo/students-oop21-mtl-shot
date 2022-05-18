package controller.player;

import controller.map.MapController;
import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.movableentity.EnvironmentConstants;
import model.character.movableentity.MovableEntity.Crouch;
import model.character.tools.health.SimpleHealth;
import util.Vector;
import view.player.PlayerView;
/**
 * 
 * 
 */
public class PlayerController {

	private final Player player;
	private final PlayerView playerView;
	private final MapController mapController;
	private final static double DELTA = 0.3;

	public PlayerController(final PlayerView playerView, final MapController mapController) {
	    this.playerView = playerView;
	    this.mapController = mapController;
		player = new PlayerBuilder()
				.hitbox(new Vector(1.25, 2))
				.position(mapController.getPlayerSpawn())
				.health(new SimpleHealth())
				.lives(3)
				.build();
	}

	public void check() {
		player.setFall(true);
		player.setCrouchCondition(Crouch.DC);
		final Vector nextPos = new Vector(player.getPosition());
        nextPos.sum(player.getSpeed());
        if (this.isCollidingUp(nextPos) && player.getSpeed().getY() < 0) {
            player.setSpeed(player.getSpeed().getX(), 0);
        }
        if (this.isCollidingDown(nextPos)) {
            player.setFall(false);
            if (player.getSpeed().getY() > 0) {
                player.setSpeed(player.getSpeed().getX(), 0);
            }
        }
        if (this.isCollidingLeft(nextPos) && player.isLeft()) {
            player.setSpeed(EnvironmentConstants.getHorizontalAcceleration(), player.getSpeed().getY());
        } else if (this.isCollidingRight(nextPos) && player.isRight()) {
            player.setSpeed(-EnvironmentConstants.getHorizontalAcceleration(), player.getSpeed().getY());
        }        
        if (this.isCollidingUp(new Vector(player.getPosition().getX(), player.getPosition().getY() - player.getHitbox().getY())) &&
                player.isCrouching()) {
            player.setCrouchCondition(Crouch.TRUE);
            player.setJump(false);
        }
        player.moveEntity();
		playerView.updatePlayer(player.getPosition(), player.isCrouching(), player.getSpeed(), player.getAim().getDirection());
	}

	private boolean isCollidingLeft(final Vector nextPos) {
	    final Vector botLeft = new Vector(0, player.getHitbox().getY()-DELTA);
	    final Vector topLeft = new Vector(0, DELTA);
	    botLeft.sum(nextPos);
        topLeft.sum(nextPos);
        return mapController.hasSingleCollidable(topLeft) || mapController.hasSingleCollidable(botLeft);
	}
	
	private boolean isCollidingRight(final Vector nextPos) {
	    final Vector botRight = new Vector(player.getHitbox().getX(), player.getHitbox().getY()-DELTA);
        final Vector topRight = new Vector(player.getHitbox().getX(), DELTA);
        botRight.sum(nextPos);
        topRight.sum(nextPos);
        return mapController.hasSingleCollidable(topRight) || mapController.hasSingleCollidable(botRight);
	}

	private boolean isCollidingUp(final Vector nextPos) {
	    final Vector topRight = new Vector(player.getHitbox().getX()-DELTA, 0);
        final Vector topLeft = new Vector(DELTA, 0);
        topLeft.sum(nextPos);
        topRight.sum(nextPos);
        return mapController.hasSingleCollidable(topLeft) || mapController.hasSingleCollidable(topRight);
	}
	
    private boolean isCollidingDown(final Vector nextPos) {
        final Vector botRight = new Vector(player.getHitbox().getX()-DELTA, player.getHitbox().getY());
        final Vector botLeft = new Vector(DELTA, player.getHitbox().getY());
        botRight.sum(nextPos);
        botLeft.sum(nextPos);
        return mapController.hasSingleCollidable(botLeft) || mapController.hasSingleCollidable(botRight);
    }

    public Player getPlayer() {
		return this.player;
	}





}
