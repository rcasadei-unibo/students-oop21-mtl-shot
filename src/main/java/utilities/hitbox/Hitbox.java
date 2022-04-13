package utilities.hitbox;

import utilities.Vector;

public interface Hitbox {
    double getWidth();
    double getHeight();
    Vector getPosition();
    boolean isColliding(Hitbox hitbox);
    /* Hitbox is an observer of movement, every time movement changes its position, hitbox has to follow it*/
    void updatePosition(Vector position);
}
