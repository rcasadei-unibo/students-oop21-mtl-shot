package model.map.tile;

import util.Vector2D;
/**
 * 
 */
public interface Tile {
    /**
     * @return the Tile's position.
     */
    Vector2D getPosition();

    /**
     * @return the Tile's collidability.
     */
    boolean isCollidable();

    /**
     * @return the Tile's tileability.
     */
    boolean isTileable();

    /**
     * @return the Tile's image path.
     */
    String getPath();

}
