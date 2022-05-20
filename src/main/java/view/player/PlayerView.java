package view.player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Direction;
import util.Vector2D;
import util.map.MapConstants;
import view.CharacterView;

/**
 * 
 *
 */
public class PlayerView extends CharacterView{
    /**
     * 
     * @param playerSize
     * @throws FileNotFoundException
     */
    public PlayerView(final double playerSize) {
        super(playerSize);
    }

}
