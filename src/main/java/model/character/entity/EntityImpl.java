package model.character.entity;

import utilities.movement.Movement;

public class EntityImpl implements Entity{

    private final Movement movement;
    
    public EntityImpl(final Movement movement) {
        this.movement = movement;
    }
    @Override
    public Movement getMovement() {
        return this.movement;
    }
    
    @Override
    public String toString() {
    	return this.getMovement().toString();
    }

}
