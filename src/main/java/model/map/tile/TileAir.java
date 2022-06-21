package model.map.tile;

import java.io.File;

import util.Vector2D;
/**
 * 
 * @author filippo.gurioli
 *
 */
public class TileAir extends AbstractTile {
	
	public TileAir(final Vector2D position) {
        super(position, "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Air.png");
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
