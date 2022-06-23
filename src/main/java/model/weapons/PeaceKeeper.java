package model.weapons;

/**
 * Weapon - PeaceKeeper.
 *
 */
public class PeaceKeeper extends Weapon {
	public PeaceKeeper() {
		super("PeaceKeeper",  // Name
				6,            // Mag capacity
				15,           // Damage per bullet
				70,           // Fire rate
				200,          // Reload time
				0.15);        // Accuracy
	}
}
