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
	private final double scale;
	
	public BulletsView(final double scale) throws FileNotFoundException {
	    this.scale = scale;
		this.imageViewList = new LinkedList<>();
		BULLET_IMAGE_RIGHT = new Image(new FileInputStream("src" + File.separator + "main" + File.separator + "resources" + File.separator + "bullet6x4.png"));
	}
	
    public void updateBullets(final List<Vector2D> bullets) {
    	for (int i = 0; i < this.imageViewList.size() && i < bullets.size(); i++) {
    		this.imageViewList.get(i).setX(bullets.get(i).getX() * this.scale * MapConstants.getTilesize());
    		this.imageViewList.get(i).setY(bullets.get(i).getY() * this.scale * MapConstants.getTilesize());
    	}
    	for (int i = this.imageViewList.size(); i < bullets.size(); i++) {
    		final var iv = new ImageView(this.BULLET_IMAGE_RIGHT);
    		iv.setX(bullets.get(i).getX() * this.scale * MapConstants.getTilesize());
    		iv.setY(bullets.get(i).getY() * this.scale * MapConstants.getTilesize());
    		iv.setScaleX(this.scale);
    		iv.setScaleY(this.scale);
    		this.imageViewList.add(iv);
    	}
    	while (bullets.size() < this.imageViewList.size()) {
    		this.imageViewList.remove(this.imageViewList.size() - 1);
    	}
    	/*this.imageViewList.clear();
    	for (final var v : bullets) {
    		final var iv = new ImageView(this.BULLET_IMAGE_RIGHT);
    		iv.setX(v.getX() * this.scale * MapConstants.getTilesize());
    		iv.setY(v.getY() * this.scale * MapConstants.getTilesize());
    		iv.setScaleX(this.scale);
    		iv.setScaleY(this.scale);
    		this.imageViewList.add(iv);
    	}*/
    }

	public List<ImageView> getImageViewList() {
		return this.imageViewList;
	}
}
