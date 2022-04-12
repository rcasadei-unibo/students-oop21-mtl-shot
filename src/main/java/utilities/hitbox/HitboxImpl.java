package utilities.hitbox;

import utilities.Vector;

//e se hitbox fosse un observer di movement? non appena la sua pos cambia avvisa hitbox di fare una "update position"
public class HitboxImpl implements Hitbox{

    private final double width;
    private final double height;
    private final Vector position; //correspond to the up left point of the box
    
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
    
    @Override
    public boolean isColliding(final Hitbox hitbox) {
        return this.position.getX() >= hitbox.getPosition().getX() && this.position.getX() <= hitbox.getPosition().getX() + hitbox.getWidth() &&
               this.position.getY() >= hitbox.getPosition().getY() && this.position.getY() <= hitbox.getPosition().getY() + hitbox.getHeight();
    }

    @Override
    public String toString() {
        return position + "-" + new Vector(position.getX() + width, position.getY() + height);
    }
    
    
}
