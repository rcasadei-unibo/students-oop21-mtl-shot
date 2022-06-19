package view.map;

import controller.Controller;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.util.Duration;
import util.Vector2D;
import util.map.MapConstants;

public class CameraManager {

	private final Camera camera;
	private final Controller controller;
	private Vector2D prevPosSegment;
	private final Group root;
	private final Group cameraSupport;
	private final LevelView levelView;
	
	public CameraManager(final Controller controller, final Group root, final LevelView levelView) {
		this.controller = controller;
		this.prevPosSegment = new Vector2D(controller.getStage().getPlayer().getPosition());
		this.root = root;
		this.camera = new PerspectiveCamera();
		this.cameraSupport = new Group(camera);
		this.levelView = levelView;
	}
	
	public void updateCamera() {
		if (!controller.getStage().getLevel().getSegmentAtPosition(controller.getStage().getPlayer().getPosition()).equals(controller.getStage().getLevel().getSegmentAtPosition(prevPosSegment))) {
			prevPosSegment = new Vector2D(controller.getStage().getPlayer().getPosition());
			this.root.getChildren().removeAll(levelView.getDisplayed());
			this.root.getChildren().addAll(levelView.displaySegments(controller.getStage().getPlayer().getPosition()));
			TranslateTransition tt = new TranslateTransition(Duration.millis(1000), this.cameraSupport);
			tt.setToX((new Vector2D(controller.getStage().getLevel().getSegmentAtPosition(controller.getStage().getPlayer().getPosition()).getOrigin()).getX()) * MapConstants.getTilesize() - camera.getTranslateX() );
			ParallelTransition pt = new ParallelTransition();
			pt.getChildren().add(tt);
			pt.play();
		}
		if(controller.getStage().getPlayer().getSpeed().getX() > 0 
				&& controller.getStage().getPlayer().getPosition().getX()> 4 + controller.getStage().getLevel().getDistance(controller.getStage().getLevel().getSegmentAtPosition(controller.getStage().getPlayer().getPosition())) - controller.getStage().getLevel().getSegmentAtPosition(controller.getStage().getPlayer().getPosition()).getTextMap().getWidth()
				&& controller.getStage().getLevel().getDistance(controller.getStage().getLevel().getSegmentAtPosition(controller.getStage().getPlayer().getPosition())) - controller.getStage().getPlayer().getPosition().getX() > 26) {
			camera.setTranslateX(camera.getTranslateX() + (controller.getStage().getPlayer().getSpeed().getX()*MapConstants.getTilesize()));
		}
	}
	
	public Camera getCamera() {
		return this.camera;
	}
	
}
