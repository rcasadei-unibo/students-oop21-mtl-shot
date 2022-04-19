package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public final class MetalShot extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {

    
        primaryStage.setTitle("メタルショット");
        primaryStage.show();
    }

    public static void run(final String... args) {
        launch();
    }

    // Defining the main methods directly within JavaFXApp may be problematic:
    // public static void main(final String[] args) {
    //        run();
    // }

    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        public static void main(final String...args) {
            Application.launch(MetalShot.class, args);
        }
    }
}
