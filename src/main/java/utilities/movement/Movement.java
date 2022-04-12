package utilities.movement;

import utilities.Vector;

public interface Movement {

    boolean isLeft();
    boolean isRight();
    boolean isJumping();
    boolean isCrawling();
    boolean isFalling();
    void setLeft(boolean left);
    void setRight(boolean right);
    void setJump(boolean jump);
    void setCrawl(boolean crawl);
    void setFall(boolean fall);
    void setHorizontalSpeed(double speed);
    void setVerticalSpeed(double speed);
    Vector getSpeed();
    Vector getPosition();
    void setPosition(Vector position);
    void reset();
    void moveEntity();
}
