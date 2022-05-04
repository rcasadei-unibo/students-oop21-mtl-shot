package view.player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Vector;
import util.map.MapConstants;

public class PlayerView {

	private final ImageView playerImageView;
	private Image playerIcon;
	private final double playerSize;
	
	public PlayerView(final double playerSize) throws FileNotFoundException {
		playerIcon = new Image(new FileInputStream("src\\main\\resources\\pleier.png"));
		playerImageView = new ImageView(playerIcon);
		playerImageView.setScaleX(playerSize);
		playerImageView.setScaleY(playerSize);
		this.playerSize = playerSize;
	}
	
    public void updatePlayer(final Vector position) {
    	playerImageView.setX(position.getX()*playerSize*MapConstants.getTilesize());
    	playerImageView.setY(position.getY()*playerSize*MapConstants.getTilesize());
 
    }

	public ImageView getPlayerImageView() {
		return playerImageView;
	}
	
}
