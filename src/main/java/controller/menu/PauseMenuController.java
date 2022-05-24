package controller.menu;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.GameView;

/**
 * 
 *
 */
public class PauseMenuController {

    @FXML
    void optionReleased(final MouseEvent event) {
        //show option scene
    }

    @FXML
    void quitReleased(final MouseEvent event) throws IOException {
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml")));
    }

    @FXML
    void restartReleased(final MouseEvent event) throws IOException {
        new GameView((Stage) ((Node) event.getSource()).getScene().getWindow(), "");
    }

    @FXML
    void resumeReleased(final MouseEvent event) throws IOException {
        System.out.println("entrato");
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.println(((GridPane) stage.getScene().getRoot()).getChildren().remove(FXMLLoader.load(getClass().getResource("/fxml/PauseMenu.fxml"))));
    }
}
