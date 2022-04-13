package utilities.hitbox;

import java.util.Objects;

import utilities.Vector;

//e se hitbox fosse un observer di movement? non appena la sua pos cambia avvisa hitbox di fare una "update position"
public class HitboxImpl implements Hitbox{

    private final double width;
    private final double height;
    private Vector position; //correspond to the up left point of the box
    
    public HitboxImpl(final double width, final double height, final Vector position) {
        this.width = width;
        this.height = height;
        this.position = position;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }
    
    @Override
    public Vector getPosition() {
        return this.position;
    }
    
    /*x2 <= x1 <= x2 + w2 && y2 <= y1 <= y2 + h2
    					  ||
      x1 <= x2 <= x1 + w1 && y1 <= y2 <= y1 + h1*/
    @Override
    public boolean isColliding(final Hitbox hitbox) {
        return this.position.getX() >= hitbox.getPosition().getX() && this.position.getX() <= hitbox.getPosition().getX() + hitbox.getWidth() &&
               this.position.getY() >= hitbox.getPosition().getY() && this.position.getY() <= hitbox.getPosition().getY() + hitbox.getHeight() ||
               hitbox.getPosition().getX() >= this.position.getX() && hitbox.getPosition().getX() <= this.position.getX() + this.width &&
               hitbox.getPosition().getY() >= this.position.getY() && hitbox.getPosition().getY() <= this.position.getY() + this.height;
    }

    @Override
    public String toString() {
        return position + "-" + new Vector(position.getX() + width, position.getY() + height);
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
		final HitboxImpl other = (HitboxImpl) obj;
		return Double.doubleToLongBits(height) == Double.doubleToLongBits(other.height)
				&& Objects.equals(position, other.position)
				&& Double.doubleToLongBits(width) == Double.doubleToLongBits(other.width);
	}

	@Override
	public void updatePosition(final Vector position) {
		this.position = position;
	}
    
    
}
