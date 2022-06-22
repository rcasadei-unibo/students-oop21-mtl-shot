package controller.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import util.UserData;
import util.Vector2D;
import util.map.MapConstants;

/**
 * The controller class for the win menu (managed by FXML sheet).
 */
public class WinMenuController {

	@FXML
	private BorderPane pane;
	@FXML
	private Label totalPoints;
	@FXML
	private Label remeaningTime;
	@FXML
	private Label remeaningLives;
	/**
	 * Sets the size of the options menu panel.
	 * 
	 * @param width
	 * @param height
	 */
	public void setSize(final double width, final double height) {
		pane.setPrefSize(width, height);
	}
	
	public void setInfoToDisplay(final UserData userData, final int lives) {
		this.remeaningLives.setText("Remeaning lives: " + lives);
		this.remeaningTime.setText("Remeaning time: " + userData.getTime());
		this.totalPoints.setText("Total points: " + userData.getPoints());
	}

	public void setShift(final Vector2D origin) {
		pane.setTranslateX(MapConstants.getTilesize() * origin.getX());
		System.out.println(origin.getX());
	}
}
