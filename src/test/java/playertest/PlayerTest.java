package playertest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import util.Vector2D;

/**
 * 
 *
 */
public class PlayerTest {

    @Test
    void builderFailingTest() {
        assertThrows(IllegalStateException.class, () -> {
            new PlayerBuilder().build();
        });
        assertThrows(IllegalStateException.class, () -> {
            new PlayerBuilder().health(new SimpleHealth(100)).build();
        });
        assertThrows(IllegalStateException.class, () -> {
            new PlayerBuilder().hitbox(new Vector2D(1, 1)).build();
        });
        assertThrows(IllegalStateException.class, () -> {
            new PlayerBuilder().lives(-1).build();
        });
    }

    @Test
    void builderCorrectTest() {
        try {
            new PlayerBuilder().health(new SimpleHealth()).hitbox(new Vector2D(1, 1)).lives(3)
                    .position(new Vector2D(0, 0)).build();
        } catch (final Exception e) {
            fail("Builder doesn't work");
        }
    }

    @Test
    void goRightTest() {
        final var player = new PlayerBuilder().health(new SimpleHealth()).hitbox(new Vector2D(1, 1)).lives(3)
                .position(new Vector2D(0, 0)).build();
        final var pos = player.getPosition();
        player.setRight(true);
        player.moveEntity();
        assertTrue(player.getSpeed().getX() > 0);
        assertEquals(0, player.getSpeed().getY());
        assertEquals(player.getSpeed().getX(), player.getPosition().getX() - pos.getX());
        player.setRight(false);
    }

    @Test
    void goLeftTest() {
        final var player = new PlayerBuilder().health(new SimpleHealth()).hitbox(new Vector2D(1, 1)).lives(3)
                .position(new Vector2D(0, 0)).build();
        final var pos = player.getPosition();
        player.setLeft(true);
        player.moveEntity();
        assertTrue(player.getSpeed().getX() < 0);
        assertEquals(0, player.getSpeed().getY());
        assertEquals(player.getSpeed().getX(), player.getPosition().getX() - pos.getX());
        player.setLeft(false);
    }
}
