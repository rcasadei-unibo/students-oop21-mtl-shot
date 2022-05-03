package app;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.map.MapController;
import util.map.TextMap;
import view.map.MapView;

public final class MetalShot extends Application {
	
    @Override
    public void start(final Stage primaryStage) throws Exception {
    	final Scene mainScene;
    	final TextMap textMap = new TextMap("src\\main\\resources\\map.txt");
    	final MapController mapController = new MapController(textMap);	
    
    	final MapView mapView = new MapView(mapController , 32d);
    	
    	final List<Node> totalList = new ArrayList<>();
    	
    	
    	totalList.addAll(mapView.getNodes());
    	
    	Group mainGroup = new Group(totalList);
    	
    	mainScene = new Scene(mainGroup, 600, 600);
    	
    	primaryStage.setScene(mainScene);
        primaryStage.setTitle("メタルショット");
        primaryStage.show();
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
