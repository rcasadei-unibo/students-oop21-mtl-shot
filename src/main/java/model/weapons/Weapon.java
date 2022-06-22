package model.weapons;

import java.util.Objects;

/**
 * Weapon class models an infinite-bullets
 * generic weapon
 * 
 */
public abstract class Weapon {
	private final String name;
	
	/*
	 * The amount of bullets that can be contained in a mag
	 */
	private final int magCapacity;
	
	/*
	 * The current amount of bullets in the mag
	 */
	private int bulletsInMag;
	
	/*
	 * The amount of damage every bullet does when hits a Character
	 */
	private int damagePerBullet;
	
	/*
	 * The amount of ticks between two consecutive bullets
	 */
	private final int fireRate;
	
	/*
	 * The amount of ticks needed to reload
	 */
	private final int reloadTime;
	
	private final double accuracy;
	
	public Weapon(final String name, final int magCapacity, final int damagePerBullet, final int fireRate, final int reloadTime, final double accuracy) {
		this.name = name;
		this.magCapacity = magCapacity;
		this.damagePerBullet = damagePerBullet;
		this.fireRate = fireRate;
		this.reloadTime = reloadTime;
		this.accuracy = accuracy;
		
		/*
		 * When initialized, the mag is full
		 */
		this.bulletsInMag = this.magCapacity;
	}
	
	public void reload() {
		this.bulletsInMag = this.magCapacity;
        System.out.println("Reloading...");
	}
	
	public void shoot() {
		if (this.bulletsInMag != 0) {
			this.bulletsInMag--;
		}
		System.out.println("Shooting...");
	}

	public int getDamagePerBullet() {
		return this.damagePerBullet;
	}

	public void setDamagePerBullet(final int damagePerBullet) {
		this.damagePerBullet = damagePerBullet;
	}

	public String getName() {
		return this.name;
	}

	public int getMagCapacity() {
		return this.magCapacity;
	}

	public int getBulletsInMag() {
		return this.bulletsInMag;
	}
	
	public int getReloadTime() {
		return this.reloadTime;
	}
	
	public int getFireRate() {
		return this.fireRate;
	}
	
	public double getAccuracy() {
		return this.accuracy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accuracy, bulletsInMag, damagePerBullet, fireRate, magCapacity, name, reloadTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weapon other = (Weapon) obj;
		return Double.doubleToLongBits(accuracy) == Double.doubleToLongBits(other.accuracy)
				&& bulletsInMag == other.bulletsInMag && damagePerBullet == other.damagePerBullet
				&& fireRate == other.fireRate && magCapacity == other.magCapacity && Objects.equals(name, other.name)
				&& reloadTime == other.reloadTime;
	}
	
}
