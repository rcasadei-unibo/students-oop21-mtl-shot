package view.map;

import java.awt.Image;
import java.util.List;

import javafx.scene.image.WritableImage;
import model.map.tile.Tile;

public class AutotileManager {

    private List<Tile> tileList;
    private Image tileset;
    
    public AutotileManager(List<Tile> tileList, Image tileset) {
	this.tileList = tileList;
	this.tileset = tileset;
    }
    
    public WritableImage autotile() {
	return null;
    }
    
}
