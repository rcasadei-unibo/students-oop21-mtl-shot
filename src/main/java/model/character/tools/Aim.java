package model.character.tools;

import utilities.Direction;

public class Aim {

	private Direction horizontal;
	private Direction vertical;
	
	public Aim() {
		this.horizontal = Direction.RIGHT;
		this.vertical = Direction.NOTHING;
	}
	
	public Aim(final Direction direction) {
		this.setDirection(direction);
	}
	
	public Direction getDirection() {
		if (this.vertical != Direction.NOTHING) {
			return this.vertical;
		} else {
			return this.horizontal;
		}
	}
	
	public void setDirection(final Direction direction) {
		if (direction == Direction.LEFT || direction == Direction.RIGHT) {
			this.horizontal = direction;
		} else if (direction != Direction.NOTHING){
			this.vertical = direction;
		}
	}
	
	public void returnToHorizontal() {
		this.vertical = Direction.NOTHING;
	}
	
	@Override
	public String toString() {
		return "Aim: " + this.getDirection();
	}
}
