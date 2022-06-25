package model.map.tile;

import java.util.Objects;

import util.Vector2D;
/**
 *
 */
public abstract class AbstractTile implements Tile {

    private final String path;
    private final Vector2D position;

    /**
     * 
     * @param position
     * @param path
     */
    public AbstractTile(final Vector2D position, final String path) {
        this.position = position;
        this.path = path;
    }
/**
 * Returns the tile's position.
 * @return position
 */
    public Vector2D getPosition() {
        return this.position;
    }
    /**
     * Returns the tile's path.
     * @return position
     */
    public String getPath() {
       return this.path;
    }
    /**
     * @return boolean
     */
    public abstract boolean isCollidable();
    /**
    * @return boolean
    */
    public abstract boolean isTileable();
    @Override
    public String toString() {
        return this.position + " - Is collidable: " + isCollidable(); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, position);
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
           return true;
        }
        if (obj == null) {
           return false;
        }
        if (getClass() != obj.getClass()) {
           return false;
        }
        final TileStone other = (TileStone) obj;
        return Objects.equals(position, other.getPosition()) && isCollidable() == other.isCollidable();
    }
}
