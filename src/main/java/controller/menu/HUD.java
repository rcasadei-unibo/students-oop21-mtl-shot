package controller.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import util.UserData;

/**
 * The controller class for the in game HUD (Heads Up Display, managed by FXML sheet).
 *
 */
public class HUD {
    @FXML
    private Label points;
    @FXML
    private Label health;
    @FXML
    private Label date;
    @FXML
    private BorderPane bp;

    /**
     * Modifies the Label with the values form UserData.
     * @param ud
     */
    public void refresh(final UserData ud) {
        points.setText(String.valueOf(ud.getPoints()));
        health.setText(ud.getLpLeft() + "/100");
        date.setText(ud.getTime());
    }

    /**
     * Sets the size of the BorderPane.
     * @param height
     * @param width
     */
    public void setSize(final double height, final double width) {
        bp.setPrefSize(width, height);
    }
}
