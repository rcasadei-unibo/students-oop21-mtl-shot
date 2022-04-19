package model.map.tile;

import utilities.Hitbox;
import utilities.Position;

public interface Tile {

    Hitbox getHitbox();

    TileType getTileType();
    
    Position<Integer, Integer> getPosition();

}