package controller.menu;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.GameView;

/**
 * The controller class for the sign in menu (managed by FXML sheet).
 * 
 */
public class SignInController {
    @FXML
    public TextField name;

    /**
     * Executes when the insert button is released.
     * @param event
     * @throws IOException 
     * @throws Throwable 
     */
    @FXML
    public void insertReleased(final MouseEvent event) throws IOException {
        if (this.isValid(name.getText())) {
            new GameView(name.getText());
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }
    }
    /**
     * Executes when the back button is released.
     * @param event
     * @throws IOException
     */
    @FXML
    public void backReleased(final MouseEvent event) throws IOException {
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml")));
        stage.show();
    }

    private boolean isValid(final String text) {
        return !text.isBlank();
    }
}
