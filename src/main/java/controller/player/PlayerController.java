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
				.hitbox(new Vector(1, 1))
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
		botRight.sum(nextPos);
		botLeft.sum(nextPos);
		botLeft.setX(Math.floor(botLeft.getX()));
        botLeft.setY(Math.floor(botLeft.getY()));
        botRight.setX(Math.floor(botRight.getX()));
        botRight.setY(Math.floor(botRight.getY()));
		/*System.out.println("nextPos:" + nextPos);
		System.out.println("botLeft:" + botLeft);
		System.out.println("botRight:" + botRight);
		System.out.println("---------------------------");*/
		if (this.collidableObjects.contains(botLeft) || this.collidableObjects.contains(botLeft)) {
		    player.setFall(false);
		    if (player.getSpeed().getY() > 0) {		        
		        player.setSpeed(player.getSpeed().getX(), 0);
		    }
		}
		player.moveEntity();
		playerView.updatePlayer(player.getPosition());
	}

	public Player getPlayer() {
		return this.player;
	}





}
