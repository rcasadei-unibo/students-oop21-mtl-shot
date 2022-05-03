package model.character.movableEntity;

/**
 * This is a static class where all the main environment constants are contained.
 * The constants can be modified to fit any possible environment
 * */
public final class EnvironmentConstants {
	
    /**
     * Field that represents the max horizontal speed (in module) reachable in this environment
     * */
	private static double maxHorizontalSpeed = 0.1;
	/**
	 * Field that represents the max vertical speed (in module) reachable in this environment
	 * */
	private static double maxVerticalSpeed = 3;
	/**
	 * Field that represents the speed reached by the jumping subjects in this environment
	 * */
	private static double jump = -0.4;
	/**
	 * Field that represents the horizontal deceleration (in module) in this environment
	 * */
	private static double deceleration = 0.5;
	/**
	 * Field that represents the horizontal acceleration (in module) in this environment
	 * */
	private static double horizontalAcceleration = 0.1;
	/**
	 * Field that represents the vertical acceleration in this environment
	 * */
	private static double gravity = 0.02;
	
	private EnvironmentConstants() {}

	/**
	 * @return the max horizontal speed (in module) reachable in this environment
	 * */
	public static double getMaxHorizontalSpeed() {
		return maxHorizontalSpeed;
	}

	/**
	 * Sets the max horizontal speed (in module) reachable in this environment
	 * @throws IllegalArgumentException if @maxHorizontalSpeed is less than 0
	 * */
	public static void setMaxHorizontalSpeed(final double maxHorizontalSpeed) {
	    moduleCheck(maxHorizontalSpeed);
		EnvironmentConstants.maxHorizontalSpeed = maxHorizontalSpeed;
	}

	private static void moduleCheck(final double value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @return the max vertical speed (in module) reachable in this environment
     * */
	public static double getMaxVerticalSpeed() {
		return maxVerticalSpeed;
	}

	/**
	 * Sets the max vertical speed (in module) reachable in this environment
     * @throws IllegalArgumentException if @maxVerticalSpeed is less than 0
     * */
	public static void setMaxVerticalSpeed(final double maxVerticalSpeed) {
	    moduleCheck(maxVerticalSpeed);
		EnvironmentConstants.maxVerticalSpeed = maxVerticalSpeed;
	}

	/**
	 * @return the speed reached by the jumping subjects in this environment
	 * */
	public static double getJump() {
		return jump;
	}

	/**
	 * Sets the speed reached by the jumping subjects in this environment
	 * */
	public static void setJump(final double jump) {
	    double value = jump;
	    if (jump > 0) {
	        value = -jump;
	    } //this method doesn't throw an exception because the jump sign depends on which reference system do you use
		EnvironmentConstants.jump = value;
	}

	/**
	 * @return the horizontal deceleration (in module) in this environment
	 * */
	public static double getDeceleration() {
		return deceleration;
	}

	/**
	 * Sets the horizontal deceleration (in module) in this environment
     * @throws IllegalArgumentException if @deceleration is less than 0
     * */
	public static void setDeceleration(final double deceleration) {
	    moduleCheck(deceleration);
		EnvironmentConstants.deceleration = deceleration;
	}

	/**
	 * @return the horizontal acceleration (in module) in this environment
	 * */
	public static double getHorizontalAcceleration() {
		return horizontalAcceleration;
	}

	/**
	 * Sets the horizontal acceleration (in module) in this environment
     * @throws IllegalArgumentException if @horizontalAcceleration is less than 0
     * */
	public static void setHorizontalAcceleration(final double horizontalAcceleration) {
	    moduleCheck(horizontalAcceleration);
		EnvironmentConstants.horizontalAcceleration = horizontalAcceleration;
	}

    /**
     * @return the vertical acceleration in this environment
     * */
	public static double getGravity() {
		return gravity;
	}

	/**
	 * Sets the vertical acceleration in this environment
     * @throws IllegalArgumentException if @gravity is less than 0
     * */
	public static void setGravity(final double gravity) {
	    moduleCheck(gravity);
		EnvironmentConstants.gravity = gravity;
	}

}
