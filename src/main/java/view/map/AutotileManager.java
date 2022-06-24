package view.map;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import model.map.Level;
import util.Vector2D;
import util.map.MapConstants;

/**
 * 
 *
 */
public class AutotileManager {

    private final List<List<Group>> renderedSegments = new LinkedList<>();

    /**
     * 
     * @param segmentList
     * @param level
     * @throws FileNotFoundException
     */
    public AutotileManager(final List<List<Vector2D>> segmentList, final Level level) throws FileNotFoundException {
        final List<Vector2D> toBeTiled = new LinkedList<>();
        for (final var segment : segmentList) {
            toBeTiled.addAll(segment);
        }
        List<Group> tilesInSegment;
        for (final var segment : segmentList) {
            tilesInSegment = new LinkedList<>();
            for (final var tile : segment) {

                tilesInSegment.add(autotile(tile, toBeTiled,
                        new Image(ClassLoader.getSystemResourceAsStream(level.getSegmentAtPosition(tile).getTile(tile).get().getPath()))
                                .getPixelReader()));
            }
            renderedSegments.add(tilesInSegment);
        }
    }

    /**
     * 
     * @param index
     * @return bla
     */
    public List<Group> getSegment(final int index) {
        return renderedSegments.get(index);
    }

    private Group autotile(final Vector2D position, final List<Vector2D> tileList, final PixelReader reader) {
        // First we check adjacent tiles, then corners.

        final ImageView topLeftDef = new ImageView(
                new WritableImage(reader, 0, 0, 1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize()));
        final ImageView topRightDef = new ImageView(new WritableImage(reader, 1 * MapConstants.getTilesize(), 0,
                1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize()));
        final ImageView botRightDef = new ImageView(new WritableImage(reader, 1 * MapConstants.getTilesize(),
                1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize()));
        final ImageView botLeftDef = new ImageView(new WritableImage(reader, 0, 1 * MapConstants.getTilesize(),
                1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize()));

        ImageView topLeftCorner = new ImageView(new WritableImage(reader, 4 * MapConstants.getTilesize(),
                2 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize()));
        ImageView topRightCorner = new ImageView(new WritableImage(reader, 4 * MapConstants.getTilesize(),
                2 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize()));
        ImageView botRightCorner = new ImageView(new WritableImage(reader, 4 * MapConstants.getTilesize(),
                2 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize()));
        ImageView botLeftCorner = new ImageView(new WritableImage(reader, 4 * MapConstants.getTilesize(),
                2 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize()));

        ImageView topLeft = topLeftDef;
        ImageView topRight = topRightDef;
        ImageView botRight = botRightDef;
        ImageView botLeft = botLeftDef;

        for (int j = -1; j < 2; j++) {
            for (int i = -1; i < 2; i++) {
                if (tileList.contains(new Vector2D(position.getX() + j, position.getY() + i))) {
                    // TOP
                    if (i == -1 && j == 0) {
                        if (topLeft.equals(topLeftDef)) {
                            topLeft = new ImageView(new WritableImage(reader, 4 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize()));
                        } else {
                            topLeft = new ImageView(new WritableImage(reader, 3 * MapConstants.getTilesize(),
                                    3 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize()));
                        }
                        if (topRight.equals(topRightDef)) {
                            topRight = new ImageView(new WritableImage(reader, 5 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize()));
                        } else {
                            topRight = new ImageView(new WritableImage(reader, 2 * MapConstants.getTilesize(),
                                    3 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize()));
                        }
                    }
                    // RIGHT
                    if (i == 0 && j == 1) {
                        if (topRight.equals(topRightDef)) {
                            topRight = new ImageView(new WritableImage(reader, 2 * MapConstants.getTilesize(), 0,
                                    1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize()));
                        } else {
                            topRight = new ImageView(new WritableImage(reader, 2 * MapConstants.getTilesize(),
                                    3 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize()));
                        }
                        if (botRight.equals(botRightDef)) {
                            botRight = new ImageView(new WritableImage(reader, 2 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize()));
                        } else {
                            botRight = new ImageView(new WritableImage(reader, 2 * MapConstants.getTilesize(),
                                    2 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize()));
                        }
                    }
                    // BOTTOM
                    if (i == 1 && j == 0) {
                        if (botRight.equals(botRightDef)) {
                            botRight = new ImageView(new WritableImage(reader, 5 * MapConstants.getTilesize(), 0,
                                    1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize()));
                        } else {
                            botRight = new ImageView(new WritableImage(reader, 2 * MapConstants.getTilesize(),
                                    2 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize()));
                        }
                        if (botLeft.equals(botLeftDef)) {
                            botLeft = new ImageView(new WritableImage(reader, 4 * MapConstants.getTilesize(), 0,
                                    1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize()));
                        } else {
                            botLeft = new ImageView(new WritableImage(reader, 3 * MapConstants.getTilesize(),
                                    2 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize()));
                        }
                    }
                    // LEFT
                    if (i == 0 && j == -1) {
                        if (botLeft.equals(botLeftDef)) {
                            botLeft = new ImageView(new WritableImage(reader, 3 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize()));
                        } else {
                            botLeft = new ImageView(new WritableImage(reader, 3 * MapConstants.getTilesize(),
                                    2 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize()));
                        }
                        if (topLeft.equals(topLeftDef)) {
                            topLeft = new ImageView(new WritableImage(reader, 3 * MapConstants.getTilesize(), 0,
                                    1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize()));
                        } else {
                            topLeft = new ImageView(new WritableImage(reader, 3 * MapConstants.getTilesize(),
                                    3 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(),
                                    1 * MapConstants.getTilesize()));
                        }
                    }

                } else {
                    // TOP-LEFT CORNER
                    if (i == -1 && j == -1 && tileList.contains(new Vector2D(position.getX() - 1, position.getY()))
                            && tileList.contains(new Vector2D(position.getX(), position.getY() - 1))) {
                        topLeftCorner = new ImageView(new WritableImage(reader, 1 * MapConstants.getTilesize(),
                                3 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(),
                                1 * MapConstants.getTilesize()));
                    }
                    // TOP-RIGHT CORNER
                    if (i == -1 && j == 1 && tileList.contains(new Vector2D(position.getX() + 1, position.getY()))
                            && tileList.contains(new Vector2D(position.getX(), position.getY() - 1))) {
                        topRightCorner = new ImageView(new WritableImage(reader, 0, 3 * MapConstants.getTilesize(),
                                1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize()));
                    }
                    // BOT-RIGHT CORNER
                    if (i == 1 && j == 1 && tileList.contains(new Vector2D(position.getX() + 1, position.getY()))
                            && tileList.contains(new Vector2D(position.getX(), position.getY() + 1))) {
                        botRightCorner = new ImageView(new WritableImage(reader, 0, 2 * MapConstants.getTilesize(),
                                1 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize()));
                    }
                    // BOT-LEFT CORNER
                    if (i == 1 && j == -1 && tileList.contains(new Vector2D(position.getX() - 1, position.getY()))
                            && tileList.contains(new Vector2D(position.getX(), position.getY() + 1))) {
                        botLeftCorner = new ImageView(new WritableImage(reader, 1 * MapConstants.getTilesize(),
                                2 * MapConstants.getTilesize(), 1 * MapConstants.getTilesize(),
                                1 * MapConstants.getTilesize()));
                    }
                }

            }
        }

        final Vector2D tempPos = new Vector2D(position);

        tempPos.mlt(new Vector2D(MapConstants.getTilesize(), MapConstants.getTilesize()));

        topLeft.setX(tempPos.getX());
        topLeft.setY(tempPos.getY());
        topRight.setX(tempPos.getX());
        topRight.setY(tempPos.getY());
        botRight.setX(tempPos.getX());
        botRight.setY(tempPos.getY());
        botLeft.setX(tempPos.getX());
        botLeft.setY(tempPos.getY());
        topLeftCorner.setX(tempPos.getX());
        topLeftCorner.setY(tempPos.getY());
        topRightCorner.setX(tempPos.getX());
        topRightCorner.setY(tempPos.getY());
        botRightCorner.setX(tempPos.getX());
        botRightCorner.setY(tempPos.getY());
        botLeftCorner.setX(tempPos.getX());
        botLeftCorner.setY(tempPos.getY());

        // create the new image, canvas size is the max. of both image sizes

        return new Group(topLeft, topRight, botRight, botLeft, topLeftCorner, topRightCorner,
                botRightCorner, botLeftCorner);

    }

}
