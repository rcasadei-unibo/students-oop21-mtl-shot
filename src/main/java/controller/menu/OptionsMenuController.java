package controller.menu;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.GameView;

/**
 * 
 * 
 */
public class OptionsMenuController {

    @FXML
    void backReleased(final MouseEvent event) throws IOException {
        if (((Node) event.getSource()).getScene().getWindow() instanceof GameView) {
            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/PauseMenu.fxml")));
            stage.show();
        } else {
            final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml")));
            stage.show();
        }
    }

    @FXML
    void fullScreenReleased(final MouseEvent event) {
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
            stage.setWidth(800);
            stage.setHeight(600);
        } else {
            stage.setFullScreen(true);
        }
    }
}
