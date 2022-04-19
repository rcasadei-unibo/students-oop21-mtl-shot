package view.map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import model.map.tile.Tile;
import model.map.tile.TileType;

public class MapView {

    public List<Node> drawMap(final List<Tile> map, final int tileSize) throws FileNotFoundException{
	final List<Node> outputMap = new LinkedList<>();
	for(Tile tile : map) {
	    WritableImage newImage = null;
	    if(tile.getTileType() == TileType.AIR) {
		final PixelReader reader = new Image(new FileInputStream("src\\\\main\\\\resources\\\\Air.png")).getPixelReader();
		newImage = new WritableImage(reader, 0, 0, 32, 32);
	    } else if(tile.getTileType() == TileType.GROUND) {
		final PixelReader reader = new Image(new FileInputStream("src\\\\main\\\\resources\\\\tempBlock.png")).getPixelReader();
		newImage = new WritableImage(reader, 0, 0, 32, 32);
	    }
	    final ImageView tileImage = new ImageView(newImage);
	    
	    tileImage.setX(tileSize*tile.getPosition().getX());
	    tileImage.setY(tileSize*tile.getPosition().getY());
	    
	    tileImage.setScaleX(1.25);
	    tileImage.setScaleY(1.25);
	    
	    outputMap.add(tileImage);
	}
	
	return outputMap;
    }
    
}
