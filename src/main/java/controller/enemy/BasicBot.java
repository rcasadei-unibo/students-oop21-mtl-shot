package controller.enemy;

import model.character.Enemy;
import model.character.Player;
import model.character.movableentity.MovableEntity;
import model.character.tools.health.SimpleHealth;
import util.Vector2D;

/**
 * TODO
 * @author Matteo Susca
 *
 */
public class BasicBot implements SimpleBot {
	
	private MovableEntity enemy = new Enemy(new Vector2D(), null, new SimpleHealth());	
	private Player player;
	
	public void setPlayer(final Player p) {
		this.player = p;
	}

	@Override
	public void move() {
		if (this.player != null) {
			double distance = enemy.getPosition().getX() - player.getPosition().getX();
			boolean dir = distance > 0;
			enemy.setLeft(dir);
			enemy.setRight(!dir);
			if(Math.abs(distance) < 0.5) {
				enemy.setRight(false);
				enemy.setLeft(false);
			}
			enemy.moveEntity();
		}
	}

	@Override
	public MovableEntity getEntity() {
		// TODO Auto-generated method stub
		return this.enemy;
	}

}
