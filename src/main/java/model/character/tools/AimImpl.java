package model.character.tools;

import utilities.Direction;

public class AimImpl implements Aim {

    private Direction horizontalDirection;
    private Direction verticalDirection;
    
    public AimImpl() {
        this.horizontalDirection = Direction.RIGHT;
        this.verticalDirection = Direction.NOTHING;
    }
    
    @Override
    public void setDirection(final Direction direction) {
        if (direction == Direction.RIGHT || direction == Direction.LEFT) {
            this.horizontalDirection = direction;
            this.verticalDirection = Direction.NOTHING;
        } else if (direction == Direction.DOWN || direction == Direction.UP) {
            this.verticalDirection = direction;
        }
    }

    @Override
    public Direction getDirection() {
        if (this.verticalDirection != Direction.NOTHING) {
            return this.verticalDirection;
        } else {
            return this.horizontalDirection;
        }
    }

    @Override
    public void returnToHorizontal() {
        this.verticalDirection = Direction.NOTHING;
    }

    @Override
    public String toString() {
        return this.getDirection().toString();
    }
    
}
