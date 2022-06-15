package controller.enemy;

import controller.CharacterController;
import model.character.Character;
import model.map.Level;
import view.EnemyView;
/**
 * TODO: javadoc.
 *
 */
public class EnemyController extends CharacterController{
	
	private final SimpleBot brain;

	public EnemyController(Level level, Character character) {
		super(level, character);
		this.brain = new BasicBot();
		this.brain.setEnemy(character);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void controllerTick() {
		brain.move();
		super.controllerTick();
	}
	
	public SimpleBot getBrain() {
		return this.brain;
	}

	public void brainTick() {
		this.brain.move();
	}

}
