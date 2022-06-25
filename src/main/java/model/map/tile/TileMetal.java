package model.map.tile;

import util.Vector2D;
/**
 * 
 * Extends AbstractTile into a TileMetal.
 */
public class TileMetal extends AbstractTile {
    /**
     * 
     * @param position
     */
    public TileMetal(final Vector2D position) {
        super(position, "MetalTilesetCompact.png");
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
