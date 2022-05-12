package view.player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.player.PlayerController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Vector2D;
import util.map.MapConstants;
/**
 * 
 *
 */
public class PlayerView {

    private final PlayerController playerController;
	private final ImageView playerImageView;
	private final Image playerIcon;
	private final Image playerCrawlIcon;
	private final double playerSize;
	private boolean crouch;
	
	public PlayerView(final PlayerController playerController, final double playerSize) throws FileNotFoundException {
	    this.playerController = playerController;
		playerIcon = new Image(new FileInputStream("src\\main\\resources\\pleier.png"));
		playerCrawlIcon = new Image(new FileInputStream("src\\main\\resources\\croulpleier.png"));
		playerImageView = new ImageView(playerIcon);
		playerImageView.setScaleX(playerSize);
		playerImageView.setScaleY(playerSize);
		this.playerSize = playerSize;
	}
	
    public void updatePlayer(final Vector2D position, final boolean crouch) {
    	playerImageView.setX(position.getX()*playerSize*MapConstants.getTilesize());
    	playerImageView.setY(position.getY()*playerSize*MapConstants.getTilesize());
    	if (crouch && crouch != this.crouch) {
    	    playerImageView.setImage(playerCrawlIcon);
    	} else if (crouch != this.crouch) {
    	    playerImageView.setImage(playerIcon);
    	}
    	this.crouch = crouch;
    }

	public ImageView getPlayerImageView() {
		return playerImageView;
	}
	
}
