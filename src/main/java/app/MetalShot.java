package app;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * TODO
 * @author sysosus
 *
 */
/*public final class MetalShot extends Application {
	private Controller controller;
	private ImageView player;
    private ImageView enemy;
    private Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
	
    @Override
    public void start(final Stage primaryStage) throws Exception {
    	this.controller = new Controller(this);

    	FileInputStream person = new FileInputStream("src/main/resources/person.png");
    	FileInputStream person2 = new FileInputStream("src/main/resources/person2.png");

    	this.player = new ImageView(new Image(person));
    	this.enemy = new ImageView(new Image(person2));

    	final Group mainGroup = new Group(enemy,player);

    	System.out.println(enemy.getX());

        primaryStage.setScene(new Scene(mainGroup,1000,500));
        primaryStage.setTitle("Hello");
        primaryStage.show();
        controller.gameStart();
    }

    public static void run(final String... args) {
        launch();
    }
/**
 * TODO
 * @author sysosus
 *
 */


import model.StageImpl;
import util.Direction;
import util.Vector2D;
import util.map.MapConstants;
import view.BulletsView;
import view.map.MapView;
import view.player.PlayerView;

/**
 * 
 *
 */
public final class MetalShot extends Application {
	
	private Controller controller;	
    private PlayerView playerView;
    private static final double VIEWRESIZE = 1d;
    private Group mainGroup;
    private BulletsView bulletsView;
    private ImageView enemy;
    private Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {

        this.playerView = new PlayerView(VIEWRESIZE);
        this.bulletsView = new BulletsView(VIEWRESIZE);
        this.controller = new Controller(this);
        final Scene mainScene;
        final MapView mapView = new MapView(controller.getMapController(), VIEWRESIZE);
        final List<Node> totalList = new ArrayList<>();

        totalList.addAll(mapView.getNodes());
        totalList.add(playerView.getPlayerImageView());

        FileInputStream person = new FileInputStream("src/main/resources/person.png");
    	FileInputStream person2 = new FileInputStream("src/main/resources/person2.png");

    	this.enemy = new ImageView(new Image(person2));

    	totalList.add(enemy);


        this.mainGroup = new Group(totalList);

        mainScene = new Scene(mainGroup);
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
    public void refresh(StageImpl stage) {
        playerView.updateCharacter(stage.getPlayer().getPosition(), stage.getPlayer().isCrouching(), stage.getPlayer().getAim().getDirection());
    }

    /**
     * 
     *
     */

    public void setEnemyPos(Vector2D pos) {
    	this.enemy.setX(pos.getX()*MapConstants.getTilesize());
    }

//    public void setPlayerPos(Vector2D pos) {
//    	this.player.setX(pos.getX());
//    }

    public double getX() {
    	return this.enemy.getX();
    }

    public Dimension getDim() {
    	return new Dimension(this.res);
    }
}
