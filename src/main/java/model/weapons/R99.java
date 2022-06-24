package model.weapons;

/**
 * Weapon - R99, assault rifle.
 *
 */
public class R99 extends Weapon {

    private static final int MAG_CAPACITY = 30;
    private static final int DAMAGE_PER_BULLET = 8;
    private static final int FIRE_RATE = 7;
    private static final int RELOAD_TIME = 100;
    private static final double ACCURACY = 1.0;

    /**
     * Creates weapon.
     */
    public R99() {
        super("R99", MAG_CAPACITY, DAMAGE_PER_BULLET, FIRE_RATE, RELOAD_TIME, ACCURACY);
    }
}
