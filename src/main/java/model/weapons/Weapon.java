package model.weapons;

public abstract class Weapon {
	// This Weapon class models an infinite-bullets generic weapon
	private final String name;
	private final int magCapacity;
	private int bulletsInMag;
	private int damagePerBullet;
	private final int fireRate; // fireRate is the amount of milliseconds between two consecutive bullets
	
	public Weapon(final String name, final int magCapacity, final int damagePerBullet, final int fireRate) {
		this.name = name;
		this.magCapacity = magCapacity;
		this.damagePerBullet = damagePerBullet;
		this.fireRate = fireRate;
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
