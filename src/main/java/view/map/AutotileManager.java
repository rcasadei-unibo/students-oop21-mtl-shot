package view.map;

import java.io.FileInputStream;
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

/**
 * 
 *
 */
public class AutotileManager {

	private List<List<Group>> renderedSegments = new LinkedList<>();

	private final double tileSize;

	public AutotileManager(final List<List<Vector2D>> segmentList, final double tileSize, final Level level)
			throws FileNotFoundException {
		this.tileSize = tileSize;
		final List<Vector2D> toBeTiled = new LinkedList<>();
		for (final var segment : segmentList) {
			toBeTiled.addAll(segment);
		}
		List<Group> tilesInSegment = new LinkedList<>();
		for (final var segment : segmentList) {
			tilesInSegment = new LinkedList<>();
			for (final var tile : segment) {
				
				tilesInSegment.add(autotile(tile, toBeTiled,
						new Image(new FileInputStream(level.getSegmentAtPosition(tile).getTile(tile).get().getPath()))
								.getPixelReader()));
			}
			renderedSegments.add(tilesInSegment);
		}
	}

	public List<Group> getSegment(final int index) {
		return renderedSegments.get(index);
	}

	private Group autotile(final Vector2D position, final List<Vector2D> tileList, final PixelReader reader) {
		// First we check adjacent tiles, then corners.

		final ImageView topLeftDef = new ImageView(new WritableImage(reader, 0, 0, 32, 32));
		final ImageView topRightDef = new ImageView(new WritableImage(reader, 32, 0, 32, 32));
		final ImageView botRightDef = new ImageView(new WritableImage(reader, 32, 32, 32, 32));
		final ImageView botLeftDef = new ImageView(new WritableImage(reader, 0, 32, 32, 32));

		ImageView topLeftCorner = new ImageView(new WritableImage(reader, 128, 64, 32, 32));
		ImageView topRightCorner = new ImageView(new WritableImage(reader, 128, 64, 32, 32));
		ImageView botRightCorner = new ImageView(new WritableImage(reader, 128, 64, 32, 32));
		ImageView botLeftCorner = new ImageView(new WritableImage(reader, 128, 64, 32, 32));

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
							topLeft = new ImageView(new WritableImage(reader, 128, 32, 32, 32));
						} else {
							topLeft = new ImageView(new WritableImage(reader, 96, 96, 32, 32));
						}
						if (topRight.equals(topRightDef)) {
							topRight = new ImageView(new WritableImage(reader, 160, 32, 32, 32));
						} else {
							topRight = new ImageView(new WritableImage(reader, 64, 96, 32, 32));
						}
					}
					// RIGHT
					if (i == 0 && j == 1) {
						if (topRight.equals(topRightDef)) {
							topRight = new ImageView(new WritableImage(reader, 64, 0, 32, 32));
						} else {
							topRight = new ImageView(new WritableImage(reader, 64, 96, 32, 32));
						}
						if (botRight.equals(botRightDef)) {
							botRight = new ImageView(new WritableImage(reader, 64, 32, 32, 32));
						} else {
							botRight = new ImageView(new WritableImage(reader, 64, 64, 32, 32));
						}
					}
					// BOTTOM
					if (i == 1 && j == 0) {
						if (botRight.equals(botRightDef)) {
							botRight = new ImageView(new WritableImage(reader, 160, 0, 32, 32));
						} else {
							botRight = new ImageView(new WritableImage(reader, 64, 64, 32, 32));
						}
						if (botLeft.equals(botLeftDef)) {
							botLeft = new ImageView(new WritableImage(reader, 128, 0, 32, 32));
						} else {
							botLeft = new ImageView(new WritableImage(reader, 96, 64, 32, 32));
						}
					}
					// LEFT
					if (i == 0 && j == -1) {
						if (botLeft.equals(botLeftDef)) {
							botLeft = new ImageView(new WritableImage(reader, 96, 32, 32, 32));
						} else {
							botLeft = new ImageView(new WritableImage(reader, 96, 64, 32, 32));
						}
						if (topLeft.equals(topLeftDef)) {
							topLeft = new ImageView(new WritableImage(reader, 96, 0, 32, 32));
						} else {
							topLeft = new ImageView(new WritableImage(reader, 96, 96, 32, 32));
						}
					}

				} else {
					// TOP-LEFT CORNER
					if (i == -1 && j == -1 && tileList.contains(new Vector2D(position.getX() - 1, position.getY()))
							&& tileList.contains(new Vector2D(position.getX(), position.getY() - 1))) {
						topLeftCorner = new ImageView(new WritableImage(reader, 32, 96, 32, 32));
					}
					// TOP-RIGHT CORNER
					if (i == -1 && j == 1 && tileList.contains(new Vector2D(position.getX() + 1, position.getY()))
							&& tileList.contains(new Vector2D(position.getX(), position.getY() - 1))) {
						topRightCorner = new ImageView(new WritableImage(reader, 0, 96, 32, 32));
					}
					// BOT-RIGHT CORNER
					if (i == 1 && j == 1 && tileList.contains(new Vector2D(position.getX() + 1, position.getY()))
							&& tileList.contains(new Vector2D(position.getX(), position.getY() + 1))) {
						botRightCorner = new ImageView(new WritableImage(reader, 0, 64, 32, 32));
					}
					// BOT-LEFT CORNER
					if (i == 1 && j == -1 && tileList.contains(new Vector2D(position.getX() - 1, position.getY()))
							&& tileList.contains(new Vector2D(position.getX(), position.getY() + 1))) {
						botLeftCorner = new ImageView(new WritableImage(reader, 32, 64, 32, 32));
					}
				}

			}
		}

		final Vector2D tempPos = new Vector2D(position);

		tempPos.mlt(new Vector2D(this.tileSize, this.tileSize));

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

		final Group result = new Group(topLeft, topRight, botRight, botLeft, topLeftCorner, topRightCorner,
				botRightCorner, botLeftCorner);

		//result.setScaleX(this.tileSize);
		//result.setScaleY(this.tileSize);
		return result;
	}

}
