package model.character.tools.health;

/**
 * A simple implementation of the Health interface.
 */
public class SimpleHealth implements Health {

    /**
     * The value that can be increased and decreased.
     */
    private int health;
    /**
     * The maximum value that health can reach.
     */
    private int maxHealth;

    /**
     * The SimpleHealth constructor.
     */
    public SimpleHealth() {
        this.maxHealth = 100;
        this.health = this.maxHealth;
    }

    /**
     * The simpleHealth constructor that puts the maxHealth to the passed parameter.
     * 
     * @param maxHealth
     * @throws IllegalArgumentException if the parameter is negative or 0.
     */
    public SimpleHealth(final int maxHealth) {
        this.consistencyCheck(maxHealth);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException if the parameter is negative or 0.
     */
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
            throw new IllegalArgumentException();
        }
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException if the parameter is negative or 0.
     */
    @Override
    public void hurt(final int amount) {
        this.consistencyCheck(amount);
        this.health -= amount;
        if (health < 0) {
            this.health = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHealth() {
        return this.health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDead() {
        return this.health <= 0;
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException if @amount is negative or 0.
     */
    @Override
    public void setMaxHealth(final int amount) {
        this.consistencyCheck(amount);
        this.maxHealth = amount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Health: " + this.getHealth();
    }
}
