package view.player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.MapConstants;
import util.Vector;

public class PlayerView {

	private ImageView playerImageView;
	private Image playerIcon;
	
	public PlayerView() throws FileNotFoundException {
		playerIcon = new Image(new FileInputStream("src\\main\\resources\\pleier.png"));
		playerImageView = new ImageView(playerIcon);
	}
	
    public void updatePlayer(final Vector position) {
    	playerImageView.setX(position.getX()*MapConstants.getTilesize());
    	playerImageView.setY(position.getY()*MapConstants.getTilesize());
 
    }

	public ImageView getPlayerImageView() {
		return playerImageView;
	}
	
}
