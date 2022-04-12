package utilities;

import java.util.Objects;

public class Position<X, Y> {
    
    private final X x;
    private final Y y;
    
    public Position(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }
    
    public X getX() {
        return this.x;
    }
    
    public Y getY() {
        return this.y;
    }
    
    public String toString() {
        return "[" + x.toString() + ", " + y.toString() + "]";
    }
    
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@SuppressWarnings("unchecked")
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
		final Position<X,Y> other = (Position<X, Y>) obj;
		return Objects.equals(x, other.x) && Objects.equals(y, other.y);
	}
    
}
