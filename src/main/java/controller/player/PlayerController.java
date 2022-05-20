package controller.player;

import controller.Controller;
import controller.map.MapController;
import model.character.Player;
import model.character.Character.Crouch;
import model.character.movableentity.EntityConstants;
import util.Direction;
import util.Vector2D;
import view.player.PlayerView;

/**
 * The player controller. It checks if the player is colliding into the ground, colliding with bullets 
 * and manages everything about the weapon.
 */
public class PlayerController {

    /**
     * The player that has to be controlled.
     */
    private final Player player;
    /**
     * The View part of the player that has to be updated.
     */
    private final PlayerView playerView;
    /**
     * The map controller, used to handle the checks with the ground.
     */
    private final MapController mapController;
    

    /**
     * The player controller constructor: it needs a player representation on a view and a master controller that passes to it 
     * what it needs.
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! DA RIGUARDARE (TOM) !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * @param playerView
     * @param controller
     */
    public PlayerController(final PlayerView playerView, final Controller controller, final Player player) {
        this.playerView = playerView;
        this.mapController = controller.getMapController();
        this.player = player;
    }

    /**
     * The main method that checks everything about the player.
     */
    public void check() {
        player.setCrouchCondition(Crouch.FREE);
        player.setFall(true);
        final Vector2D nextPos = new Vector2D(player.getPosition());
        nextPos.add(player.getSpeed());
        nextPos.add(HITBOXSHIFT);
        this.movementChecks(nextPos);
        player.moveEntity();
        this.aimChecks();
        this.bulletsChecks();
        playerView.updatePlayer(player.getPosition(), player.isCrouching(), player.getAim().getDirection());
    }

    /**
     * Gets the player who is being controlled. 
     * 
     * @return player
     */
    public Player getPlayer() {
        return this.player;
    }

    private void bulletsChecks() {

    }
    private void aimChecks() {
        //if crouching he can't aim at the ground
        if (player.isCrouching()) {
            player.getAim().returnToHorizontal();
        //if fling and pressing the down button he has to aim at the ground
        } else if (!player.isCrouching() && player.getCrouchKey()) {
            player.getAim().setDirection(Direction.DOWN);
        }
    }
    private void movementChecks(final Vector2D nextPos) {
        //Roof collisions
        if (this.isCollidingUp(nextPos) && player.getSpeed().getY() < 0) {
            player.setSpeed(player.getSpeed().getX(), 0);
        }
        //Floor collisions
        if (this.isCollidingDown(nextPos)) {
            player.setFall(false);
            if (player.getSpeed().getY() > 0) {
                player.setSpeed(player.getSpeed().getX(), 0);
            }
        }
        //Left wall collisions
        if (this.isCollidingLeft(nextPos) /*&& player.isLeft()*/) {
            player.setSpeed(EntityConstants.ACCELERATION, player.getSpeed().getY());
        //Right wall collisions
        } else if (this.isCollidingRight(nextPos) /*&& player.isRight()*/) {
            player.setSpeed(-EntityConstants.ACCELERATION, player.getSpeed().getY());
        }
        //Special case: while fling he can not crouch
        if (player.isFalling()) {
            player.setCrouchCondition(Crouch.UP);
        }
        //Special case: stuck crouching
        if (this.isCollidingUp(
                new Vector2D(player.getPosition().getX(), player.getPosition().getY() - player.getHitbox().getY()))
                && player.isCrouching()) {
            player.setCrouchCondition(Crouch.DOWN);
            player.setJump(false);
        }
    }

    private boolean isCollidingLeft(final Vector2D nextPos) {
        final Vector2D botLeft = new Vector2D(0, player.getHitbox().getY() - DELTA);
        final Vector2D topLeft = new Vector2D(0, DELTA);
        botLeft.add(nextPos);
        topLeft.add(nextPos);
        return mapController.hasSingleCollidable(topLeft) || mapController.hasSingleCollidable(botLeft);
    }

    private boolean isCollidingRight(final Vector2D nextPos) {
        final Vector2D botRight = new Vector2D(player.getHitbox().getX(), player.getHitbox().getY() - DELTA);
        final Vector2D topRight = new Vector2D(player.getHitbox().getX(), DELTA);
        botRight.add(nextPos);
        topRight.add(nextPos);
        return mapController.hasSingleCollidable(topRight) || mapController.hasSingleCollidable(botRight);
    }

    private boolean isCollidingUp(final Vector2D nextPos) {
        final Vector2D topRight = new Vector2D(player.getHitbox().getX() - DELTA, 0);
        final Vector2D topLeft = new Vector2D(DELTA, 0);
        topLeft.add(nextPos);
        topRight.add(nextPos);
        return mapController.hasSingleCollidable(topLeft) || mapController.hasSingleCollidable(topRight);
    }

    private boolean isCollidingDown(final Vector2D nextPos) {
        final Vector2D botRight = new Vector2D(player.getHitbox().getX() - DELTA, player.getHitbox().getY());
        final Vector2D botLeft = new Vector2D(DELTA, player.getHitbox().getY());
        botRight.add(nextPos);
        botLeft.add(nextPos);
        return mapController.hasSingleCollidable(botLeft) || mapController.hasSingleCollidable(botRight);
    }
}
