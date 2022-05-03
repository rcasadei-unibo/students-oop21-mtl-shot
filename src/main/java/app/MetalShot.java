package app;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.map.MapView;

public final class MetalShot extends Application {
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
    	final Controller controller = new Controller(this);
    	final Scene mainScene;
        final MapView mapView = new MapView(controller.getMapController() , 32d);
        final List<Node> totalList = new ArrayList<>();
        totalList.addAll(mapView.getNodes());
        final Group mainGroup = new Group(totalList);
        mainScene = new Scene(mainGroup, 600, 600);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("メタルショット");
        primaryStage.show();
        controller.gameStart();
    }

    public static void run(final String... args) {
        launch();
    }

    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        public static void main(final String...args) {
            Application.launch(MetalShot.class, args);
        }
    }
}
