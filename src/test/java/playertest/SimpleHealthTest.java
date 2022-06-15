package playertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.character.tools.health.SimpleHealth;

/**
 * JUnit to test SimpleHealth class.
 */
public class SimpleHealthTest {

    @Test
    void constructorTest1() {
        final var sh = new SimpleHealth();
        assertEquals(100, sh.getHealth());
        assertEquals(100, sh.getMaxHealth());
    }

    @Test
    void constructorTest2() {
        final var sh = new SimpleHealth(10);
        assertEquals(10, sh.getHealth());
        assertEquals(10, sh.getMaxHealth());
    }

    @Test
    void casualTests() {
        final var sh = new SimpleHealth();
        sh.hurt(10);
        assertEquals(90, sh.getHealth());
        assertEquals(100, sh.getMaxHealth());
        sh.hurt(5);
        assertEquals(85, sh.getHealth());
        sh.heal(8);
        assertEquals(93, sh.getHealth());
        assertFalse(sh.isDead());
    }

    @Test
    void healingOverMaxTest() {
        final var sh = new SimpleHealth();
        sh.heal(10);
        assertEquals(100, sh.getHealth());
    }

    @Test
    void hurtingOverMaxTest() {
        final var sh = new SimpleHealth(1);
        sh.hurt(10);
        assertEquals(0, sh.getHealth());
        assertTrue(sh.isDead());
        }

    @Test
    void dataInconsistencyTest1() {
        assertThrows(IllegalArgumentException.class, () -> new SimpleHealth(-1));
    }

    @Test
    void dataInconsistencyTest2() {
        assertThrows(IllegalArgumentException.class, () -> new SimpleHealth().heal(-1));
    }

    @Test
    void dataInconsistencyTest3() {
        assertThrows(IllegalArgumentException.class, () -> new SimpleHealth().hurt(-1));
    }

    @Test
    void dataInconsistencyTest4() {
        assertThrows(IllegalArgumentException.class, () -> new SimpleHealth().setMaxHealth(-1));
    }
}
