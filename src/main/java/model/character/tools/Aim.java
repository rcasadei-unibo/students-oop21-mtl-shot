package model.character.tools;

import utilities.Direction;

/**A class that manage the aim of something that lives in a 2D world*/
final public class Aim {

	private Direction horizontal;
	private Direction vertical;
	
	/**Start by default aiming at its right*/
	public Aim() {
		this.horizontal = Direction.RIGHT;
		this.vertical = Direction.NOTHING;
	}
	
	/**starts aiming at direction @direction*/
	public Aim(final Direction direction) {
		this.setDirection(direction);
	}
	/**@return the current aiming direction*/
	public Direction getDirection() {
		if (this.vertical != Direction.NOTHING) {
			return this.vertical;
		} else {
			return this.horizontal;
		}
	}
	
	/**set the aiming direction to @direction*/
	public void setDirection(final Direction direction) {
		if (direction == Direction.LEFT || direction == Direction.RIGHT) {
			this.horizontal = direction;
		} else if (direction != Direction.NOTHING){
			this.vertical = direction;
		}
	}
	
	/**A method used to return to the last horizontal put direction*/
	public void returnToHorizontal() {
		this.vertical = Direction.NOTHING;
	}
	
	@Override
	public String toString() {
		return "Aim: " + this.getDirection();
	}
}
