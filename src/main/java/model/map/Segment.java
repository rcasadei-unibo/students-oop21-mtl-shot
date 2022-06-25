package model.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import model.map.tile.Tile;
import model.map.tile.TileAir;
import model.map.tile.TileMetal;
import model.map.tile.TileStone;
import util.Vector2D;
import util.map.TextMap;

/**
 * 
 * Defines a Segment made up of Tiles.
 */
public class Segment {

    private final Set<Set<Tile>> map;
    private Vector2D playerSpawn;
    private final Collection<Vector2D> enemiesSpawn;
    private final TextMap textMap;
    private final double offset;

    /**
     * 
     * @param textMap
     * @param offset
     * @throws IOException
     */
    public Segment(final TextMap textMap, final double offset) throws IOException {
        this.enemiesSpawn = new LinkedList<>();
        this.offset = offset;
        this.textMap = textMap;
        this.map = new HashSet<>();
        final String path = textMap.getPath();
        final BufferedReader mapTxtInput = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path)));
        for (int i = 0; i < textMap.getHeight(); i++) {
            int j = 0;
            while (j < textMap.getWidth()) {
                final int check = mapTxtInput.read();
                if (check == '0') {
                    this.addTile(new TileAir(new Vector2D(j + offset, i)));
                    j++;
                } else if (check == '1') {
                    this.addTile(new TileStone(new Vector2D(j + offset, i)));
                    j++;
                } else if (check == '2') {
                    this.addTile(new TileMetal(new Vector2D(j + offset, i)));
                    j++;
                } else if (check == 'p') {
                    this.addTile(new TileAir(new Vector2D(j + offset, i)));
                    playerSpawn = new Vector2D(j + offset, i);
                    j++;
                } else if (check == 'e') {
                    this.addTile(new TileAir(new Vector2D(j + offset, i)));
                    enemiesSpawn.add(new Vector2D(j + offset, i));
                    j++;
                }
            }
        }
        mapTxtInput.close();
    }

    /**
     * Adds a Collection of tiles to the Segment.
     * @param bundle
     */
    public void addBundle(final Collection<Tile> bundle) {
        for (final var tile : bundle) {
            this.addTile(tile);
        }
    }

    /**
     * Returns a Set of all Tiles of a specific Tile type in the Segment.
     * @param tileClass
     * @return all the Tiles of a specific Tile type in the Segment.
     */
    public Optional<Set<Tile>> getTileSet(final Class<? extends Tile> tileClass) {
        for (final var temp : map) {
            if (temp.iterator().next().getClass() == tileClass) {
                return Optional.of(temp);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns a Set of Sets of Tiles, grouped up by columns and rows.
     * @return a Collection of every Tile from the Level.
     */
    public Set<Set<Tile>> getMap() {
        return this.map;
    }

    private Set<Tile> getAllTiles() {
        final Set<Tile> output = new HashSet<>();
        for (final var set : map) {
            for (final var tile : set) {
                output.add(tile);
            }
        }
        return output;
    }

    /**
     * Returns the player's spawn position in the Segment, if it has one.
     * @return the player's spawn position.
     */
    public Vector2D getPlayerSpawn() {
        return playerSpawn;
    }

    /**
     * Returns the enemies' spawn positions in the Segment, if it has any.
     * @return the enemies' spawn positions.
     */
    public Collection<Vector2D> getEnemiesSpawn() {
        return this.enemiesSpawn;
    }

    private void addTile(final Tile tile) {
        for (final var temp : map) {
            if (temp.iterator().next().getClass() == tile.getClass()) {
                temp.add(tile);
                return;
            }
        }
        final Set<Tile> set = new HashSet<>();
        set.add(tile);
        map.add(set);
    }

    /**
     * The Segment's TextMap, a Character based representation of the Segment's contents.
     * @return the Segment's TextMap.
     */
    public TextMap getTextMap() {
        return this.textMap;
    }

    /**
     * The Segment's tileable Tiles as a List of positions.
     * @return the Segment's tileable Tiles' positions.
     */
    public List<Vector2D> getTileables() {
        return this.getAllTiles().stream().filter(t -> t.isTileable()).map(t -> t.getPosition())
                .collect(Collectors.toList());
    }

    /**
     * The Segment's collidable Tiles as a List of positions.
     * @return the Segment's collidable Tiles' positions.
     */
    public List<Vector2D> getCollidables() {
        return this.getAllTiles().stream().filter(t -> t.isCollidable()).map(t -> t.getPosition())
                .collect(Collectors.toList());
    }

    /**
     * Checks if the Tile present at the give position is collidable.
     * @param position
     * @return the requested Tile's collidability.
     */
    public boolean isCollidableAtPosition(final Vector2D position) {
        final var tmp = this.getAllTiles().stream().filter(t -> t.getPosition().getX() == Math.floor(position.getX()))
                .filter(t -> t.getPosition().getY() == Math.floor(position.getY())).findFirst();
        if (tmp.equals(Optional.empty())) {
            return false;
        }
        return tmp.get().isCollidable();
    }

    /**
     * Returns the collidable tile at given position, if it exists.
     * @param position
     * @return the collidable tile at given position.
     */
    public Optional<Tile> getCollidableAtPosition(final Vector2D position) {
        return this.getAllTiles().stream().filter(t -> t.getPosition().getX() == Math.floor(position.getX()))
                .filter(t -> t.getPosition().getY() == Math.floor(position.getY())).filter(t -> t.isCollidable())
                .findFirst();
    }

    /**
     * Returns the tile at given position, if it exists.
     * @param position
     * @return the tile at given position.
     */
    public Optional<Tile> getTile(final Vector2D position) {
        return this.getAllTiles().stream().filter(t -> t.getPosition().equals(position)).findFirst();
    }

    /**
     * Returns a specific Tile from a List of Tiles, if it exists.
     * @param position
     * @param tileList
     * @return a specific Tile from a List of Tiles.
     */
    public Optional<Tile> getTile(final Vector2D position, final List<Tile> tileList) {
        return tileList.stream().filter(t -> t.getPosition().equals(position)).findFirst();
    }

    /**
     * Returns the Segment's left side's distance from the leftmost side of the Level.
     * @return the Segment's left side's distance from the leftmost side of the Level.
     */
    public Vector2D getOrigin() {
        return new Vector2D(this.offset, textMap.getHeight());
    }

    /**
     * Returns a Tile's position simplified to the TextMap's Tile's position.
     * @param position
     * @return a Tile's position on the TextMap.
     */
    public Vector2D getTilePos(final Vector2D position) {
        return this.getAllTiles().stream().filter(t -> t.getPosition().getX() == Math.floor(position.getX()))
                .filter(t -> t.getPosition().getY() == Math.floor(position.getY())).map(t -> t.getPosition())
                .findFirst().get();
    }
}
