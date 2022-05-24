package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.StageImpl;
import util.Direction;
import util.UserData;
import util.Vector2D;
import util.map.MapConstants;
import view.map.MapView;
import view.player.PlayerView;

/**
 * The game main view. It contains all sub-views and handles the view refresh.
 * 
 */
public class GameView extends Stage {

    private static final double VIEWRESIZE = 1d;
    private final PlayerView playerView = new PlayerView(VIEWRESIZE);
    private final BulletsView bulletsView = new BulletsView(VIEWRESIZE);
    private final MapView mapView;
    private final ImageView enemy;
    private final Group mainGroup;

    private final Controller controller = new Controller(this);
    private final UserData userData;

    /**
     * The GameView constructor.
     * @param username
     * @throws IOException 
     */
    public GameView(final String username) throws IOException {
        this.setFullScreenExitHint("");
        this.userData = new UserData(username);
        this.mapView = new MapView(controller.getMapController(), VIEWRESIZE);
        final List<Node> totalList = new ArrayList<>();
        totalList.addAll(mapView.getNodes());
        totalList.add(playerView.getPlayerImageView());
        this.enemy = new ImageView(new Image(new FileInputStream("src/main/resources/person2.png")));
        totalList.add(enemy);
        this.mainGroup = new Group(totalList);
        this.setScene(new Scene(mainGroup));
        this.setFullScreen(true);
        controller.gameStart();
        this.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                controller.keyPressed(event.getCode());
            }
        });
        this.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                try {
                    controller.keyReleased(event.getCode());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        this.getScene().setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent event) {
            }
        });
        this.show();
    }

    /**
     * TODO: Andrea Biagini.
     * @param bullets
     */
    public void displayBullets(final Map<Vector2D, Direction> bullets) {
        this.mainGroup.getChildren().removeAll(this.bulletsView.getImageViewList());
        this.bulletsView.updateBullets(bullets.keySet().stream().collect(Collectors.toList()));
        this.mainGroup.getChildren().addAll(this.bulletsView.getImageViewList());
    }

    /**
     * Gets the visible part of the player.
     * 
     * @return PlayerView
     */
    public PlayerView getPlayerView() {
        return this.playerView;
    }

    public MapView getMapView() {
        return this.mapView;
    }

    public BulletsView getBulletsView() {
        return this.bulletsView;
    }

    /**
     * TODO: Matteo Susca.
     * @param pos
     */
    public void setEnemyPos(final Vector2D pos) {
        this.enemy.setX(pos.getX() * MapConstants.getTilesize());
    }

    /**
     * Updates the current visual frame using the info of the stage.
     * @param stage
     */
    public void refresh(final StageImpl stage) {
        playerView.updateCharacter(stage.getPlayer().getPosition(), stage.getPlayer().isCrouching(),
                stage.getPlayer().getAim().getDirection());
    }

    public Controller getController() {
        return this.controller;
    }
    
    public UserData getUserData() {
        return this.userData;
    }

    public void displayPauseMenu() throws IOException {
        this.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/PauseMenu.fxml")));
        this.show();
    }

    public void disposePauseMenu() {
        this.getScene().setRoot(mainGroup);
        this.controller.gameStart();
        this.show();
    }
}
