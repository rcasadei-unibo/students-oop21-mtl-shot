package model.character.tools.aim;

import utilities.Direction;

public interface Aim {

    void setDirection(Direction direction);
    Direction getDirection();
    void returnToHorizontal();
}
