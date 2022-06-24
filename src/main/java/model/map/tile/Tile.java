package model.map.tile;

import util.Vector2D;
/**
 * 
 */
public interface Tile {
	
	Vector2D getPosition();
	
    boolean isCollidable();
    
    boolean isTileable();
    
    String getPath();
	
}
