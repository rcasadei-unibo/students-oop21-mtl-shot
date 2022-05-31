package view;

import javafx.scene.Camera;
import util.Vector2D;
import util.map.MapConstants;

public class CameraManager {
	private final Camera camera;
	private double offset = -16;
	private double smooth = 16;
	//private final GameView viewReference;
	private Vector2D target;
	
	public CameraManager(final GameView viewReference, final Camera camera) {
		//this.viewReference = viewReference;
		this.camera = camera;
		this.camera.setScaleX(0.75);
		this.camera.setScaleY(0.75);
		this.target = new Vector2D(viewReference.getController().getStage().getLevel().getSegmentAtPosition(viewReference.getController().getStage().getPlayer().getPosition()).getCenter());
	}
	
	public void updateCamera() {
		final double compOffset = offset * GameView.VIEWRESIZE * MapConstants.getTilesize() / smooth;
		camera.setTranslateX(camera.getTranslateX() + (((target.getX() * GameView.VIEWRESIZE * MapConstants.getTilesize()) - camera.getTranslateX()) / smooth) + compOffset);
	}
	
	public void setTarget(final Vector2D target) {
		this.target = target;
	}
}
