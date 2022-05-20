package app;

import controller.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.StageImpl;
import model.map.Segment;
import util.Direction;
import util.Vector2D;
import view.BulletsView;
import view.map.LevelView;
import view.player.PlayerView;

/**
 * 
 *
 */
public final class MetalShot extends Application {
	
	private Controller controller;	
    private PlayerView playerView;
    private LevelView levelView;
    private static final double VIEWRESIZE = 1d;
    private Group mainGroup;
    private BulletsView bulletsView;
    private Segment lastSegment;
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {

        this.playerView = new PlayerView(VIEWRESIZE);
        this.bulletsView = new BulletsView(VIEWRESIZE);
        this.controller = new Controller(this);

        final Scene mainScene;
        this.levelView = new LevelView(controller.getStage().getLevel(), VIEWRESIZE);
        final List<Node> totalList = new ArrayList<>();

        lastSegment = this.controller.getStage().getLevel().getSegmentAtPosition(this.controller.getStage().getPlayer().getPosition());
        totalList.addAll(levelView.displaySegments(controller.getStage().getPlayer().getPosition()));
        totalList.add(playerView.getPlayerImageView());
        this.mainGroup = new Group(totalList);
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
            public void handle(final KeyEvent event) {
            }
        });
    }

    /**
     * 
     * @param bullets
     */
    public void displayBullets(final Map<Vector2D, Direction> bullets) {
        this.mainGroup.getChildren().removeAll(this.bulletsView.getImageViewList());
        this.bulletsView.updateBullets(bullets.keySet().stream().collect(Collectors.toList()));
        this.mainGroup.getChildren().addAll(this.bulletsView.getImageViewList());
    }

    public static void run(final String... args) {
        launch();
    }

    /**
     * Gets the visible part of the player.
     * 
     * @return PlayerView
     */
    public PlayerView getPlayerView() {
        return this.playerView;
    }
    
    public void refresh(final StageImpl stage) {
    	if(lastSegment != stage.getLevel().getSegmentAtPosition(stage.getPlayer().getPosition())) {
    		this.mainGroup.getChildren().clear();
    		this.mainGroup.getChildren().addAll(levelView.displaySegments(controller.getStage().getPlayer().getPosition()));
    		this.mainGroup.getChildren().add(playerView.getPlayerImageView());
    		System.out.println("Changed!");
    	}
    	lastSegment = stage.getLevel().getSegmentAtPosition(stage.getPlayer().getPosition());
    }

    /**
     * 
     *
     */
    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        public static void main(final String... args) {
            Application.launch(MetalShot.class, args);
        }
    }
}
