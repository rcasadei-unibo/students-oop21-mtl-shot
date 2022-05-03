package controller.player;

import java.util.List;

import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import util.Vector;
import view.player.PlayerView;

public class PlayerController {

	private final Player player;
	private final PlayerView playerView;
	private final List<Vector> collidableObjects;

	public PlayerController(final List<Vector> collidableObjects, final PlayerView playerView, final Vector spawn) {
	    this.collidableObjects = collidableObjects;
	    this.playerView = playerView;
		player = new PlayerBuilder()
				.hitbox(new Vector(1, 1.5))
				.position(spawn)
				.health(new SimpleHealth())
				.lives(3)
				.build();
	}

	public void check() {
		player.setFall(true);
		final Vector nextPos = new Vector(player.getPosition());
		nextPos.sum(player.getSpeed());		
		final Vector botRight = new Vector(player.getHitbox());
		final Vector botLeft = new Vector(player.getHitbox().getX(), 0);
		botRight.sum(nextPos);
		botLeft.sum(nextPos);
		if (this.collidableObjects.contains(botLeft) || this.collidableObjects.contains(botLeft)) {
		    player.setFall(false);
		    if (player.getSpeed().getY() > 0) {		        
		        player.setSpeed(player.getSpeed().getX(), 0);
		    }
		}
		System.out.println(player.getPosition());
		playerView.updatePlayer(player.getPosition());
	}

	public Player getPlayer() {
		return this.player;
	}





}
