package view.map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import model.map.tile.Tile;

public class AutotileManager {

	private List<Tile> tileList;
	private PixelReader reader;

	public AutotileManager(List<Tile> tileList) throws FileNotFoundException {
		this.reader = new Image(new FileInputStream("src\\main\\resources\\DesertTilesetCompact.png")).getPixelReader();
		this.tileList = tileList;
	}

	public Group autotile(double x, double y) {
		ImageView layer1 = new ImageView(new WritableImage(reader, 0, 0, 32, 32));
		ImageView layer2 = new ImageView(new WritableImage(reader, 64, 0, 32, 32));
		ImageView layer3 = new ImageView(new WritableImage(reader, 96, 0, 32, 32));
		ImageView layer4 = new ImageView(new WritableImage(reader, 128, 64, 32, 32));
		
		layer1.setX(x);
		layer1.setY(y);
		layer2.setX(x);
		layer2.setY(y);
		layer3.setX(x);
		layer3.setY(y);
		layer4.setX(x);
		layer4.setY(y);
		
		// create the new image, canvas size is the max. of both image sizes
		
		Group result = new Group(
				layer1,
				layer2,
				layer3,
				layer4
				);
		
		return result;
	}

}
