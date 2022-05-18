package model.map.tile;

import util.Vector;
/**
 * 
 * 
 */
public class TileMetal extends AbstractTile {
	
	public TileMetal(final Vector position) {
		super(position, "src\\main\\resources\\MetalTilesetCompact.png");
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
