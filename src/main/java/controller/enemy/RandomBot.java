package controller.enemy;

import java.util.Random;

import model.character.Character;
import model.character.Enemy;
import model.character.Player;
import model.character.movableentity.MovableEntity;
import model.character.tools.health.SimpleHealth;
import util.Vector2D;
/**
 * A SimpleBot that make decision randomly
 * @author Matteo Susca
 *
 */
public class RandomBot implements SimpleBot {
	
	private MovableEntity entity = new Enemy(new Vector2D(0, 0), null, new SimpleHealth());

	@Override
	public void move() {
		boolean dir = new Random().nextBoolean();
		System.out.println(dir ? "right" : "left");
		entity.setRight(dir);
		entity.setLeft(!dir);
		entity.moveEntity();
	}
	
	public MovableEntity getEntity() {
		return this.entity;
	}

	@Override
	public void setPlayer(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnemy(Character character) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void fire() {
        // TODO Auto-generated method stub
        
    }

}
