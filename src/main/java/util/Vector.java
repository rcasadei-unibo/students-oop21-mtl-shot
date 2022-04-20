package util;

public class Vector {

	private double x;
	private double y;
	
	public Vector(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector(final Vector vec) {
		this.x = vec.x;
		this.y = vec.y;
	}

	public double getX() {
		return x;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(final double y) {
		this.y = y;
	}
	
	public void sum(final Vector vec) {
		this.setX(this.x + vec.x);
		this.setY(this.y + vec.y);
	}
	
	public void mlt(final Vector vec) {
		this.setX(this.x * vec.x);
		this.setY(this.y * vec.y);
	}
	
	@Override
	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}
	
}
