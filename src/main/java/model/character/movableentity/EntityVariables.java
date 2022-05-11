package model.character.movableentity;
/**
 * This is a static class where all the main environment constants are contained.
 * The constants can be modified to fit any possible environment
 * */
public final class EntityVariables {
	
    /**
     * Field that represents the max horizontal speed (in module) reachable in this environment.
     * */
	private static double maxHorizontalSpeed = 0.01;
	/**
	 * Field that represents the max vertical speed (in module) reachable in this environment.
	 * */
	private static double maxVerticalSpeed = 0.02;
	/**
	 * Field that represents the speed reached by the jumping subjects in this environment.
	 * */
	private static double jump = -0.02;
	/**
	 * Field that represents the horizontal deceleration (in module) in this environment.
	 * */
	private static double deceleration = 0.000_05;
	/**
	 * Field that represents the horizontal acceleration (in module) in this environment.
	 * */
	private static double acceleration = 0.000_05;
	/**
	 * Field that represents the vertical acceleration in this environment.
	 * */
	private static double gravity = 0.000_03;
	
	private EntityVariables() {
	}
	
	/**
	 * Gets the max horizontal speed (in module) reachable in this environment.
	 * @return max horizontal speed
	 * */
	public static double getMaxHorizontalSpeed() {
		return maxHorizontalSpeed;
	}

	/**
	 * Sets the max horizontal speed (in module) reachable in this environment.
	 * 
	 * @throws IllegalArgumentException if max horizontal speed is less than 0
	 * @param maxHorizontalSpeed
	 * */
	public static void setMaxHorizontalSpeed(final double maxHorizontalSpeed) {
	    moduleCheck(maxHorizontalSpeed);
	    EntityVariables.maxHorizontalSpeed = maxHorizontalSpeed;
	}


    /**
     * Gets the max vertical speed (in module) reachable in this environment.
     * 
     * @return max vertical speed
     * */
	public static double getMaxVerticalSpeed() {
		return maxVerticalSpeed;
	}

	/**
	 * Sets the max vertical speed (in module) reachable in this environment.
	 * 
     * @throws IllegalArgumentException if maxVerticalSpeed is less than 0
     * @param maxVerticalSpeed
     * */
	public static void setMaxVerticalSpeed(final double maxVerticalSpeed) {
	    moduleCheck(maxVerticalSpeed);
	    EntityVariables.maxVerticalSpeed = maxVerticalSpeed;
	}

	/**
	 * Gets the speed reached by the jumping subjects in this environment.
	 * 
	 * @return the jump
	 * */
	public static double getJump() {
		return jump;
	}

	/**
	 * Sets the speed reached by the jumping subjects in this environment.
	 * 
	 * @param jump
	 * */
	public static void setJump(final double jump) {
	    double value = jump;
	    if (jump > 0) {
	        value = -jump;
	    } //this method doesn't throw an exception because the jump sign depends on which reference system do you use
	    EntityVariables.jump = value;
	}

	/**
	 * Gets the horizontal deceleration (in module) in this environment.
	 * 
	 * @return the horizontal deceleration
	 * */
	public static double getDeceleration() {
		return deceleration;
	}

	/**
	 * Sets the horizontal deceleration (in module) in this environment.
	 * 
     * @throws IllegalArgumentException if deceleration is less than 0
     * @param deceleration
     * */
	public static void setDeceleration(final double deceleration) {
	    moduleCheck(deceleration);
	    EntityVariables.deceleration = deceleration;
	}

	/**
	 * Gets the horizontal acceleration (in module) in this environment.
	 * 
	 * @return the horizontal acceleration
	 * */
	public static double getAcceleration() {
		return acceleration;
	}

	/**
	 * Sets the horizontal acceleration (in module) in this environment.
	 * 
     * @throws IllegalArgumentException if horizontal acceleration is less than 0
     * @param acceleration
     * */
	public static void setAcceleration(final double acceleration) {
	    moduleCheck(acceleration);
	    EntityVariables.acceleration = acceleration;
	}

    /**
     * Gets the vertical acceleration in this environment.
     * 
     * @return the gravity
     * */
	public static double getGravity() {
		return gravity;
	}

	/**
	 * Sets the vertical acceleration in this environment.
	 * 
     * @throws IllegalArgumentException if gravity is less than 0
     * @param gravity
     * */
	public static void setGravity(final double gravity) {
	    moduleCheck(gravity);
	    EntityVariables.gravity = gravity;
	}

	private static void moduleCheck(final double value) {
	    if (value < 0) {
	        throw new IllegalArgumentException();
	    }
	}
}
