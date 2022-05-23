package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * The javafx application.
 *
 */
public final class MetalShot extends Application {
    /**
     * {@inheritDoc}
     * 
     * @throws IOException
     */
    @Override
    public void start(final Stage primaryStage) throws IOException {
        final Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
        final Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}

