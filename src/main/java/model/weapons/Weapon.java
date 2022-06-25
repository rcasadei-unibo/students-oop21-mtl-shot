package model.weapons;

import java.util.Objects;

/**
 * Weapon class models an infinite-bullets generic weapon.
 * 
 */
public abstract class Weapon {

	/**
	 * Weapon's name.
	 */
	private final String name;

	/**
	 * The amount of bullets that can be contained in a mag.
	 */
	private final int magCapacity;

	/**
	 * The current amount of bullets in the mag.
	 */
	private int bulletsInMag;

	/**
	 * The amount of damage every bullet does when hits a Character.
	 */
	private int damagePerBullet;

	/**
	 * The amount of ticks between two consecutive bullets.
	 */
	private final int fireRate;

	/**
	 * The amount of ticks needed to reload.
	 */
	private final int reloadTime;

	/**
	 * Weapon's accuracy (higher is better).
	 */
	private final double accuracy;

	/**
	 * Instantiate weapon.
	 * 
	 * @param name            - Weapon's name
	 * @param magCapacity     - Mag capacity
	 * @param damagePerBullet - Damage per bullet
	 * @param fireRate        - Fire rate
	 * @param reloadTime      - Reload time
	 * @param accuracy        - Accuracy
	 */
	public Weapon(final String name, final int magCapacity, final int damagePerBullet, final int fireRate,
			final int reloadTime, final double accuracy) {
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

	/**
	 * Refills weapon's mag.
	 */
	public void reload() {
		this.bulletsInMag = this.magCapacity;
	}

	/**
	 * If mag is not empty, the amount of bullets in mag is decremented.
	 */
	public void shoot() {
		if (this.bulletsInMag != 0) {
			this.bulletsInMag--;
		}
	}

	/**
	 * @return weapon's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param damagePerBullet the damagePerBullet to set
	 */
	public void setDamagePerBullet(final int damagePerBullet) {
		this.damagePerBullet = damagePerBullet;
	}

	/**
	 * @return weapon's mag capacity
	 */
	public int getMagCapacity() {
		return magCapacity;
	}

	/**
	 * @return weapon's bullets in mag
	 */
	public int getBulletsInMag() {
		return bulletsInMag;
	}

	/**
	 * @return weapon's damage per bullet
	 */
	public int getDamagePerBullet() {
		return damagePerBullet;
	}

	/**
	 * @return weapon's fire rate
	 */
	public int getFireRate() {
		return fireRate;
	}

	/**
	 * @return weapon's reload time
	 */
	public int getReloadTime() {
		return reloadTime;
	}

	/**
	 * @return weapon's accuracy
	 */
	public double getAccuracy() {
		return accuracy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accuracy, bulletsInMag, damagePerBullet, fireRate, magCapacity, name, reloadTime);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Weapon other = (Weapon) obj;
		return Double.doubleToLongBits(accuracy) == Double.doubleToLongBits(other.accuracy)
				&& damagePerBullet == other.damagePerBullet && fireRate == other.fireRate
				&& magCapacity == other.magCapacity && Objects.equals(name, other.name)
				&& reloadTime == other.reloadTime;
	}

}
