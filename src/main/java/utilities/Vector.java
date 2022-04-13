package utilities;

public class Vector {

	private final double x;
	private final double y;
	
	public Vector(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	@Override
	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}
}
