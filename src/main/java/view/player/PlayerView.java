package view.player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Direction;
import util.Vector2D;
import util.map.MapConstants;

/**
 * 
 *
 */
public class PlayerView {

    private final ImageView playerImageView;
    private final Image playerIcon;
    private final Image playerCrouchIcon;
    private final Image playerUp;
    private final Image playerDown;
    private final Image playerLeft;
    private final Image playerRight;
    private final double playerSize;

    /**
     * 
     * @param playerSize
     * @throws FileNotFoundException
     */
    public PlayerView(final double playerSize) throws FileNotFoundException {
        playerIcon = new Image(new FileInputStream("src\\main\\resources\\pleier.png"));
        playerCrouchIcon = new Image(new FileInputStream("src\\main\\resources\\croulpleier.png"));
        playerUp = new Image(new FileInputStream("src\\main\\resources\\pleierUP.png"));
        playerDown = new Image(new FileInputStream("src\\main\\resources\\pleierDOWN.png"));
        playerRight = new Image(new FileInputStream("src\\main\\resources\\pleierRIGHT.png"));
        playerLeft = new Image(new FileInputStream("src\\main\\resources\\pleierLEFT.png"));
        playerImageView = new ImageView(playerIcon);
        playerImageView.setScaleX(playerSize);
        playerImageView.setScaleY(playerSize);
        this.playerSize = playerSize;
    }

    public void updatePlayer(final Vector2D position, final boolean crouch, final Direction direction) {
        playerImageView.setX(position.getX() * playerSize * MapConstants.getTilesize());
        playerImageView.setY(position.getY() * playerSize * MapConstants.getTilesize());
        switch (direction) {
        case UP:
            playerImageView.setImage(playerUp);
            break;
        case DOWN:
            playerImageView.setImage(playerDown);
            break;
        case RIGHT:
            playerImageView.setImage(playerRight);
            break;
        case LEFT:
            playerImageView.setImage(playerLeft);
            break;
        case NEUTRAL:
        default:
            break;
        }
        if (crouch) {
            playerImageView.setImage(playerCrouchIcon);
        }
    }

    public ImageView getPlayerImageView() {
        return playerImageView;
    }

}
