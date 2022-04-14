package model.character.tools.health;

/**An health wrap an object that has a durability that can increase and decrease*/
public interface Health {

    /**The method that increases the actual health by @amount
     * @throws IllegalStateException if @amount is negative*/
	void heal(int amount);
	/**The method that decreases the actual health by @amount
     * @throws IllegalStateException if @amount is negative*/
	void hurt(int amount);
	/**@return the actual health*/
	int getHealth();
	/**@return true if the actual health is equals to 0*/
	boolean isDead();
	/**set the maximum value that health can reach at @amount
	 * @throws IllegalStateException if @amount is negative*/
	void setMaxHealth(int amount);
	/**@return the maximum value that health can reach*/
	int getMaxHealth();
	
}
