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
			Group tileImage = null; // Will never be null, map is composed of solely AIR or GROUND tiles, and as such will never evade the if construct.
			if(tile.getTileType() == TileType.AIR) {
				final PixelReader reader = new Image(new FileInputStream("src\\main\\resources\\Air.png")).getPixelReader();
				final WritableImage newImage = new WritableImage(reader, 0, 0, 32, 32);
				tileImage = new Group(new ImageView(newImage));
			} else if(tile.getTileType() == TileType.GROUND) {
				tileImage = autotileManager.autotile(tile.getPosition(), tileSize, new Image(new FileInputStream("src\\main\\resources\\DesertTilesetCompact.png")).getPixelReader());
			}
			

			//final ImageView tileImage = new ImageView(newImage);

			tileImage.setScaleX(tileSize/MapConstants.getTilesize());
			tileImage.setScaleY(tileSize/MapConstants.getTilesize());

			outputMap.add(tileImage);
		}

		return outputMap;
	}

}
