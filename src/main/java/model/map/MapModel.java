package model.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.map.tile.Tile;
import model.map.tile.TileImpl;
import model.map.tile.TileType;
import utilities.Position;

public class MapModel {

    private final List<Tile> map = new LinkedList<>();
    private final TextMap textMap;

    public MapModel(final TextMap textMap) {
	this.textMap = textMap;
    }

    public List<Tile> create() throws IOException{
	File mapTxt = new File(textMap.getPath());
	BufferedReader mapTxtInput = new BufferedReader(new FileReader(mapTxt));
	for(int i = 0; i < textMap.getHeight(); i++) {
	    int j = 0;
	    while(j < textMap.getWidth()) {
		int check = mapTxtInput.read();
		if(check == '0') {							
		    map.add(new TileImpl(new Position<Integer, Integer>(j, i), TileType.AIR, null));
		    j++;
		} else if(check == '1') {
		    map.add(new TileImpl(new Position<Integer, Integer>(j, i), TileType.GROUND, null));
		    j++;
		} else if(check == 'p')	{
		    //PLAYER SPAWN
		}
	    }
	}
	mapTxtInput.close();
	return map;
    }
    
    public Optional<Tile> getTile(final Position<Double, Double> position) {
	for(Tile tile : map) {
	    if(tile.getPosition().getX() == (int)Math.floor(position.getX()/40) &&
		    tile.getPosition().getY() == (int)Math.floor(position.getY()/40)) {
		return Optional.of(tile);
	    }
	}
	return Optional.empty();
    }
    
}
