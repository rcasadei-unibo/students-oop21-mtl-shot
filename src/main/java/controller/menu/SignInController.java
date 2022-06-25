package controller.menu;

import java.io.IOException;

import javax.management.InstanceNotFoundException;

import controller.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.sounds.SoundManager;
import view.sounds.SoundManager.Sounds;

/**
 * The controller class for the sign in menu (managed by FXML sheet).
 * 
 */
public class SignInController {

    /**
     * The TextField where the user has to put its username.
     */
    @FXML
    private TextField name;

    /**
     * Executes when the insert button is released.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    public void insertReleased(final Event event) throws IOException, InstanceNotFoundException {
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (this.isValid(name.getText())) {
            new Controller(name.getText(), stage);
        }
        final var sm = new SoundManager();
        sm.stopSound(Sounds.METAL_SHOT_HAHA);
    }

    /**
     * Executes when the back button is released.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    public void backReleased(final Event event) throws IOException {
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml")));
        stage.show();
    }

    private boolean isValid(final String text) {
        return !text.isBlank();
    }

}
