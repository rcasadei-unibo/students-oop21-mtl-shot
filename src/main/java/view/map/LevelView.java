package view.map;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.Group;
import model.map.Level;
import util.Vector2D;

 /**
 * 
 * Implements the View side of the Level.
 */
public class LevelView {

    private final Level level;
    private List<Group> displayed;
    private final AutotileManager atManager;

    /**
     * 
     * @param level
     * @throws FileNotFoundException
     */
    public LevelView(final Level level) throws FileNotFoundException {
        this.level = level;
        final List<List<Vector2D>> segments = new LinkedList<>();
        List<Vector2D> tiles;
        for (int i = 0; i < level.getSegments().size(); i++) {
            tiles = new LinkedList<>();
            for (int j = 0; j < level.getSegments().get(i).getTileables().size(); j++) {
                tiles.add(level.getSegments().get(i).getTileables().get(j));
            }
            segments.add(tiles);
        }
        this.atManager = new AutotileManager(segments, level);
    }

    /**
     * Displays only the visible Segments for performance.
     * @param playerPosition
     * @return a list of Groups, each is all the ImageViews from a Segment.
     */
    public List<Group> displaySegments(final Vector2D playerPosition) {
        final List<Group> nodes = new LinkedList<>();
        nodes.addAll(atManager.getSegment(level.getSegments().indexOf(level.getSegmentAtPosition(playerPosition))));
        if (level.getSegmentAtPositionOffset(playerPosition, 1).isPresent()) {
            nodes.addAll(atManager.getSegment(
                    level.getSegments().indexOf(level.getSegmentAtPositionOffset(playerPosition, 1).get())));
        }
        if (level.getSegmentAtPositionOffset(playerPosition, -1).isPresent()) {
            nodes.addAll(atManager.getSegment(
                    level.getSegments().indexOf(level.getSegmentAtPositionOffset(playerPosition, -1).get())));
        }
        displayed = nodes;
        return nodes;
    }

    /**
     * Returns the currently displayed Segments' Tile ImageViews, bundled in a List of Groups.
     * @return the displayed Segments' Tile ImageViews.
     */
    public List<Group> getDisplayed() {
        return displayed;
    }
     /**
     * Returns the Segment before the one the player is traversing's Tile ImageViews, bundled in a List of Groups.
     * @param playerPosition
     * @return the Segment before the one the player is traversing's Tile ImageViews.
     */
    public List<Group> getPreviousSegment(final Vector2D playerPosition) {
        return atManager.getSegment(
                level.getSegments().indexOf(level.getSegmentAtPositionOffset(playerPosition, -1).get()));
    }

}
