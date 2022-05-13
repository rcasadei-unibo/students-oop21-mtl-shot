package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Vector2D;
import util.map.MapConstants;

/**
 * TODO: write javadoc.
 *
 */
public class BulletsView {
	private List<ImageView> imageViewList;
	private final Image BULLET_IMAGE_RIGHT;
	private final double SCALE;
	
	public BulletsView(final double scale) throws FileNotFoundException {
	    this.SCALE = scale;
		this.imageViewList = new LinkedList<>();
		BULLET_IMAGE_RIGHT = new Image(new FileInputStream("src" + File.separator + "main" + File.separator + "resources" + File.separator + "bullet6x4.png"));
	}
	
    public void updateBullets(final List<Vector2D> bullets) {
    	this.imageViewList.clear();
    	for (final var v : bullets) {
    		final var iv = new ImageView(this.BULLET_IMAGE_RIGHT);
    		iv.setX(v.getX() * this.SCALE * MapConstants.getTilesize());
    		iv.setY(v.getY() * this.SCALE * MapConstants.getTilesize());
    		iv.setScaleX(this.SCALE);
    		iv.setScaleY(this.SCALE);
    		this.imageViewList.add(iv);
    	}
    }

	public List<ImageView> getImageViewList() {
		return this.imageViewList;
	}
}
