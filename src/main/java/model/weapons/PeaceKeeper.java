package model.weapons;

/**
 * Weapon - PeaceKeeper.
 *
 */
public class PeaceKeeper extends Weapon {
	public PeaceKeeper() {
		super("PeaceKeeper",  // Name
				6,            // Mag capacity
				30,           // Damage per bullet
				0,           // Fire rate
				120,          // Reload time
				0.2);        // Accuracy
	}
}
