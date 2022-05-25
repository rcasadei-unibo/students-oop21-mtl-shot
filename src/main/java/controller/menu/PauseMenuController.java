package controller.menu;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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
    public GridPane pane;

    @FXML
    void optionReleased(final MouseEvent event) throws IOException {
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/OptionsMenu.fxml")));
    }

    @FXML
    void quitReleased(final MouseEvent event) throws IOException {
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        final Stage s = new Stage();
        s.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"))));
        s.setWidth(stage.getWidth());
        s.setHeight(stage.getHeight());
        s.setFullScreen(stage.isFullScreen());
        s.setFullScreenExitHint("");
        s.setTitle(stage.getTitle());
        s.initStyle(stage.getStyle());
        s.show();
        stage.close();
    }

    @FXML
    void restartReleased(final MouseEvent event) throws IOException {
        final GameView gv = (GameView) ((Node) event.getSource()).getScene().getWindow();
        final GameView gv1 = new GameView(gv.getUserData().getName());
        gv1.setWidth(gv.getWidth());
        gv1.setHeight(gv.getHeight());
        gv1.setFullScreen(gv.isFullScreen());
        gv1.setFullScreenExitHint("");
        gv1.setTitle(gv.getTitle());
        gv1.initStyle(gv.getStyle());
        gv1.show();
        gv.close();
    }

    @FXML
    void resumeReleased(final MouseEvent event) throws IOException {
        ((GameView) ((Node) event.getSource()).getScene().getWindow()).disposePauseMenu();
    }

    public void setStage(final Stage stage) {
        pane.setPrefSize(stage.getWidth(), stage.getHeight());
    }
}
