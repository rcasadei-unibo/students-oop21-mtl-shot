package view.map;

import controller.Controller;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.util.Duration;
import util.Pair;
import util.Vector2D;
import util.map.MapConstants;
import view.GameView;

/**
 * Handles camera movement and resizing of viewable space.
 */
public class CameraManager {

	private final Controller controller;
	private Vector2D prevPosSegment;
	private final Group root;
	private final LevelView levelView;
	private final GameView gameView;
	private double offset = 0;
	private double cameraScaleFactorX;
	private double cameraScaleFactorY;
	private Camera camera = new PerspectiveCamera();
	private static final double HORIZONTALDEFAULT = 1920;
	private static final double VERTICALDEFAULT = 1080;

	/**
	 * Camera manager constructor.
	 * @param controller
	 * @param root
	 * @param levelView
	 * @param gameView
	 */
	public CameraManager(final Controller controller, final Group root, final LevelView levelView, final GameView gameView) {
		this.controller = controller;
		this.prevPosSegment = new Vector2D(controller.getStage().getPlayer().getPosition());
		this.root = root;
		this.levelView = levelView;
		this.gameView = gameView;
	}
	
	/**
	 * Updates the camera's parameters based on the game's state.
	 */
	public void updateCamera() {
		double adjust;
		cameraScaleFactorX = HORIZONTALDEFAULT / gameView.getWidth();
		cameraScaleFactorY = VERTICALDEFAULT / gameView.getHeight();

		if (cameraScaleFactorX <= cameraScaleFactorY) {
			adjust = HORIZONTALDEFAULT;
			this.camera.setScaleX(cameraScaleFactorX);
			this.camera.setScaleY(cameraScaleFactorX);
		} else {
			adjust = gameView.getWidth() * cameraScaleFactorY;
			this.camera.setScaleX(cameraScaleFactorY);
			this.camera.setScaleY(cameraScaleFactorY);
		}
		if (!controller.getStage().getLevel().getSegmentAtPosition(controller.getStage().getPlayer().getPosition())
				.equals(controller.getStage().getLevel().getSegmentAtPosition(prevPosSegment))) {
			prevPosSegment = new Vector2D(controller.getStage().getPlayer().getPosition());
			this.root.getChildren().removeAll(levelView.getDisplayed());
			this.root.getChildren().addAll(levelView.displaySegments(controller.getStage().getPlayer().getPosition()));
			final TranslateTransition tt = new TranslateTransition(Duration.millis(1000), this.root);
			tt.setToX(-(new Vector2D(controller.getStage().getLevel()
					.getSegmentAtPosition(controller.getStage().getPlayer().getPosition()).getOrigin()).getX())
					* MapConstants.getTilesize());
			final ParallelTransition pt = new ParallelTransition();
			pt.getChildren().add(tt);
			pt.play();
			pt.setOnFinished(new EventHandler<ActionEvent>() {

				@Override
				public void handle(final ActionEvent event) {
					root.getChildren()
							.removeAll(levelView.getPreviousSegment(controller.getStage().getPlayer().getPosition()));
				}
			});
			offset = controller.getStage().getLevel()
					.getSegmentAtPosition(controller.getStage().getPlayer().getPosition()).getOrigin().getX();

			controller.refreshEnemiesStatus();
		}

		if (controller.getStage().getPlayer().getSpeed().getX() > 0
				&& controller.getStage().getPlayer().getPosition().getX() > 4 + offset
				&& (adjust / MapConstants.getTilesize()) + offset
						- controller.getStage().getLevel().getDistance(controller.getStage().getLevel()
								.getSegmentAtPosition(controller.getStage().getPlayer().getPosition())) < 0) {
			final TranslateTransition tt = new TranslateTransition(Duration.millis(1), this.root);
			tt.setToX(-(((offset + controller.getStage().getPlayer().getSpeed().getX()) * MapConstants.getTilesize())));
			final ParallelTransition pt = new ParallelTransition();
			pt.getChildren().add(tt);
			pt.play();
			offset += controller.getStage().getPlayer().getSpeed().getX();
		}
	}

	/**
	 * Returns the left and right bounds of the area the player can traverse.
	 * @return the left and right bound of the traversable area as a Pair.
	 */
	public Pair<Double, Double> getBounds() {
		return new Pair<Double, Double>(offset, controller.getStage().getLevel().getDistance(controller.getStage()
				.getLevel().getSegmentAtPosition(controller.getStage().getPlayer().getPosition())));
	}
	
	/**
	 * Returns the space in Game Units traversed by the camera when following the player, is zero when changing segment.
	 * @return the offset value.
	 */
	public double getOffset() {
		return this.offset;
	}
	
	/**
	 * Returns the camera managed by the CameraManager.
	 * @return the camera object.
	 */
	public Camera getCamera() {
		return this.camera;
	}
	
	/**
	 * Resets the camera's scale for correct menù handling.
	 */
	public void resetCamera() {
		this.camera.setScaleX(1);
		this.camera.setScaleY(1);
	}

	/**
	 * Returns the scale factors.
	 * @return the scale factors as a Pair.
	 */
	public Pair<Double, Double> getScaleFactors() {
		return new Pair<Double, Double>(this.cameraScaleFactorX, this.cameraScaleFactorY);
	}
}
