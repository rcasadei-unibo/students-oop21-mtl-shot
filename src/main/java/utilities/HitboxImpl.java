package utilities;

public class HitboxImpl implements Hitbox {
    
    private final double width;
    private final double height;
    
    public HitboxImpl(final double width, final double height) {
	this.width = width;
	this.height = height;
    }
    

    @Override
    public double getWidth() {
	return width;
    }

    @Override
    public double getHeight() {

	return height;
    }

    @Override
    public boolean isColliding(final Hitbox collider) {
	return false;
    }

}
