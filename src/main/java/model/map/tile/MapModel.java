package model.map.tile;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MapModel {

	private final Set<Set<Tile>> map;
	
	public MapModel(){
		this.map = new HashSet<>();
	}
	
	public void addTile(final Tile tile) {
		for(var temp : map) {
			if(temp.iterator().next().getClass() == tile.getClass()) {
				temp.add(tile);
				return;
			}
		}
		final Set<Tile> set = new HashSet<>();
		set.add(tile);
		map.add(set);
	}
	
	public void addBundle(final Collection<Tile> bundle) {
		for(var tile : bundle) {
			this.addTile(tile);
		}
	}
	
	public Optional<Set<Tile>> getTileSet(Class<? extends Tile> tileClass){
		for(var temp : map) {
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
		for(var set : map) {
			for(var tile : set) {
				output.add(tile);
			}
		}
		return output;
	}
}
