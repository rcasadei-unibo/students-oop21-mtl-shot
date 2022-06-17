package controller;

import java.util.HashMap;
import java.util.Map;

import model.character.Character;

/**
 * TODO: write javadoc
 *
 */
public class WeaponController {
	private Controller controllerReference;
	private Map<Character, Cooldown> timers;

	public WeaponController(final Controller controllerReference) {
		this.controllerReference = controllerReference;
		this.timers = new HashMap<>();
	}

	public void controllerTick() {
		this.timers.forEach((c, sc) -> { sc.tick(); });
		this.timers.entrySet().removeIf(e -> e.getValue().isCooldownOver());
	}

	public boolean tryToShoot(final Character characterShooting) {
		if (!this.timers.containsKey(characterShooting) && characterShooting.getWeapon().getBulletsInMag() != 0) {
			/* If characterShooting is not in this.timers, he can shoot */
			this.timers.put(characterShooting, new Cooldown(characterShooting.getWeapon().getFireRate()));
			characterShooting.getWeapon().shoot();
			return true;
		}
		return false;
	}
}
