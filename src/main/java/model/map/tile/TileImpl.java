package model.map.tile;

import util.Vector;

public class TileImpl implements Tile {

	private final TileType tileType;
	private final Vector position;

	public TileImpl(final Vector position, final TileType tileType) {
		this.position = position;
		this.tileType = tileType;
	}

	public Vector getPosition(){
		return this.position;
	}

	public TileType getTileType() {
		return tileType;
	}

	public String toString() {
		return this.position + " - " + tileType; 
	}

}
