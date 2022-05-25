package controller.menu;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.GameView;

/**
 * 
 * 
 */
public class OptionsMenuController {

    private Optional<GameView> gameView;

    @FXML
    public BorderPane pane;

    @FXML
    void backReleased(final MouseEvent event) throws IOException {
        if (gameView.isPresent()) {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PauseMenu.fxml"));
            final Group group = new Group(gameView.get().getGroup());
            group.getChildren().add(loader.load());
            final PauseMenuController pmc = (PauseMenuController) loader.getController();
            pmc.setSize(pane.getWidth(), pane.getHeight());
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).getScene().setRoot(group);
            pmc.setGameView(this.gameView.get());
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
            stage.setWidth(800);
            stage.setHeight(600);
        } else {
            stage.setFullScreen(true);
        }
    }

    public void setGameView(final Optional<GameView> gameView) {
        this.gameView = gameView;

    }
}
