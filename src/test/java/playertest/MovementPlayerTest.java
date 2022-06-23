package playertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import model.weapons.P2020;
import util.Vector2D;

/**
 * JUnit to test the MetalShot player movements.
 *
 */
public class MovementPlayerTest {

    private static final int MOVES = 100;

    @Test
    void startingPlayer() {
        final var player = new PlayerBuilder().health(new SimpleHealth()).hitbox(new Vector2D(1, 1)).lives(3)
                .position(new Vector2D(0, 0)).weapon(new P2020()).build();
        assertFalse(player.isCrouching());
        assertFalse(player.isFalling());
        assertFalse(player.isJumping());
        assertFalse(player.isLeft());
        assertFalse(player.isRight());
        assertEquals(new Vector2D(), player.getSpeed());
    }

    @Test
    void goRightTest() {
        final var player = new PlayerBuilder().health(new SimpleHealth()).hitbox(new Vector2D(1, 1)).lives(3)
                .position(new Vector2D(0, 0)).weapon(new P2020()).build();
        final var pos = new Vector2D(player.getPosition());
        player.setRight(true);
        player.moveEntity();
        assertTrue(player.getSpeed().getX() > 0);
        assertEquals(0, player.getSpeed().getY());
        assertEquals(player.getSpeed().getX(), player.getPosition().getX() - pos.getX());
    }

    @Test
    void goLeftTest() {
        final var player = new PlayerBuilder().health(new SimpleHealth()).hitbox(new Vector2D(1, 1)).lives(3)
                .position(new Vector2D(0, 0)).weapon(new P2020()).build();
        final var pos = new Vector2D(player.getPosition());
        player.setLeft(true);
        player.moveEntity();
        assertTrue(player.getSpeed().getX() < 0);
        assertEquals(0, player.getSpeed().getY());
        assertEquals(player.getSpeed().getX(), player.getPosition().getX() - pos.getX());
    }

    @Test
    void jumpTest() {
        final var player = new PlayerBuilder().health(new SimpleHealth()).hitbox(new Vector2D(1, 1)).lives(3)
                .position(new Vector2D(0, 0)).weapon(new P2020()).build();
        final var pos = new Vector2D(player.getPosition());
        player.setJump(true);
        player.moveEntity();
        assertTrue(player.getSpeed().getY() < 0);
        assertEquals(0, player.getSpeed().getX());
        assertEquals(player.getSpeed(), player.getPosition().dif(pos));
        assertTrue(player.isFalling());
    }

    @Test
    void crouchTest() {
        final var player = new PlayerBuilder().health(new SimpleHealth()).hitbox(new Vector2D(1, 1)).lives(3)
                .position(new Vector2D(0, 0)).weapon(new P2020()).build();
        player.setCrouchKey(true);
        player.moveEntity();
        assertTrue(player.isCrouching());
        assertEquals(new Vector2D(), player.getSpeed());
    }

    @Test
    void jumpAfterAWhileTest() {
        final var player = new PlayerBuilder().health(new SimpleHealth()).hitbox(new Vector2D(1, 1)).lives(3)
                .position(new Vector2D(0, 0)).weapon(new P2020()).build();
        player.setJump(true);
        for (int i = 0; i < MOVES; i++) {
            player.moveEntity();
        }
        //After a while it should prevail the gravity force against the jump force.
        assertTrue(player.getSpeed().getY() > 0);
    }
}
