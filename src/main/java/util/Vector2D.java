package util;

import java.util.Objects;

/**
 * The class that represents a vector in 2 dimensions
 * */
public class Vector2D {

	/**
	 * The horizontal component
	 * */
	private double x;
	/**
	 * The vertical component
	 */
	private double y;
	
	/**
	 * The vector constructor that sets x and y
	 * 
	 * @param x component
	 * @param y component
	 */
	public Vector2D(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * The vector constructor, sets by default to 0 every component
	 */
	public Vector2D() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * The vector constructor that sets x and y equal to the passed vector
	 * 
	 * @param vector
	 */
	public Vector2D(final Vector2D vector) {
		this.x = vector.x;
		this.y = vector.y;
	}

	/**
	 * Gets the x component
	 * 
	 * @return x
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the horizontal component 
	 * 
	 * @param x
	 */
	public void setX(final double x) {
		this.x = x;
	}

	/**
	 * Gets the vertical component
	 * 
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the vertical component
	 * 
	 * @param y
	 */
	public void setY(final double y) {
		this.y = y;
	}
	
	/**
	 * Sums two vectors
	 * 
	 * @param vec second vector
	 * @return summed vector
	 */
	public Vector2D sum(final Vector2D vec) {
		return new Vector2D(this.x + vec.getX(), this.y + vec.getY());
	}
	
	/**
	 * Subtracts two vectors
	 * 
	 * @param vec second vector
	 * @return subtracted vector
	 */
	public Vector2D dif(final Vector2D vec) {
		return new Vector2D(this.x - vec.getX(), this.y + vec.getY());
	}
	
	/**
	 * Multiplies two vectors
	 * 
	 * @param vec second vector
	 * @return multiplied vector
	 */
	public Vector2D prod(final Vector2D vec) {
		return new Vector2D(this.x * vec.getX(), this.y * vec.getY());
	}
	
	/**
	 * Divides two vectors
	 * 
	 * @param vec second vector
	 * @return divided vector
	 */
	public Vector2D div(final Vector2D vec) {
		return new Vector2D(this.x / vec.getX(), this.y / vec.getY());
	}
	
	/**
	 * Add the vector passed to the current vector
	 * 
	 * @param vec vector to be added
	 */
	public void add(final Vector2D vec) {
		this.x += vec.getX();
		this.y += vec.getY();
	}
	
	/**
	 * Multiplies the current vector with the scalar value
	 * 
	 * @param scalar to be multiplied
	 */
	public void scalarProd(final double scalar) {
		this.x *= scalar;
		this.y *= scalar;
	}
	
	/**
	 * Multiplies the vector passed to the current vector
	 * 
	 * @param vec vector to be multiplied
	 */
	public void mlt(final Vector2D vec) {
		this.setX(this.x * vec.x);
		this.setY(this.y * vec.y);
	}
	
	/**
	 * Gets the current vector modulus
	 * 
	 * @return modulus
	 */
	public double getModulus() {
		return Math.sqrt(this.x * this.x + this.y + this.y);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	/**
	 * {@inheritDoc}
	 */
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
		final Vector2D other = (Vector2D) obj;
		return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
	}
	
}
