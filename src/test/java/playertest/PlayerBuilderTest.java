package playertest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import model.character.Player.PlayerBuilder;
import model.character.tools.health.SimpleHealth;
import model.weapons.R99;
import util.Vector2D;

/**
 * JUnit to test the builder of the Metal Shot player.
 *
 */
public class PlayerBuilderTest {

    @Test
    void incorrectHitbox() {
        assertThrows(IllegalArgumentException.class, () -> new PlayerBuilder().health(new SimpleHealth())
                .hitbox(new Vector2D(0, -1)).lives(3).position(new Vector2D()).weapon(new R99()).build());
    }

    @Test
    void builderFailingTests() {
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
        assertThrows(IllegalStateException.class, () -> {
            new PlayerBuilder().weapon(new R99()).build();
        });
        assertThrows(IllegalStateException.class, () -> {
            new PlayerBuilder().position(new Vector2D()).build();
        });
    }

    @Test
    void builderCombinationsFailingTests() {
        assertThrows(IllegalStateException.class, () -> {
            new PlayerBuilder().position(new Vector2D()).health(new SimpleHealth()).build();
        });
        assertThrows(IllegalStateException.class, () -> {
            new PlayerBuilder().position(new Vector2D()).hitbox(new Vector2D(1, 1)).build();
        });
        assertThrows(IllegalStateException.class, () -> {
            new PlayerBuilder().position(new Vector2D()).lives(3).build();
        });
        assertThrows(IllegalStateException.class, () -> {
            new PlayerBuilder().position(new Vector2D()).weapon(new R99()).build();
        });
        assertThrows(IllegalStateException.class, () -> {
            new PlayerBuilder().position(new Vector2D()).health(new SimpleHealth()).hitbox(new Vector2D(1, 1)).build();
        });
        assertThrows(IllegalStateException.class, () -> {
            new PlayerBuilder().position(new Vector2D()).health(new SimpleHealth()).hitbox(new Vector2D(1, 1))
                    .weapon(null).build();
        });
    }

    @Test
    void builderCorrectTest() {
        try {
            new PlayerBuilder().health(new SimpleHealth()).hitbox(new Vector2D(1, 1)).lives(3)
                    .position(new Vector2D(0, 0)).weapon(new R99()).build();
        } catch (final Exception e) {
            fail("Builder doesn't work");
        }
    }
}
