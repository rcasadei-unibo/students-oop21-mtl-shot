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

public class CameraManager {

	private final Controller controller;
	private Vector2D prevPosSegment;
	private final Group root;
	private final LevelView levelView;
	private final GameView gameView;
	private double offset = 0;
	double cameraScaleFactorX;
	double cameraScaleFactorY;
	private Camera camera = new PerspectiveCamera();

	public CameraManager(final Controller controller, final Group root, final LevelView levelView, GameView gameView) {
		this.controller = controller;
		this.prevPosSegment = new Vector2D(controller.getStage().getPlayer().getPosition());
		this.root = root;
		this.levelView = levelView;
		this.gameView = gameView;
	}

	public void updateCamera() {
		double adjust;
		cameraScaleFactorX = 1920 / gameView.getWidth();
		cameraScaleFactorY = 1080 / gameView.getHeight();

		if (cameraScaleFactorX <= cameraScaleFactorY) {
			adjust = 1920;
			this.camera.setScaleX(cameraScaleFactorX);
			this.camera.setScaleY(cameraScaleFactorX);
		} else {
			adjust = gameView.getWidth() * cameraScaleFactorY;
			this.camera.setScaleX(cameraScaleFactorY);
			this.camera.setScaleY(cameraScaleFactorY);
		}
		System.out.println(cameraScaleFactorX <= cameraScaleFactorY);
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
				public void handle(ActionEvent event) {
					root.getChildren()
							.removeAll(levelView.getPreviousSegment(controller.getStage().getPlayer().getPosition()));
				}
			});
			offset = controller.getStage().getLevel()
					.getSegmentAtPosition(controller.getStage().getPlayer().getPosition()).getOrigin().getX();

			controller.refreshEnemiesStatus();
			controller.removeOldEnemies();
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
		System.out.println(adjust/MapConstants.getTilesize());
	}

	public Pair<Double, Double> getBounds() {
		return new Pair<Double, Double>(offset, controller.getStage().getLevel().getDistance(controller.getStage()
				.getLevel().getSegmentAtPosition(controller.getStage().getPlayer().getPosition())));
	}

	public double getOffset() {
		return this.offset;
	}

	public Camera getCamera() {
		return this.camera;
	}

	public void resetCamera() {
		this.camera.setScaleX(1);
		this.camera.setScaleY(1);
	}

	public Pair<Double, Double> getScaleFactors() {
		return new Pair<Double, Double>(this.cameraScaleFactorX, this.cameraScaleFactorY);
	}
}
