package model.map.tile;

import util.Vector;

public interface Tile {
	
	Vector getPosition();
	
    boolean isCollidable();
    
    boolean isTileable();
    
    String getPath();
	
}
