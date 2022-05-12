package controller.player;

import controller.Controller;
import controller.map.MapController;
import model.character.Player;
import model.character.Player.Crouch;
import model.character.Player.PlayerBuilder;
import model.character.movableentity.EntityConstants;
import model.character.tools.health.SimpleHealth;
import util.Vector2D;
import view.player.PlayerView;

/**
 * 
 *
 */
public class PlayerController {

    private final Player player;
    private final PlayerView playerView;
    private final MapController mapController;
    private static final double DELTA = 0.2; // Constant used to have a shift from the hitbox corners
    private static final Vector2D HITBOXSHIFT = new Vector2D();
    // Constant used to have the shift from the playerPos to the hitbox pos
    // (player should penetrate at least a bit the field with the head and the arms)
    // DELTA > HITBOXSHIFT.x
    // Il replacing al momento del crouch non funziona più in questo modo
    // TROPPE CONDIZIONI DI ESISTENZA, MEGLIO WRAPPARLE NELLA HITBOX (?)

    public PlayerController(final PlayerView playerView, final Controller controller) {
        this.playerView = playerView;
        this.mapController = controller.getMapController();
        player = new PlayerBuilder().hitbox(new Vector2D(1, 1)).position(mapController.getPlayerSpawn())
                .health(new SimpleHealth()).lives(3).build();
    }

    public void check() {
        player.setCrouchCondition(Crouch.FREE);
        player.setFall(true);
        final Vector2D nextPos = new Vector2D(player.getPosition());
        nextPos.add(player.getSpeed());
        nextPos.add(HITBOXSHIFT);
        if (this.isCollidingUp(nextPos) && player.getSpeed().getY() < 0) {
            player.setSpeed(player.getSpeed().getX(), 0);
        }
        if (this.isCollidingDown(nextPos)) {
            player.setFall(false);
            if (player.getSpeed().getY() > 0) {
                player.setSpeed(player.getSpeed().getX(), 0);
            }
        }
        if (this.isCollidingLeft(nextPos) && player.isLeft()) {
            player.setSpeed(EntityConstants.ACCELERATION, player.getSpeed().getY());
        } else if (this.isCollidingRight(nextPos) && player.isRight()) {
            player.setSpeed(-EntityConstants.ACCELERATION, player.getSpeed().getY());
        }
        if (this.isCollidingUp(
                new Vector2D(player.getPosition().getX(), player.getPosition().getY() - player.getHitbox().getY()))
                && player.isCrouching()) {
            player.setJump(false);
        }
        if (player.isFalling()) {
            player.setCrouchCondition(Crouch.UP);
        }
        if (this.isCollidingUp(
                new Vector2D(player.getPosition().getX(), player.getPosition().getY() - player.getHitbox().getY()))
                && player.isCrouching()) {
            player.setCrouchCondition(Crouch.DOWN);
            player.setJump(false);
        }
        player.moveEntity();
        playerView.updatePlayer(player.getPosition(), player.isCrouching());
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

    public Player getPlayer() {
        return this.player;
    }

}
