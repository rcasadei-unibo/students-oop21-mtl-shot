package model.weapons;

/**
 * Weapon - Flatline, assault rifle for enemies.
 *
 */
public class Flatline extends Weapon {

    private static final int MAG_CAPACITY = 25;
    private static final int DAMAGE_PER_BULLET = 6;
    private static final int FIRE_RATE = 13;
    private static final int RELOAD_TIME = 100;
    private static final double ACCURACY = 0.8;

    /**
     * Creates weapon.
     */
    public Flatline() {
        super("Flatline", MAG_CAPACITY, DAMAGE_PER_BULLET, FIRE_RATE, RELOAD_TIME, ACCURACY);
    }

}
