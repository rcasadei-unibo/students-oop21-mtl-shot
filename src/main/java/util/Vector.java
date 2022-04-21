package util;

import java.util.Objects;

/**
 * The class that represents a vector in 2 dimensions
 * */
public class Vector {

	/**
	 * The horizontal component
	 * */
	private double x;
	/**
	 * The vertical component
	 */
	private double y;
	
	/**
	 * The vector constructor that sets its x and y to @param x and @param y
	 */
	public Vector(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * The vector constructor, sets by default to 0 every component
	 */
	public Vector() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * The vector constructor that sets its x and y to 
	 * @param vec's x and y
	 */
	public Vector(final Vector vec) {
		this.x = vec.x;
		this.y = vec.y;
	}

	/**
	 * @return the horizontal component
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the horizontal component to @param x
	 */
	public void setX(final double x) {
		this.x = x;
	}

	/**
	 * @return the vertical component
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the vertical component to @param y
	 */
	public void setY(final double y) {
		this.y = y;
	}
	
	/**
	 * This method executes a vector sum between @this and @param vec
	 */
	public void sum(final Vector vec) {
		this.setX(this.x + vec.x);
		this.setY(this.y + vec.y);
	}
	
	/**
	 * This method executes a vector product between @this and @param vec
	 */
	public void mlt(final Vector vec) {
		this.setX(this.x * vec.x);
		this.setY(this.y * vec.y);
	}
	
	@Override
	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
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
	
}
