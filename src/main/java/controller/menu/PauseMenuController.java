package controller.menu;

import java.io.IOException;
import java.util.Optional;

import javax.management.InstanceNotFoundException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.Pair;
import view.GameView;

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
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OptionsMenu.fxml"));
        stage.getScene().setRoot(loader.load());
        ((OptionsMenuController) loader.getController()).setGameView(Optional.of(this.gameView));
        stage.show();
    }

    @FXML
    void quitReleased(final MouseEvent event) throws IOException {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml")));
    }

    @FXML
    void restartReleased(final MouseEvent event) throws IOException, InstanceNotFoundException {
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        final Pair<Double, Double> dim = new Pair<>(stage.getWidth(), stage.getHeight());
        final boolean fs = stage.isFullScreen();
        stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/LoadingScene.fxml")));
        final GameView gv = new GameView(this.gameView.getUserData().getName());
        stage.setScene(gv);
        stage.setFullScreen(fs);
        stage.setWidth(dim.getX());
        stage.setHeight(dim.getY());
    }

    @FXML
    void resumeReleased(final MouseEvent event) throws IOException {
        this.gameView.disposePauseMenu();
    }

    /**
     * Sets the size of the options menu panel.
     * @param width
     * @param height
     */
    public void setSize(final double width, final double height) {
        pane.setPrefSize(width, height);
    }

    /**
     * Sets the reference to the GameView.
     * @param gameView
     */
    public void setGameView(final GameView gameView) {
        this.gameView = gameView;
    }
}
