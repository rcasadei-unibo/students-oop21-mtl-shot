package model.map.tile;

import java.util.Objects;

import util.Vector;

public abstract class AbstractTile implements Tile {

	private final String path;
	private final Vector position;

	public AbstractTile(final Vector position, final String path) {
		this.position = position;
		this.path = path;
	}

	public Vector getPosition(){
		return this.position;
	}
	
	public String getPath() {
		return this.path;
	}
	
    public abstract boolean isCollidable();
    
    @Override
	public String toString() {
		return this.position + " - Is collidable: " + isCollidable(); 
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
		final TileStone other = (TileStone) obj;
		return Objects.equals(position, other.getPosition()) && isCollidable() == other.isCollidable();
	}
    
}
