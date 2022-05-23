package controller.menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import util.UserData;
import view.GameView;

/**
 * The controller class for the sign in menu (managed by FXML sheet).
 * 
 */
public class SignInController {

    private UserData userData;
    @FXML
    public TextField name;
    @FXML
    public Button insertButton;
    @FXML
    public Button backButton;

    /**
     * Executes when the insert button is pressed.
     * @param event
     * @throws FileNotFoundException 
     */
    @FXML
    public void insertPressed(final MouseEvent event) throws FileNotFoundException {
        ((ImageView) insertButton.getGraphic()).setImage(new Image(new FileInputStream("src/main/resources/buttons/insertButtonPressed.png")));
    }

    /**
     * Executes when the insert button is released.
     * @param event
     * @throws IOException
     */
    @FXML
    public void insertReleased(final MouseEvent event) throws IOException {
        ((ImageView) insertButton.getGraphic()).setImage(new Image(new FileInputStream("src/main/resources/buttons/insertButton.png")));
        if (this.isValid(name.getText())) {
            this.userData = new UserData(name.getText());
            new GameView((Stage) ((Node) event.getSource()).getScene().getWindow());
        }
    }
    /**
     * Executes when the back button is pressed.
     * @param event
     * @throws FileNotFoundException 
     */
    @FXML
    public void backPressed(final MouseEvent event) throws FileNotFoundException {
        ((ImageView) backButton.getGraphic()).setImage(new Image(new FileInputStream("src/main/resources/buttons/backButtonPressed.png")));
    }

    /**
     * Executes when the back button is released.
     * @param event
     * @throws IOException
     */
    @FXML
    public void backReleased(final MouseEvent event) throws IOException {
        ((ImageView) backButton.getGraphic()).setImage(new Image(new FileInputStream("src/main/resources/buttons/backButton.png")));
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"))));
        stage.show();
    }

    /**
     * Returns the userData inserted.
     * @return UserData
     */
    public UserData getUserData() {
        return this.userData;
    }

    private boolean isValid(final String text) {
        return !text.isBlank();
    }
}
