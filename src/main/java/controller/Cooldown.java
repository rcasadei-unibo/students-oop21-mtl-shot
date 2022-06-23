package controller;

/**
 * This class models a sort of timer, a counter that
 * is incremented every game tick. This timer has a
 * certain number of total ticks, after which the counter
 * is "over". There's no need to count how many ticks
 * the counter goes over the totalTicks number, so
 * the tick() method can be always called.
 * 
 */
public class Cooldown {
	
	/*
	 * Total amount of ticks
	 */
	private final int totalTicks;
	
	/*
	 * Amount of ticks elapsed
	 */
	private int elapsedTicks;
	
	public Cooldown(final int totalTicks) {
		this.totalTicks = totalTicks;
		this.elapsedTicks = 0;
	}
	
	/*
	 * Increments the number of elapsed ticks
	 */
	public void tick() {
		this.elapsedTicks++;
	}
	
	/*
	 * Returns true if the cooldown is over
	 */
	public boolean isCooldownOver() {
		return this.elapsedTicks >= this.totalTicks;
	}

	@Override
	public String toString() {
		return "ShootingCooldown [totalTicks=" + totalTicks + ", elapsedTicks=" + elapsedTicks + "]";
	}
}
