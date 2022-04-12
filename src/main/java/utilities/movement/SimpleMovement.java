package utilities.movement;

import utilities.Vector;

public class SimpleMovement implements Movement{

    private boolean jump;
    private boolean crawl;
    private boolean right;
    private boolean left;
    private boolean fall;
    
    private Vector speed;
    private Vector position;
    
    private final static double MAXHORIZONTALSPEED = 4;
    private final static double MAXVERTICALSPEED = 20;
    private final static double JUMP = -10;
    private final static double DECELERATION = 0.7;
    private final static double ACCELERATION = 0.3;
    private final static double GRAVITY = 0.4;
    
    public SimpleMovement() {
        this.jump = false;
        this.crawl = false;
        this.right = false;
        this.left = false;
        this.fall = false;
        this.speed = new Vector(0,0);
        this.position = new Vector(0,0);
    }
    
    public SimpleMovement(final Vector position) {
        this();
        this.position = position;
    }
    
    public SimpleMovement(final Vector position, final Vector speed) {
        this();
        this.position = position;
        this.speed = speed;
    }
    
    @Override
    public boolean isLeft() {
        return this.left;
    }

    @Override
    public boolean isRight() {
        return this.right;
    }

    @Override
    public boolean isJumping() {
        return this.jump;
    }

    @Override
    public boolean isCrawling() {
        return this.crawl;
    }

    @Override
    public boolean isFalling() {
        return this.fall;
    }

    @Override
    public void setLeft(final boolean left) {
        this.left = left;
    }

    @Override
    public void setRight(final boolean right) {
        this.right = right;
    }

    @Override
    public void setJump(final boolean jump) {
        this.jump = jump;
    }

    @Override
    public void setCrawl(final boolean crawl) {
        this.crawl = crawl;
    }

    @Override
    public void setFall(final boolean fall) {
        this.fall = fall;
    }

    @Override
    public void setHorizontalSpeed(final double speed) {
        if (speed > MAXHORIZONTALSPEED) {
            this.speed = new Vector(MAXHORIZONTALSPEED, this.speed.getY());
        } else if(speed < -MAXHORIZONTALSPEED) {
            this.speed = new Vector(-MAXHORIZONTALSPEED, this.speed.getY());
        } else {            
            this.speed = new Vector(speed, this.speed.getY());
        }
    }

    @Override
    public void setVerticalSpeed(final double speed) {
        if (speed > MAXVERTICALSPEED) {
            this.speed = new Vector(this.speed.getX(), MAXVERTICALSPEED);
        } else if(speed < -MAXVERTICALSPEED) {
            this.speed = new Vector(this.speed.getX(), -MAXVERTICALSPEED);
        } else {            
            this.speed = new Vector(this.speed.getX(), speed);
        }
    }

    @Override
    public Vector getSpeed() {
        return this.speed;
    }

    @Override
    public Vector getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(final Vector position) {
        this.position = position;
    }

    @Override
    public void reset() {
        this.jump = false;
        this.crawl = false;
        this.right = false;
        this.left = false;
        this.fall = false;
        this.position = new Vector(0,0);
    }

    @Override
    public void moveEntity() {
        if (right && !left) {
            this.setHorizontalSpeed(this.speed.getX() + ACCELERATION);
        } else if (left && !right) {
            this.setVerticalSpeed(this.speed.getY() + ACCELERATION);
        } else {
            this.decelerate();
        }
        if (jump && !fall) {
            this.fall = true;
            this.crawl = false;
            this.setVerticalSpeed(JUMP);
        } else if (fall) {
            this.setVerticalSpeed(this.speed.getY() + GRAVITY);
        } else {
            this.setVerticalSpeed(0);
        }
        this.position = new Vector(this.position.getX() + this.speed.getX(), this.position.getY() + this.speed.getY());
    }

    private void decelerate() {
        if (this.speed.getX() > DECELERATION) {
            this.setHorizontalSpeed(this.speed.getX() - DECELERATION);
        } else if (this.speed.getX() < - DECELERATION) {
            this.setHorizontalSpeed(this.speed.getX() + DECELERATION);
        } else {
            this.setHorizontalSpeed(0);
        }
    }
    
    public String toString() {
        return "Speed: " + this.speed + ", position: " + this.position;
    }

}
