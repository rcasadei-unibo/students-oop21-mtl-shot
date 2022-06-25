package controller.menu;

import java.io.IOException;
import java.util.Optional;

import javax.management.InstanceNotFoundException;

import controller.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.GameView;
import view.sounds.SoundManager;
import view.sounds.SoundManager.Sounds;

/**
 * The controller class for the in game pause menu (managed by FXML sheet).
 *
 */
public class PauseMenuController {

    private GameView gameView;

    @FXML
    private GridPane pane;

    @FXML
    void optionReleased(final MouseEvent event) throws IOException {
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SettingsMenu.fxml"));
        stage.getScene().setRoot(loader.load());
        ((OptionsMenuController) loader.getController()).setGameView(Optional.of(this.gameView));
        stage.show();
    }

    @FXML
    void quitReleased(final Event event) throws IOException {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).getScene()
                .setRoot(FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml")));
        new SoundManager().playSound(Sounds.METAL_SHOT_HAHA);
    }

    @FXML
    void restartReleased(final MouseEvent event) throws IOException, InstanceNotFoundException {
        new Controller(this.gameView.getController().getUserData().getName(), (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void resumeReleased(final Event event) throws IOException {
        this.gameView.disposePauseMenu();
    }

    /**
     * Sets the size of the options menu panel.
     * 
     * @param width
     * @param height
     */
    public void setSize(final double width, final double height) {
        pane.setPrefSize(width, height);
    }

    /**
     * Sets the reference to the GameView.
     * 
     * @param gameView
     */
    public void setGameView(final GameView gameView) {
        this.gameView = gameView;
    }
}
