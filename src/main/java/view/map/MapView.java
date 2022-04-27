package view.map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import model.map.tile.Tile;
import model.map.tile.TileType;
import util.MapConstants;

public class MapView {

    public List<Node> drawMap(final List<Tile> map, final double tileSize) throws FileNotFoundException{
	final List<Node> outputMap = new LinkedList<>();
	final AutotileManager autotileManager = new AutotileManager(map);
	
	for(Tile tile : map) {
	    WritableImage newImage = null;
	    if(tile.getTileType() == TileType.AIR) {
		final PixelReader reader = new Image(new FileInputStream("src\\main\\resources\\Air.png")).getPixelReader();
		newImage = new WritableImage(reader, 0, 0, 32, 32);
	    } else if(tile.getTileType() == TileType.GROUND) {
		final PixelReader reader = new Image(new FileInputStream("src\\main\\resources\\tempBlock.png")).getPixelReader();
		newImage = new WritableImage(reader, 0, 0, 32, 32);
	    }
	    
	    final Group tileImage = autotileManager.autotile(tileSize*tile.getPosition().getX(),tileSize*tile.getPosition().getY());
	    //final ImageView tileImage = new ImageView(newImage);
	    
	    tileImage.setScaleX(tileSize/MapConstants.getTilesize());
	    tileImage.setScaleY(tileSize/MapConstants.getTilesize());
	    
	    outputMap.add(tileImage);
	}
	
	return outputMap;
    }
    
}
