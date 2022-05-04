package view.player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Pair;
import util.Vector;
import util.map.MapConstants;
import util.view.Animation;

public class PlayerView {

	private final ImageView playerImageView;
	private final Image playerIcon;
	private final Image playerCrawlIcon;
	private final double playerSize;
	private boolean crawl;
	
	private Animation idle;
	private Animation walk;
	
	public PlayerView(final double playerSize) throws FileNotFoundException {
		playerIcon = new Image(new FileInputStream("src\\main\\resources\\pleier.png"));
		playerCrawlIcon = new Image(new FileInputStream("src\\main\\resources\\croulpleier.png"));
		playerImageView = new ImageView(playerIcon);
		playerImageView.setScaleX(playerSize);
		playerImageView.setScaleY(playerSize);
		this.playerSize = playerSize;
		
		this.idle = new Animation("src\\main\\resources\\PlayerNew.png", new Pair<>(32,32));
		this.walk = new Animation("src\\main\\resources\\PlayerRun.png", new Pair<>(32,32));
		
	}
	
    public void updatePlayer(final Vector position, final boolean crawl, final Vector speed) {
    	playerImageView.setX(position.getX()*playerSize*MapConstants.getTilesize());
    	playerImageView.setY(position.getY()*playerSize*MapConstants.getTilesize());
    	if (crawl && crawl != this.crawl) {
    	    playerImageView.setImage(playerCrawlIcon);
    	} else if (!crawl && crawl != this.crawl){
    	    playerImageView.setImage(idle.get());
    	}
    	if(speed.getX() != 0) {
    		playerImageView.setImage(walk.get());
    		walk.animate();
    	} else {
    		playerImageView.setImage(idle.get());
    	}
    	this.crawl = crawl;
    }

	public ImageView getPlayerImageView() {
		return playerImageView;
	}
	
}
