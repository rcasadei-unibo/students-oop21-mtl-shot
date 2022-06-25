package controller.weapon;

import java.util.HashMap;
import java.util.Map;

import model.character.Character;

/**
 * Handles weapons cooldowns for shooting and reloading.
 *
 */
public class WeaponController {
    private Map<Character, Cooldown> shootingTimers;
    private Map<Character, Cooldown> reloadingTimers;

    /**
     * Instantiates WeaponController.
     */
    public WeaponController() {
        this.shootingTimers = new HashMap<>();
        this.reloadingTimers = new HashMap<>();
    }

    /**
     * This function is called by Controller every game tick.
     */
    public void controllerTick() {
        this.shootingTimers.forEach((c, sc) -> {
            sc.tick();
        });
        this.shootingTimers.entrySet().removeIf(e -> e.getValue().isCooldownOver());

        this.reloadingTimers.forEach((c, sc) -> {
            sc.tick();
        });
        this.reloadingTimers.entrySet().removeIf(e -> e.getValue().isCooldownOver());
    }

    /**
     * characterShooting reloads olny if its corresponding shooting cooldown is
     * over.
     * 
     * @param characterShooting
     * @return true if characterShooting has shot
     */
    public TryToShootReturn tryToShoot(final Character characterShooting) {
        if (!this.shootingTimers.containsKey(characterShooting) && characterShooting.getWeapon().getBulletsInMag() != 0
                && !this.reloadingTimers.containsKey(characterShooting)) {

            /* If characterShooting is not in this.timers, he can shoot */
            this.shootingTimers.put(characterShooting, new Cooldown(characterShooting.getWeapon().getFireRate()));
            characterShooting.getWeapon().shoot();
            return TryToShootReturn.SHOOT;

        } else if (characterShooting.getWeapon().getBulletsInMag() == 0) {
            if (this.tryToReload(characterShooting)) {
                return TryToShootReturn.RELOAD;
            }
        }
        return TryToShootReturn.NOTHING;
    }

    /**
     * characterReloading reloads only if its corresponding reloading cooldown is
     * over.
     * 
     * @param characterReloading
     * @return true if characterReloading has reloaded
     */
    public boolean tryToReload(final Character characterReloading) {
        if (!this.reloadingTimers.containsKey(characterReloading)) {
            this.reloadingTimers.put(characterReloading, new Cooldown(characterReloading.getWeapon().getReloadTime()));
            characterReloading.getWeapon().reload();
            return true;
        }
        return false;
    }

    /**
     * Utility modeling this.tryToShoot() different outputs.
     *
     */
    public enum TryToShootReturn {

        /**
         * Character can shoot, and shoots.
         */
        SHOOT,

        /**
         * Character can't shoot because mag is empty, and reloads.
         */
        RELOAD,

        /**
         * Chacarter can't shoot and can't reload (cooldown time is not over.
         */
        NOTHING
    }

}
