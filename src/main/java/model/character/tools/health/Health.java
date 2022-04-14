package model.character.tools.health;

/**A health wraps an object that has a durability that can increase and decrease*/
public interface Health {

    /**The method that increases the health by @amount
     * @throws IllegalArgumentException if @amount is negative or 0*/
	void heal(int amount);
	/**The method that decreases the health by @amount
     * @throws IllegalArgumentException if @amount is negative or 0*/
	void hurt(int amount);
	/**@return the health*/
	int getHealth();
	/**@return true if the health is equals to 0*/
	boolean isDead();
	/**set the maximum value that health can reach to @amount
	 * @throws IllegalArgumentException if @amount is negative or 0*/
	void setMaxHealth(int amount);
	/**@return the maximum value that health can reach*/
	int getMaxHealth();
	
}
