package util;

/**
 * Status of the enemy.
 *
 */
public enum Status {
    /**
     * IDLE: the enemy moves randomly around the map.
     */
    IDLE,

    /**
     * ACTIVE: the enemy searches for and attacks the player.
     */
    ACTIVE;
}
