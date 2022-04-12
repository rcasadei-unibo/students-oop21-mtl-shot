package utilities.hitbox;

import utilities.Vector;

public interface Hitbox {

    double getWidth();
    double getHeight();
    Vector getPosition();
    boolean isColliding(Hitbox hitbox);
}
