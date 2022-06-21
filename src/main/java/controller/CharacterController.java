package controller;

import util.Direction;
import util.Vector2D;
import model.character.Character.Crouch;
import model.character.movableentity.EntityConstants;
import model.map.Level;
import util.Pair;
import model.character.Character;

/**
 * A generic controller for every character in MetalShot. It checks the movement
 * and the aim of the controlled character.
 */
public class CharacterController {

    private final Level level;

    private final Character character;

    /**
     * A shift from the hitbox corners.
     */
    private static final double DELTAX = 0.25;
    private static final double DELTAY = 0.075;
    /*
     * Constant used to have the shift from the playerPos to the hitbox pos (player
     * should penetrate at least a bit the field with the head and the arms)
     */
    // DELTA > HITBOXSHIFT.x
    // Il replacing al momento del crouch non funziona più in questo modo
    private static final Vector2D HITBOXSHIFT = new Vector2D();

    /**
     * The character controller constructor.
     * 
     * @param level     the level where the character is
     * @param character the character to control.
     */
    public CharacterController(final Level level, final Character character) {
        this.character = character;
        this.level = level;
    }

    /**
     * The main method that checks everything about the player.
     */
    public void controllerTick(final double leftBound) {
        this.movementChecks();
        this.character.moveEntity();
        if (this.character.getPosition().getX() < leftBound) {
        	this.character.setPosition(leftBound, this.character.getPosition().getY());
        }
        this.aimChecks();
    }
    
    /**
     * Gets the character who is being controlled.
     * 
     * @return character
     */
    public Character getCharacter() {
        return this.character;
    }
    
    public boolean isDead() {
        return this.character.getHealth().isDead();
    }
    
    private void aimChecks() {
        // if crouching he can't aim at the ground
        if (this.character.isCrouching()) {
            this.character.getAim().returnToHorizontal();
            // if fling and pressing the down button he has to aim at the ground
        } else if (!this.character.isCrouching() && this.character.isCrouchKey()) {
            this.character.getAim().setDirection(Direction.DOWN);
        }
    }

    private void movementChecks() {
        // Setting default values
        character.setCrouchCondition(Crouch.FREE);
        final Vector2D nextPos = new Vector2D(character.getPosition());
        // The next frame the character will be in character.pos + character.speed
        nextPos.add(character.getSpeed());
        nextPos.add(HITBOXSHIFT);
        // Roof collisions
        if (this.isCollidingUp(nextPos) && this.character.getSpeed().getY() < 0) {
            this.character.setSpeed(this.character.getSpeed().getX(), 0);
        }
        // Floor collisions
        if (this.isCollidingDown(nextPos)) {
            final var target = nextPos.sum(this.character.getHitbox());
            this.character.setFall(false);
            this.character.setPosition(this.character.getPosition().getX(),
                    this.level.getSegmentAtPosition(target).getTilePos(target).getY()
                            - this.character.getHitbox().getY());
            if (this.character.getSpeed().getY() > 0) {
                this.character.setSpeed(this.character.getSpeed().getX(), 0);
            }
        } else if (this.character.getSpeed().getX() != 0) {
            this.character.setFall(true);
        }
        // Left wall collisions
        if (this.isCollidingLeft(nextPos)) {
            this.character.setSpeed(EntityConstants.ACCELERATION, this.character.getSpeed().getY());
            // Right wall collisions
        } else if (this.isCollidingRight(nextPos)) {
            this.character.setSpeed(-EntityConstants.ACCELERATION, this.character.getSpeed().getY());
        }
        // Special case: while fling he can not crouch
        if (this.character.isFalling()) {
            this.character.setCrouchCondition(Crouch.UP);
        }
        // Special case: stuck crouching
        if (this.isCollidingUp(new Vector2D(this.character.getPosition().getX(),
                this.character.getPosition().getY() - this.character.getHitbox().getY()))
                && this.character.isCrouching()) {
            this.character.setCrouchCondition(Crouch.DOWN);
            this.character.setJump(false);
        }
    }

    private boolean isCollidingLeft(final Vector2D nextPos) {
        final Vector2D botLeft = new Vector2D(0, this.character.getHitbox().getY() - DELTAX);
        final Vector2D topLeft = new Vector2D(0, DELTAX);
        botLeft.add(nextPos);
        topLeft.add(nextPos);
        return level.getSegmentAtPosition(topLeft).isCollidableAtPosition(topLeft)
                || level.getSegmentAtPosition(botLeft).isCollidableAtPosition(botLeft);
    }

    private boolean isCollidingRight(final Vector2D nextPos) {
        final Vector2D botRight = new Vector2D(this.character.getHitbox().getX(),
                this.character.getHitbox().getY() - DELTAX);
        final Vector2D topRight = new Vector2D(this.character.getHitbox().getX(), DELTAX);
        botRight.add(nextPos);
        topRight.add(nextPos);
        return level.getSegmentAtPosition(topRight).isCollidableAtPosition(topRight)
                || level.getSegmentAtPosition(botRight).isCollidableAtPosition(botRight);
    }

    private boolean isCollidingUp(final Vector2D nextPos) {
        final Vector2D topRight = new Vector2D(this.character.getHitbox().getX() - DELTAY, 0);
        final Vector2D topLeft = new Vector2D(DELTAY, 0);
        topLeft.add(nextPos);
        topRight.add(nextPos);
        return level.getSegmentAtPosition(topLeft).isCollidableAtPosition(topLeft)
                || level.getSegmentAtPosition(topRight).isCollidableAtPosition(topRight);
    }

    private boolean isCollidingDown(final Vector2D nextPos) {
        final Vector2D botRight = new Vector2D(this.character.getHitbox().getX() - DELTAY,
                this.character.getHitbox().getY());
        final Vector2D botLeft = new Vector2D(DELTAY, this.character.getHitbox().getY());
        botRight.add(nextPos);
        botLeft.add(nextPos);
        return level.getSegmentAtPosition(botLeft).isCollidableAtPosition(botLeft)
                || level.getSegmentAtPosition(botRight).isCollidableAtPosition(botRight);
    }

}
