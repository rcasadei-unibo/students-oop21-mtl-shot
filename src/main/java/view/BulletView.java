package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Vector;

public class BulletView {
	private ImageView imageView;
	
	private Image image;
	
	public BulletView() throws FileNotFoundException {
		this.image = new Image(new FileInputStream("src\\main\\resources\\bullet6x4.png"));
		this.imageView = new ImageView(this.image);
	}
	
    public void updatePosition(final Vector position) {
    	this.imageView.setX(position.getX());
    	this.imageView.setY(position.getY());
 
    }

	public ImageView getImageView() {
		return this.imageView;
	}
}
