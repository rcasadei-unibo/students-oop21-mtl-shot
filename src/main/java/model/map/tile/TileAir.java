package model.map.tile;

import util.Vector;
/**
 * 
 * 
 */
public class TileAir extends AbstractTile {
	
	public TileAir(final Vector position) {
		super(position, "src\\main\\resources\\Air.png");
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
