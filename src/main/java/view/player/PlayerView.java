package view.player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Direction;
import util.Pair;
import util.Vector;
import util.map.MapConstants;
import util.view.Animation;
/**
 * 
 * 
 */
public class PlayerView {

	private final ImageView playerImageView;
	private final Image playerIcon;
	private final Image playerCrawlIcon;
	private final double playerSize;
	private boolean crawl;
	
	private Animation idle;
	private Animation walk;
	private Animation walkup;
	private Animation idleup;
	private Animation idlecrouch;
	
	public PlayerView(final double playerSize) throws FileNotFoundException {
		playerIcon = new Image(new FileInputStream("src\\main\\resources\\pleier.png"));
		playerCrawlIcon = new Image(new FileInputStream("src\\main\\resources\\croulpleier.png"));
		playerImageView = new ImageView(playerIcon);
		playerImageView.setScaleX(playerSize);
		playerImageView.setScaleY(playerSize);
		this.playerSize = playerSize;
		
		this.idle = new Animation("src\\main\\resources\\PlayerIdle.png", new Pair<>(64,64), 3, 20);
		this.walk = new Animation("src\\main\\resources\\PlayerRun.png", new Pair<>(64,64), 7, 10);
		this.walkup = new Animation("src\\main\\resources\\PlayerAimUpRun.png", new Pair<>(64,64), 7, 10);
        this.idleup = new Animation("src\\main\\resources\\PlayerIdleUp.png", new Pair<>(64,64), 3, 20);
        this.idlecrouch = new Animation("src\\main\\resources\\PlayerCrouchIdle.png", new Pair<>(64,64), 3, 20);
	}
	
    public void updatePlayer(final Vector position, final boolean crouch, final Vector speed, final Direction direction) {
    	playerImageView.setX(position.getX()*playerSize*MapConstants.getTilesize());
    	playerImageView.setY(position.getY()*playerSize*MapConstants.getTilesize());
    	switch(direction) {
    	case LEFT:
            if(speed.getX() != 0) {
                playerImageView.setImage(walk.get(false));
                walk.animate();
            } else {
                playerImageView.setImage(idle.get(false));
                idle.animate();
            }
    	    break;
    	case RIGHT:
            if(speed.getX() != 0) {
                playerImageView.setImage(walk.get(true));
                walk.animate();
            } else {
                playerImageView.setImage(idle.get(true));
                idle.animate();
            }
    	    break;
    	case UP:
            if(speed.getX() != 0) {
                playerImageView.setImage(walkup.get(false));
                walkup.animate();
            } else {
                playerImageView.setImage(idleup.get(false));
                idleup.animate();
            }
    	    break;
    	
    	}
    	
    }

	public ImageView getPlayerImageView() {
		return playerImageView;
	}
	
}
