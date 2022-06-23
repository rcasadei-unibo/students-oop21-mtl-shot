package view.map;

import controller.Controller;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;
import util.Pair;
import util.Vector2D;
import util.map.MapConstants;

public class CameraManager {

	private final Controller controller;
	private Vector2D prevPosSegment;
	private final Group root;
	private final LevelView levelView;
	private double offset = 0;

	public CameraManager(final Controller controller, final Group root, final LevelView levelView) {
		this.controller = controller;
		this.prevPosSegment = new Vector2D(controller.getStage().getPlayer().getPosition());
		this.root = root;
		this.levelView = levelView;
	}

	public void updateCamera() {

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
			offset = controller.getStage().getLevel()
					.getSegmentAtPosition(controller.getStage().getPlayer().getPosition()).getOrigin().getX();

			controller.refreshEnemiesStatus();
			controller.removeOldEnemies();
		}

		if (controller.getStage().getPlayer().getSpeed().getX() > 0
				&& controller.getStage().getPlayer().getPosition().getX() > 4 + offset
				&& controller.getStage().getLevel()
						.getDistance(controller.getStage().getLevel()
								.getSegmentAtPosition(controller.getStage().getPlayer().getPosition()))
						- controller.getStage().getPlayer().getPosition().getX() > 26) {

			final TranslateTransition tt = new TranslateTransition(Duration.millis(1), this.root);
			tt.setToX(-(((offset + controller.getStage().getPlayer().getSpeed().getX()) * MapConstants.getTilesize())));
			final ParallelTransition pt = new ParallelTransition();
			pt.getChildren().add(tt);
			pt.play();
			offset += controller.getStage().getPlayer().getSpeed().getX();
		}
	}

	public Pair<Double, Double> getBounds() {
		return new Pair<Double, Double>(offset, controller.getStage().getLevel().getDistance(controller.getStage()
				.getLevel().getSegmentAtPosition(controller.getStage().getPlayer().getPosition())));
	}

	public double getOffset() {
		return this.offset;
	}
}
