package model.map.tile;

import util.Vector2D;
/**
 * 
 * @author filippo.gurioli
 *
 */
public class TileMetal extends AbstractTile {
	
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
