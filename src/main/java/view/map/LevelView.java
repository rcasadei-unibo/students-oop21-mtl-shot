package view.map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	private List<Group> displayed;
	private final AutotileManager atManager;

	public LevelView(final Level level, final double tileSize) throws FileNotFoundException {
		this.level = level;
		this.tileSize = tileSize;
		final List<List<Vector2D>> segments = new LinkedList<>();
		List<Vector2D> tiles = new LinkedList<>();
		for(int i = 0; i < level.getSegments().size(); i++) {
			tiles = new LinkedList<>();
			for(int j = 0; j < level.getSegments().get(i).getTileables().size(); j++) {
				tiles.add(level.getSegments().get(i).getTileables().get(j));
			}
			segments.add(tiles);
		}
		this.atManager = new AutotileManager(segments, this.tileSize * MapConstants.getTilesize(), level);	
	}

	public List<Group> displaySegments(final Vector2D playerPosition) {
		final List<Group> nodes = new LinkedList<>();
		nodes.addAll(atManager.getSegment(level.getSegments().indexOf(level.getSegmentAtPosition(playerPosition))));
		if(level.getSegmentAtPositionOffset(playerPosition, 1).isPresent()) {
			nodes.addAll(atManager.getSegment(level.getSegments().indexOf(level.getSegmentAtPositionOffset(playerPosition, 1).get())));			
		}
		if(level.getSegmentAtPositionOffset(playerPosition, -1).isPresent()) {			
			nodes.addAll(atManager.getSegment(level.getSegments().indexOf(level.getSegmentAtPositionOffset(playerPosition, -1).get())));
		}
		displayed = nodes;
		return nodes;
	}
	
	public List<Group> getDisplayed(){
		return displayed;
	}
	
}
