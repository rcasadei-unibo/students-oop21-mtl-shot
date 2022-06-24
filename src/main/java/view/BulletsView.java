package view;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Vector2D;
import util.map.MapConstants;

/**
 * This class holds the instances
 * of the imageViews representing
 * bullets.
 */
public class BulletsView {
    
	private List<ImageView> imageViewList;
    private final Image bulletTexture;
    private final double scale;
	
	/**
	 * 
	 * @param scale - imageViews scale
	 * @throws FileNotFoundException
	 */
	public BulletsView(final double scale) throws FileNotFoundException {
	    this.scale = scale;
		this.imageViewList = new LinkedList<>();
		bulletTexture = new Image(ClassLoader.getSystemResourceAsStream("bullet6x4.png"));
	}
	
	/**
	 * 
	 * @param bullets - new bullets coordinates.
	 */
    public void updateBullets(final List<Vector2D> bullets) {
    	for (int i = 0; i < this.imageViewList.size() && i < bullets.size(); i++) {
    		this.imageViewList.get(i).setX(bullets.get(i).getX() * this.scale * MapConstants.getTilesize());
    		this.imageViewList.get(i).setY(bullets.get(i).getY() * this.scale * MapConstants.getTilesize());
    	}
    	for (int i = this.imageViewList.size(); i < bullets.size(); i++) {
    		final var iv = new ImageView(this.bulletTexture);
    		iv.setX(bullets.get(i).getX() * this.scale * MapConstants.getTilesize());
    		iv.setY(bullets.get(i).getY() * this.scale * MapConstants.getTilesize());
    		iv.setScaleX(this.scale);
    		iv.setScaleY(this.scale);
    		this.imageViewList.add(iv);
    	}
    	while (bullets.size() < this.imageViewList.size()) {
    		this.imageViewList.remove(this.imageViewList.size() - 1);
    	}
    }

    /**
     * @return bullets ImageView list.
     */
	public List<ImageView> getImageViewList() {
		return this.imageViewList;
	}
}
