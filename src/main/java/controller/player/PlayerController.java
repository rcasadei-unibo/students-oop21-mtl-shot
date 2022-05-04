package controller.player;

import model.character.Player;
import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import util.Vector;
import view.player.PlayerView;

public class PlayerController {

	private final Player player;
	private final PlayerView playerView;

	public PlayerController(final PlayerView playerView, final Vector spawn) {
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
		final Vector botLeft = new Vector(0, player.getHitbox().getY());
		final Vector topRight = new Vector(player.getHitbox().getX(), 0);
		final Vector topLeft = new Vector();
		botRight.sum(nextPos);
		botLeft.sum(nextPos);
		topLeft.sum(nextPos);
		topRight.sum(nextPos);
		
		player.moveEntity();
		playerView.updatePlayer(player.getPosition());
	}

	public Player getPlayer() {
		return this.player;
	}





}
