package controller.menu;

import java.io.IOException;

import javax.management.InstanceNotFoundException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.Pair;
import view.GameView;

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
     * @param event
     * @throws IOException 
     * @throws InstanceNotFoundException 
     * @throws InterruptedException
     */
    @FXML
    public void insertReleased(final Event event) throws IOException, InstanceNotFoundException {
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        final Pair<Double, Double> dim = new Pair<>(stage.getWidth(), stage.getHeight());
        final boolean fs = stage.isFullScreen();
        if (this.isValid(name.getText())) {
            stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/LoadingScene.fxml")));
            final GameView gv = new GameView(name.getText());
            stage.setScene(gv);
            stage.setFullScreen(fs);
            stage.setWidth(dim.getX());
            stage.setHeight(dim.getY());
        }
    }
    /**
     * Executes when the back button is released.
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
