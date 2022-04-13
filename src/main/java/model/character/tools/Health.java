package model.character.tools;

public interface Health {

	void heal(int amount);
	void hurt(int amount);
	int getHealth();
	boolean isDead();
	void setMaxHealth(int amount);
	int getMaxHealth();
	
}
