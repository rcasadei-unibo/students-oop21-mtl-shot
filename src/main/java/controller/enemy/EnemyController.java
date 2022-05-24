package controller.enemy;

import controller.CharacterController;
import controller.map.MapController;
import model.character.Character;
import view.EnemyView;

public class EnemyController extends CharacterController{
	
	private final SimpleBot brain;

	public EnemyController(EnemyView characterView, MapController mapController, Character character) {
		super(characterView, mapController, character);
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
