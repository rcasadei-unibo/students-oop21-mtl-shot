package util;

import java.util.Objects;

/**
 * An object to represent a vector in a 2D world.
 * Each vector is characterized by 2 components: x and y.
 * These components represent a point in a 2D space that is connected
 * by the vector with the center of the reference system.
 * */
public class Vector {

    /**
     * the x component
     * */
	private final double x;
	/**
	 * the y component
	 * */
	private final double y;
	
	public Vector(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the x component
	 * */
	public double getX() {
		return this.x;
	}
	
	/**
	 * @return the y component
	 * */
	public double getY() {
		return this.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
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
		final Vector other = (Vector) obj;
		return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
	}

	@Override
	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}
}
