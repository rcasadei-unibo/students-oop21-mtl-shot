package controller.menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * The controller class for the main menu (managed by FXML sheet).
 * 
 */
public class MainMenuController {
    @FXML
    public ImageView title;
    @FXML
    public Button startButton;
    @FXML
    public Button leaderboardButton;
    @FXML
    public Button optionButton;
    @FXML
    public Button closeButton;

    /**
     * Executes when the start button is pressed.
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    public void startPressed(final MouseEvent event) throws FileNotFoundException {
        ((ImageView) startButton.getGraphic()).setImage(new Image(new FileInputStream("src/main/resources/buttons/startButtonPressed.png")));
    }

    /**
     * Executes when the start button is released.
     * @param event
     * @throws IOException
     */
    @FXML
    public void startReleased(final MouseEvent event) throws IOException {
        ((ImageView) startButton.getGraphic()).setImage(new Image(new FileInputStream("src/main/resources/buttons/startButton.png")));
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/SignIn.fxml"))));
        stage.show();
    }

    /**
     * Executes when the close button is pressed.
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    public void closePressed(final MouseEvent event) throws FileNotFoundException {
        ((ImageView) closeButton.getGraphic()).setImage(new Image(new FileInputStream("src/main/resources/buttons/closeButtonPressed.png")));
    }

    /**
     * Executes when the close button is released.
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    public void closeReleased(final MouseEvent event) throws FileNotFoundException {
        ((ImageView) closeButton.getGraphic()).setImage(new Image(new FileInputStream("src/main/resources/buttons/closeButton.png")));
        Platform.exit();
    }
    /**
     * Executes when the option button is pressed.
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    public void optionPressed(final MouseEvent event) throws FileNotFoundException {
        ((ImageView) optionButton.getGraphic()).setImage(new Image(new FileInputStream("src/main/resources/buttons/optionButtonPressed.png")));
    }

    /**
     * Executes when the option button is released.
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    public void optionReleased(final MouseEvent event) throws FileNotFoundException {
        ((ImageView) optionButton.getGraphic()).setImage(new Image(new FileInputStream("src/main/resources/buttons/optionButton.png")));
    }

    /**
     * Executes when the leaderboard button is pressed.
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    public void leaderboardPressed(final MouseEvent event) throws FileNotFoundException {
        ((ImageView) leaderboardButton.getGraphic()).setImage(new Image(new FileInputStream("src/main/resources/buttons/leaderboardButtonPressed.png")));
    }

    /**
     * Executes when the leaderboard button is released.
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    public void leaderboardReleased(final MouseEvent event) throws FileNotFoundException {
        ((ImageView) leaderboardButton.getGraphic()).setImage(new Image(new FileInputStream("src/main/resources/buttons/leaderboardButton.png")));
    }
}
