package model.weapons;

/**
 * Weapon - PeaceKeeper, shotgun.
 *
 */
public class PeaceKeeper extends Weapon {

    private static final int MAG_CAPACITY = 6;
    private static final int DAMAGE_PER_BULLET = 30;
    private static final int FIRE_RATE = 0;
    private static final int RELOAD_TIME = 120;
    private static final double ACCURACY = 0.2;

    /**
     * Creates weapon.
     */
    public PeaceKeeper() {
        super("PeaceKeeper", MAG_CAPACITY, DAMAGE_PER_BULLET, FIRE_RATE, RELOAD_TIME, ACCURACY);
    }
}
