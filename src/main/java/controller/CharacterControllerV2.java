package controller;

/**
 * Provo a rifarlo questa volta un po meglio.
 *
 */
public class CharacterControllerV2 {

    private boolean linesCollision(final float x1, final float y1, final float x2, final float y2, final float x3, final float y3, final float x4, final float y4) {

        // calculate the distance to intersection point
        final float uA = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));
        final float uB = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));

        // if uA and uB are between 0-1, lines are colliding
        if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1) {

          /*
          // The intersection point
          float intersectionX = x1 + (uA * (x2 - x1));
          float intersectionY = y1 + (uA * (y2 - y1));
          */
          return true;
        }
        return false;
      }
}
