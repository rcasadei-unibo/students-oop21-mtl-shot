package controller;

import java.util.HashMap;
import java.util.Map;

import model.character.Character;

/**
 * TODO: write javadoc.
 *
 */
public class WeaponController {
    private Map<Character, Cooldown> shootingTimers;
    private Map<Character, Cooldown> reloadingTimers;

	public WeaponController() {
		this.shootingTimers = new HashMap<>();
		this.reloadingTimers = new HashMap<>();
	}

	public void controllerTick() {
		this.shootingTimers.forEach((c, sc) -> { sc.tick(); });
		this.shootingTimers.entrySet().removeIf(e -> e.getValue().isCooldownOver());
		
		this.reloadingTimers.forEach((c, sc) -> { sc.tick(); });
		this.reloadingTimers.entrySet().removeIf(e -> e.getValue().isCooldownOver());
	}

	public boolean tryToShoot(final Character characterShooting) {
		if (!this.shootingTimers.containsKey(characterShooting) &&
				characterShooting.getWeapon().getBulletsInMag() != 0 &&
				!this.reloadingTimers.containsKey(characterShooting)) {
			
			/* If characterShooting is not in this.timers, he can shoot */
			this.shootingTimers.put(characterShooting, new Cooldown(characterShooting.getWeapon().getFireRate()));
			characterShooting.getWeapon().shoot();
			return true;
			
		} else if (characterShooting.getWeapon().getBulletsInMag() == 0) {
			this.tryToReload(characterShooting);
		}
		return false;
	}
	
	public boolean tryToReload(final Character characterReloading) {
		if (!this.reloadingTimers.containsKey(characterReloading)) {
			this.reloadingTimers.put(characterReloading, new Cooldown(characterReloading.getWeapon().getReloadTime()));
			characterReloading.getWeapon().reload();
			return true;
		}
		return false;
	}
	
}
