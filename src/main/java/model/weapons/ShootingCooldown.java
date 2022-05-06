package model.weapons;

public class ShootingCooldown {
	private final int totalTicks;
	private int elapsedTicks;
	
	public ShootingCooldown(final int totalTicks) {
		this.totalTicks = totalTicks;
		this.elapsedTicks = 0;
	}
	
	public void tick() {
		this.elapsedTicks++;
	}
	
	public boolean isCooldownOver() {
		return this.elapsedTicks >= this.totalTicks;
	}

	@Override
	public String toString() {
		return "ShootingCooldown [totalTicks=" + totalTicks + ", elapsedTicks=" + elapsedTicks + "]";
	}
}
