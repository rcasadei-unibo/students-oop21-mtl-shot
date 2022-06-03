package model.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
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
	private final TextMap textMap;
	private final double offset;
	
	public Segment(final TextMap textMap, final double offset) throws IOException {
		this.offset = offset;
		this.textMap = textMap;
		this.map = new HashSet<>();
		final File mapTxt = textMap.getFile();
		final BufferedReader mapTxtInput = new BufferedReader(new FileReader(mapTxt));
		for(int i = 0; i < textMap.getHeight(); i++) {
			int j = 0;
			while(j < textMap.getWidth()) {
				final int check = mapTxtInput.read();
				if(check == '0') {	
					this.addTile(new TileAir(new Vector2D(j+offset, i)));
					j++;
				} else if(check == '1') {
					this.addTile(new TileStone(new Vector2D(j+offset, i)));
					j++;
				} else if(check == '2') {
					this.addTile(new TileMetal(new Vector2D(j+offset, i)));
					j++;
				} else if(check == 'p')	{
					this.addTile(new TileAir(new Vector2D(j+offset, i)));
					playerSpawn = new Vector2D(j+offset, i);
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
	
	private Set<Tile> getAllTiles(){
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
	
	public TextMap getTextMap() {
		return this.textMap;
	}
	
	 public List<Vector2D> getTileables() {
	        return this.getAllTiles()
	                .stream()
	                .filter(t -> t.isTileable())
	                .map(t -> t.getPosition())
	        	    .collect(Collectors.toList());
	    }

	    public List<Vector2D> getCollidables() {
	        return this.getAllTiles()
	                .stream()
	        		.filter(t -> t.isCollidable())
	        		.map(t -> t.getPosition())
	        		.collect(Collectors.toList());
	    }

	    public boolean isCollidableAtPosition(final Vector2D position) {
	        final var tmp = this.getAllTiles().stream()
	                .filter(t -> t.getPosition().getX() == Math.floor(position.getX()))
	                .filter(t -> t.getPosition().getY() == Math.floor(position.getY())).findFirst();
	        if (tmp.equals(Optional.empty())) {
	            return false;
	        }
	        return tmp.get().isCollidable();
	    }

	    public Optional<Tile> getTile(final Vector2D position) {
	        return this.getAllTiles().stream().filter(t -> t.getPosition().equals(position)).findFirst();
	    }

	    public Optional<Tile> getTile(final Vector2D position, final List<Tile> tileList) {
	        return tileList.stream().filter(t -> t.getPosition().equals(position)).findFirst();
	    }
	    
	    public Vector2D getOrigin() {
	    	return new Vector2D(this.offset, textMap.getHeight());
	    }
}
