package view.map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
		final AutotileManager autotileManager = new AutotileManager(list.stream().filter(t -> mapController.getTile(t).get().isTileable()).collect(Collectors.toList()));
		for (final var position : list) {
			final Group tileImage;		
			if(mapController.getTile(position).get().isTileable()) {
				tileImage = autotileManager.autotile(position, tileSize*MapConstants.getTilesize(),
						new Image(new FileInputStream(mapController.getTile(position).get().getPath())).getPixelReader());
			} else {
				ImageView tempImage = new ImageView(new WritableImage(new Image(new FileInputStream(mapController.getTile(position).get().getPath())).getPixelReader(), 0, 0, 32, 32));
				final Vector tempPos = new Vector(position);
				tempPos.mlt(new Vector(tileSize*MapConstants.getTilesize(), tileSize*MapConstants.getTilesize()));
				tempImage.setX(tempPos.getX());
				tempImage.setY(tempPos.getY());
				tileImage = new Group(tempImage);
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
