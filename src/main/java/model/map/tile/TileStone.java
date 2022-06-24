package model.map.tile;

import java.io.File;

import util.Vector2D;
/**
 * 
 * @author filippo.gurioli
 *
 */
public class TileStone extends AbstractTile {

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
