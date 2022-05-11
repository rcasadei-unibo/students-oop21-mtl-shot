package controller.enemy;

import model.character.Enemy;
import model.character.Player;
import model.character.movableentity.MovableEntity;
import model.character.tools.health.SimpleHealth;
import util.Vector;

/**
 * TODO
 * @author Matteo Susca
 *
 */
public class BasicBot implements SimpleBot {
	
	private MovableEntity entity = new Enemy(new Vector(0, 0), null, new SimpleHealth());	
	private Player player;
	
	public void setPlayer(Player p) {
		this.player = p;
	}

	@Override
	public void move() {
		if(this.player != null) {
			double distance = entity.getPosition().getX() - player.getPosition().getX();
			boolean dir = distance > 0;
			entity.setLeft(dir);
			entity.setRight(!dir);
			if(distance < 100) {
				entity.setRight(false);
				entity.setLeft(false);
			}
			entity.moveEntity();
		}
	}

	@Override
	public MovableEntity getEntity() {
		// TODO Auto-generated method stub
		return this.entity;
	}

}
