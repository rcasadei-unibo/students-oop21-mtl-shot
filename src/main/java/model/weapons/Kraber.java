package model.weapons;

/**
 * Weapon - Kraber, sniper.
 *
 */
public class Kraber extends Weapon {

    private static final int MAG_CAPACITY = 5;
    private static final int DAMAGE_PER_BULLET = 100;
    private static final int FIRE_RATE = 100;
    private static final int RELOAD_TIME = 150;
    private static final double ACCURACY = 50.0;

    /**
     * Creates weapon.
     */
    public Kraber() {
        super("Kraber", MAG_CAPACITY, DAMAGE_PER_BULLET, FIRE_RATE, RELOAD_TIME, ACCURACY);
    }

}
