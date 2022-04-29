package model.map.tile;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(position, tileType);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;			
		}
		if (obj == null) {
			return false;			
		}
		if (getClass() != obj.getClass()) {
			return false;			
		}
		final TileImpl other = (TileImpl) obj;
		return Objects.equals(position, other.position) && tileType == other.tileType;
	}
	
	

}
