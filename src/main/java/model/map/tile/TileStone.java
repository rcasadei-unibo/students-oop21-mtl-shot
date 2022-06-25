package model.map.tile;


import util.Vector2D;
/**
 * 
 * Extends AbstractTile into a TileStone.
 */
public class TileStone extends AbstractTile {
    /**
     * 
     * @param position
     */
    public TileStone(final Vector2D position) {
        super(position, "DesertTilesetCompact.png");
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean isTileable() {
        return true;
    }
}
