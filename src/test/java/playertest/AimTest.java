package playertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.character.tools.Aim;
import util.Pair;
import util.DirectionHorizontal;
import util.DirectionVertical;

/**
 * JUnit to test the Aim class.
 * 
 */
public class AimTest {

    @Test
    void constructorTest() {
        final var aim = new Aim();
        assertEquals(new Pair<DirectionHorizontal, DirectionVertical>(DirectionHorizontal.RIGHT, DirectionVertical.NEUTRAL), aim.getDirection());
    }

    @Test
    void setDirectionTest() {
        final var aim = new Aim();
        aim.setDirection(new Pair<>(DirectionHorizontal.LEFT, DirectionVertical.DOWN));
        assertEquals(DirectionVertical.DOWN, aim.getDirection().getY());
        aim.setDirection(new Pair<>(DirectionHorizontal.LEFT, DirectionVertical.NEUTRAL));
        assertEquals(DirectionHorizontal.LEFT, aim.getDirection().getX());
    }

    @Test
    void returnToHorizontalTest() {
        final var aim = new Aim();
        aim.setDirection(new Pair<>(DirectionHorizontal.RIGHT, DirectionVertical.DOWN));
        aim.returnToHorizontal();
        assertEquals(new Pair<>(DirectionHorizontal.RIGHT, DirectionVertical.NEUTRAL), aim.getDirection());
    }

    @Test
    void setHorizontalOrVerticalTest() {
        final var aim = new Aim();
        aim.setHorizontal(DirectionHorizontal.LEFT);
        aim.setVertical(DirectionVertical.UP);
        assertEquals(new Pair<>(DirectionHorizontal.LEFT, DirectionVertical.UP), aim.getDirection());
    }
}
