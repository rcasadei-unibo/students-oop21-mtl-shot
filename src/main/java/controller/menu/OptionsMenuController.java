package controller.menu;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.Pair;
import view.GameView;

/**
 * The controller class for the Options menu (managed by FXML sheet).
 * 
 */
public class OptionsMenuController {

    private Optional<GameView> gameView;
    private static final Pair<Integer, Integer> PREFSIZE = new Pair<>(800, 600);

    @FXML
    private BorderPane pane;

    @FXML
    void backReleased(final MouseEvent event) throws IOException {
        if (gameView.isPresent()) {
            gameView.get().displayPauseMenu();
        } else {
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).getScene()
                    .setRoot(FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml")));
        }
    }

    @FXML
    void fullScreenReleased(final MouseEvent event) {
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
            stage.setWidth(PREFSIZE.getX());
            stage.setHeight(PREFSIZE.getY());
        } else {
            stage.setFullScreen(true);
        }
    }

    /**
     * Sets the reference to the possible GameView (the options pane could be
     * displayed both in MainMenu and in PauseMenu).
     * 
     * @param gameView
     */
    public void setGameView(final Optional<GameView> gameView) {
        this.gameView = gameView;
    }
}
