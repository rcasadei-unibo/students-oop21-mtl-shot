package view.map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import model.map.Level;
import util.Vector2D;
import util.map.MapConstants;
/**
 * 
 * @author filippo.gurioli
 *
 */
public class LevelView {

	private final Level level;
	private final double tileSize;

	public LevelView(final Level level, final double tileSize) {
		this.level = level;
		this.tileSize = tileSize;
	}
	
    public List<Node> displaySegments(final Vector2D playerPosition) {
    	final List<Node> nodes = new LinkedList<>();
		final List<Vector2D> list = new LinkedList<>();
		list.addAll(this.level.getSegmentAtPosition(playerPosition).getTileables());
		if(this.level.getSegmentAtPositionOffset(playerPosition, 1).isPresent()) {
			list.addAll(this.level.getSegmentAtPositionOffset(playerPosition, 1).get().getTileables());			
		}
		AutotileManager autotileManager = null;
		try {
			autotileManager = new AutotileManager(list);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (final var position : list) {
			//Group tileImage = null; // Will never be null, map is composed of solely PASSABLE or NON-PASSABLE tiles,
									// and as such will never evade the if construct.

			Group tileImage = null;
			try {
				tileImage = autotileManager.autotile(position, this.tileSize*MapConstants.getTilesize(),
						new Image(new FileInputStream(this.level.getSegmentAtPosition(position).getTile(position).get().getPath())).getPixelReader());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			tileImage.setScaleX(this.tileSize);
			tileImage.setScaleY(this.tileSize);
			nodes.add(tileImage);
				
		}
		return nodes;
    }
}
