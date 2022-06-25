package model.character.movableentity;

import controller.Controller;
import util.Vector2D;

/**
 * This is a static class where all the main environment constants are
 * contained. The constants can be modified to fit any possible environment
 */
public final class EntityConstants {

    /**
     * Field that represents the max horizontal speed (in module) reachable in this
     * environment.
     */
    public static final double MAXHORIZONTALSPEED = 5 / Controller.TPS;
    /**
     * Field that represents the max vertical speed (in module) reachable in this
     * environment.
     */
    public static final double MAXVERTICALSPEED = 8 / Controller.TPS;
    /**
     * Field that represents the speed reached by the jumping subjects in this
     * environment.
     */
    public static final double JUMP = -7 / Controller.TPS;
    /**
     * Field that represents the horizontal deceleration (in module) in this
     * environment.
     */
    public static final double DECELERATION = 1 / Controller.TPS;
    /**
     * Field that represents the horizontal acceleration (in module) in this
     * environment.
     */
    public static final double ACCELERATION = 0.5 / Controller.TPS;
    /**
     * Field that represents the vertical acceleration in this environment.
     */
    public static final double GRAVITY = 9.81 / Math.pow(Controller.TPS, 2);

    //For enemy only

    /**
     * Field that represents the distance that the enemy keeps from the player.
     */
    public static final double ENEMY_DISTANCE = 7;

    /**
     * Field that represents the tolerance that the enemy has before start moving depending on player position.
     */
    public static final double ENEMY_TOLERANCE = 0.5;

    /**
     * Field that represents the variation of the distance kept by the enemy.
     */
    public static final int ENEMY_VARIATON = 6;

    /**
     * the probability "n" that in a random movement the enemy changes direction (1/n).
     */
    public static final int CHANGE_DIR_PROBABILITY = 50;

    /**
     * The delta for the checks of collision of the enemy.
     */
    public static final double ENEMY_DELTA = 0.5;

    /**
     * The default hitbox size.
     */
    public static final Vector2D DEFAULT_HITBOX = new Vector2D(1, 1.5);

    private EntityConstants() {
    }
}
