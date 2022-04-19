package model.character.tools;

import util.Direction;

/**
 * A class that manages the aim of something that lives in a 2D world.
 * It uses 2 Direction: one for each axis in the two-dimensional world
 * */
final public class Aim {

    /**
     * Field for horizontal axis aim
     * */
	private Direction horizontal;
	/**
	 * Field for vertical axis aim
	 * */
	private Direction vertical;
	
	/**
	 * Starts by default aiming at its right
	 * */
	public Aim() {
		this.horizontal = Direction.RIGHT;
		this.vertical = Direction.NEUTRAL;
	}
	
	/**
	 * Starts aiming at direction @direction
	 * */
	public Aim(final Direction direction) {
		this.setDirection(direction);
	}
	/**
	 * @return the current aiming direction (preferring the vertical over the horizontal)
	 * */
	public Direction getDirection() {
		if (this.vertical != Direction.NEUTRAL) {
			return this.vertical;
		} else {
			return this.horizontal;
		}
	}
	
	/**
	 * Sets the aiming direction to @direction
	 * */
	public void setDirection(final Direction direction) {
		if (direction == Direction.LEFT || direction == Direction.RIGHT) {
			this.horizontal = direction;
		} else if (direction != Direction.NEUTRAL){
			this.vertical = direction;
		}
	}
	
	/**
	 * A method used to revert to the last horizontal direction the object was facing
	 * */
	public void returnToHorizontal() {
		this.vertical = Direction.NEUTRAL;
	}
	
	@Override
	public String toString() {
		return "Aim: " + this.getDirection();
	}
}
