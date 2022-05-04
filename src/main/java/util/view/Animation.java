package util.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import util.Vector;

public class Animation {

	private int currFrame;
	final private PixelReader reader;
	final private Vector size;
	
	public Animation(final String path, final Vector size) throws FileNotFoundException {
		this.currFrame = 0;
		this.reader = new Image(new FileInputStream(path)).getPixelReader();
		this.size = size;
	}
	
	public Animation(int startFrame, final String path, final Vector size) throws FileNotFoundException {
		this.currFrame = startFrame;
		this.reader = new Image(new FileInputStream(path)).getPixelReader();
		this.size = size;
	}
	
	public ImageView get() {

		return new ImageView(new WritableImage(reader, currFrame*(int)size.getX(), currFrame*(int)size.getY(), (int)size.getX(), (int)size.getY()));
	}
	
	public void animate() {
		this.currFrame++;
	}
	
}
