package controller.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.UserData;
/**
 * 
 *
 */
public class HUD {
    @FXML
    public Label points;
    public Label health;
    

    public void refresh(final UserData ud) {
        points.setText(ud.getPoints() + "");
        health.setText(ud.getLpLeft() + "/100");
    }
}
