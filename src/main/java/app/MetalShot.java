package app;

import java.util.ArrayList;
import java.util.List;

import controller.player.PlayerController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.map.MapModel;
import model.map.TextMap;
import view.map.MapView;
import view.player.PlayerView;

public final class MetalShot extends Application {
	
    @Override
    public void start(final Stage primaryStage) throws Exception {
    	final Scene mainScene;
    	final TextMap textMap = new TextMap(10, 5, "src\\main\\resources\\map.txt");
    	final MapModel mapModel = new MapModel(textMap);
    	final PlayerView playerView = new PlayerView(); 	
    
    	final MapView mapView = new MapView();
    	
    	final List<Node> totalList = new ArrayList<>();
    	
    	
    	totalList.addAll(mapView.drawMap(mapModel.create(), 32d));
    	final PlayerController playerController = new PlayerController(mapModel, playerView);
    	totalList.add(playerView.getPlayerImageView());
        playerController.startGame();
    	System.out.println(mapModel.getPlayerSpawn());
    	
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
