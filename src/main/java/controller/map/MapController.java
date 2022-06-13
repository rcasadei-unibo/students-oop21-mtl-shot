package controller.map;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.map.tile.MapModel;
import model.map.tile.Tile;
import util.Vector2D;

/**
 * 
 *
 */
public class MapController {

    private final MapModel mapModel;
    private Vector2D playerSpawn;

    public MapController(final MapModel mapModel) throws IOException {
        this.mapModel = mapModel;
    }

    public List<Vector2D> getTileables() {
        return mapModel.getAllTiles()
                .stream()
                .filter(t -> t.isTileable())
                .map(t -> t.getPosition())
        	    .collect(Collectors.toList());
    }

    public List<Vector2D> getCollidables() {
        return mapModel.getAllTiles()
                .stream()
        		.filter(t -> t.isCollidable())
        		.map(t -> t.getPosition())
        		.collect(Collectors.toList());
    }

    public boolean hasSingleCollidable(final Vector2D position) {
        return mapModel.getAllTiles().stream()
                .filter(t -> t.getPosition().getX() == Math.floor(position.getX()))
                .filter(t -> t.getPosition().getY() == Math.floor(position.getY()))
                .map(t -> t.isCollidable())
                .findFirst().get();
    }

    public Optional<Tile> getTile(final Vector2D position) {
        return mapModel.getAllTiles().stream().filter(t -> t.getPosition().equals(position)).findFirst();
    }

    public Optional<Tile> getTile(final Vector2D position, final List<Tile> tileList) {
        return tileList.stream().filter(t -> t.getPosition().equals(position)).findFirst();
    }
    
    public Vector2D getTilePos(final Vector2D position) {
        return mapModel.getAllTiles().stream()
                .filter(t -> t.getPosition().getX() == Math.floor(position.getX()))
                .filter(t -> t.getPosition().getY() == Math.floor(position.getY()))
                .map(t -> t.getPosition())
                .findFirst().get();
    }

    public Vector2D getPlayerSpawn() {
        return playerSpawn;
    }

}
