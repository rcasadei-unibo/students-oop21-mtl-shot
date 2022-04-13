package utilities;

public final class EnviromentConstants {
	
	private static double maxHorizontalSpeed = 8;
	private static double maxVerticalSpeed = 30;
	private static double jump = -20;
	private static double deceleration = 0.5;
	private static double horizontalAcceleration = 0.3;
	private static double verticalAcceleration = 0.5;
	private static double gravity = 0.7;
	
	private EnviromentConstants() {}

	public static double getMaxHorizontalSpeed() {
		return maxHorizontalSpeed;
	}

	public static void setMaxHorizontalSpeed(final double maxHorizontalSpeed) {
		EnviromentConstants.maxHorizontalSpeed = maxHorizontalSpeed;
	}

	public static double getMaxVerticalSpeed() {
		return maxVerticalSpeed;
	}

	public static void setMaxVerticalSpeed(final double maxVerticalSpeed) {
		EnviromentConstants.maxVerticalSpeed = maxVerticalSpeed;
	}

	public static double getJump() {
		return jump;
	}

	public static void setJump(final double jump) {
		EnviromentConstants.jump = jump;
	}

	public static double getDeceleration() {
		return deceleration;
	}

	public static void setDeceleration(final double deceleration) {
		EnviromentConstants.deceleration = deceleration;
	}

	public static double getHorizontalAcceleration() {
		return horizontalAcceleration;
	}

	public static void setHorizontalAcceleration(final double horizontalAcceleration) {
		EnviromentConstants.horizontalAcceleration = horizontalAcceleration;
	}

	public static double getVerticalAcceleration() {
		return verticalAcceleration;
	}

	public static void setVerticalAcceleration(final double verticalAcceleration) {
		EnviromentConstants.verticalAcceleration = verticalAcceleration;
	}

	public static double getGravity() {
		return gravity;
	}

	public static void setGravity(final double gravity) {
		EnviromentConstants.gravity = gravity;
	}

}
