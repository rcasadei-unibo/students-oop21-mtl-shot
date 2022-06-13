package controller;

import controller.map.MapController;
import util.Direction;
import util.Vector2D;
import model.character.Character.Crouch;
import model.character.movableentity.EntityConstants;
import model.character.Character;

/**
 * TODO: javadoc.
 */
public class CharacterController {

    private final MapController mapController;

    private final Character character;

    // private final CharacterView characterView;

    /**
     * A shift from the hitbox corners.
     */
    private static final double DELTA = 0.075;
    /*
     * Constant used to have the shift from the playerPos to the hitbox pos
     * (player should penetrate at least a bit the field with the head and the arms)
     */
    // DELTA > HITBOXSHIFT.x
    // Il replacing al momento del crouch non funziona più in questo modo
    private static final Vector2D HITBOXSHIFT = new Vector2D();

    /**
     * The character controller constructor.
     * 
     * @param mapController
     * @param character
     */
    public CharacterController(final MapController mapController, final Character character) {
        this.character = character;
        this.mapController = mapController;
    }

    /**
     * The main method that checks everything about the player.
     */
    public void controllerTick() {
        this.movementChecks();
        this.character.moveEntity();
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
        // character.setFall(true);
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
            this.character.setFall(false);
            this.character.setPosition(this.character.getPosition().getX(),
                    this.mapController.getTilePos(nextPos.sum(this.character.getHitbox())).getY()
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
        final Vector2D botLeft = new Vector2D(0, this.character.getHitbox().getY() - DELTA);
        final Vector2D topLeft = new Vector2D(0, DELTA);
        botLeft.add(nextPos);
        topLeft.add(nextPos);
        return mapController.hasSingleCollidable(topLeft) || mapController.hasSingleCollidable(botLeft);
    }

    private boolean isCollidingRight(final Vector2D nextPos) {
        final Vector2D botRight = new Vector2D(this.character.getHitbox().getX(),
                this.character.getHitbox().getY() - DELTA);
        final Vector2D topRight = new Vector2D(this.character.getHitbox().getX(), DELTA);
        botRight.add(nextPos);
        topRight.add(nextPos);
        return mapController.hasSingleCollidable(topRight) || mapController.hasSingleCollidable(botRight);
    }

    private boolean isCollidingUp(final Vector2D nextPos) {
        final Vector2D topRight = new Vector2D(this.character.getHitbox().getX() - DELTA, 0);
        final Vector2D topLeft = new Vector2D(DELTA, 0);
        topLeft.add(nextPos);
        topRight.add(nextPos);
        return mapController.hasSingleCollidable(topLeft) || mapController.hasSingleCollidable(topRight);
    }

    private boolean isCollidingDown(final Vector2D nextPos) {
        final Vector2D botRight = new Vector2D(this.character.getHitbox().getX() - DELTA,
                this.character.getHitbox().getY());
        final Vector2D botLeft = new Vector2D(DELTA, this.character.getHitbox().getY());
        botRight.add(nextPos);
        botLeft.add(nextPos);
        return mapController.hasSingleCollidable(botLeft) || mapController.hasSingleCollidable(botRight);
    }

}
