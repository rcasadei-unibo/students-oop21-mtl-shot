package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Vector;

public class BulletsView {
	private List<ImageView> imageView;
	private final Image BULLET_IMAGE_RIGHT;
	private final double SCALE = 5.0;
	
	public BulletsView() throws FileNotFoundException {
		this.imageView = new LinkedList<>();
		BULLET_IMAGE_RIGHT = new Image(new FileInputStream("src" + File.separator + "main" + File.separator + "resources" + File.separator + "bullet6x4.png"));
	}
	
    public void updateBullets(final List<Vector> bullets) {
    	this.imageView.clear();
    	for (var v : bullets) {
    		var iv = new ImageView(this.BULLET_IMAGE_RIGHT);
    		iv.setX(v.getX() * this.SCALE);
    		iv.setY(v.getY() * this.SCALE);
    		iv.setScaleX(this.SCALE);
    		iv.setScaleY(this.SCALE);
    		this.imageView.add(iv);
    	}
    }

	public List<ImageView> getImageViews() {
		return this.imageView;
	}
}
