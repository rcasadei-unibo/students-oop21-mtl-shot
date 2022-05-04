package app;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import view.map.MapView;
import view.player.PlayerView;

public final class MetalShot extends Application {
    
    private PlayerView playerView;
    private final static double VIEWRESIZE = 1d;
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.playerView = new PlayerView(VIEWRESIZE);
    	final Controller controller = new Controller(this);
    	final Scene mainScene;
        final MapView mapView = new MapView(controller.getMapController() , VIEWRESIZE);
        final List<Node> totalList = new ArrayList<>();
        totalList.addAll(mapView.getNodes());
        totalList.add(playerView.getPlayerImageView());
        final Group mainGroup = new Group(totalList);
        mainScene = new Scene(mainGroup, 600, 600);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("メタルショット");
        primaryStage.show();
        controller.gameStart();
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                controller.keyPressed(event.getCode());
            } 
        });
        mainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                controller.keyReleased(event.getCode());
            } 
        });
        mainScene.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent event) {}
        });
    }

    public static void run(final String... args) {
        launch();
    }

    public PlayerView getPlayerView() {
        return this.playerView;
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
