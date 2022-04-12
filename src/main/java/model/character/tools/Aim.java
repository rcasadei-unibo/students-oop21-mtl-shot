package model.character.tools;

import utilities.Direction;

public interface Aim {

    void setDirection(Direction direction);
    Direction getDirection();
    void returnToHorizontal();
}
