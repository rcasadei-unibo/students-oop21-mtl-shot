package model.map.tile;

import java.util.Objects;

import utilities.Hitbox;
import utilities.Position;

public class TileImpl implements Tile {

    private final Hitbox hitbox;
    private final TileType tileType;
    private final Position<Integer, Integer> position;

    public TileImpl(final Position<Integer, Integer> position, final TileType tileType, final Hitbox hitbox) {
	this.position = position;
	this.tileType = tileType;
	this.hitbox = hitbox;
    }
    
    public Position<Integer, Integer> getPosition(){
	return position;
    }

    public Hitbox getHitbox() {
	return hitbox;
    }

    public TileType getTileType() {
	return tileType;
    }

    public String toString() {
	return hitbox.toString() + " - " + tileType; 
    }

    @Override
    public int hashCode() {
	return Objects.hash(hitbox, position, tileType);
    }

}
