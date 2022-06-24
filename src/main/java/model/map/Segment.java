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
 *
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
     * 
     * @param bundle
     */
    public void addBundle(final Collection<Tile> bundle) {
        for (final var tile : bundle) {
            this.addTile(tile);
        }
    }

    /**
     * 
     * @param tileClass
     * @return bla
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
     * 
     * @return bla
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
     * 
     * @return bla
     */
    public Vector2D getPlayerSpawn() {
        return playerSpawn;
    }

    /**
     * 
     * @return bla
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
     * 
     * @return bla
     */
    public TextMap getTextMap() {
        return this.textMap;
    }

    /**
     * 
     * @return bla
     */
    public List<Vector2D> getTileables() {
        return this.getAllTiles().stream().filter(t -> t.isTileable()).map(t -> t.getPosition())
                .collect(Collectors.toList());
    }

    /**
     * 
     * @return bla
     */
    public List<Vector2D> getCollidables() {
        return this.getAllTiles().stream().filter(t -> t.isCollidable()).map(t -> t.getPosition())
                .collect(Collectors.toList());
    }

    /**
     * 
     * @param position
     * @return bla
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
     * 
     * @param position
     * @return bla
     */
    public Optional<Tile> getCollidableAtPosition(final Vector2D position) {
        return this.getAllTiles().stream().filter(t -> t.getPosition().getX() == Math.floor(position.getX()))
                .filter(t -> t.getPosition().getY() == Math.floor(position.getY())).filter(t -> t.isCollidable())
                .findFirst();
    }

    /**
     * 
     * @param position
     * @return bla
     */
    public Optional<Tile> getTile(final Vector2D position) {
        return this.getAllTiles().stream().filter(t -> t.getPosition().equals(position)).findFirst();
    }

    /**
     * 
     * @param position
     * @param tileList
     * @return bla
     */
    public Optional<Tile> getTile(final Vector2D position, final List<Tile> tileList) {
        return tileList.stream().filter(t -> t.getPosition().equals(position)).findFirst();
    }

    /**
     * 
     * @return bla
     */
    public Vector2D getOrigin() {
        return new Vector2D(this.offset, textMap.getHeight());
    }

    /**
     * 
     * @param position
     * @return bla
     */
    public Vector2D getTilePos(final Vector2D position) {
        return this.getAllTiles().stream().filter(t -> t.getPosition().getX() == Math.floor(position.getX()))
                .filter(t -> t.getPosition().getY() == Math.floor(position.getY())).map(t -> t.getPosition())
                .findFirst().get();
    }
}
