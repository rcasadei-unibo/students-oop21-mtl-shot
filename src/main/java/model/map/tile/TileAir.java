package model.map.tile;

import util.Vector2D;
/**
 * 
 * Extends AbstractTile into a TileAir.
 */
public class TileAir extends AbstractTile {
	
	public TileAir(final Vector2D position) {
        super(position, "tileSet/Air.png");
	}

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public boolean isTileable() {
        return false;
    }
}
