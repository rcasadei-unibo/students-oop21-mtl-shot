package controller.player;

import controller.map.MapController;
import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import util.Vector;
import view.player.PlayerView;

public class PlayerController {

	private final Player player;
	private final PlayerView playerView;
	private final MapController mapController;

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
		final Vector botRight = new Vector(player.getHitbox());
		final Vector botLeft = new Vector(0, player.getHitbox().getY());
		final Vector topRight = new Vector(player.getHitbox().getX(), 0);
		final Vector topLeft = new Vector();
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
		player.moveEntity();
		playerView.updatePlayer(player.getPosition(), player.isCrawling(), player.getSpeed());
	}

	public Player getPlayer() {
		return this.player;
	}





}
