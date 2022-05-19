package util;

import java.util.Objects;

/**
 * A simple class that models a pair of 2 different objects.
 *
 * @param <X>
 * @param <Y>
 */
public class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * The pair constructor.
     * 
     * @param x
     * @param y
     */
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the first element.
     * 
     * @return x
     */
    public X getX() {
        return this.x;
    }

    /**
     * Gets the second element.
     * 
     * @return y
     */
    public Y getY() {
        return this.y;
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
        final Pair<X, Y> other = (Pair<X, Y>) obj;
        return Objects.equals(x, other.x) && Objects.equals(y, other.y);
    }

}
