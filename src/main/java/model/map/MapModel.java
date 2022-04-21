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
import util.MapConstants;
import util.Vector;

public class MapModel {

	private final List<Tile> map = new LinkedList<>();
	private final TextMap textMap;
	private Vector playerSpawn;

	public MapModel(final TextMap textMap) {
		this.textMap = textMap;
	}

	public List<Tile> create() throws IOException{
		final Vector tileHitbox = new Vector(MapConstants.getTilesize(),MapConstants.getTilesize());
		File mapTxt = new File(textMap.getPath());
		BufferedReader mapTxtInput = new BufferedReader(new FileReader(mapTxt));
		for(double i = 0; i < textMap.getHeight(); i++) {
			double j = 0;
			while(j < textMap.getWidth()) {
				int check = mapTxtInput.read();
				if(check == '0') {							
					map.add(new TileImpl(new Vector(j, i), TileType.AIR, null));
					j++;
				} else if(check == '1') {
					map.add(new TileImpl(new Vector(j, i), TileType.GROUND, null));
					j++;
				} else if(check == 'p')	{
					map.add(new TileImpl(new Vector(j, i), TileType.AIR, null));
					playerSpawn = new Vector(j, i);
					j++;
				}
			}
		}
		mapTxtInput.close();
		return map;
	}

	public Optional<Tile> getTile(final Vector position) {
		for(Tile tile : map) {
			if(tile.getPosition().getX() == Math.floor(position.getX()/MapConstants.getTilesize()) &&
					tile.getPosition().getY() == Math.floor(position.getY()/MapConstants.getTilesize())) {
				return Optional.of(tile);
			}
		}
		return Optional.empty();
	}

	public Vector getPlayerSpawn() {
		return new Vector(playerSpawn.getX()*MapConstants.getTilesize(), playerSpawn.getY()*MapConstants.getTilesize());
	}

}
