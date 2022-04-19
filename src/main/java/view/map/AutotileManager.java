package view.map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import model.map.tile.Tile;

public class AutotileManager {

    private List<Tile> tileList;
    private Image tileset;
    
    public AutotileManager(List<Tile> tileList, Image tileset) throws FileNotFoundException {
	PixelReader reader = new Image(new FileInputStream("src\\\\main\\\\resources\\\\DesertTileSet.png")).getPixelReader();
	this.tileList = tileList;
	this.tileset = tileset;
    }
    
    public WritableImage autotile() {
	/*BufferedImage image = ImageIO.read(new File(path, "image.png"));
	BufferedImage overlay = ImageIO.read(new File(path, "overlay.png"));

	// create the new image, canvas size is the max. of both image sizes
	int w = Math.max(image.getWidth(), overlay.getWidth());
	int h = Math.max(image.getHeight(), overlay.getHeight());
	BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	// paint both images, preserving the alpha channels
	Graphics g = combined.getGraphics();
	g.drawImage(image, 0, 0, null);
	g.drawImage(overlay, 0, 0, null);

	g.dispose();

	// Save as new image
	ImageIO.write(combined, "PNG", new File(path, "combined.png"));*/
	return null;
    }
    
}
