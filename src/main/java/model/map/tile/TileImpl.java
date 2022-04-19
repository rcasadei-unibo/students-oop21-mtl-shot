package model.map.tile;

import model.Entity;
import util.Vector;

public class TileImpl extends Entity implements Tile {

	private final TileType tileType;

	public TileImpl(final Vector position, final TileType tileType, final Vector hitbox) {
		super(position, hitbox);
		this.tileType = tileType;
	}

	public Vector getPosition(){
		return super.getPosition();
	}

	public Vector getHitbox() {
		return super.getHitbox();
	}

	public TileType getTileType() {
		return tileType;
	}

	public String toString() {
		return super.getHitbox().toString() + " - " + tileType; 
	}

}
