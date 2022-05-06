package controller;

import java.util.HashMap;
import java.util.Map;

import model.character.Character;
import model.weapons.ShootingCooldown;

public class WeaponController {
	//private Controller controllerReference; TODO UNCOMMENT AFTER MERGING
	private TemporaryController controllerReference;
	private Map<Character, ShootingCooldown> timers;

	public WeaponController(final TemporaryController controllerReference /* final Controller controllerReference TODO UNCOMMENT AFTER MERGING */) {
		this.controllerReference = controllerReference;
		this.timers = new HashMap<>();
	}

	public void controllerTick() {
		this.timers.forEach((c, sc) -> {
			sc.tick();
			System.out.println("Ticking " + c.getWeapon().getClass().getName() + "'s weapon");
			System.out.println(this.timers.values());
			if (sc.isCooldownOver()) {
				System.out.println("Ending " + c.getWeapon().getClass().getName() + "'s shooting timer");
			}
		});
		this.timers.entrySet().removeIf(e -> e.getValue().isCooldownOver());
	}

	public boolean tryToShoot(final Character characterShooting) {
		if (!this.timers.containsKey(characterShooting)) { /* If characterShooting is not in this.timers, he can shoot */
			this.timers.put(characterShooting, new ShootingCooldown(characterShooting.getWeapon().getFireRate()));
			characterShooting.getWeapon().shoot();
			return true;
		}
		return false;
	}
}
