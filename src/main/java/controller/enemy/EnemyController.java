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

	public EnemyController(EnemyView characterView, Level level, Character character) {
		super(characterView, level, character);
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
