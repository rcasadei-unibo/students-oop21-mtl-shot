package controller;

import model.character.Character.Crouch;
import model.map.tile.Tile;
import controller.map.MapController;
import model.character.Character;
import util.Vector2D;

/**
 * Provo a rifarlo questa volta un po' meglio.
 *
 */
public class CharacterControllerV2 {

    private final Character character;
    private final MapController mapController;

    public CharacterControllerV2(final Character character, final MapController mapController) {
        this.character = character;
        this.mapController = mapController;
    }

    public void controllerTick() {
        character.setCrouchCondition(Crouch.FREE);
        final Vector2D nextPos = new Vector2D(character.getPosition());
        nextPos.add(character.getSpeed());
        this.movementChecks(nextPos);
        this.character.moveEntity();
    }

    private void movementChecks(final Vector2D nextPos) {
        if (this.isCollidingDown(nextPos)) {
            character.setFall(false);
            //character.setPosition(nextPos.getX(), mapController.getTile(nextPos).get().getPosition().getY() - this.character.getHitbox().getHeight());
        }
    }

    private boolean isCollidingDown(final Vector2D nextPos) {
        final Vector2D pos1 = nextPos.sum(new Vector2D(0, character.getHitbox().getHeight()));
        final Vector2D pos2 = pos1.sum(new Vector2D(character.getHitbox().getWidth(), 0));
        final var tile1 = mapController.getSingleCollidable(pos1);
        final var tile2 = mapController.getSingleCollidable(pos2);
        final Tile tile;
        if (tile1.isEmpty() && tile2.isEmpty()) {
            return false;
        } else if (tile1.isPresent()) {
            tile = tile1.get();
        } else {
            tile = tile2.get();
        }
        return collision(
                tile.getPosition().getX(),
                tile.getPosition().getY(),
                tile.getPosition().getX() + character.getHitbox().getWidth(),
                tile.getPosition().getY(),
                nextPos.getX(), 
                nextPos.getY(),
                character.getHitbox().getWidth(),
                character.getHitbox().getHeight()
                );
    }

    //Peek code from https://www.jeffreythompson.org 
    private boolean collision(final double x1, final double y1, final double x2, final double y2, final double rx, final double ry, final double rw, final double rh) {

        System.out.println("left");
        final boolean left =   lineLine(x1, y1, x2, y2, rx, ry, rx, ry + rh);
        System.out.println(left);
        System.out.println("right");
        final boolean right =  lineLine(x1, y1, x2, y2, rx + rw, ry, rx + rw, ry + rh);
        System.out.println(right);
        System.out.println("top");
        final boolean top =    lineLine(x1, y1, x2, y2, rx, ry, rx + rw, ry);
        System.out.println(top);
        System.out.println("bot");
        final boolean bottom = lineLine(x1, y1, x2, y2, rx, ry + rh, rx + rw, ry + rh);
        System.out.println(bottom);
        // if ANY of the above are true, the line
        // has hit the rectangle
        System.out.println("--------------------");
        System.out.println(left || right || top || bottom);
        System.out.println("--------------------");
        return left || right || top || bottom;
    }

    private boolean lineLine(final double x1, final double y1, final double x2, final double y2, final double x3, final double y3, final double x4, final double y4) {
        final double uA = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));
        final double uB = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));

        // if uA and uB are between 0-1, lines are colliding
        return uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1;
    }
}
