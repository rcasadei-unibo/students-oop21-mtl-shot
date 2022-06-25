package model.map;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.management.InstanceNotFoundException;

import util.Vector2D;
import util.map.TextMap;

/**
 * 
 * Defines a Level made up of Segments.
 */
public class Level {

    private final List<Segment> segments = new LinkedList<>();

    /**
     * 
     * @param segmentTextList
     * @throws IOException
     */
    public Level(final List<String> segmentTextList) throws IOException {
        double i = 0;
        for (final String segmentText : segmentTextList) {
            final TextMap tempMap = new TextMap(segmentText);
            segments.add(new Segment(tempMap, i));
            i += tempMap.getWidth();
        }
    }

    /**
     * Returns the Segments that make up the level.
     * @return the Segments that make up the level.
     */
    public List<Segment> getSegments() {
        return this.segments;
    }

    /**
     * Returns the Segment on which the coordinates land.
     * @param position
     * @return the Segment on which the coordinates land.
     */
    public Segment getSegmentAtPosition(final Vector2D position) {
        double i = 0;
        for (final Segment tempSegment : this.getSegments()) {
            if (position.getX() >= i && position.getX() < i + tempSegment.getTextMap().getWidth()) {
                return tempSegment;
            }
            i += tempSegment.getTextMap().getWidth();
        }
        throw new IllegalArgumentException();
    }

    /**
     * Returns the nth Segment after the Segment on which the coordinates land.
     * @param position
     * @param offset
     * @return the nth Segment after the Segment on which the coordinates land.
     */
    public Optional<Segment> getSegmentAtPositionOffset(final Vector2D position, final int offset) {
        if (this.getSegments().indexOf(this.getSegmentAtPosition(position)) + offset >= this.getSegments().size()
                || this.getSegments().indexOf(this.getSegmentAtPosition(position)) + offset < 0) {
            return Optional.empty();
        }
        return Optional
                .of(this.getSegments().get(this.getSegments().indexOf(this.getSegmentAtPosition(position)) + offset));
    }

    /**
     * Returns the Player's spawn position.
     * @return the Player's spawn.
     * @throws InstanceNotFoundException
     */
    public Vector2D getPlayerSpawn() throws InstanceNotFoundException {
        for (final Segment tempSegment : this.getSegments()) {
            if (tempSegment.getPlayerSpawn() != null) {
                return tempSegment.getPlayerSpawn();
            }
        }
        throw new InstanceNotFoundException();
    }

    /**
     * The distance from the rightmost edge of the current segment and the leftmost edge of the map.
     * @param target
     * @return the distance of the rightmost edge of the current segment.
     */
    public double getDistance(final Segment target) {
        double dist = 0;
        for (final Segment temp : this.getSegments()) {
            dist += temp.getTextMap().getWidth();
            if (temp.equals(target)) {
                return dist;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * A collection containing the Enemies' spawn positions.
     * @return the Enemies' spawn positions.
     */
    public Collection<Vector2D> getEnemiesSpawn() {
        final Collection<Vector2D> positions = new LinkedList<>();
        for (final Segment tempSegment : this.getSegments()) {
            positions.addAll(tempSegment.getEnemiesSpawn());
        }
        return positions;
    }
}
