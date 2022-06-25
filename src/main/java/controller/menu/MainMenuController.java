package controller.menu;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * The controller class for the main menu (managed by FXML sheet).
 * 
 */
public class MainMenuController {

    /**
     * Executes when the start button is released.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    public void startReleased(final Event event) throws IOException {
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/SignIn.fxml")));
        stage.show();
    }

    /**
     * Executes when the close button is released.
     * 
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    public void closeReleased(final Event event) throws FileNotFoundException {
        Platform.exit();
    }

    /**
     * Executes when the option button is released.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    public void optionReleased(final MouseEvent event) throws IOException {
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/SettingsMenu.fxml")));
        stage.show();
    }
}
