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
 * 
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
     * 
     * @return bla
     */
    public List<Segment> getSegments() {
        return this.segments;
    }

    /**
     * 
     * @param position
     * @return bla
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
     * 
     * @param position
     * @param offset
     * @return bla
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
     * 
     * @return bla
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
     * 
     * @param target
     * @return bla
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
     * 
     * @return bla
     */
    public Collection<Vector2D> getEnemiesSpawn() {
        final Collection<Vector2D> positions = new LinkedList<>();
        for (final Segment tempSegment : this.getSegments()) {
            positions.addAll(tempSegment.getEnemiesSpawn());
        }
        return positions;
    }
}
