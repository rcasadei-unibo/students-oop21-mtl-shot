package model.character.tools.health;

public class HealthImpl implements Health{

    private int health;
    private int maxHealth; //meant for power ups only
    
    private void integrityCheck(final int amount) {
        if (amount <= 0) {
            throw new IllegalStateException();
        }
    }
    
    public HealthImpl() {
        this.maxHealth = 100;
        this.health = maxHealth;
    }
    
    public HealthImpl(final int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    @Override
    public void heal(final int amount) {
        this.integrityCheck(amount);
        this.health += amount;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }


    @Override
    public void hurt(final int amount) {
        this.integrityCheck(amount);
        this.health -= amount;
        if (this.health < 0) {
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
        this.integrityCheck(amount);
        this.maxHealth = amount;
    }

    @Override
    public int getMaxHealth() {
        return this.maxHealth;
    }
    
    
}
