package controller.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import util.UserData;
/**
 * 
 *
 */
public class HUD {
    @FXML
    public Label points;
    public Label health;
    public Label date;
    public BorderPane ac;
    

    public void refresh(final UserData ud) {
        points.setText(ud.getPoints() + "");
        health.setText(ud.getLpLeft() + "/100");
        date.setText(ud.getTime());
    }
    
    public void setSize(final double height, final double width) {
        ac.setPrefSize(width, height);
    }
}
