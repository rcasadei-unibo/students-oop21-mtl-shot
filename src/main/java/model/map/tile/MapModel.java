package model.map.tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import util.Vector2D;
import util.map.TextMap;
/**
 * 
 *
 */
public class MapModel {

	private final Set<Set<Tile>> map;
	private Vector2D playerSpawn;
	private Vector2D enemySpawn;
	
	public MapModel(final TextMap textMap) throws IOException {
		this.map = new HashSet<>();
        final File mapTxt = textMap.getFile();
        final BufferedReader mapTxtInput = new BufferedReader(new FileReader(mapTxt));
        for (int i = 0; i < textMap.getHeight(); i++) {
            int j = 0;
            while (j < textMap.getWidth()) {
                final int check = mapTxtInput.read();
                if (check == '0') {
                    this.addTile(new TileAir(new Vector2D(j, i)));
                    j++;
                } else if (check == '1') {
                    this.addTile(new TileStone(new Vector2D(j, i)));
                    j++;
                } else if (check == '2') {
                    this.addTile(new TileMetal(new Vector2D(j, i)));
                    j++;
                } else if (check == 'p') {
                    this.addTile(new TileAir(new Vector2D(j, i)));
                    playerSpawn = new Vector2D(j, i);
                    j++;
                } else if (check == 'e') {
                    this.addTile(new TileAir(new Vector2D(j, i)));
                    enemySpawn = new Vector2D(j, i);
                    j++;
                }
            }
        }
        mapTxtInput.close();
	}
	
	public void addBundle(final Collection<Tile> bundle) {
		for(final var tile : bundle) {
			this.addTile(tile);
		}
	}
	
	public Optional<Set<Tile>> getTileSet(final Class<? extends Tile> tileClass){
		for(final var temp : map) {
			if(temp.iterator().next().getClass() == tileClass) {
				return Optional.of(temp);
			}
		}
		return Optional.empty();
	}
	
	public Set<Set<Tile>> getMap(){
		return this.map;
	}
	
	public Set<Tile> getAllTiles(){
		final Set<Tile> output = new HashSet<>();
		for(final var set : map) {
			for(final var tile : set) {
				output.add(tile);
			}
		}
		return output;
	}
	
	public Vector2D getPlayerSpawn() {
        return playerSpawn;
    }
	
	public Vector2D getEnemySpawn() {
        return enemySpawn;
    }
	
	private void addTile(final Tile tile) {
        for(final var temp : map) {
            if(temp.iterator().next().getClass() == tile.getClass()) {
                temp.add(tile);
                return;
            }
        }
        final Set<Tile> set = new HashSet<>();
        set.add(tile);
        map.add(set);
    }
}
