package model.map.tile;

import util.Vector;

public class TileStone extends AbstractTile {

	public TileStone(final Vector position) {
		super(position, "src\\main\\resources\\DesertTilesetCompact.png");
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
