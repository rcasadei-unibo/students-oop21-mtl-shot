package utilities.entity;

public final class EnvironmentConstants {
	
	private static double maxHorizontalSpeed = 8;
	private static double maxVerticalSpeed = 30;
	private static double jump = -20;
	private static double deceleration = 0.5;
	private static double horizontalAcceleration = 0.3;
	private static double verticalAcceleration = 0.5;
	private static double gravity = 0.7;
	
	private EnvironmentConstants() {}

	public static double getMaxHorizontalSpeed() {
		return maxHorizontalSpeed;
	}

	public static void setMaxHorizontalSpeed(final double maxHorizontalSpeed) {
		EnvironmentConstants.maxHorizontalSpeed = maxHorizontalSpeed;
	}

	public static double getMaxVerticalSpeed() {
		return maxVerticalSpeed;
	}

	public static void setMaxVerticalSpeed(final double maxVerticalSpeed) {
		EnvironmentConstants.maxVerticalSpeed = maxVerticalSpeed;
	}

	public static double getJump() {
		return jump;
	}

	public static void setJump(final double jump) {
		EnvironmentConstants.jump = jump;
	}

	public static double getDeceleration() {
		return deceleration;
	}

	public static void setDeceleration(final double deceleration) {
		EnvironmentConstants.deceleration = deceleration;
	}

	public static double getHorizontalAcceleration() {
		return horizontalAcceleration;
	}

	public static void setHorizontalAcceleration(final double horizontalAcceleration) {
		EnvironmentConstants.horizontalAcceleration = horizontalAcceleration;
	}

	public static double getVerticalAcceleration() {
		return verticalAcceleration;
	}

	public static void setVerticalAcceleration(final double verticalAcceleration) {
		EnvironmentConstants.verticalAcceleration = verticalAcceleration;
	}

	public static double getGravity() {
		return gravity;
	}

	public static void setGravity(final double gravity) {
		EnvironmentConstants.gravity = gravity;
	}

}
