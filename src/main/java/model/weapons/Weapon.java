package model.weapons;

/*
 * Weapon class models an infinite-bullets
 * generic weapon
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
	
	private int damagePerBullet;
	
	/*
	 * The amount of milliseconds between two consecutive bullets
	 */
	private final int fireRate;
	
	/*
	 * The amount of milliseconds needed to reload
	 */
	private final int reloadTime;
	
	public Weapon(final String name, final int magCapacity, final int damagePerBullet, final int fireRate, final int reloadTime) {
		this.name = name;
		this.magCapacity = magCapacity;
		this.damagePerBullet = damagePerBullet;
		this.fireRate = fireRate;
		this.reloadTime = reloadTime;
	}
	
	public void reload() {
		this.bulletsInMag = this.magCapacity;
	}
	
	public void shoot() {
		if (this.bulletsInMag == 0) {
			//throw new NoBulletsInMagException()
		} else {
			this.bulletsInMag--;
		}
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
}
