package model.map.tile;

import utilities.Hitbox;

public interface Tile {

    Hitbox getHitbox();

    TileType getTileType();

}