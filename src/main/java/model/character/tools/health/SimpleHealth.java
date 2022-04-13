package model.character.tools.health;

public class SimpleHealth implements Health {

	private int health;
	private int maxHealth;
	
	public SimpleHealth() {
		this.maxHealth = 100;
		this.health = this.maxHealth;
	}
	
	public SimpleHealth(final int maxHealth) {
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}
	
	@Override
	public void heal(final int amount) {
		this.consistencyCheck(amount);
		this.health += amount;
		if (health > this.maxHealth) {
			this.health = maxHealth;
		}
	}

	private void consistencyCheck(final int amount) {
		if (amount <= 0) {
			throw new IllegalStateException();
		}
	}

	@Override
	public void hurt(final int amount) {
		this.consistencyCheck(amount);
		this.health -= amount;
		if (health < 0) {
			this.health = 0;
		}
	}

	@Override
	public int getHealth() {
		return this.health;
	}

	@Override
	public boolean isDead() {
		return this.health == 0;
	}

	@Override
	public void setMaxHealth(final int amount) {
		this.consistencyCheck(amount);
		this.maxHealth = amount;
	}

	@Override
	public int getMaxHealth() {
		return this.maxHealth;
	}

	@Override
	public String toString() {
		return this.getHealth() + "";
	}
}
