package model.character.tools.health;

public interface Health {

	void heal(int amount);
	void hurt(int amount);
	int getHealth();
	boolean isDead();
	void setMaxHealth(int amount);
	int getMaxHealth();
	
}
