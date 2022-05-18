package model.character.tools.health;

/**
 * A health wraps an object that has a durability that can increase and decrease.
 */
public interface Health {
    /**
     * The method that increases the health.
     * 
     * @param amount
     */
    void heal(int amount);

    /**
     * The method that decreases the health.
     * 
     * @param amount
     */
    void hurt(int amount);

    /**
     * Gets the current health.
     * 
     * @return health
     */
    int getHealth();

    /**
     * Returns true if the health is equals to 0.
     * 
     * @return if the object is dead
     */
    boolean isDead();

    /**
     * Sets the maximum value that health can reach.
     * 
     * @param amount
     */
    void setMaxHealth(int amount);

    /**
     * Gets the maximum value that health can reach.
     * 
     * @return maximum health reachable
     */
    int getMaxHealth();

}
