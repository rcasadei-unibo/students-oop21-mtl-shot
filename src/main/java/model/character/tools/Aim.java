package model.character.tools;

import util.Pair;
import util.direction.DirectionHorizontal;
import util.direction.DirectionVertical;

/**
 * A class that manages the aim of something that lives in a 2D world. It uses 2
 * Direction(s): one for each axis in the two-dimensional world.
 */
public final class Aim {

    /**
     * Field for direction.
     */
    private Pair<DirectionHorizontal, DirectionVertical> direction;

    /**
     * Starts by default aiming at its right.
     */
    public Aim() {
        this.direction = new Pair<>(DirectionHorizontal.RIGHT, DirectionVertical.NEUTRAL);
    }

    /**
     * Sets the aiming direction.
     * 
     * @param direction
     */
    public void setDirection(final Pair<DirectionHorizontal, DirectionVertical> direction) {
        this.direction = direction;
    }

    /**
     * Sets the horizontal aiming direction.
     * 
     * @param direction
     */
    public void setHorizontal(final DirectionHorizontal direction) {
        this.direction = new Pair<>(direction, DirectionVertical.NEUTRAL);
    }

    /**
     * Sets the vertical aiming direction.
     * 
     * @param direction
     */
    public void setVertical(final DirectionVertical direction) {
        this.direction = new Pair<>(this.direction.getX(), direction);
    }

    /**
     * Gets the current aiming direction.
     * 
     * @return Pair<DirectionHorizontal, DirectionVertical>
     */
    public Pair<DirectionHorizontal, DirectionVertical> getDirection() {
        return this.direction;
    }

    /**
     * A method used to revert to the last horizontal direction the object was
     * facing.
     */
    public void returnToHorizontal() {
        this.direction = new Pair<>(this.direction.getX(), DirectionVertical.NEUTRAL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Aim: " + this.getDirection();
    }
}
