package controller.enemy;

import model.character.Character;
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
	
	private Enemy enemy;	
	private Player player;
	
	public void setPlayer(final Player p) {
		this.player = p;
	}

	@Override
	public void move() {
		if(this.player != null) {
			System.out.println("Pl POS: " + player.getPosition());
			double distance = enemy.getPosition().getX() - player.getPosition().getX();
			System.out.println("En POS: " + enemy.getPosition());
			System.out.println("En DIS: " + distance);
			boolean dir = distance > 0;
			enemy.setLeft(dir);
			enemy.setRight(!dir);
			if(Math.abs(distance) < 4) {
				enemy.setRight(false);
				enemy.setLeft(false);
				if(Math.abs(distance) < 3) {
					enemy.setRight(dir);
					enemy.setLeft(!dir);
				}
			}
		}
	}

	@Override
	public MovableEntity getEntity() {
		// TODO Auto-generated method stub
		return this.enemy;
	}

	@Override
	public void setEnemy(Character character) {
		this.enemy = (Enemy)character;
		
	}

}
