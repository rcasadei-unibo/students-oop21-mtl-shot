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
import util.Vector;
import util.map.MapConstants;

public class MapView {

	private final List<Node> nodes = new LinkedList<>();

	public MapView(final MapController mapController, final double tileSize) throws FileNotFoundException {
		final List<Vector> list = mapController.getAllTiles();
		final AutotileManager autotileManager = new AutotileManager(list);
		for (final var position : list) {
			
			final Group tileImage;
			
			if(mapController.getTile(position).get().isTileable()) {
				tileImage = autotileManager.autotile(position, tileSize*MapConstants.getTilesize(),
						new Image(new FileInputStream(mapController.getTile(position).get().getPath())).getPixelReader());
			} else {
				tileImage = new Group(new ImageView("pah"));
			}

			tileImage.setScaleX(tileSize);
			tileImage.setScaleY(tileSize);

			nodes.add(tileImage);
		}
	}

	public List<Node> getNodes() {
		return this.nodes;
	}

}
