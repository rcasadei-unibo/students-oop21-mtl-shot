package controller.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import model.map.TextMap;
import model.map.tile.Tile;
import model.map.tile.TileImpl;
import model.map.tile.TileType;
import utilities.HitboxImpl;
import utilities.Position;

public class MapReader {

    private final List<Tile> map = new LinkedList<>();
    private Position<Double, Double> playerSpawn;
    private final static double TILESIZE = 40;

    public List<Tile> read(final TextMap textmap){
	final File mapFile = new File(textmap.getPath());

	try (BufferedReader mapFileInput = new BufferedReader(new FileReader(mapFile))) {
	    for(int j = 0; j<textmap.getWidth(); j++) {
		int i = 0;
		while(i<textmap.getHeight()) {
		    final int check = mapFileInput.read();
		    if(check == '0') {			

			map.add(new TileImpl(new Position<>(i, j), TileType.AIR, new HitboxImpl(1, 1)));

			i++;
			
		    } else if(check == '1') {

			map.add(new TileImpl(new Position<>(i, j), TileType.GROUND, new HitboxImpl(1, 1)));

			i++;
			
		    } else if(check == 'p')	{

			map.add(new TileImpl(new Position<>(i, j), TileType.AIR, new HitboxImpl(1, 1)));

			playerSpawn = new Position<>((double) i*TILESIZE,(double) j*TILESIZE);

			i++;
			
		    }
		}
	    }
	} catch (IOException e) {

	    e.printStackTrace();
	}

	return map;

    }
    
    public Position<Double, Double> getPlayerSpawn(){
	return playerSpawn;
    }

}
