package view.map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import controller.map.MapController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import model.map.tile.Tile;
import util.Vector;
import util.map.MapConstants;

public class MapView {
	
	private final MapController mapController;
	
	public MapView(final List<Vector> list, final MapController mapController, final double tileSize) {
		this.mapController = mapController;
		final List<Node> outputMap = new LinkedList<>();
		final AutotileManager autotileManager = new AutotileManager(list);

		for(var position : list) {
			Group tileImage = null; // Will never be null, map is composed of solely PASSABLE or NON-PASSABLE tiles, and as such will never evade the if construct.
			
				tileImage = autotileManager.autotile(position, tileSize, new Image(new FileInputStream(mapController.getTile(position).get().getPath())).getPixelReader());
			/*if(tile.getTileType() == TileType.AIR) {
				final PixelReader reader = new Image(new FileInputStream("src\\main\\resources\\Air.png")).getPixelReader();
				final WritableImage newImage = new WritableImage(reader, 0, 0, 32, 32);
				tileImage = new Group(new ImageView(newImage));
			} else if(tile.getTileType() == TileType.GROUND) {
				tileImage = autotileManager.autotile(tile.getPosition(), tileSize, new Image(new FileInputStream("src\\main\\resources\\DesertTilesetCompact.png")).getPixelReader());
			}*/
			
			
			

			tileImage.setScaleX(tileSize/MapConstants.getTilesize());
			tileImage.setScaleY(tileSize/MapConstants.getTilesize());

			outputMap.add(tileImage);
		}

		return outputMap;
	}
	


	
	

}
