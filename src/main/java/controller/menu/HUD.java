package controller.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HUD {
    @FXML
    public Label points;
    
    public void refresh(final String s) {
        points.setText(s);
    }
}
