package controller.player;

import controller.map.MapController;
import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.movableentity.EnvironmentConstants;
import model.character.tools.health.SimpleHealth;
import util.Vector;
import view.player.PlayerView;

public class PlayerController {

	private final Player player;
	private final PlayerView playerView;
	private final MapController mapController;
	private final static double DELTA = 0.1;

	public PlayerController(final PlayerView playerView, final MapController mapController) {
	    this.playerView = playerView;
	    this.mapController = mapController;
		player = new PlayerBuilder()
				.hitbox(new Vector(1, 1))
				.position(mapController.getPlayerSpawn())
				.health(new SimpleHealth())
				.lives(3)
				.build();
	}

	public void check() {
		player.setFall(true);
		final Vector nextPos = new Vector(player.getPosition());
        nextPos.sum(player.getSpeed());
        this.verticalCollisions(nextPos);
        this.horizontalCollisions(nextPos);
		player.moveEntity();
		playerView.updatePlayer(player.getPosition(), player.isCrawling());
	}

	private void horizontalCollisions(final Vector nextPos) {
        final Vector botRight = new Vector(player.getHitbox().getX(), player.getHitbox().getY()-DELTA);
        final Vector botLeft = new Vector(0, player.getHitbox().getY()-DELTA);
        final Vector topRight = new Vector(player.getHitbox().getX(), DELTA);
        final Vector topLeft = new Vector(0, DELTA);
        botRight.sum(nextPos);
        botLeft.sum(nextPos);
        topLeft.sum(nextPos);
        topRight.sum(nextPos);
        if ((mapController.hasSingleCollidable(topLeft) || mapController.hasSingleCollidable(botLeft)) && player.isLeft()) {
            player.setSpeed(EnvironmentConstants.getHorizontalAcceleration(), player.getSpeed().getY());
        }
        if ((mapController.hasSingleCollidable(topRight) || mapController.hasSingleCollidable(botRight)) && player.isRight()) {
            player.setSpeed(-EnvironmentConstants.getHorizontalAcceleration(), player.getSpeed().getY());
        }
    }

    private void verticalCollisions(final Vector nextPos) {
        final Vector botRight = new Vector(player.getHitbox().getX()-DELTA, player.getHitbox().getY());
        final Vector botLeft = new Vector(DELTA, player.getHitbox().getY());
        final Vector topRight = new Vector(player.getHitbox().getX()-DELTA, 0);
        final Vector topLeft = new Vector(DELTA, 0);
        botRight.sum(nextPos);
        botLeft.sum(nextPos);
        topLeft.sum(nextPos);
        topRight.sum(nextPos);
        if (mapController.hasSingleCollidable(botLeft) || mapController.hasSingleCollidable(botRight)) {
            player.setFall(false);
            if (player.getSpeed().getY() > 0) {
                player.setSpeed(player.getSpeed().getX(), 0);
            }
        }
        if (mapController.hasSingleCollidable(topLeft) || mapController.hasSingleCollidable(topRight) && player.getSpeed().getY() < 0) {
            player.setSpeed(player.getSpeed().getX(), 0);
        }
    }

    public Player getPlayer() {
		return this.player;
	}





}
