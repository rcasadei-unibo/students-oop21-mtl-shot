package controller.enemy;

import controller.CharacterController;
import model.character.Character;
import model.map.Level;
/**
 * TODO: javadoc.
 *
 */
public class EnemyController extends CharacterController{
	
	private final SimpleBot brain;
	private boolean isActive = true;

	public EnemyController(Level level, Character character) {
		super(level, character);
		this.brain = new BasicBot(character, level);
	}
	
	@Override
	public void controllerTick() {
		brain.move();
		brain.fire();
		super.controllerTick();
	}
	
	public SimpleBot getBrain() {
		return this.brain;
	}

	public void brainTick() {
		this.brain.move();
	}
	
	public boolean isActive() {
	    return this.isActive;
	}
	
	public void setActive(final boolean status) {
	    this.isActive = status;
	}

}
