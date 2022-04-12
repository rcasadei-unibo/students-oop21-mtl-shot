package utilities;

import java.util.Objects;

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
	
}
