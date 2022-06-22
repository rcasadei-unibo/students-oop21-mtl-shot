package controller.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.UserData;
/**
 * 
 *
 */
public class HUD {
    @FXML
    public Label points;
    public Label health;
    public AnchorPane ac;
    

    public void refresh(final UserData ud) {
        points.setText(ud.getPoints() + "");
        health.setText(ud.getLpLeft() + "/100");
    }
    
    public void setSize(final double height, final double width) {
        ac.setPrefSize(width, height);
    }
}
