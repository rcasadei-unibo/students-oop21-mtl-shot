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
	private int curdelay;
	private int delay;
	private int frames;
	
	public Animation(final String path, final Pair<Integer,Integer> size, final int frames, final int delay) throws FileNotFoundException {
		this.currFrame = 0;
		this.reader = new Image(new FileInputStream(path)).getPixelReader();
		this.size = size;
		this.delay = delay;
		this.frames = frames;
		this.curdelay = delay;
	}
	
	public Animation(int startFrame, final String path, final Pair<Integer, Integer> size) throws FileNotFoundException {
		this.currFrame = startFrame;
		this.reader = new Image(new FileInputStream(path)).getPixelReader();
		this.size = size;
	}
	
	public Image get(final boolean right) {
		int line;
		if(right) { line = 0; } else { line = 1; }
		return new ImageView(new WritableImage(reader, currFrame*size.getX(), size.getY()*line, size.getX(), size.getY())).getImage();
	}
	
	public void animate() {
		if(curdelay == 0) {			
			if(currFrame == frames) {
				currFrame = 0;
			} else {			
				this.currFrame++;
			}
			curdelay = delay;
		} else {
			if(currFrame == frames) {
				curdelay = 0;
			} else {			
				this.curdelay--;
			}
		}
	}
	
}
