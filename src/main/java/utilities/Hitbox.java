package utilities;

public interface Hitbox {
    
    double getWidth();
    double getHeight();
    
    boolean isColliding(Hitbox collider);
    
}
