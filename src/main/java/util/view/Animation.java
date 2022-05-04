package util.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import util.Pair;

public class Animation {

	private int currFrame;
	final private PixelReader reader;
	final private Pair<Integer, Integer> size;
	private int stall;
	
	public Animation(final String path, final Pair<Integer,Integer> size) throws FileNotFoundException {
		this.currFrame = 0;
		this.reader = new Image(new FileInputStream(path)).getPixelReader();
		this.size = size;
		this.stall = 25;
	}
	
	public Animation(int startFrame, final String path, final Pair<Integer, Integer> size) throws FileNotFoundException {
		this.currFrame = startFrame;
		this.reader = new Image(new FileInputStream(path)).getPixelReader();
		this.size = size;
	}
	
	public Image get() {
		return new ImageView(new WritableImage(reader, currFrame*size.getX(), 0, size.getX(), size.getY())).getImage();
	}
	
	public void animate() {
		if(stall == 0) {			
			if(currFrame == 3) {
				currFrame = 0;
			} else {			
				this.currFrame++;
			}
			stall = 25;
		} else {
			if(currFrame == 3) {
				stall = 0;
			} else {			
				this.stall--;
			}
		}
	}
	
}
