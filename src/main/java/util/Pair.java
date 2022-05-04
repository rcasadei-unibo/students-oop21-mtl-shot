package util;

import java.util.Objects;

public class Pair<X, Y> {

	private final X x;
	private final Y y;
	
	public Pair(final X x, final Y y) {
		this.x = x;
		this.y = y;
	}
	
	public X getX() {
		return this.x;
	}
	
	public Y getY() {
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
		final Pair<X,Y> other = (Pair<X,Y>) obj;
		return Objects.equals(x, other.x) && Objects.equals(y, other.y);
	}
	
	
}
